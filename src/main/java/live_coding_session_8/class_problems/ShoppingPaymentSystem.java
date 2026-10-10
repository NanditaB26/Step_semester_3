
import java.util.*;

public class ShoppingPaymentSystem {

    static class Customer {
        private final String name;

        Customer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Product {
        private final String name;
        private final double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        String getName() {
            return name;
        }

        double getPrice() {
            return price;
        }
    }

    static class OrderItem {
        private final Product product;
        private final int quantity;

        OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        double getSubtotal() {
            return product.getPrice() * quantity;
        }
    }

    interface PaymentMethod {
        String getName();
        boolean processPayment(double amount);
    }

    static class CreditCardPayment implements PaymentMethod {
        private final boolean successful;

        CreditCardPayment(boolean successful) {
            this.successful = successful;
        }

        public String getName() {
            return "Credit Card";
        }

        public boolean processPayment(double amount) {
            return successful;
        }
    }

    static class PayPalPayment implements PaymentMethod {
        private final boolean successful;

        PayPalPayment(boolean successful) {
            this.successful = successful;
        }

        public String getName() {
            return "PayPal";
        }

        public boolean processPayment(double amount) {
            return successful;
        }
    }

    static class BankTransferPayment implements PaymentMethod {
        private final boolean successful;

        BankTransferPayment(boolean successful) {
            this.successful = successful;
        }

        public String getName() {
            return "Bank Transfer";
        }

        public boolean processPayment(double amount) {
            return successful;
        }
    }

    enum Status {
        PENDING, PAID
    }

    static class Order {
        private final String id;
        private final Customer customer;
        private final List<OrderItem> items = new ArrayList<>();
        private Status status = Status.PENDING;

        Order(String id, Customer customer) {
            this.id = id;
            this.customer = customer;
            System.out.println("Order created for " + customer.getName() + ".");
        }

        void addProduct(Product product, int quantity) {
            if (status == Status.PAID) {
                System.out.println("Cannot modify a paid order.");
                return;
            }

            if (quantity <= 0 || product.getPrice() < 0) {
                System.out.println("Invalid product quantity or price.");
                return;
            }

            items.add(new OrderItem(product, quantity));
        }

        double getTotal() {
            double total = 0;
            for (OrderItem item : items) {
                total += item.getSubtotal();
            }
            return total;
        }

        void pay(PaymentMethod method) {
            if (status == Status.PAID) {
                System.out.println("Order " + id + " is already Paid.");
                return;
            }

            if (items.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return;
            }

            System.out.println("Payment initiated via " + method.getName()
                    + " for Order " + id + ".");

            if (method.processPayment(getTotal())) {
                status = Status.PAID;
                System.out.println("Payment for Order " + id + " successful.");
                System.out.println("Order status: Paid.");
            } else {
                System.out.println("Payment for Order " + id + " failed.");
                System.out.println("Order status: Pending.");
            }
        }
    }

    public static void main(String[] args) {
        Customer x = new Customer("Customer X");
        Order orderX = new Order("X", x);

        orderX.addProduct(new Product("Product A", 20.0), 2);
        orderX.addProduct(new Product("Product B", 30.0), 1);
        orderX.pay(new CreditCardPayment(true));

        Customer y = new Customer("Customer Y");
        Order orderY = new Order("Y", y);
        orderY.pay(new CreditCardPayment(true));

        Customer z = new Customer("Customer Z");
        Order orderZ = new Order("Z", z);
        orderZ.addProduct(new Product("Product C", 40.0), 1);
        orderZ.pay(new PayPalPayment(false));
    }
}
