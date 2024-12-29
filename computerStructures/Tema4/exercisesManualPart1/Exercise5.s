/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

ldr r0, =GPBASE
ldr r2,  =0x600

loop:
	str r2, [r0, #GPSET0]
	bl wait
	str r2, [r0, #GPCLR0]
	bl wait
	b loop
		
wait: push {r0, r3, r4}
	ldr r0, =STBASE
	ldr r3, [r0, #STCLO]
	/* Now the time that you want ej=1s */
	ldr r4, =1000000
	add r4, r3, r4
ret1:
	ldr r3, [r0, #STCLO]
	cmp r3, r4
	blt ret1
	POP {r0, r3, r4}
	bx lr

end:   b end
