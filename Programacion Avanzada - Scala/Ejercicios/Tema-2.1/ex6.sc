import scala.annotation.tailrec

def map[A, B](list: List[A], f: A => B): List[B] = {
  @tailrec
  def mapPrime(list: List[A], f: A => B, acc: List[B]): List[B] = {
    list match {
      case Nil => acc
      case head :: tail => mapPrime(tail, f, acc :+ f(head))
    }
  }
  
  mapPrime(list, f, List())
}

map(List(1,2,3,4,5), _ * 2) // Output: List(2,4,6,8,10)