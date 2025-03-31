import scala.annotation.tailrec

def unzip[A, B](list: List[(A, B)]): (List[A], List[B]) = {
  @tailrec
  def unzipPrime(list: List[(A, B)], acc: (List[A], List[B])): (List[A], List[B]) = {
    list match {
      case Nil => acc
      case (x, y) :: tail => unzipPrime(tail, (x :: acc._1, y :: acc._2))
    }
  }

  unzipPrime(list, (List(), List()))
}

unzip(List((10, 'a'), (20, 'b'), (10, 'c'))) //(List(10, 20, 30), List('a', 'b', 'c'))