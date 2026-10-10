
package live_coding_session_5.class_problems;

public class MovieTicket {

    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(String seatNumber, String screenId,
                       double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    public static void main(String[] args) {
        MovieTicket ticket = new MovieTicket(
                "A1", "SCREEN-01", 250.0, "Interstellar");

        System.out.println(ticket.screenId);
        System.out.println(ticket.ticketPrice);
        System.out.println(ticket.movieTitle);
    }
}
