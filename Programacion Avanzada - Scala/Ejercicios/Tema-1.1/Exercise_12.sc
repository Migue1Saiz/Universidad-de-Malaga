def frequency(str: String): Char = {
  var res = 'c'
  var map = Map[Char, Int]()
  for (char: Char <- str) {
    // Is it in map?
    if (map.contains(char)) { // Yes, update the value
      val freq = map(char)
      map = map.updated(char, freq + 1)
    } else { // No, create an entry
      map = map + (char -> 1)
    }
  }
  res = map.maxBy(_._2)._1
  res
}

frequency("tttaaaoioddagasgrhdhsaajtrjfsdrdgdgsg")
frequency("tttttaaaa")