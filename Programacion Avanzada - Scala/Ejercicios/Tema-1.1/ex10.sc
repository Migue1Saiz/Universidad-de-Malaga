def isPermutation(arr1: Array[Int], arr2: Array[Int]): Boolean = {
  if (arr1.length != arr2.length) return false
  val countMap = scala.collection.mutable.Map[Int, Int]()


  // Count occurrences in arr1
  for (num <- arr1) {
    countMap(num) = countMap.getOrElse(num, 0) + 1
  }

  // Decrease count based on arr2
  for (num <- arr2) {
    countMap(num) = countMap.getOrElse(num, 0) - 1
    if (countMap(num) < 0) return false
  }

  true
}


isPermutation(Array(1,2,3), Array(3, 1, 2))
isPermutation(Array(1,2,3), Array(3, 2, 2))
isPermutation(Array(1,2,3), Array(3, 1, 2, 3))