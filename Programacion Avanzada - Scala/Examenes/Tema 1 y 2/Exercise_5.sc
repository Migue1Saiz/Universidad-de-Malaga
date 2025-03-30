def mostFrequentCharPerWord(words: List[String]): List[(String, Char)] = {
  def mostFrequentChar(word: String): Char = {
    var freq = 0
    var maxChar = 'c'
    var map = Map[Char, Int]()
    
    // Iterate over all the characters of the word and add them to the list or update the frequency of them
    for (char: Char <- word){
      map += (char, map.getOrElse(char, 0) +1)
    }
    
    // Iterate over all the keys of the map, searching for the one with the highest frequency
    for (key <- map.keySet) {
      if (map.getOrElse(key, 0) > freq){
        freq = map.getOrElse(key, 0)
        maxChar = key
      }
    }
    maxChar
  }

  var list = List[(String, Char)]()
  
  // Iterate the previous algorithm and add the words with their most frequent letter into the result list
  for (word <- words) {
    list = list.appended((word, mostFrequentChar(word)))
  }
  list
}

mostFrequentCharPerWord(List("banana", "apple", "cherry")) // List((banana,a), (apple,p), (cherry,r))