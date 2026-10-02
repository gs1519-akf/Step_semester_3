import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
    String getMethodName();
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Credit Card payment is successful.");
        return true;
    }
    @Override
    public String getMethodName() { return "Credit Card"; }
}

class PayPalPayment implements PaymentMethod {
    private boolean shouldSucceed;

    public PayPalPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public boolean processPayment(double amount) {
        if (shouldSucceed) {
            System.out.println("PayPal payment is successful.");
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String getMethodName() { return "PayPal"; }
}

class ShoppingOrder {
    private String orderId;
    private String customer;
    private Map<String, Integer> items = new HashMap<>();
    private String status; // Pending, Paid

    public ShoppingOrder(String orderId, String customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.status = "Pending";
        System.out.printf("Order created for %s.%n", customer);
    }

    public void addItem(String product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public boolean pay(PaymentMethod method, double amount) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return false;
        }

        System.out.printf("Payment initiated via %s for %s.%n", method.getMethodName(), orderId);
        boolean success = method.processPayment(amount);
        if (success) {
            status = "Paid";
            System.out.printf("Payment for %s successful. Order status: %s.%n", orderId, status);
            return true;
        } else {
            System.out.printf("Payment for %s failed. Order status: %s.%n", orderId, status);
            return false;
        }
    }
}

public class ShoppingPaymentProcessing {
    public static void main(String[] args) {
        ShoppingOrder orderX = new ShoppingOrder("Order X", "Customer X");
        orderX.addItem("Product A", 2);
        orderX.addItem("Product B", 1);
        orderX.pay(new CreditCardPayment(), 250.0);

        ShoppingOrder orderY = new ShoppingOrder("Order Y", "Customer Y");
        orderY.pay(new CreditCardPayment(), 0.0);

        ShoppingOrder orderZ = new ShoppingOrder("Order Z", "Customer Z");
        orderZ.addItem("Product C", 1);
        orderZ.pay(new PayPalPayment(false), 80.0);
    }
}
