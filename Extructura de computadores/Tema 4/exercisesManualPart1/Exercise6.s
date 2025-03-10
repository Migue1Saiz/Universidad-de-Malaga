/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

ldr r0, =GPBASE
ldr r2, =0x10

loop:
	bl wait1
	bl sound
	ldr r1, =1000000
	bl wait
	b loop

/* set timeStop at r5 */
wait1:
	push {r0}
	ldr r0, =STBASE
	ldr r5, =1000000
	ldr r3, [r0, #STCLO]
	add r5, r3, r5
	pop {r0}
	bx lr

sound:
	push {lr}  @ to jump back to main loop
	ldr r1, =2272
	
	str r2, [r0, #GPSET0]
	bl wait
	str r2,[r0, #GPCLR0]
	bl wait
	
	push {r0}
	ldr r0, =STBASE
	ldr r3, [r0, #STCLO]
	pop {r0}
	pop {lr} @ to go back to main loop with bxgt lr
	cmp r3, r5
	bxgt lr
	b sound
	
/* standart wait with the time at r1 */
wait: 
	PUSH {r0, r4}
	ldr r0, =STBASE
	ldr r3, [r0, #STCLO]
	add r4, r3, r1
ret1:
	ldr r3, [r0, #STCLO]
	cmp r3, r4
	blt ret1
	POP {r0, r4}
	bx lr
	


end:   b end
