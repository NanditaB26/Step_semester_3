
package live_coding_session_5.class_problems;

public class CineScreen {

    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException(
                    "Total seats must be positive");
        }

        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public boolean bookSeat() {
        if (seatsAvailable <= 0) {
            return false;
        }

        seatsAvailable--;
        return true;
    }

    public boolean cancelBooking() {
        if (seatsAvailable >= seatsTotal) {
            return false;
        }

        seatsAvailable++;
        return true;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static void main(String[] args) {
        CineScreen screen = new CineScreen(3);

        screen.bookSeat();
        screen.bookSeat();

        System.out.println(screen.getSeatsAvailable());

        screen.cancelBooking();

        System.out.println(screen.getSeatsAvailable());
    }
}
