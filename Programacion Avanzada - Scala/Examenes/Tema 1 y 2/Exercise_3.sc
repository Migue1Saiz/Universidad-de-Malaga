def countItems(lst: List[Any]): Int ={
  def countItemsPrime(lst: Any): Int = {
    var res = 0
    // Check if the instance of Any is a List
    if (lst.isInstanceOf[List[Any]]){
      for (element <- lst.asInstanceOf[List[Any]]) // If it is a list, iterate over its elements
        if (element.isInstanceOf[List[Any]]){   // Check if the element that we got is a list
          // If it is, then pass it through countItemsPrime
          res = res + countItems(element.asInstanceOf[List[Any]])
        } else {
          // If not, then it is an element, and it adds 1
          res += 1
        }
    }
    // Return the solution
    res
  }
  countItemsPrime(lst)
}

countItems(List(List(1, 2), 3, List(4, List(5, 6)), 7)) //7