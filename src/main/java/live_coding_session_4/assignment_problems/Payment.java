package live_coding_session_4.assignment_problems;

class CardPayment extends Payment {

    public double payWithProcessingFee(double amount) {
        return amount + (amount * 0.02);
    }
}

public class Payment {

    public double pay(double amount) {
        return amount;
    }

    public double processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            double total = ((CardPayment) payment)
                    .payWithProcessingFee(amount);

            System.out.println("Charged (card, incl. fee): Rs " + total);
            return total;
        } else {
            double total = payment.pay(amount);

            System.out.println("Paid (cash): Rs " + total);
            return total;
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {100, 50, 200, 75, 120};

        Payment processor = new Payment();
        double totalCollected = 0;

        for (int i = 0; i < payments.length; i++) {
            totalCollected += processor.processTransaction(
                    payments[i], amounts[i]);
        }

        System.out.println("Total Collected: Rs " + totalCollected);
    }
}