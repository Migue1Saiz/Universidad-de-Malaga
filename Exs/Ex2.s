/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

	ldr r0, =GPBASE
	ldr r1, =0x800
	
loop:
	ldr r2, [r0, #GPLEV0]
	tst r2, #0b0100
	streq r1, [r0, #GPSET0]
	strne r1, [r0, #GPCLR0]
	bl loop

end:   b end