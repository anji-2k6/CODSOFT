class BankAccount {
    double bal = 1000;
    public void deposit(double amt) {
        bal = bal + amt;
    }
    public boolean withdraw(double amt) {
        if (amt > bal)
        {
            return false;
        }
        bal = bal - amt;
        return true;
    }
    public double checkBalance() {
        return bal;
    }
}