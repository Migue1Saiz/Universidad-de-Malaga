// Couldn't get it working
//
def remove_duplicates(array: Array[Int]): Array[Int] =
  var newArr = array
  var dummyArr = array
  var index = newArr.length
  var i = 0
  var j = 0
  while (i < newArr.length)
    while (i < newArr.length)
      if (newArr(i) == newArr(j))
        dummyArr = newArr.slice(0, j) ++ newArr.slice(j+1, newArr.length)
        newArr = dummyArr

      j += 1

    i += 1

  newArr

val arr = Array(1, 1, 1, 3, 2, 34, 5, 4, 3, 2, 1)
remove_duplicates(arr)