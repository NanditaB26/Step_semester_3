
import java.util.*;

public class CampusVehiclePass {

    interface Chargeable {
        void bookChargingBay();
    }

    static class Vehicle {
        String passNumber;
        String owner;
        String type;
        int fee;

        Vehicle(String passNumber, String owner, String type, int fee) {
            this.passNumber = passNumber;
            this.owner = owner;
            this.type = type;
            this.fee = fee;
        }
    }

    static class Bike extends Vehicle {
        Bike(String id, String owner) {
            super(id, owner, "Bike", 300);
        }
    }

    static class Car extends Vehicle {
        Car(String id, String owner) {
            super(id, owner, "Car", 1000);
        }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String id, String owner) {
            super(id, owner, "EBike", 300);
        }

        public void bookChargingBay() {
            // Charging bay booking is supported.
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String id, String owner) {
            super(id, owner, "ECar", 1000);
        }

        public void bookChargingBay() {
            // Charging bay booking is supported.
        }
    }

    static Vehicle createVehicle(String type, String id, String owner) {
        switch (type) {
            case "Bike": return new Bike(id, owner);
            case "Car": return new Car(id, owner);
            case "EBike":
            case "E-Bike": return new EBike(id, owner);
            case "ECar":
            case "E-Car": return new ECar(id, owner);
            default: return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("PASS")) {
                Vehicle v = createVehicle(p[1], p[2], p[3]);

                if (v != null) {
                    vehicles.put(v.passNumber, v);
                    System.out.println(v.passNumber + " (" + v.type
                            + ") pass fee " + v.fee);
                }
            } else if (p[0].equals("CHARGE")) {
                Vehicle v = vehicles.get(p[1]);

                if (v == null) {
                    System.out.println("Vehicle not found");
                } else if (v instanceof Chargeable) {
                    ((Chargeable) v).bookChargingBay();
                    System.out.println(v.passNumber + " charging bay allotted");
                } else {
                    System.out.println(v.passNumber
                            + " rejected: charging unsupported");
                }
            }
        }
        sc.close();
    }
}
