/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

ldr r0, =GPBASE
ldr r1, =300000

/* led sequence */
ldr r2, =0x200 @ red 1
str r2, [r0, #GPSET0]
bl wait

ldr r2, =0x600 @ red 2
str r2, [r0, #GPSET0]
bl wait

ldr r2, =0xe00 @ yellow 1
str r2, [r0, #GPSET0]
bl wait

ldr r2, =0x20e00 @ yellow 2
str r2, [r0, #GPSET0]
bl wait

ldr r2, =0x420e00 @ green 1
str r2, [r0, #GPSET0]
bl wait

ldr r2, =0x8420E00 @ green 2
str r2, [r0, #GPSET0]
bl wait

/* sound */
ldr r1, =2272
ldr r2, =0b10000
sound:
	str r2, [r0, #GPSET0]
	bl wait
	str r2, [r0, #GPCLR0]
	bl wait
	b sound

/* the time is specified in r1 */
wait:
	push {r0, r2, r3}
	ldr r0, =STBASE
	ldr r2, [r0, #STCLO]
	add r3, r1, r2
check:
	ldr r2, [r0, #STCLO]
	cmp r2, r3
	blt check
	pop {r0, r2, r3}
	bx lr


end:   b end
