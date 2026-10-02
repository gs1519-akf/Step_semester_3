import java.util.*;

abstract class ExamQuestion {
    protected String questionText;
    protected int points;

    public ExamQuestion(String questionText, int points) {
        this.questionText = questionText;
        this.points = points;
    }

    public int getPoints() { return points; }
    public abstract boolean evaluate(String answer);
}

class MCQQuestion extends ExamQuestion {
    private String correctAnswer;

    public MCQQuestion(String questionText, int points, String correctAnswer) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TFQuestion extends ExamQuestion {
    private String correctAnswer;

    public TFQuestion(String questionText, int points, String correctAnswer) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class ExamAttempt {
    private String student;
    private String examName;
    private Map<Integer, String> answers = new HashMap<>();
    private boolean submitted = false;

    public ExamAttempt(String student, String examName) {
        this.student = student;
        this.examName = examName;
        System.out.printf("%s started by %s.%n", examName, student);
    }

    public void recordAnswer(int questionNum, String ans) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        answers.put(questionNum, ans);
        System.out.printf("Answer recorded for Question %d.%n", questionNum);
    }

    public void submit(List<ExamQuestion> questions) {
        if (submitted) {
            System.out.println("Exam already submitted.");
            return;
        }
        submitted = true;

        int totalScore = 0;
        int maxScore = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < questions.size(); i++) {
            ExamQuestion q = questions.get(i);
            int qNum = i + 1;
            maxScore += q.getPoints();
            String ans = answers.get(qNum);
            boolean correct = (ans != null && q.evaluate(ans));

            if (correct) {
                totalScore += q.getPoints();
                result.append(String.format("Question %d: Correct (%d points)", qNum, q.getPoints()));
            } else {
                result.append(String.format("Question %d: Incorrect (0 points)", qNum));
            }
            if (i < questions.size() - 1) {
                result.append(", ");
            }
        }

        System.out.printf("%s submitted by %s. Result: %s. Total score: %d/%d.%n",
                examName, student, result.toString(), totalScore, maxScore);
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        List<ExamQuestion> questions = Arrays.asList(
            new MCQQuestion("What is the capital of France?", 5, "C"),
            new TFQuestion("Java supports multiple inheritance for classes?", 5, "False")
        );

        ExamAttempt attempt = new ExamAttempt("Student 1", "Exam A");
        attempt.recordAnswer(1, "C");
        attempt.recordAnswer(2, "True");
        attempt.submit(questions);

        attempt.recordAnswer(1, "A");
    }
}
