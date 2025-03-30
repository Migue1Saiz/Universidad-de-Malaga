def printPrimeNumbers(N: Int): Unit = {
  def isPrime(n: Int): Boolean = {
    var i = 2
    var res: Boolean = true
    while (i < (n / 2) && res) {
      res = !(n % i == 0)
      i += 1
    }
    res
  }


  var i = 2
  while (i <= N)
    var bool = isPrime(i)
    if (bool){
      println(i)
    }
    i += 1
}

printPrimeNumbers(100)