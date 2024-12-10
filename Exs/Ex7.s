/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

	ldr r0, =GPBASE
	ldr r2, =0x010
	
	ldr r1, =0x0000200
	str r1, [r0, #GPSET0]
	bl wait
	ldr r1, =0x0000600
	str r1, [r0, #GPSET0]
	bl wait
	ldr r1, =0x0000E00
	str r1, [r0, #GPSET0]
	bl wait
	ldr r1, =0x0020E00
	str r1, [r0, #GPSET0]
	bl wait
	ldr r1, =0x0420E00
	str r1, [r0, #GPSET0]
	bl wait
	ldr r1, =0x8420E00
	str r1, [r0, #GPSET0]
	bl wait
	bl sound
	
wait:
	PUSH {r0,r3,r4}
	ldr r0,=STBASE
	ldr r3,[r0,#STCLO]
	ldr r4, =300000
	add r4, r3, r4
ret1: 
	ldr r3,[r0,#STCLO]
	cmp r3,r4
	blt ret1
	POP {r0,r3,r4}
	bx lr
	
sound:
	str r2, [r0, #GPSET0]
	bl wait2
	str r2, [r0, #GPCLR0]
	bl wait2
	bl sound
	
	
wait2:
	PUSH {r0,r3,r4}
	ldr r0,=STBASE
	ldr r3,[r0,#STCLO]
	ldr r4, =1136
	add r4, r3, r4
ret2: 
	ldr r3,[r0,#STCLO]
	cmp r3,r4
	blt ret2
	POP {r0,r3,r4}
	bx lr

end:   b end