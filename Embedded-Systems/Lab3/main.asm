;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Lab3.asm
;
; Created: 2/25/2023 3:11:30 PM
; Author : Brandon Cano, Ian Kuk
;
; Notes - I/O pins and registers:
;	6   -> PD6 - signal b
;	7   -> PD7 - signal a
;	8	-> PB0 (SRCLK)
;	9	-> PB1 (RCLK)
;	10	-> PB2 (SER)
;	11  -> PB3 (Button) 
;
;	R16 -> holds the value for the 7 segment display
;	R17 -> SREG
;	R18
;	R19 -> button press
;	R20 -> timer
;	R21 -> delay counters
;	R22 -> store current RPG value
;	R23	-> store current RPG sequence
;	R24 -> 1st hex number
;	R25 -> 2nd hex number
;	R26 -> 3rd hex number
;	R27 -> 4th hex number
;	R28	-> 5th hex number
;	R29 -> display value counter
;	R30
;	R31
;
;   Code - Group 12 - 7E0E2
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

.include "m328Pdef.inc"
.cseg
.org 0

sbi DDRB, 0		; PB0 is now output
sbi DDRB, 1		; PB1 is now output
sbi DDRB, 2		; PB2 is now output
cbi DDRB, 3		; PB3 is now input
sbi DDRB, 5		; PB5 is now output

cbi DDRD, 6		; PD6 is now input
cbi DDRD, 7		; PD7 is now input

; display values for seven segment display
.equ zero = 0x3f
.equ one = 0x06
.equ two = 0x5b
.equ three = 0x4f
.equ four = 0x66 
.equ five = 0x6d
.equ six = 0x7d
.equ seven = 0x07
.equ eight = 0x7f
.equ nine = 0x6f
.equ a = 0x77
.equ b = 0x7c
.equ c = 0x39
.equ d = 0x5e
.equ e = 0x79
.equ f = 0x71
.equ dot = 0x80
.equ dash = 0x40
.equ off = 0x00
.equ underscore = 0x08

; code values - 7e0e2
.equ first_digit = 0x07
.equ second_digit = 0x0e
.equ third_digit = 0x00
.equ fourth_digit = 0x0e
.equ fifth_digit = 0x02

; timer setup
ldi R20, 0x05
out TCCR0B, R20

; start of main program
main:
	; load the timer counter to 0
	ldi R19, 0x00
	; load in values for pattern detection with RPG
	ldi R22, 0b00000011
	ldi R23, 0b00000011
	; load values for registers saving values, set a value above the max
	ldi R24, 0x10 
	ldi R25, 0x10 
	ldi R26, 0x10 
	ldi R27, 0x10
	ldi R28, 0x10
	; start with the counter being -1 so when the CW turn occurs it goes to 0
	ldi R29, -0x01
	ldi R16, dash
	rcall display

; main loop for checking for inputs from the RPG and the button
main_loop:
	in R18, PIND				; read entire input
	andi R18, 0b11000000		; keep only the two MSBs
	lsr R18						; shift the input from MSBs to LSBs
	lsr R18
	lsr R18
	lsr R18
	lsr R18
	lsr R18
	cp R18, R22					; compare to the previously stored value
	brne compare				; if the value is not the same, we branch since we moved onto the next value in the sequence

	wait_for_button:
		sbic PINB, 3			; wait for button press
		rjmp main_loop			; go back to main loop if not pressed
		rjmp button_pressed		; if pressed go to next routine
		rjmp main_loop
	button_pressed:
		rcall delay_10ms		; start timer counting
		cpi R19, 0xc9			; compare to one value above the 2 second mark
		breq skip_increment		; if equal we don't want to count anymore, this prevents an overflow error
		inc R19					; otherwise, we continue to increment the counte 
		skip_increment: nop		; used to skip the incrementing
		sbis PINB, 3			; detects button release
		rjmp button_pressed		; jump back to button press subroutine if not released
		cpi R19, 0xc8			; compare the register to the immediate value to determine ~2 seconds
		brsh main				; if held for 2 seconds or more, reset
		rcall save_value		; otherwise go and perform the button action
		ldi R29, 0x00			; reset back to zero as an identifier to know something happened
		rcall update_display	; update display
	rjmp main_loop				; go back to main loop
/*
 * This block will compapre the current sequence that is recorded and depending if there is
 * a match it will know which direction the RPG is turning and perform the correct action.
 */
compare:
	mov R22, R18				; R22 <- R18
	lsl R23						; move the previous value to the left
	lsl R23
	or R23, R18					; add in the current part of the pattern
	cpi R23, 0b01001011			; clock-wise pattern
	breq increase_counter
	cpi R23, 0b10000111			; counter clock-wise pattern
	breq decrease_counter
	rjmp main_loop				; if no match go back to main loop and continue sequence
increase_counter:
	ldi R23, 0b00000011			; reset sequence register
	inc R29						; increment counter register
	rcall update_display		; update display
	rcall display
	rjmp main_loop				; jump back to main loop
decrease_counter:
	ldi R23, 0b00000011			; reset sequence register
	cpi R16, dash				; check to see if the display is showing a dash
	brne decrease				; if it is not, decrease as usual
	ldi R29, -0x01				; otherwise, set the counter to -1, so when a right turn happens it goes to 0
	rjmp main_loop				; jump back to the start of the loop
decrease:
	dec R29						; decrement counter register
	rcall update_display		; update display
	rcall display
	rjmp main_loop				; jump back to main loop
/*
 * This block contains the subroutines for checking which value the seven segment should be set to
 * before the display subroutine is called.
 * We do this by comparing the counter to the immediates to determine which value to show.
 */
update_display:
	; zero
	cpi R29,0x00
	breq show_zero
	; one
	cpi R29,0x01
	breq show_one
	; two
	cpi R29,0x02
	breq show_two
	; three
	cpi R29,0x03
	breq show_three
	; four
	cpi R29,0x04
	breq show_four
	; five
	cpi R29,0x05
	breq show_five
	; six
	cpi R29,0x06
	breq show_six
	; seven
	cpi R29,0x07
	breq show_seven
	; eight
	cpi R29,0x08
	breq show_eight
	; nine
	cpi R29,0x09
	breq show_nine
	; a
	cpi R29,0x0a
	breq show_a
	; b
	cpi R29,0x0b
	breq show_b
	; c
	cpi R29,0x0c
	breq show_c
	; d
	cpi R29,0x0d
	breq show_d
	; e
	cpi R29,0x0e
	breq show_e
	; f
	cpi R29,0x0f
	breq show_f
	; prevent from counting above 15(f)
	cpi R29, 0x0f
	brge upper_count
	; prevent from counting below 0
	cpi R29, 0x00
	brlt lower_count
	ret
upper_count:
	ldi R29, 0x0f
	ret
lower_count:
	ldi R29, 0x00
	ret
; load and display each possible value needed
show_zero:
	ldi R16, zero
	rcall display
	ret
show_one:
	ldi R16, one
	rcall display
	ret
show_two:
	ldi R16, two
	rcall display
	ret
show_three:
	ldi R16, three
	rcall display
	ret
show_four:
	ldi R16, four
	rcall display
	ret
show_five:
	ldi R16, five
	rcall display
	ret
show_six:
	ldi R16, six
	rcall display
	ret
show_seven:
	ldi R16, seven
	rcall display
	ret
show_eight:
	ldi R16, eight
	rcall display
	ret
show_nine:
	ldi R16, nine
	rcall display
	ret
show_a:
	ldi R16, a
	rcall display
	ret
show_b:
	ldi R16, b
	rcall display
	ret
show_c:
	ldi R16, c
	rcall display
	ret
show_d:
	ldi R16, d
	rcall display
	ret
show_e:
	ldi R16, e
	rcall display
	ret
show_f:
	ldi R16, f
	rcall display
	ret
/*
 * This block of code will store any values after the button press and
 * will also check the comparison of all 5 digits once they are all selected
 */
save_value:
    ldi R19, 0x00				; reset button hold counter back to zero
	cpi R24, 0x10				; check register one
	breq set_register_one		; if equal, then we set a value to the register
	cpi R25, 0x10				; check register two
	breq set_register_two		; if equal, then we set a value to the register
	cpi R26, 0x10				; check register three
	breq set_register_three		; if equal, then we set a value to the register
	cpi R27, 0x10				; check register four
	breq set_register_four		; if equal, then we set a value to the register
	cpi R28, 0x10				; check register five
	breq set_register_five		; if equal, then we set a value to the register
	ret
set_register_one:
	mov R24, R29				; move over the number from the counter
	ret
set_register_two:
	mov R25, R29				; move over the number from the counter
	ret
set_register_three:
	mov R26, R29				; move over the number from the counter
	ret
set_register_four:
	mov R27, R29				; move over the number from the counter
	ret
set_register_five:
	mov R28, R29				; move over the number from the counter
compare_values:					; after register five has been set, go right into comparing the value
	cpi R24, first_digit		; we check each value one at a time, if any are not equal then a fail case will occur
	brne fail_case
	cpi R25, second_digit
	brne fail_case
	cpi R26, third_digit
	brne fail_case
	cpi R27, fourth_digit
	brne fail_case
	cpi R28, fifth_digit
	brne fail_case
	rjmp success_case			; if all comparisons are equal, then a success case is called
fail_case:
	ldi R16, underscore			; show '_'
	rcall display
	rcall delay_nine			; wait 9 seconds
	rjmp main					; reset back to main
success_case:
	ldi R16, dot				; show '.'
	rcall display
	sbi PORTB, 5				; turn the LED on the microcontroller on
	rcall delay_five			; wait five seconds
	cbi PORTB, 5				; turn off the LED
	rjmp main					; reset back to main
/*
 *	display - updates the seven segment display
 */
display:
	push R16				; add the display
	push R17				
	in R17, SREG
	push R17
	ldi R17, 8				; loop --> test all 8 bits, both displays
loop:
	rol R16					; rotate left trough display carry
	BRCS set_ser_in_1		; branch if Carry is set
	cbi PORTB,2
	rjmp end
set_ser_in_1:
	; set SER to 1
	sbi PORTB,2
end:
	; generate SRCLK pulse
	sbi PORTB,0
	cbi PORTB,0
	dec R17
	brne loop
	; generate RCLK pulse
	sbi PORTB,1
	cbi PORTB,1
	; restore registers from stack
	pop R17
	out SREG, R17
	pop R17
	pop R16
	ret

/*
 * delay_10ms - uses the timer in order to count 10ms
 * delay_five - uses the 10ms for a 5 second delay
 * delay_nine - uses the both the 10ms and 5s delays for a 9 second delay
 */
delay_10ms:
	ldi R20, 0x63
	out TCNT0, R20
wait:
	in R20, TCNT0
	cpi R20, 0x00
	brne wait
	ret

delay_five:
	ldi R21, 0xea
loopFiveOne:
	rcall delay_10ms
	dec R21
	cpi R21, 0x00
	brne loopFiveOne
	ldi R21, 0xea
loopFiveTwo:
	rcall delay_10ms
	dec R21
	cpi R21, 0x00
	brne loopFiveTwo
	ret

delay_nine:
	rcall delay_five
	ldi R21, 0xff
loopNineOne:
	rcall delay_10ms
	dec R21
	cpi R21, 0x00
	brne loopNineOne
	ldi R21, 0x8c
loopNineTwo:
	rcall delay_10ms
	dec R21
	cpi R21, 0x00
	brne loopNineTwo
	ret
.exit