def second(list: List[Int]): Int =
  var first: Int = -100000000
  var second: Int = 0
  for (number <- list)
    if (number > first)
      second = first
      first = number

  second

val list: List[Int] = List(1,2,3,4,5,6,7,8,9,10)
second(list)
