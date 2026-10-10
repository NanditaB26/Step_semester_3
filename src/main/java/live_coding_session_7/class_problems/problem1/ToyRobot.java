package live_coding_session_7.class_problems.problem1;

public class ToyRobot extends Toy {
    private final String name;

    public ToyRobot(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Toy name cannot be blank");
        }
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}