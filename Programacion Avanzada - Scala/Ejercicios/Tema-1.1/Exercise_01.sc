def leapYear(year: Int): Boolean = {
  (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)
}

leapYear(2000)
leapYear(2001)
leapYear(2020)