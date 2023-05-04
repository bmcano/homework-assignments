/*
 * main.c
 *
 * Created: 4/10/2023 11:48:56 AM
 *  Author: Brandon Cano, Ian Kuk
 */ 
#define F_CPU 16000000L
#define UART_BAUD 9600
#define DAC_ADDRESS	0b01011000
#define slave 0101100
#define __DELAY_BACKWARD_COMPATIBLE__ 

#include <ctype.h>
#include <stdint.h>
#include <stdio.h>

#include <avr/io.h>
#include <util/delay.h>
#include <util/twi.h>

// files 'twimaster.c' and 'i2cmaster.h' came from: https://github.com/alx741/avr_i2c
#include "twimaster.c"

int uart_putchar(char, FILE*);
int uart_getchar(FILE*);

// setup file stream to use with printf/scanf
static FILE uart_io = FDEV_SETUP_STREAM(uart_putchar, uart_getchar, _FDEV_SETUP_RW);

static float read_adc(uint8_t pin) {
	ADMUX = (ADMUX & 0xf0) | pin;
	ADCSRA |= 1 << ADSC;             // conversion to 8 bit 
	
	while (!(ADCSRA & (1 << ADIF))); // wait for conversion to complete
	
	ADCSRA |= 1 << ADIF;
	
	return 5.0f * (ADCL + (ADCH << 8)) / 1023.0f;
}

void set_dac_voltage(uint8_t channel, float voltage) {
	i2c_start(DAC_ADDRESS | I2C_WRITE);     // set device address and write mode
	i2c_write(channel);						// write to DAC channel, either 0 or 1
	uint8_t byte_val = 51*voltage;			// convert from float to 8bit
	i2c_write(byte_val);					// write DAC voltage
	i2c_stop();
}

void create_sine_wave(uint8_t channel, int frequency, int cycles) {
	double period = 1.0f / frequency;   // gets the period of the wave
	
	double t;                           // will give the proper delay for a displayable frequency
	if (frequency == 10) {				// determine which frequency to write
		t = ((period*800.0f) / 64.0f);  // f=10
	} else {
		t = ((period*600.0f) / 64.0f);  // f=20
	}

	// sine wave values
	uint8_t sine_values[64] = {
        128, 141, 153, 165, 177, 188, 199, 209, 219, 227, 234, 241, 246, 250, 254, 255,
        255, 255, 254, 250, 246, 241, 234, 227, 219, 209, 199, 188, 177, 165, 153, 141, 
        128, 115, 103,  91,  79,  68,  57,  47,  37,  29,  22,  15,  10,   6,   2,   1, 
          0,   1,   2,   6,  10,  15,  22,  29,  37,  47,  57,  68,  79,  91, 103, 115
    };

	for (int i=0; i<cycles; i++) {     // number of cycles
		for (int j=0; j<64; j++) {     // amount of values for sine wave
			i2c_start(DAC_ADDRESS | I2C_WRITE);
			i2c_write(channel);		   // write to channel
			i2c_write(sine_values[j]); // write voltage value in wave
			i2c_stop();
			_delay_ms(t);			   // add delay
		}
	}
}


int main() {
	// enables printf and scanf
	stdout = stdin = &uart_io;
	
	i2c_init();
	
	// ADC setup
	ADMUX |= 0b01 << REFS0;
	ADCSRA = 1 << ADEN | 0b110 << ADPS0;
	
	UCSR0A = 1 << U2X0;
	UBRR0L = (F_CPU / (8UL * UART_BAUD)) - 1;
	UCSR0B = 1 << TXEN0 | 1 << RXEN0;

	// initialize variables for inputs
    float voltage;
	char buf[32];
    
	uint8_t channelInput[1];
	uint8_t sineWaveInputs[2];
	
	for (;;) {
		printf("> ");
		if (fgets(buf, sizeof buf - 1, stdin) == NULL)   // grab user input
			return;

		switch (buf[0]) {                                // check for command (G,S,W) 
			case 'G':                                    // reads the ADC voltage
				printf("ADC Voltage = %0.3f V\n", read_adc(0));
				break;
			case 'S':
				if (sscanf(buf, "%*c,%hhd,%f", &channelInput[0], &voltage) > 0) {
					if(channelInput[0] != 0 && channelInput[0] != 1) {
						printf("Invalid DAC channel %d", channelInput[0]);
					} 
					else if (voltage > 5 || voltage < 0) {
						printf("Voltage out of range %0.2f", voltage);
					} 
					else {
						printf("DAC Channel %hhd set to %0.2f V (177d)\n", channelInput[0], voltage);
						set_dac_voltage(channelInput[0], voltage);
					}
				}
				break;
			case 'W':
				if (sscanf(buf, "%*c,%hhd,%hhd,%hhd", &channelInput[0], &sineWaveInputs[0], &sineWaveInputs[1]) > 0) {
					if (channelInput[0] != 0 && channelInput[0] != 1) {
						printf("Invalid DAC channel %d", channelInput[0]);
					} 
					else if (sineWaveInputs[0] != 10 && sineWaveInputs[0] != 20) {
						printf("Invalid sine wave frequency %d", sineWaveInputs[0]);
					} 
					else if (sineWaveInputs[1] < 1 || sineWaveInputs[1] > 100) {
						printf("Invalid number of waveform cycles %d", sineWaveInputs[1]);
					} 
					else {
						printf("Generating %hhd sine wave cycles with f=%hhd on DAC channel %hhd\n", sineWaveInputs[1], sineWaveInputs[0], channelInput[0]);
						create_sine_wave(channelInput[0], sineWaveInputs[0], sineWaveInputs[1]);
					}
				}
				break;
			default:
				printf("Invalid Command.");
				break;
		}
		
		putchar('\n');
	}
}

int uart_putchar(char c, FILE *s) {
	// CRLF insertion
	if (c == '\n') {
		uart_putchar('\r', s);
	}
	
	while (!(UCSR0A & (1 << UDRE0)));
	UDR0 = c;
	
	return 0;
}

int uart_getchar(FILE *s) {
	// wait for data to be received
	while (!(UCSR0A & (1 << RXC0)));
	uint8_t c = UDR0;
	
	return c;
}
