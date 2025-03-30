import scala.annotation.tailrec

def primeFactors(n: Int): List[Int] = {
  @tailrec
  def Factors(n: Int, divisor: Int, factors: List[Int]): List[Int] = {
    // There are no factors lower than 2
    if (n < 2) {
      factors
    } else if (n % divisor == 0){
      Factors(n / divisor, divisor, divisor :: factors)
    } else {
      Factors(n, divisor + 1, factors)
    }
  }
  Factors(n, 2, List())
}

println(primeFactors(60)) // Output: List(2, 2, 3, 5)
println(primeFactors(97)) // Output: List(97)
println(primeFactors(84)) // Output: List(2, 2, 3, 7)