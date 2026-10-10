
import java.util.*;

public class OnlineExaminationSystem {

    static class Student {
        private final String name;

        Student(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static abstract class Question {
        private final String title;
        private final int points;

        Question(String title, int points) {
            this.title = title;
            this.points = points;
        }

        String getTitle() {
            return title;
        }

        int getPoints() {
            return points;
        }

        abstract boolean evaluate(String answer);
    }

    static class MultipleChoiceQuestion extends Question {
        private final String correctOption;

        MultipleChoiceQuestion(String title, int points, String correctOption) {
            super(title, points);
            this.correctOption = correctOption;
        }

        @Override
        boolean evaluate(String answer) {
            return correctOption.equalsIgnoreCase(answer.trim());
        }
    }

    static class TrueFalseQuestion extends Question {
        private final boolean correctAnswer;

        TrueFalseQuestion(String title, int points, boolean correctAnswer) {
            super(title, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        boolean evaluate(String answer) {
            return Boolean.toString(correctAnswer).equalsIgnoreCase(answer.trim());
        }
    }

    static class ShortAnswerQuestion extends Question {
        private final String correctAnswer;

        ShortAnswerQuestion(String title, int points, String correctAnswer) {
            super(title, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer.trim());
        }
    }

    static class Examination {
        private final String title;
        private final List<Question> questions = new ArrayList<>();

        Examination(String title) {
            this.title = title;
        }

        void addQuestion(Question question) {
            questions.add(question);
        }

        String getTitle() {
            return title;
        }

        List<Question> getQuestions() {
            return Collections.unmodifiableList(questions);
        }
    }

    static class Attempt {
        private final Student student;
        private final Examination examination;
        private final Map<Question, String> answers = new LinkedHashMap<>();
        private boolean submitted = false;

        Attempt(Student student, Examination examination) {
            this.student = student;
            this.examination = examination;
            System.out.println(examination.getTitle() + " started by "
                    + student.getName() + ".");
        }

        void answer(Question question, String answer) {
            if (submitted) {
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }

            if (!examination.getQuestions().contains(question)) {
                System.out.println("Question does not belong to this examination.");
                return;
            }

            answers.put(question, answer);
            System.out.println("Answer recorded for " + question.getTitle() + ".");
        }

        void submit() {
            if (submitted) {
                System.out.println("Examination has already been submitted.");
                return;
            }

            submitted = true;
            System.out.println(examination.getTitle() + " submitted by "
                    + student.getName() + ".");

            int total = 0;
            int maximum = 0;

            for (Question question : examination.getQuestions()) {
                maximum += question.getPoints();
                String answer = answers.getOrDefault(question, "");

                if (question.evaluate(answer)) {
                    total += question.getPoints();
                    System.out.println(question.getTitle() + ": Correct ("
                            + question.getPoints() + " points).");
                } else {
                    System.out.println(question.getTitle() + ": Incorrect (0 points).");
                }
            }

            System.out.println("Total score: " + total + "/" + maximum + ".");
        }
    }

    public static void main(String[] args) {
        Student student = new Student("Student 1");
        Examination exam = new Examination("Exam A");

        Question q1 = new MultipleChoiceQuestion("Question 1", 5, "C");
        Question q2 = new TrueFalseQuestion("Question 2", 5, false);

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt = new Attempt(student, exam);
        attempt.answer(q1, "C");
        attempt.answer(q2, "True");
        attempt.submit();
        attempt.answer(q1, "A");
    }
}
