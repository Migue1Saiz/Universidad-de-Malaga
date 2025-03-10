/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

ldr r0, =GPBASE
ldr r1, =0 				 @ Value for silence
ldr r2, =0b10000 		 @ Speaker GPIO val

loop:
	ldr r3, [r0, #GPLEV0]
	tst r3, #0b100		 @ test for button 1
	ldreq r1, =1984		 @ load 252Hz
	tst r3, #0b1000		 @ test for button 2
	ldreq r1, =1275		 @ load 392Hz
	
/* Play selected input */
	str r2, [r0, #GPSET0]
	bl wait
	str r2, [r0, #GPCLR0]
	bl wait
	b loop



/* r1 has the time to be waited to */
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
