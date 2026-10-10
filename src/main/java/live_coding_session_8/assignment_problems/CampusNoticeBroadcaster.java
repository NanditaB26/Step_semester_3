
import java.util.*;

public class CampusNoticeBroadcaster {

    interface NotificationChannel {
        void send(String studentName, String title);
    }

    static class EmailChannel implements NotificationChannel {
        public void send(String studentName, String title) {
            System.out.println("[Email → " + studentName + "] " + title);
        }
    }

    static class SmsChannel implements NotificationChannel {
        public void send(String studentName, String title) {
            System.out.println("[SMS → " + studentName + "] " + title);
        }
    }

    static class AppChannel implements NotificationChannel {
        public void send(String studentName, String title) {
            System.out.println("[App → " + studentName + "] " + title);
        }
    }

    static class Student {
        private final String name;
        private final String department;
        private final List<NotificationChannel> channels = new ArrayList<>();

        Student(String name, String department) {
            this.name = name;
            this.department = department;
        }

        void addChannel(NotificationChannel channel) {
            channels.add(channel);
        }

        String getName() {
            return name;
        }

        String getDepartment() {
            return department;
        }

        List<NotificationChannel> getChannels() {
            return Collections.unmodifiableList(channels);
        }
    }

    static class Notice {
        private final String title;
        private final Set<String> departments;

        Notice(String title, Set<String> departments) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Notice title cannot be empty."
                );
            }

            if (departments == null || departments.isEmpty()) {
                throw new IllegalArgumentException(
                    "At least one target department is required."
                );
            }

            this.title = title;
            this.departments = new LinkedHashSet<>(departments);
        }

        String getTitle() {
            return title;
        }

        Set<String> getDepartments() {
            return Collections.unmodifiableSet(departments);
        }
    }

    static class NoticeBoard {
        private final List<Student> students = new ArrayList<>();

        void registerStudent(Student student) {
            students.add(student);
        }

        void postNotice(String title, String... departments) {
            Set<String> targets = new LinkedHashSet<>(
                Arrays.asList(departments)
            );

            Notice notice;

            try {
                notice = new Notice(title, targets);
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot post notice: " + e.getMessage());
                return;
            }

            System.out.println(
                "Notice '" + notice.getTitle() + "' posted to "
                + String.join(", ", notice.getDepartments()) + "."
            );

            for (Student student : students) {
                if (notice.getDepartments().contains(student.getDepartment())) {
                    for (NotificationChannel channel : student.getChannels()) {
                        channel.send(student.getName(), notice.getTitle());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        board.registerStudent(asha);
        board.registerStudent(ravi);

        board.postNotice("Lab Closed Tomorrow", "CSE");
        board.postNotice("Fee Deadline Extended", "CSE", "ECE");
        board.postNotice("Sports Day");
    }
}
