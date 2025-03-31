import scala.annotation.tailrec

def filter[A](l: List[A], f: A => Boolean): List[A] = {
  @tailrec
  def primeFilter(l: List[A], f: A => Boolean, acc: List[A]): List[A] = {
    l match {
      case Nil => acc
      case head :: tail => {
        if (f(head)) {
          primeFilter(tail, f, acc :+ head)
        } else {
          primeFilter(tail, f, acc)
        }
      }
    }
  }

  primeFilter(l, f, List())
}


filter(List(1,2,3,4,5), _ % 2 == 0)  // List(2,4)