/*
 * Project.c
 *
 * Created: 4/23/2023 2:17:23 PM
 * Author : Brandon Cano, Ian Kuk
 */ 

#define F_CPU 16000000L
#define UART_BAUD 9600
#define DAC_ADDRESS	0b01011000
#define slave 0101100

#include <ctype.h>
#include <stdio.h>
#include <stdint.h>

#include <avr/eeprom.h>
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>
#include <util/twi.h>

#include "lcd.h"
#include "serial/twimaster.c"

int uart_putchar(char, FILE*);
int uart_getchar(FILE*);

static FILE uart_io = FDEV_SETUP_STREAM(uart_putchar, uart_getchar, _FDEV_SETUP_RW);
static FILE lcd_str = FDEV_SETUP_STREAM(lcd_putchar, NULL, _FDEV_SETUP_WRITE);

static const char line_break[] = "                        ";

static const int x_max = 15; // 0 - 15

// global variables
int x = 0;
int y = 0;
int moved_right = -1;
int moved_down = -1;
int moved_up = -1;
char block = 0x00;
// 0x00 top half
// 0x01 bottom half
// 0xFF full block
char board_1[] = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
char board_2[] = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };

// custom characters for LCD
unsigned char custom_char_1[8] = { 0b11111, 0b11111, 0b11111, 0b11111, 0b00000, 0b00000, 0b00000, 0b00000 };
unsigned char custom_char_2[8] = { 0b00000, 0b00000, 0b00000, 0b00000, 0b11111, 0b11111, 0b11111, 0b11111 };

// setup IO and 
void main_init(void) 
{
	stdout = stdin = &uart_io;
	stderr = &lcd_str;
	
	ADMUX |= 0b01 << REFS0;
	ADCSRA = 1 << ADEN | 0b110 << ADPS0;
	
	UCSR0A = 1 << U2X0;
	UBRR0L = (F_CPU / (8UL * UART_BAUD)) - 1;
	UCSR0B = 1 << TXEN0 | 1 << RXEN0;
}

// add custom characters to the LCD
void lcd_char_init(void) 
{	// this function was created by us and added to the lcd.c file
	lcd_build_char(0, custom_char_1); // 0x00
	lcd_build_char(1, custom_char_2); // 0x01
	/*
	lcd_putchar(0x00, &lcd_str);
	lcd_putchar(0x01, &lcd_str);
	*/
}

// load the last saved board from EEPROM to be displayed
void load_board(void) 
{
	uint8_t board_from_eeprom[32];
	eeprom_read_block((void*)&board_from_eeprom, (const void*)12, 32);
	for (int i=0; i<16; i++) 
	{
		board_1[i] = board_from_eeprom[i];
		board_2[i] = board_from_eeprom[i+16];
	}
}

// reads in the analog input from the accelerometer and converts to 8-bit
static float read_accel(uint8_t pin) 
{
	ADMUX = (ADMUX & 0xf0) | pin;
	ADCSRA |= 1 << ADSC;             // conversion to 8 bit
	while (!(ADCSRA & (1 << ADIF))); // wait for conversion to complete
	ADCSRA |= 1 << ADIF;
	return 5.0f * (ADCL + (ADCH << 8)) / 1023.0f;
}

// main loop
int main(void)
{
	// initialize everything
	main_init();
	lcd_init();
	lcd_char_init();
	load_board();
	
	// starting screen
	fprintf(&lcd_str, " Etch a Sketch\n");
	_delay_ms(2000);

	// write board loaded from EEPROM
	write_board();
	
	// variables for input components
	uint8_t LR1;
	uint8_t LR2 = 0b00000011;
	uint8_t LR3 = 0b00000011;
	
	uint8_t UD1;
	uint8_t UD2 = 0b00000011;
	uint8_t UD3 = 0b00000011;

	uint8_t PB;
	
	// main loop
    while (1) 
    {
		// check accelerometer
		check_accel();
		
		// left/right RPG
		LR1 = PIND;
		LR1 &= 0b11000000;
		LR1 >>= 6;
		if (LR1 != LR2) 
		{
			LR2 = LR1;
			LR3 <<= 1;
			LR3 <<= 1;
			LR3 |= LR1;
			if (LR3 == 0b01001011) 
			{   // clockwise pattern
				move_left();
			} 
			else if (LR3 == 0b10000111) 
			{   // counterclockwise pattern
				move_right();
			}
		}
		
		// up/down RPG
		UD1 = PIND;
		UD1 &= 0b00110000;
		UD1 >>= 4;
		if (UD1 != UD2) 
		{
			UD2 = UD1;
			UD3 <<= 1;
			UD3 <<= 1;
			UD3 |= UD1;
			if (UD3 == 0b01001011) 
			{   // clockwise pattern
				move_up();
			} 
			else if (UD3 == 0b10000111) 
			{   // counterclockwise pattern
				move_down();
			}
		}
		
		// push button
		PB = PIND;
		PB &= 0b00000100;
		// check button press
		if (PB == 0b00000000) 
		{	// wait for release
			while (PB != 0b00000100) 
			{
				PB = PIND;
				PB &= 0b00000100;
			}
			// upon release we save the board
			save_board(0);
		}
    }
}

// accelerometer clears the LCD screen
int check_accel(void) 
{
	float z = read_accel(0);
	if (z > 2.000) 
	{	// write clear screen flag
		lcd_putchar('\n', &lcd_str);
		// clear board arrays
		for (int i=0; i<16; i++) 
		{
			board_1[i] = ' ';
			board_2[i] = ' ';
		}
		save_board(1);
		write_board();
	}
	return 0;
}

// same logic for both left and right motions to determine what to draw on the board
void write_left_right(void) 
{
	if (y == 0) 
	{
		if ((board_1[x] == 0x00 && block == 0x01) || (board_1[x] == 0x01 && block == 0x00) || (board_1[x] == 0xFF)) 
		{
			board_1[x] = 0xFF;
		}
		else 
		{
			board_1[x] = block;
		}
	}
	if (y == 1) 
	{
		if ((board_2[x] == 0x00 && block == 0x01) || (board_2[x] == 0x01 && block == 0x00) || (board_2[x] == 0xFF)) 
		{
			board_2[x] = 0xFF;
		}
		else 
		{
			board_2[x] = block;
		}
	}
}

void move_left(void) 
{	// fix x-cord
	if (moved_down == 1 || moved_up == 1) 
	{
		x--;
	}
	// don't do anything when at lower bound
	if (x < 0) 
	{
		x = 0;
		return;
	}
	// determine how to write to the board
	write_left_right();
	write_board();
	x--;
	// update flags
	moved_right = 0;
	moved_down = -1;
	moved_up = -1;
}

void move_right(void) 
{	// fix x-cord
	if (moved_down == 1 || moved_up == 1) 
	{
		x++;
	}
	// don't do anything when at upper bound
	if (x > x_max) 
	{
		x = x_max;
		return;
	}
	// determine how to draw to board
	write_left_right();
	write_board();
	x++;
	// update flags
	moved_right = 1;
	moved_down = -1;
	moved_up = -1;
}

void move_up(void) 
{	// fix x-cord
	if (moved_right == 1 && x != x_max) 
	{
		x--;
	}
	else if (moved_right == 0 && x != 0) 
	{
		x++;
	}
	// determine how to write to the board
	if (y == 1) {
		if (board_2[x] == ' ') {
			board_2[x] = 0x01;
			block = 0x01;
			y = 1;
		}
		else if (board_2[x] == 0x00 && (board_1[x] == ' ' || board_1[x] == 0x01)) {
			board_1[x] = 0x01;
			block = 0x01;
			y = 0;
		}
		else if (board_2[x] == 0x01) {
			board_2[x] = 0xFF;
			block = 0x00;
			y = 1;
		}
		else if (board_2[x] == 0xFF && (board_1[x] == ' ' || board_1[x] == 0x01)) {
			board_1[x] = 0x01;
			block = 0x01;
			y = 0;
		}
		else if (board_1[x] == 0x00) {
			board_1[x] = 0xFF;
			block = 0x01;
			y = 0;
		}
	}
	else if (y == 0) {
		if (board_1[x] == ' ') {
			board_1[x] = 0x01;
			block = 0x01;
			y = 0;
		}
		else if (board_1[x] == 0x01) {
			board_1[x] = 0xFF;
			block = 0x00;
			y = 0;
		}
		else if (board_1[x] == 0xFF) {
			block = 0x00;
			y = 0;
		}
	}
	
	write_board();
	moved_right = -1;
	moved_down = 0;
	moved_up = 1;
}

void move_down(void) 
{	// fix x-cord
	if (moved_right == 1 && x != x_max) 
	{
		x--;
	}
	else if (moved_right == 0 && x != 0) 
	{
		x++;
	}
	// determine how to write to the board
	if (y == 0) {
		if (board_1[x] == ' ') {
			board_1[x] = 0x00;
			block = 0x00;
			y = 0;
		}
		else if (board_1[x] == 0x01 && (board_2[x] == ' ' || board_2[x] == 0x00)) {
			board_2[x] = 0x00;
			block = 0x00;
			y = 1;
		}
		else if (board_1[x] == 0x00) {
			board_1[x] = 0xFF;
			block = 0x01;
			y = 0;
		}
		else if (board_1[x] == 0xFF && (board_2[x] == ' ' || board_2[x] == 0x00)) {
			board_2[x] = 0x00;
			block = 0x00;
			y = 1;
		}
		else if (board_2[x] == 0x01) {
			board_2[x] = 0xFF;
			block = 0x00;
			y = 1;
		}
	}
	else if (y == 1) {
		if (board_2[x] == ' ') {
			board_2[x] = 0x00;
			block = 0x00;
			y = 1;
		}
		else if (board_2[x] == 0x00) {
			board_2[x] = 0xFF;
			block = 0x01;
			y = 1;
		}
		else if (board_2[x] == 0xFF) {
			block = 0x01;
			y = 1;
		}
	}
		
	write_board();
	moved_right = -1;
	moved_down = 1;
	moved_up = 0;
}

void write_board(void) 
{
	for (int i=0; i<16; i++)
	{
		lcd_putchar(board_1[i], &lcd_str);
	}
	fprintf(&lcd_str, line_break);
	for (int i=0; i<16; i++) 
	{
		lcd_putchar(board_2[i], &lcd_str);
	}
	lcd_putchar('\n', &lcd_str);
}

void save_board(int is_cleared) {
	uint8_t eeprom_saved_board[32];
	for (int i=0; i<16; i++) 
	{
		eeprom_saved_board[i] = board_1[i];
		eeprom_saved_board[i+16] = board_2[i];
	}
	eeprom_update_block((const void*)&eeprom_saved_board, (void*)12, sizeof(eeprom_saved_board));
	if (!is_cleared) {
		fprintf(&lcd_str, "     Saved!     \n");
		_delay_ms(2000);
	}
	write_board();
}

/** TEMPORARY FOR TESTING PURPOSES **/
int uart_putchar(char c, FILE *s) 
{
	// CRLF insertion
	if (c == '\n') 
	{
		uart_putchar('\r', s);
	}
	while (!(UCSR0A & (1 << UDRE0)));
	UDR0 = c;
	return 0;
}

int uart_getchar(FILE *s) 
{
	// wait for data to be received
	while (!(UCSR0A & (1 << RXC0)));
	uint8_t c = UDR0;
	return c;
}
