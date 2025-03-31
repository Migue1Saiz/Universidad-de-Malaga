def runLength(str: String): String = {
  var res: String = ""
  var c: Char = str.charAt(0)
  var freq: Int = 0
  for (char <- str)
    if (c == char) {
      freq += 1
    } else {
      res += s"$c$freq"
      freq = 1
      c = char
    }
  res += s"$c$freq"
  res
}

runLength("aaabbc")