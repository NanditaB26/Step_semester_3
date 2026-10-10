
import java.util.*;

public class StudentClub {

    static class Student {
        String rollNumber;
        String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Student)) return false;

            Student other = (Student) obj;
            return rollNumber.equals(other.rollNumber);
        }

        @Override
        public int hashCode() {
            return rollNumber.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new LinkedHashSet<>();
        List<String> addResults = new ArrayList<>();
        List<String> containsResults = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+", 3);
            String operation = p[0];
            String roll = p[1];
            String name = p.length > 2 ? p[2] : "";

            Student student = new Student(roll, name);

            if (operation.equals("ADD")) {
                if (members.add(student)) {
                    addResults.add("Added");
                } else {
                    addResults.add("duplicate rejected");
                }
            } else if (operation.equals("CONTAINS")) {
                containsResults.add(
                        "contains: " + members.contains(student));
            }
        }

        for (String result : addResults) {
            System.out.println(result);
        }

        System.out.println("member count " + members.size());

        for (String result : containsResults) {
            System.out.println(result);
        }

        sc.close();
    }
}
