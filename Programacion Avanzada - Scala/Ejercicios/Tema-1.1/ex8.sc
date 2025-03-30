// modulus function to keep track of the pointer
def transverse(i: Int, k: Int): Int = {
  ((i + 1) % k)
}

def rotator(list: List[Int], k: Int): List[Int] =
  {
    var newList: List[Int] = List()
    var i = k + 1
    var j = 0
    while (j < list.length)
      newList = newList :+ list(i)
      j += 1
      i = transverse(i, list.length)


    newList
  }

var list = List(1, 2, 3, 4, 5)
rotator(list, 2)