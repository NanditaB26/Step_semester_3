
import java.time.LocalDateTime;
import java.util.*;

public class CampusPremiereTicketCounter {

    static abstract class Seat {
        private final String id;

        Seat(String id) {
            this.id = id;
        }

        String getId() {
            return id;
        }

        abstract double getPrice();
    }

    static class RegularSeat extends Seat {
        RegularSeat(String id) { super(id); }
        double getPrice() { return 150; }
    }

    static class PremiumSeat extends Seat {
        PremiumSeat(String id) { super(id); }
        double getPrice() { return 250; }
    }

    static class ReclinerSeat extends Seat {
        ReclinerSeat(String id) { super(id); }
        double getPrice() { return 400; }
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

    static class Show {
        private final String name;
        private final LocalDateTime startTime;
        private final Map<String, Seat> seats = new LinkedHashMap<>();
        private final Set<String> bookedSeatIds = new HashSet<>();

        Show(String name, LocalDateTime startTime) {
            this.name = name;
            this.startTime = startTime;
        }

        void addSeat(Seat seat) {
            seats.put(seat.getId(), seat);
        }

        boolean isAvailable(String id) {
            return seats.containsKey(id) && !bookedSeatIds.contains(id);
        }

        Seat getSeat(String id) {
            return seats.get(id);
        }

        boolean reserve(List<Seat> requested) {
            for (Seat seat : requested) {
                if (!isAvailable(seat.getId())) {
                    System.out.println(
                        "Seat " + seat.getId() + " is already booked for this show."
                    );
                    return false;
                }
            }

            for (Seat seat : requested) {
                bookedSeatIds.add(seat.getId());
            }
            return true;
        }

        void release(List<Seat> requested) {
            for (Seat seat : requested) {
                bookedSeatIds.remove(seat.getId());
            }
        }

        boolean hasStarted() {
            return !LocalDateTime.now().isBefore(startTime);
        }

        String getName() {
            return name;
        }
    }

    static class Booking {
        private final Customer customer;
        private final Show show;
        private final List<Seat> seats;
        private boolean cancelled = false;

        Booking(Customer customer, Show show, List<String> seatIds) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>();

            if (seatIds.isEmpty() || seatIds.size() > 6) {
                System.out.println("Booking must contain 1 to 6 seats.");
                cancelled = true;
                return;
            }

            Set<String> uniqueIds = new HashSet<>(seatIds);
            if (uniqueIds.size() != seatIds.size()) {
                System.out.println("Duplicate seat IDs are not allowed.");
                cancelled = true;
                return;
            }

            for (String id : seatIds) {
                Seat seat = show.getSeat(id);
                if (seat == null) {
                    System.out.println("Seat " + id + " does not exist.");
                    cancelled = true;
                    return;
                }
                seats.add(seat);
            }

            if (!show.reserve(seats)) {
                cancelled = true;
                return;
            }

            System.out.println(
                "Booking confirmed for " + customer.getName() + ": "
                + String.join(", ", seatIds) + "."
            );
            System.out.printf("Total: ₹%.2f.%n", calculateTotal());
        }

        double calculateTotal() {
            double total = 0;
            for (Seat seat : seats) {
                total += seat.getPrice();
            }
            return total;
        }

        void cancel() {
            if (cancelled) {
                System.out.println("Booking cannot be cancelled.");
                return;
            }

            if (show.hasStarted()) {
                System.out.println("Cannot cancel: the show has already started.");
                return;
            }

            show.release(seats);
            cancelled = true;

            List<String> ids = new ArrayList<>();
            for (Seat seat : seats) {
                ids.add(seat.getId());
            }

            System.out.println(
                customer.getName() + "'s booking cancelled. Seats "
                + String.join(", ", ids) + " released."
            );
        }
    }

    public static void main(String[] args) {
        Show show = new Show(
            "7 PM Show", LocalDateTime.now().plusHours(2)
        );

        show.addSeat(new RegularSeat("A1"));
        show.addSeat(new RegularSeat("A2"));
        show.addSeat(new PremiumSeat("F5"));
        show.addSeat(new ReclinerSeat("R1"));

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(
            asha, show, Arrays.asList("A1", "A2", "F5")
        );
        Booking b2 = new Booking(
            ravi, show, Arrays.asList("A2")
        );
        Booking b3 = new Booking(
            ravi, show, Arrays.asList("R1")
        );

        b1.cancel();

        new Booking(neha, show, Arrays.asList("A2"));
    }
}
