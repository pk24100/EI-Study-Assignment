// Unified interface for payment methods
 interface PaymentMethod {
    void pay(double amount);
}

// Concrete payment methods
class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using credit card");
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using PayPal");
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using bank transfer");
    }
}

// Adapters for payment methods
class CreditCardAdapter implements PaymentMethod {
    private CreditCardPayment creditCardPayment;

    public CreditCardAdapter() {
        creditCardPayment = new CreditCardPayment();
    }

    @Override
    public void pay(double amount) {
        creditCardPayment.pay(amount);
    }
}

class PayPalAdapter implements PaymentMethod {
    private PayPalPayment payPalPayment;

    public PayPalAdapter() {
        payPalPayment = new PayPalPayment();
    }

    @Override
    public void pay(double amount) {
        payPalPayment.pay(amount);
    }
}

class BankTransferAdapter implements PaymentMethod {
    private BankTransferPayment bankTransferPayment;

    public BankTransferAdapter() {
        bankTransferPayment = new BankTransferPayment();
    }

    @Override
    public void pay(double amount) {
        bankTransferPayment.pay(amount);
    }
}

public class PaymentGateway {
    public static void main(String[] args) {
        // Create adapters for payment methods
        PaymentMethod creditCardAdapter = new CreditCardAdapter();
        PaymentMethod payPalAdapter = new PayPalAdapter();
        PaymentMethod bankTransferAdapter = new BankTransferAdapter();

        // Use adapters to pay
        creditCardAdapter.pay(100);
        payPalAdapter.pay(200);
        bankTransferAdapter.pay(300);
    }
}