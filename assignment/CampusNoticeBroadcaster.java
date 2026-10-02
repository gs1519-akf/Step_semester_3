import java.util.*;

interface NotificationChannel {
    void send(String recipient, String message);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.printf("[Email → %s] %s%n", recipient, message);
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.printf("[SMS → %s] %s%n", recipient, message);
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.printf("[App → %s] %s%n", recipient, message);
    }
}

class BroadcastStudent {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    public BroadcastStudent(String name, String department, List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.preferredChannels = channels;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<NotificationChannel> getPreferredChannels() { return preferredChannels; }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments;
    }

    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }
}

class NoticeBoard {
    private List<BroadcastStudent> students = new ArrayList<>();

    public void registerStudent(BroadcastStudent student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {
        if (notice.getTitle() == null || notice.getTitle().trim().isEmpty() ||
            notice.getTargetDepartments() == null || notice.getTargetDepartments().isEmpty()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        String depts = String.join(", ", notice.getTargetDepartments());
        System.out.printf("Notice '%s' posted to %s.%n", notice.getTitle(), depts);

        for (BroadcastStudent student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student.getName(), notice.getTitle() + ".");
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        BroadcastStudent asha = new BroadcastStudent("Asha", "CSE", Arrays.asList(new EmailChannel(), new AppChannel()));
        BroadcastStudent ravi = new BroadcastStudent("Ravi", "ECE", Collections.singletonList(new SmsChannel()));

        board.registerStudent(asha);
        board.registerStudent(ravi);

        board.postNotice(new Notice("Lab Closed Tomorrow", Collections.singletonList("CSE")));
        board.postNotice(new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE")));
        board.postNotice(new Notice("Sports Day", Collections.emptyList()));
    }
}
