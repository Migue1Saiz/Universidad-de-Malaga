/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

ldr r0, =GPBASE
ldr r2, =0x800
ldr r3, =0x400000

loop:
	ldr r1, [r0, #GPLEV0]
	tst r1, #0b100
	streq r2, [r0, #GPSET0]
	tst r1, #0b1000
	streq r3, [r0, #GPSET0]
	b loop

end:   b end
