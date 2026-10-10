package live_coding_session_7.class_problems.problem5;

public abstract class DeliveryNote {
    public DeliveryNote() {
    }

    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        if (signature == null || signature.trim().isEmpty()) {
            throw new IllegalArgumentException("Signature cannot be blank");
        }

        return confirmDelivery() + ", signed by " + signature;
    }
}