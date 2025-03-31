def isPalindrome1(string: String): Boolean =
  {
    val reverse = string.reverse
    string == reverse
  }

def isPalindrome2(string: String): Boolean =
  {
    var i = 0
    var res: Boolean = true
    while (res && i < string.length / 2) {
      res = string.charAt(i) == string.charAt(string.length - 1 - i)
      i += 1
    }
    res
  }

isPalindrome2("aassddddssaa") // true
isPalindrome2("aassdddd8ssaa")  // false
isPalindrome1("aassddddssaa") // true
isPalindrome1("aassdddd8ssaa")  // false