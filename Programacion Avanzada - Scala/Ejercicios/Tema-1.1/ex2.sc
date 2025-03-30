
class ATM(var balance: Int) {
  // check the balance
  def checkBalance(): Int = {
    balance
  }

  // Deposit the money
  def deposit(money: Int): Unit = {
    balance += money
  }

  // withdraw money
  def withdraw(money: Int): Int = {
    var withdrawal: Int = -1
    if (money > balance)
      println("There is no sufficient money.")
      withdrawal = -1
    else {
      println("Withdraw successfull.")
      balance -= money
      withdrawal = money
    }
    withdrawal
  }
}

val atm = ATM(1000)
atm.checkBalance()  // 1000
atm.withdraw(10020) // error
atm.checkBalance()  // 1000
atm.withdraw(800)  // accepted
atm.checkBalance()  // 200
atm.deposit(100)   // accepted
atm.checkBalance()  // 300


// check the balance
// Deposit money
// withdraw money while handling insufficient funds