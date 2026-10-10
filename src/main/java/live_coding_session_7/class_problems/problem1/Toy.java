package live_coding_session_7.class_problems.problem1;

public abstract class Toy {
    private static int counter = 1000;
    private final String toyId;

    public Toy() {
        toyId = "TOY-" + (++counter);
    }

    public abstract String makeSound();

    public String getToyId() {
        return toyId;
    }
}