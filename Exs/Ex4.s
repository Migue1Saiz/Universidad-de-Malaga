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
	ldr r2, =0x400000
	
loop:
	ldr r3, [r0, #GPLEV0]
	tst r3, #0b00100
	bleq btn1
	tst r3, #0b01000
	bleq btn2
	bl loop
	
btn1:
	streq r1, [r0, #GPSET0]
	bl loop
btn2:
	streq r2, [r0, #GPSET0]
	bl loop

end:   b end