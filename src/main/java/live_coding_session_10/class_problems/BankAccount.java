
import java.util.*;

public class BankAccount {

    static abstract class Account {
        String id;
        int balance;

        Account(String id, int balance) {
            this.id = id;
            this.balance = balance;
        }

        abstract String withdraw(int amount);
    }

    static class Savings extends Account {
        Savings(String id, int balance) {
            super(id, balance);
        }

        @Override
        String withdraw(int amount) {
            if (amount <= 0) {
                return id + " rejected: invalid amount";
            }
            if (balance - amount < 1000) {
                return id + " rejected: minimum balance 1000";
            }
            balance -= amount;
            return id + " balance " + balance;
        }
    }

    static class Current extends Account {
        Current(String id, int balance) {
            super(id, balance);
        }

        @Override
        String withdraw(int amount) {
            if (amount <= 0) {
                return id + " rejected: invalid amount";
            }
            if (balance - amount < -5000) {
                return id + " rejected: overdraft limit 5000";
            }
            balance -= amount;
            return id + " balance " + balance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            switch (p[0]) {
                case "Savings":
                    accounts.put(p[1],
                            new Savings(p[1], Integer.parseInt(p[2])));
                    break;

                case "Current":
                    accounts.put(p[1],
                            new Current(p[1], Integer.parseInt(p[2])));
                    break;

                case "WITHDRAW":
                    Account account = accounts.get(p[1]);

                    if (account == null) {
                        System.out.println("Account not found");
                    } else {
                        System.out.println(
                                account.withdraw(Integer.parseInt(p[2])));
                    }
                    break;
            }
        }
        sc.close();
    }
}
