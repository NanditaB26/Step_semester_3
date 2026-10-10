package live_coding_session_7.class_problems.problem5;

public class Problem5Demo {
    public static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            if (note != null) {
                System.out.println(note.confirmDelivery());
            }
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");

        System.out.println(p.confirmDelivery());
        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p;

        logAll(new DeliveryNote[]{
            ref,
            new LetterNote("TRK-2")
        });
    }
}