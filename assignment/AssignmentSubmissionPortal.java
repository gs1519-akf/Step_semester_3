abstract class Assignment {
    private String title;
    private int maxMarks;
    private int dueDayOfMonth;

    public Assignment(String title, int maxMarks, int dueDayOfMonth) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDayOfMonth = dueDayOfMonth;
    }

    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public int getDueDayOfMonth() { return dueDayOfMonth; }

    public abstract double calculateFinalMarks(int awardedMarks, int lateDays);
    public abstract int getPenaltyPercentage(int lateDays);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, int dueDayOfMonth) {
        super(title, maxMarks, dueDayOfMonth);
    }

    @Override
    public double calculateFinalMarks(int awardedMarks, int lateDays) {
        if (lateDays <= 0) return awardedMarks;
        double penaltyFactor = 1.0 - (0.10 * lateDays);
        return Math.max(0, awardedMarks * penaltyFactor);
    }

    @Override
    public int getPenaltyPercentage(int lateDays) {
        return (lateDays <= 0) ? 0 : lateDays * 10;
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, int dueDayOfMonth) {
        super(title, maxMarks, dueDayOfMonth);
    }

    @Override
    public double calculateFinalMarks(int awardedMarks, int lateDays) {
        if (lateDays <= 0) return awardedMarks;
        double penaltyFactor = 1.0 - (0.20 * lateDays);
        return Math.max(0, awardedMarks * penaltyFactor);
    }

    @Override
    public int getPenaltyPercentage(int lateDays) {
        return (lateDays <= 0) ? 0 : lateDays * 20;
    }
}

class Submission {
    private String student;
    private Assignment assignment;
    private int submitDayOfMonth;
    private String status; // Submitted, Graded
    private double finalMarks;

    public Submission(String student, Assignment assignment, int submitDayOfMonth) {
        this.student = student;
        this.assignment = assignment;
        this.submitDayOfMonth = submitDayOfMonth;
        this.status = "Submitted";

        int lateDays = Math.max(0, submitDayOfMonth - assignment.getDueDayOfMonth());
        if (lateDays == 0) {
            System.out.printf("%s's submission for '%s' received (on time). Status: %s.%n",
                    student, assignment.getTitle(), status);
        } else {
            System.out.printf("%s's submission for '%s' received (%d days late). Status: %s.%n",
                    student, assignment.getTitle(), lateDays, status);
        }
    }

    public void grade(int awardedMarks) {
        if (!"Submitted".equals(status)) {
            System.out.println("Cannot grade: submission is already graded or not submitted.");
            return;
        }
        int lateDays = Math.max(0, submitDayOfMonth - assignment.getDueDayOfMonth());
        this.finalMarks = assignment.calculateFinalMarks(awardedMarks, lateDays);
        this.status = "Graded";

        int penalty = assignment.getPenaltyPercentage(lateDays);
        if (penalty > 0) {
            System.out.printf("%s graded: %.0f/%d after %d%% late penalty. Status: %s.%n",
                    student, finalMarks, assignment.getMaxMarks(), penalty, status);
        } else {
            System.out.printf("%s graded: %d/%d. Status: %s.%n",
                    student, awardedMarks, assignment.getMaxMarks(), status);
        }
    }

    public boolean canResubmit() {
        if ("Graded".equals(status)) {
            System.out.printf("Cannot resubmit: '%s' has already been graded.%n", assignment.getTitle());
            return false;
        }
        return true;
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Assignment coding = new CodingAssignment("Linked List Lab", 50, 10);
        Assignment written = new WrittenAssignment("Design Essay", 50, 12);

        Submission ashaSub = new Submission("Asha", coding, 10);
        Submission raviSub = new Submission("Ravi", written, 14);

        ashaSub.grade(45);
        raviSub.grade(40);

        ashaSub.canResubmit();
    }
}
