def subsets[A](set: Set[A]): Set[Set[A]] = {
  // not tail recursive, because it needs to compute the subset of a previous step to make the current step
  def subsetsPrime(l: List[A], set)
}


subsets(Set()) // Output: Set(Set())
subsets(Set(1)) // Output: Set(Set(), Set(1))
subsets(Set(1,2))  // Output: Set(Set(),Set(1),Set(2),Set(1,2)
subsets(Set(1, 2, 3))  // Output: Set(Set(),Set(1),Set(2),Set(1,2),Set(3),Set(1,3),Set(2,3),Set(1,2,3))