// Eucledian algorithm for finding the GCD implementation
def GCD(number1: Int, number2: Int): Int = {
  if (number2 == 0) number1 else GCD(number2, number1 % number2)
}

def LCM(A: Int, B: Int): Int =
  (A * B) / GCD(A, B)


GCD(12, 15)
LCM(12, 15)