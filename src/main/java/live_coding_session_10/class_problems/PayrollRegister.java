
import java.util.*;

public class PayrollRegister {

    static class Payslip {
        String name;
        String type;
        int pay;

        Payslip(String name, String type, int pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }

        @Override
        public String toString() {
            return "Payslip[name=" + name
                    + ", type=" + type
                    + ", pay=" + pay + "]";
        }
    }

    static abstract class Staff {
        String name;

        Staff(String name) {
            this.name = name;
        }

        abstract Payslip generatePayslip();
    }

    static class FullTime extends Staff {
        int salary;

        FullTime(String name, int salary) {
            super(name);
            this.salary = salary;
        }

        @Override
        Payslip generatePayslip() {
            return new Payslip(name, "FullTime", salary);
        }
    }

    static class PartTime extends Staff {
        int hours;
        int rate;

        PartTime(String name, int hours, int rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        Payslip generatePayslip() {
            return new Payslip(name, "PartTime", hours * rate);
        }
    }

    static class Intern extends Staff {
        int stipend;

        Intern(String name, int stipend) {
            super(name);
            this.stipend = stipend;
        }

        @Override
        Payslip generatePayslip() {
            return new Payslip(name, "Intern", stipend);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Payslip> payslips = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");
            Staff staff = null;

            switch (p[0]) {
                case "FullTime":
                    staff = new FullTime(p[1], Integer.parseInt(p[2]));
                    break;

                case "PartTime":
                    staff = new PartTime(p[1],
                            Integer.parseInt(p[2]),
                            Integer.parseInt(p[3]));
                    break;

                case "Intern":
                    staff = new Intern(p[1], Integer.parseInt(p[2]));
                    break;
            }

            if (staff != null) {
                payslips.add(staff.generatePayslip());
            }
        }

        int total = 0;
        int highestPay = -1;
        String topEarner = "";

        for (Payslip slip : payslips) {
            System.out.println(slip);
            total += slip.pay;

            if (slip.pay > highestPay) {
                highestPay = slip.pay;
                topEarner = slip.name;
            }
        }

        System.out.println("Total " + total);

        if (!payslips.isEmpty()) {
            System.out.println("top earner " + topEarner);
        }

        sc.close();
    }
}
