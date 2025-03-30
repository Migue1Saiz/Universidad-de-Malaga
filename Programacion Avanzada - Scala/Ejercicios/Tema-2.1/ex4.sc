import scala.annotation.tailrec

def zip[A, B](listA: List[A], listB: List[B]): List[(A, B)] = {
  @tailrec
  def zipPrime(listA: List[A], listB: List[B], acc: List[(A, B)]): List[(A, B)] =
    if (listA == Nil) {
      acc
    }
    else if (listB == Nil) {
      acc
    }
    else {
      zipPrime(listA.tail, listB.tail, (listA.head, listB.head) :: acc)
    }

  zipPrime(listA, listB, List())
}


zip(List(10, 20, 30), List('a', 'b', 'c'))  // List((10, 'a'), (20, 'b'), (10, 'c'))
zip(List(10, 20, 30), List('a', 'b'))   // List((10,'a'), (20,'b'))