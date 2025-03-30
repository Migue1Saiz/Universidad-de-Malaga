def movingSum(lst: List[Int]): List[Int] = {
  lst.foldLeft((List[Int](), 0)) {
    case ((acc, sum), elm) => ((sum + elm) :: acc, sum + elm)
  }._1.reverse
  // we reverse the list to get the correct order
}

movingSum(List(1, 2, 3, 4))