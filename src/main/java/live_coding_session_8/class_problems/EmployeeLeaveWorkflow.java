
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeLeaveWorkflow {

    interface LeavePolicy {
        boolean canRequestLeave(long days);
    }

    static class StandardLeavePolicy implements LeavePolicy {
        @Override
        public boolean canRequestLeave(long days) {
            return days > 0;
        }
    }

    static abstract class Employee {
        private final String name;
        private final LeavePolicy policy;

        Employee(String name, LeavePolicy policy) {
            this.name = name;
            this.policy = policy;
        }

        String getName() {
            return name;
        }

        boolean canRequestLeave(long days) {
            return policy.canRequestLeave(days);
        }
    }

    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String name) {
            super(name, new StandardLeavePolicy());
        }
    }

    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String name) {
            super(name, new StandardLeavePolicy());
        }
    }

    static class Contractor extends Employee {
        Contractor(String name) {
            super(name, new StandardLeavePolicy());
        }
    }

    static class LeaveRequest {
        private final Employee employee;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private Status status = Status.PENDING;

        enum Status {
            PENDING, APPROVED, REJECTED
        }

        LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
            this.employee = employee;
            this.startDate = start;
            this.endDate = end;

            long days = ChronoUnit.DAYS.between(start, end) + 1;

            if (end.isBefore(start) || !employee.canRequestLeave(days)) {
                throw new IllegalArgumentException("Invalid leave dates.");
            }

            System.out.println("Leave request submitted for "
                    + employee.getName() + " (" + formatDates()
                    + "). Status: Pending.");
        }

        private String formatDates() {
            return startDate.getMonth().getDisplayName(
                    java.time.format.TextStyle.SHORT,
                    java.util.Locale.ENGLISH)
                    + " " + startDate.getDayOfMonth() + "-"
                    + endDate.getDayOfMonth();
        }

        void review(String reviewer, boolean approve) {
            if (status != Status.PENDING) {
                System.out.println("Cannot review: request is already " + status + ".");
                return;
            }

            status = approve ? Status.APPROVED : Status.REJECTED;

            System.out.println(employee.getName() + "'s leave request ("
                    + formatDates() + ") "
                    + (approve ? "approved" : "rejected")
                    + " by " + reviewer + ". Status: "
                    + status.toString().charAt(0)
                    + status.toString().substring(1).toLowerCase() + ".");
        }

        void changeStatus(Status newStatus) {
            if (status != Status.PENDING || newStatus == Status.PENDING) {
                System.out.println("Cannot change leave request status from "
                        + titleCase(status) + " to " + titleCase(newStatus) + ".");
                return;
            }

            status = newStatus;
        }

        private String titleCase(Status value) {
            String s = value.toString().toLowerCase();
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        }
    }

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest johnRequest = new LeaveRequest(
                john, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));

        johnRequest.review("Alice", true);

        LeaveRequest janeRequest = new LeaveRequest(
                jane, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));

        janeRequest.review("Bob", false);

        johnRequest.changeStatus(LeaveRequest.Status.PENDING);
    }
}
