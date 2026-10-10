
package live_coding_session_5.class_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingReceipt {

    private final String bookingId;
    private final List<String> seats;

    public BookingReceipt(String bookingId, List<String> seats) {
        this.bookingId = bookingId;
        this.seats = new ArrayList<>(seats);
    }

    public String getBookingId() {
        return bookingId;
    }

    public List<String> getSeats() {
        return new ArrayList<>(seats);
    }

    public BookingReceipt withUpdatedSeat(String oldSeat, String newSeat) {
        List<String> updatedSeats = new ArrayList<>(seats);

        int index = updatedSeats.indexOf(oldSeat);

        if (index >= 0) {
            updatedSeats.set(index, newSeat);
        }

        return new BookingReceipt(bookingId, updatedSeats);
    }

    public static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (BookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " + nullSkipped
                + " null skipped | " + group + " group | "
                + individual + " individual";
    }

    public static void main(String[] args) {
        BookingReceipt receipt = new BookingReceipt(
                "B001", Arrays.asList("A1", "A2"));

        System.out.println(receipt.getSeats());

        BookingReceipt updated =
                receipt.withUpdatedSeat("A2", "A3");

        System.out.println(receipt.getSeats());
        System.out.println(updated.getSeats());

        BookingReceipt[] receipts = {
            receipt,
            new GroupBookingReceipt(
                    "B002", Arrays.asList("B1", "B2")),
            null
        };

        System.out.println(processNightlySettlement(receipts));
    }
}

class GroupBookingReceipt extends BookingReceipt {

    public GroupBookingReceipt(String bookingId, List<String> seats) {
        super(bookingId, seats);
    }
}
