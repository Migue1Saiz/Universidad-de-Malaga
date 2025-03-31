import scala.annotation.tailrec

def groupBy[A, B](l: List[A], f: A => B): Map[B, List[A]] = {
  @tailrec
  def groupByPrime(l: List[A], f: A => B, acc: Map[B, List[A]]): Map[B, List[A]] = {
    l match {
      case Nil => acc
      case head :: tail => groupByPrime(tail, f, acc.updated(f(head), head :: acc.getOrElse(f(head), List()))  )
    }
  }

  groupByPrime(l, f, Map[B, List[A]]())
}


groupBy(List(1,2,3,4,5), _ % 2 == 0) // Output: Map(false -> List(5, 3, 1), true -> List(4, 2))