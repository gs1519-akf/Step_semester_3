class MessWallet {
    private double balance;

    public MessWallet(double initialBalance) {
        if (initialBalance < 0) {
            System.out.println("Warning: Opening balance cannot be negative. Initialized to 0.");
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: Amount must be positive.");
            return;
        }
        this.balance += amount;
    }

    public void deduct(double amount) {
        if (amount > this.balance) {
            System.out.println("Deduct rejected: insufficient balance");
            return;
        }
        this.balance -= amount;
    }

    public double getBalance() {
        return this.balance;
    }
}

public class MessWalletApp {
    public static void main(String[] args) {
        MessWallet wallet = new MessWallet(500);
        wallet.topUp(200);
        System.out.println("Balance after top-up: " + wallet.getBalance());
        wallet.deduct(1000);
        System.out.println("Final balance: " + wallet.getBalance());
    }
}
