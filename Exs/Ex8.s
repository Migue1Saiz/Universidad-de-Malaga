/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

	ldr r0, =GPBASE
	ldr r1, =0x010
	
loop:
	ldr r2, [r0, #GPLEV0]
	tst r2, #0b00100
	bleq btn1
	tst r2, #0b01000
	bleq btn2
	bl loop
	
btn1:
	ldr r2, [r0, #GPLEV0]
	str r1, [r0, #GPSET0]
	bl wait1
	str r1, [r0, #GPCLR0]
	bl wait1
	tst r2, #0b01000
	bleq btn2
	bl btn1
btn2:
	ldr r2, [r0, #GPLEV0]
	str r1, [r0, #GPSET0]
	bl wait2
	str r1, [r0, #GPCLR0]
	bl wait2
	tst r2, #0b00100
	bleq btn1
	bl btn2
	
wait1:
	PUSH {r0,r3,r4}
	ldr r0,=STBASE
	ldr r3,[r0,#STCLO]
	ldr r4, =2000
	add r4, r3, r4
ret1: 
	ldr r3,[r0,#STCLO]
	cmp r3,r4
	blt ret1
	POP {r0,r3,r4}
	bx lr
	
wait2:
	PUSH {r0,r3,r4}
	ldr r0,=STBASE
	ldr r3,[r0,#STCLO]
	ldr r4, =1275
	add r4, r3, r4
ret2: 
	ldr r3,[r0,#STCLO]
	cmp r3,r4
	blt ret2
	POP {r0,r3,r4}
	bx lr

end:   b end