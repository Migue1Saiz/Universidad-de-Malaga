def sorted_array_merge(arr1: Array[Int], arr2: Array[Int]): Array[Int] = {
  var array: Array[Int] = Array()
  var i = 0
  var j = 0

  // Add the elements considering that they are in increasing order
  while (i < arr1.length && j < arr2.length){
    if (arr1(i) < arr2(j)){
      array = array :+ arr1(i)
      i += 1
    } else {
      array = array :+ arr2(j)
      j += 1
    }
  }

  // add the elements that are left
  while (i < arr1.length){
    array = array :+ arr1(i)
    i += 1
  }
  while (j < arr2.length){
    array = array :+ arr2(j)
    j += 1
  }

  array
}