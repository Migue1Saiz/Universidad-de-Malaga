def reduce[A](l: List[A], f: (A, A) => A): A = {
  l match {
    case Nil => throw new UnsupportedOperationException("empty List")
    case head :: Nil => head
    case head :: tail => f(head, reduce(tail, f))
  }
}

// cannot create tail recursive, as the first element to be inputted must be an empty instance and scala doesnt support the creation of it from a type parameter

reduce(List(1,2,3,4,5), _ + _) // Output: 15