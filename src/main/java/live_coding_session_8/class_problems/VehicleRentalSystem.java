
import java.util.*;

public class VehicleRentalSystem {

    static abstract class Vehicle {
        private final String id;
        private boolean available = true;

        Vehicle(String id) {
            this.id = id;
        }

        String getId() {
            return id;
        }

        boolean isAvailable() {
            return available;
        }

        abstract double calculateCharge(int days);

        boolean rent(Customer customer, int days) {
            if (!available) {
                System.out.println(id + " is currently unavailable.");
                return false;
            }

            if (days <= 0) {
                System.out.println("Rental duration must be positive.");
                return false;
            }

            available = false;
            System.out.println(id + " rented successfully by "
                    + customer.getName() + ".");
            System.out.printf("Rental charge: $%.2f.%n",
                    calculateCharge(days));
            return true;
        }

        void returnVehicle(Customer customer) {
            if (available) {
                System.out.println(id + " is not currently rented.");
                return;
            }

            available = true;
            System.out.println(id + " returned by " + customer.getName() + ".");
        }
    }

    static class Sedan extends Vehicle {
        private static final double DAILY_RATE = 50.0;

        Sedan(String id) {
            super(id);
        }

        @Override
        double calculateCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    static class SUV extends Vehicle {
        private static final double DAILY_RATE = 80.0;

        SUV(String id) {
            super(id);
        }

        @Override
        double calculateCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    static class Truck extends Vehicle {
        private static final double DAILY_RATE = 120.0;

        Truck(String id) {
            super(id);
        }

        @Override
        double calculateCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    static class Customer {
        private final String name;

        Customer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Rental {
        private final Customer customer;
        private final Vehicle vehicle;
        private final int days;
        private boolean active;

        Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.active = vehicle.rent(customer, days);
        }

        void returnVehicle() {
            if (!active) {
                System.out.println("No active rental to return.");
                return;
            }

            vehicle.returnVehicle(customer);
            active = false;
        }
    }

    public static void main(String[] args) {
        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        Rental r1 = new Rental(c1, sedanA, 3);
        new Rental(c2, sedanA, 2);

        r1.returnVehicle();

        new Rental(c3, suvB, 5);
    }
}
