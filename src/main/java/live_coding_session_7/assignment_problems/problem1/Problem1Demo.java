package live_coding_session_7.assignment_problems.problem1;

public class Problem1Demo {
    public static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            if (device != null) {
                System.out.println(device.ring());
            }
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        Doorbell d = new Doorbell("Front Door");

        System.out.println(a.ring());
        System.out.println(d.ring());

        ringAll(new Ringable[]{a, d});
    }
}