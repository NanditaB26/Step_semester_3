package live_coding_session_3.assignment_problems;

public class PayrollAccount {

    private double basicSalary;
    private double bonus;

    public PayrollAccount(double openingSalary) {

        if (openingSalary < 0) {
            System.out.println("Warning: Negative salary not allowed.");
            basicSalary = 0;
        } else {
            basicSalary = openingSalary;
        }

        bonus = 0;
    }

    public void creditBonus(double amount) {

        if (amount <= 0) {
            System.out.println("Bonus rejected: Invalid amount");
        } else {
            bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }
    }

    public void deductTax(double percent) {

        if (percent < 0 || percent > 100) {
            System.out.println("Tax rejected: Invalid percentage");
        } else {
            basicSalary = basicSalary - (basicSalary * percent / 100);
            System.out.println("Tax deducted: " + percent + "%");
        }
    }

    public double getNetSalary() {
        return basicSalary + bonus;
    }

    public static void main(String[] args) {

        PayrollAccount account = new PayrollAccount(50000);

        account.creditBonus(5000);
        account.deductTax(10);

        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}