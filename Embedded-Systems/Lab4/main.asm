;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Lab4.asm
;
; Author : Ian Kuk, Brandon Cano
;
; Notes: I/0 pins and registers
;	PD2   -> Push Button
;	PD3   -> Yellow Fan Wire
;	PD6   -> RPG Right
;	PD7   -> RPG Left
;	PB3   -> LCD Enable
;	PB5   -> LCD RS
;	PC0-3 -> LCD D3-7
;	...
;   VIN -> Fan Power
;   A0  -> LCD
;	A1  -> LCD
;	A2  -> LCD
;	A3  -> LCD
;
;   R0
;   R16 -> Temp value holder
;   R17 -> Used in divide subroutine
;   R18 -> Hold duty cycle percentage for LCD display
;   R19 -> Divisor in division
;   R20 -> Flag for RPG direction - 0x00 -> no turn, 0x01 -> CW, 0x02 -> CCW
;   R21 -> Flag to see if display needs to be updated - 0x00 -> no, 0x01 -> yes
;   R22 -> Flag to see if the fan is on or off - 0x00 -> OFF, 0x01 -> ON
;   R23 -> Register that is used to copy and push to stack and temporarily hold values
;   R24 -> Used as the character counter for displaying strings to the LCD
;   R25 -> Used for timer
;   R26 -> Used for timer
;   R27 -> Holds values read in from PIND to see whats on or off
;   R28 -> UNUSED
;   R29 -> LCD Display commands and division use
;   R30 -> Message Use
;   R31 -> Message Use
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

.cseg 
.org 0x0000
rjmp main				; skip over addresses for interrupts

.org 0x0002				; address for INT0 - for button detection
rjmp toggle_fan

.org 0x000A				; address for PCINT2 - for RPG detection
rjmp check_rpg

; strings
msg:    .db "DC= "
msg100: .db "DC=100"
msg2:   .db "%                                 FAN:  "
off:    .db "OFF       "
on:     .db "ON        "
        .dw 0

; start of main program
.org 0x0034
main:
	cli					; clear the global interrupt

	ldi R16, 0			; set timer counter to 0
	sts TCNT2, R16

	sbi DDRD, 3			; set the port that has the fan wire as an output
	ldi R16, 0x0F		; set pins 0-3 on PORTC as outputs
	out DDRC, R16
	ldi R16, 0x38		; set pins 11-13 on PORTB as outputs
	out DDRB, R16

	ldi R16, 200		; set top value 200, with prescaler 1 it will make a 80kHz signal
	sts OCR2A, R16
	ldi R16, 100		; half the value will set the duty cycle to 50%
	sts OCR2B, R16

	ldi R16, 0x23		; enables fast PMW and non-inverting output
	sts TCCR2A, R16
	ldi R16, 0x01		; set prescaler 1
	sts TCCR2B, R16

	ldi R16, 0x03		; rising edge will generate a request to the interrupt
	sts EICRA, R16
	ldi R16, 0x01		; enable INT0
	out EIMSK, R16
	ldi R16, 0b01000000 ; RPG - PCINT22 interrupt
	sts PCMSK1, R16
	ldi R16, 0b10000000 ; RPG - PCINT23 interrupt
	sts PCMSK2, R16
	ldi R16, 0x06		; enable pin change interrupt for the above PCINTs
	sts PCICR, R16

	ldi R16, 0x00		; initialize registers with default values
	ldi R17, 0x00
	ldi R18, 50
	ldi R20, 0x00
	ldi R21, 0x00
	ldi R22, 0x00

	; initialize LCD
	cbi PORTB, 3
	cbi PORTB, 5

	ldi R29, 0x03
	out PORTC, R29
	rcall pulse_lcd

	ldi R29, 0x03         
	out PORTC, R29
	rcall pulse_lcd

	ldi R29, 0x03
	out PORTC, R29
	rcall pulse_lcd

	ldi R29, 0x02
	out PORTC, R29
	rcall pulse_lcd

	; writes all the commands to the LCD
	ldi R29, 0x01	      ; clear and home
	rcall write_command
	ldi R29, 0x06	      ; set cursor move direction
	rcall write_command
	ldi R29, 0x08	      ; enable display/cursor
	rcall write_command
	ldi R29, 0x0C	      ; turn on display
	rcall write_command
	ldi R29, 0x28	      ; set interface
	rcall write_command
	sei					  ; enable global interrupt flag

/*
 * this will load the Z register with the message to be displayed to the LCD
 */
display_lcd:
	cli
	ldi R29, 0x01	      ; clear the LCD
	rcall write_command
	sbi PORTB, 5          ; set R/S to write characters

	ldi R24, 4			  ; set number of characters for the base percentage message
	cpi R18, 100		  ; if R18 is 100 then we want to branch to show "DC=100%"
	brge set5			
	rjmp line_one		  ; otherwise, we jump down to the regular 2 digit percentage
	set5:
		ldi R24, 6				 ; set the number of characters needed to display 100%
		ldi R30, LOW(2*msg100)   ; load lower byte into the low Z register
		ldi R31, HIGH(2*msg100)  ; load higher byte into the high Z register

		rcall display_message    ; send data to LCD
		rjmp line_two			 ; jump to the end to display line 2 
	line_one:
		ldi R30, LOW(2*msg)      ; load lower byte into the low Z register
		ldi R31, HIGH(2*msg)     ; load higher byte into the high Z register

		rcall display_message    ; send data to LCD
	
		ldi R16, 0x00            ; resets remainder to 0
		ldi R17, 0x00            ; resets division result to 0
		rcall divide             ; calls the divide subroutine to seperate the two characters of the PWM counter

		mov R31, R17             ; moves tens place into R31 to be displayed
		rcall display_data       ; displays the character in R31 to the LCD
		mov R31, R16             ; moves ones place into R31 to be displayed
		rcall display_data       ; displays the character in R31 to the LCD
	line_two:
		ldi R24, 40              ; sets the loop counter to loop over every character in the string
		ldi R30, LOW(2*msg2)     ; splits the message into 8 bit parts
		ldi R31, HIGH(2*msg2)
		rcall display_message    ; loops over all the characters to display them to the LCD

	ldi R20, 0x00
	cpi R22, 0x01		      ; if R22 is 0x01, we want to show the fan is on, otherwise off
	breq display_on
	display_off:
		ldi R30, LOW(2*off)	  ; display -> "Fan: OFF"
		ldi R31, HIGH(2*off)	
		ldi R16, 0x00
		sts TCCR2A, R16
		rjmp display_end
	display_on:
		ldi R30, LOW(2*on)	  ; display -> "Fan: ON"
		ldi R31, HIGH(2*on)
		ldi R16, 0x23		  ; enable fast PWM and non-inverting
		sts TCCR2A, R16
	display_end:
		ldi R24, 10			  ; load R24 with the number of characters in the 'on' or 'off' strings
		rcall display_message
	sei


/*
 * the main_loop will be what checks flags, while being able to have interrupts occur
 */
main_loop:
	cpi R21, 0x01			; R21 determines if the display needs to be updated
	breq display_lcd

	cpi R20, 0x01			; if R20 is 1, then we want the clockwise motion
	breq clockwise_move

	cpi R20, 0x02			; if R20 is 2, then we want the counter-clockwise motion
	breq counter_clockwise_move

	rjmp main_loop			; continue looping
clockwise_move:
	cli
	push R23

	cpi R18, 0x04			; if R18 is less than 4, we need to prevent the program from breaking
	brlt increment_fix

	inc R18                 ; 1% duty cycle increase
	cpi R18, 0x64
	brge stop_incrementing  ; if at 100, we want to stop increasing the duty cycle

	lds R16, OCR2B          ; load value of OCR2B into R16
	inc R16					; increase by 2
	inc R16
	sts OCR2B, R16		    ; set new value in R16 back out to OCR2B, this increase the duty cycle of the PWM

	rcall delay_100ms
	ldi R20, 0x00           ; resets the RPG rotation indicator flag
	pop R23					; restore R23
	rjmp display_lcd		; jumps to display_lcd to update the LCD with the new numbers
	
	sei
	ret
counter_clockwise_move:
	cli
	push R23				; save the value of R23 on the stack

	cpi R18, 0x01			; if R18 hits one, we need to stop decreasing on the display
	breq decrement_fix

	dec R18					; 1% duty cycle decrease
	cpi R18, 0x05			; once we get below 5, we need to stop decreasing the PWM, but keep decreasing the display
	brlt stop_decrementing
	             
	lds R16, OCR2B			; load value of OCR2B into R16
	dec R16
	dec R16
	sts OCR2B, R16			; set new value in R16 back out to OCR2B, this decreases the duty cycle of the PWM

	rcall delay_100ms
	ldi R20, 0x00			; resets the RPG rotation indicator flag
	pop R23					; restore R23
	rjmp display_lcd		; jumps to display_lcd to update the LCD with the new numbers
	
	sei
	ret
stop_incrementing:
	ldi R20, 0x00			; reset RPG indicator
	ldi R16, 200			; max the PWM for the fan
	ldi R18, 100			; max the display value for LCD
	sts OCR2B, R16			; load value back into OCR2B
	pop R23					; remove from stack
	rjmp display_lcd		; update display
increment_fix:
	inc R18					; increases only the display value
	pop R23
	rjmp display_lcd		; update display
stop_decrementing:
	ldi R20, 0x00			; reset RPG indicator
	ldi R16, 10				; min value we can set for the PWM
	sts OCR2B, R16
	pop R23				
	rjmp display_lcd		; update display
decrement_fix:
	ldi R18, 0x01			; stop counter at 1
	pop R23
	rjmp display_lcd		; update display

/*
 * this subroutine is responsible for sending the contents of the Z register to the LCD
 */
display_message:
	cli
	dm_inner_loop:
		lpm					; loads one byte from the Z register into a destination register   
		swap R0				; swap the nibbles in R0 and outputs them
		out PORTC, R0
		rcall pulse_lcd

		rcall delay_1ms		; delay

		swap R0				; swap the nibbles in r0 and outputs them
		out PORTC, R0
		rcall pulse_lcd

		rcall delay_1ms		; delay

		adiw zh:zl, 1		; adds 1 to the Z-pointer register pair
		dec R24
		brne dm_inner_loop  ; branches back to the start if R24 is 0

	ldi R21, 0x00			; set update display flag to 0
	sei
	ret

display_data:
	cli

	swap R31				; swaps the nibbles of R31
	out PORTC, R31			; send upper nibble
	rcall pulse_lcd
	rcall delay_1ms			; delay

	swap R31				; swaps the nibbles of R31
	out PORTC, R31			; send lower nibble
	rcall pulse_lcd
	rcall delay_1ms			; delay

	sei
	ret

pulse_lcd:
	sbi PORTB, 3			; enable high
	rcall delay_200us		; delay for a small amount
	cbi PORTB, 3			; enable low
	; we need to delay ~2ms in order for the LCD to properly send and display data correctly
	rcall delay_1ms
	rcall delay_1ms
	ret

write_command:
	cli
	cbi PORTB, 5          ; sets register select low

	swap R29	          ; upper nibble -> LCD
	out PORTC, R29
	rcall pulse_lcd

	swap R29	          ; lower nibble -> LCD
	out PORTC, R29
	rcall pulse_lcd

	sei
	ret
/*
 * the divide subroutine uses the restoring divison algorithm in order to compute
 * the result and remainder values separately.
 */
divide:
	cli
	ldi R19, 10           ; 10 is the divisor
	mov R23, R18          ; copy to R23 to be pushed on the stack
	push R23
	sub	R16, R16		  ; clear remainder and carry
	ldi	R29, 9			  ; loop counter

	divide_one:	
		rol R17
		rol	R18
		dec	R29				; decrement counter
		brne divide_two

		ldi R23, 0x30		; will be added to the result and remainder to convert to ASCII value
		add R16, R23
		add R17, R23
		pop R23				; take from stack and move new value into R18
		mov R18, R23
		ret
	divide_two:	
		rol	R16				; put dividend into remainder
		sub	R16, R19		; remainder = remainder - divisor
		brcc divide_three	; if negative
		add	R16, R19		; restore remainder
		clc					; clear carry to be shifted into result
		rjmp divide_one
	divide_three:	
		sec					; set carry
		rjmp divide_one
	sei
	ret

/*
 * this subroutine is called from the button interrupt, will turn fan either on or off
 */
toggle_fan:
	cli					  ; clear interrupt enable flag
	
	sbis PIND, 3		  ; check if the fan is on
	rjmp fan_on			  ; if off, turn it on
	rjmp fan_off		  ; if on, turn it off

	fan_on:
		sbi PORTD, 3
		ldi R22, 0x01	  ; set display flag so that the LCD will update in main_loop
		rjmp toggle_end   ; jump to end
	fan_off:              ; set R22 indicating the fan needs to be turned off in the main_loop
		cbi PORTD, 3
		ldi R22, 0x00     ; set display flag so that the LCD will update in main_loop
	toggle_end:	
		ldi R21, 0x01
	reti

/*
 * The following subroutines determine which actions to take depending on
 *  the interrupt call for the RPG and checks the direction
 */
check_rpg:
	cli
	in R27, PIND			; prevents the RPG from changing the PWM if the fan is off
	sbis PIND, 3
	rjmp end_rpg

	sbis PIND, 7			; determines if the direction is CW
	rcall clockwise

	sbis PIND, 6			; determines if the direction is CCW
	rcall counterclockwise

	end_rpg: reti
clockwise:
	ldi R20, 0x00			; resets direction flag
	cpi R18, 0x64			; if R18 is 100 go to the end since it's at the upper bound
	breq end_cw

	ldi R20, 0x01			; set direction flag for RPG (CW)
	end_cw: reti

counterclockwise:
	ldi R20, 0x00			; resets direction flag
	cpi R18, 0x01			; if R18 is 1 go to the end since it's at the lower bound
	breq end_ccw

	ldi R20, 0x02			; set direction flag for RPG (CCW)
	end_ccw: reti

/*
 * delay subroutines
 *  - the 100ms and 5ms loops are made from the 1ms timer
 *  - the 200us is made fromt he 50us timer
 */
delay_1ms:
	ldi R25, 0x03
	out TCCR0B, R25
	ldi R25, 0x06
	out TCNT0, R25
loop_1ms:
	in R25, TCNT0
	cpi R25, 0x00
	brne loop_1ms
	ret

delay_5ms:
	ldi R26, 0x05
loop_5ms:
	rcall delay_1ms
	dec R26
	cpi R26, 0x00
	brne loop_5ms
	ret

delay_100ms:
	ldi R26, 0x64
loop_100ms:
	rcall delay_1ms
	dec R26
	cpi R26, 0x00
	brne loop_100ms
	ret

delay_50us:
	ldi R25, 0x02
	out TCCR0B, R25
	ldi R25, 0x9C
	out TCNT0, R25
loop_50us:
	in R25, TCNT0
	cpi R25, 0x00
	brne loop_50us
	ret

delay_200us:
	ldi R26, 0x04
loop_200us:
	rcall delay_50us
	dec R26
	cpi R26, 0x00
	brne loop_200us
	ret
.exit