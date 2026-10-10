
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AssignmentSubmissionPortal {

    static abstract class Assignment {
        private final String title;
        private final int maxMarks;
        private final LocalDate dueDate;

        Assignment(String title, int maxMarks, LocalDate dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        String getTitle() {
            return title;
        }

        int getMaxMarks() {
            return maxMarks;
        }

        LocalDate getDueDate() {
            return dueDate;
        }

        abstract double getDailyPenalty();

        double calculateFinalMarks(int awardedMarks, long lateDays) {
            double penalty = lateDays * getDailyPenalty();
            return Math.max(0, Math.min(awardedMarks, maxMarks) *
                               (1 - penalty));
        }
    }

    static class CodingAssignment extends Assignment {
        CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
            super(title, maxMarks, dueDate);
        }

        @Override
        double getDailyPenalty() {
            return 0.10;
        }
    }

    static class WrittenAssignment extends Assignment {
        WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
            super(title, maxMarks, dueDate);
        }

        @Override
        double getDailyPenalty() {
            return 0.20;
        }
    }

    static class Student {
        private final String name;

        Student(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    enum Status {
        SUBMITTED, GRADED
    }

    static class Submission {
        private final Student student;
        private final Assignment assignment;
        private final LocalDate submissionDate;
        private Status status = Status.SUBMITTED;
        private double finalMarks;

        Submission(Student student, Assignment assignment,
                   LocalDate submissionDate) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;

            long lateDays = Math.max(0, ChronoUnit.DAYS.between(
                assignment.getDueDate(), submissionDate
            ));

            System.out.printf(
                "%s's submission for '%s' received (%s). Status: Submitted.%n",
                student.getName(), assignment.getTitle(),
                lateDays == 0 ? "on time" : lateDays + " days late"
            );
        }

        void grade(int awardedMarks) {
            if (status == Status.GRADED) {
                System.out.println("Cannot grade: submission is already graded.");
                return;
            }

            if (awardedMarks < 0 || awardedMarks > assignment.getMaxMarks()) {
                System.out.println("Invalid marks awarded.");
                return;
            }

            long lateDays = Math.max(0, ChronoUnit.DAYS.between(
                assignment.getDueDate(), submissionDate
            ));

            finalMarks = assignment.calculateFinalMarks(awardedMarks, lateDays);
            status = Status.GRADED;

            if (lateDays > 0) {
                double penalty = lateDays * assignment.getDailyPenalty() * 100;
                System.out.printf(
                    "%s graded: %.0f/%d after %.0f%% late penalty. Status: Graded.%n",
                    student.getName(), finalMarks,
                    assignment.getMaxMarks(), penalty
                );
            } else {
                System.out.printf(
                    "%s graded: %.0f/%d. Status: Graded.%n",
                    student.getName(), finalMarks, assignment.getMaxMarks()
                );
            }
        }

        void resubmit() {
            if (status == Status.GRADED) {
                System.out.println(
                    "Cannot resubmit: '" + assignment.getTitle()
                    + "' has already been graded."
                );
            } else {
                System.out.println("Resubmission accepted.");
            }
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
            "Linked List Lab", 50, LocalDate.of(2026, 3, 10)
        );
        Assignment written = new WrittenAssignment(
            "Design Essay", 50, LocalDate.of(2026, 3, 12)
        );

        Submission s1 = new Submission(
            asha, coding, LocalDate.of(2026, 3, 10)
        );
        Submission s2 = new Submission(
            ravi, written, LocalDate.of(2026, 3, 14)
        );

        s1.grade(45);
        s2.grade(40);
        s1.resubmit();
    }
}
