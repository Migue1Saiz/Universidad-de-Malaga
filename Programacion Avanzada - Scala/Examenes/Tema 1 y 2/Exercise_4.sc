import scala.annotation.tailrec

def slidingTransform[T, U](l: List[T], size: Int, f: List[T] => U): List[U] = {
  @tailrec
  def slidingTransformPrime(l: List[T], size: Int, f: List[T] => U, acc: List[U]): List[U] = {
    if (l.length < size) {
      // If the length of the list is less than the one of the size to be processed, then it cannot
      // be processed further and return the acc (Solution)
      acc
    } else {
      // It can be processed further, remove the first element from the list and process the first three
      // elements with the parsed function, and append it to the acc list
      slidingTransformPrime(l.tail, size, f, acc :+ f(l.slice(0, size)))
    }
  }

  slidingTransformPrime(l, size, f, List())
}


slidingTransform(List(1, 2, 3, 4, 5, 6), 3, _.sum)