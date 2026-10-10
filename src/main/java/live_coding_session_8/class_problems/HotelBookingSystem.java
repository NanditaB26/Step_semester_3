
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {

    // Abstract Room class
    static abstract class Room {
        private final String roomNumber;

        Room(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        String getRoomNumber() {
            return roomNumber;
        }

        abstract double calculatePrice(long nights);
    }

    // Standard Room
    static class StandardRoom extends Room {
        StandardRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        double calculatePrice(long nights) {
            return nights * 80.0;
        }
    }

    // Deluxe Room
    static class DeluxeRoom extends Room {
        DeluxeRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        double calculatePrice(long nights) {
            return nights * 120.0;
        }
    }

    // Suite Room
    static class Suite extends Room {
        Suite(String roomNumber) {
            super(roomNumber);
        }

        @Override
        double calculatePrice(long nights) {
            return nights * 200.0;
        }
    }

    // Customer class
    static class Customer {
        private final String name;

        Customer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    // Reservation class
    static class Reservation {
        private final Customer customer;
        private final Room room;
        private final LocalDate checkIn;
        private final LocalDate checkOut;
        private final LocalDate cancellationDeadline;
        private boolean cancelled = false;

        Reservation(Customer customer, Room room,
                    LocalDate checkIn, LocalDate checkOut,
                    LocalDate cancellationDeadline) {
            this.customer = customer;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.cancellationDeadline = cancellationDeadline;
        }

        boolean overlaps(LocalDate start, LocalDate end) {
            return !cancelled
                    && start.isBefore(checkOut)
                    && end.isAfter(checkIn);
        }

        boolean cancel() {
            if (cancelled) {
                System.out.println("Reservation is already cancelled.");
                return false;
            }

            if (LocalDate.now().isAfter(cancellationDeadline)) {
                System.out.println("Cancellation deadline has passed.");
                return false;
            }

            cancelled = true;

            System.out.println(
                    "Reservation for " + customer.getName() + ", "
                    + room.getClass().getSimpleName() + " "
                    + room.getRoomNumber() + " ("
                    + checkIn + " to " + checkOut
                    + ") cancelled successfully."
            );

            return true;
        }

        Room getRoom() {
            return room;
        }

        boolean isCancelled() {
            return cancelled;
        }

        LocalDate getCheckIn() {
            return checkIn;
        }

        LocalDate getCheckOut() {
            return checkOut;
        }
    }

    // Hotel class
    static class Hotel {
        private final List<Reservation> reservations =
                new ArrayList<>();

        boolean isAvailable(Room room,
                            LocalDate start,
                            LocalDate end) {
            if (start == null || end == null || !end.isAfter(start)) {
                return false;
            }

            for (Reservation reservation : reservations) {
                if (reservation.getRoom() == room
                        && reservation.overlaps(start, end)) {
                    return false;
                }
            }

            return true;
        }

        void checkAvailability(Room room,
                               LocalDate start,
                               LocalDate end) {
            String roomName = room.getClass().getSimpleName()
                    + " " + room.getRoomNumber();

            if (isAvailable(room, start, end)) {
                System.out.println(roomName + " is available from "
                        + start + " to " + end + ".");
            } else {
                System.out.println(roomName + " is not available from "
                        + start + " to " + end + ".");
            }
        }

        Reservation reserve(Customer customer,
                            Room room,
                            LocalDate start,
                            LocalDate end,
                            LocalDate cancellationDeadline) {
            if (start == null || end == null
                    || !end.isAfter(start)) {
                System.out.println("Invalid reservation dates.");
                return null;
            }

            if (!isAvailable(room, start, end)) {
                System.out.println(
                        room.getClass().getSimpleName() + " "
                        + room.getRoomNumber()
                        + " is not available from "
                        + start + " to " + end + "."
                );
                return null;
            }

            Reservation reservation = new Reservation(
                    customer, room, start, end,
                    cancellationDeadline
            );

            reservations.add(reservation);

            System.out.println(
                    "Reservation confirmed for "
                    + customer.getName() + ", "
                    + room.getClass().getSimpleName() + " "
                    + room.getRoomNumber() + " ("
                    + start + " to " + end + ")."
            );

            long nights = ChronoUnit.DAYS.between(start, end);

            System.out.printf("Price: $%.2f.%n",
                    room.calculatePrice(nights));

            return reservation;
        }
    }

    // Main method
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        Room standardRoom = new StandardRoom("101");
        Room deluxeRoom = new DeluxeRoom("201");

        // Use future dates so the sample is repeatable.
        LocalDate jan1 = LocalDate.of(2027, 1, 1);
        LocalDate jan5 = LocalDate.of(2027, 1, 5);
        LocalDate jan3 = LocalDate.of(2027, 1, 3);
        LocalDate jan7 = LocalDate.of(2027, 1, 7);

        // 1. Customer A checks availability
        hotel.checkAvailability(standardRoom, jan1, jan5);

        // 2. Customer A reserves the room
        Reservation reservationA = hotel.reserve(
                customerA,
                standardRoom,
                jan1,
                jan5,
                jan1.plusDays(1)
        );

        // 3. Customer B tries to book overlapping dates
        hotel.reserve(
                customerB,
                standardRoom,
                jan3,
                jan7,
                jan3.plusDays(1)
        );

        // 4. Cancel Customer A's reservation
        if (reservationA != null) {
            if (!reservationA.cancel()) {
                System.out.println(
                        "Customer A's reservation was not cancelled."
                );
            }
        }

        // 5. Customer C books a Deluxe Room
        hotel.reserve(
                customerC,
                deluxeRoom,
                LocalDate.of(2027, 2, 10),
                LocalDate.of(2027, 2, 12),
                LocalDate.of(2027, 2, 9)
        );
    }
}
