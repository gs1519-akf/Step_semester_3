class LeaveRequest {
    private String employeeName;
    private String dateRange;
    private String status; // Pending, Approved, Rejected

    public LeaveRequest(String employeeName, String dateRange) {
        this.employeeName = employeeName;
        this.dateRange = dateRange;
        this.status = "Pending";
        System.out.printf("Leave request submitted for %s (%s). Status: %s.%n",
                employeeName, dateRange, status);
    }

    public void approve() {
        this.status = "Approved";
        System.out.printf("%s's leave request (%s) approved. Status: %s.%n",
                employeeName, dateRange, status);
    }

    public void reject() {
        this.status = "Rejected";
        System.out.printf("%s's leave request (%s) rejected. Status: %s.%n",
                employeeName, dateRange, status);
    }

    public void changeStatus(String newStatus) {
        if (("Approved".equals(status) || "Rejected".equals(status)) && "Pending".equals(newStatus)) {
            System.out.printf("Cannot change leave request status from %s to %s.%n", status, newStatus);
            return;
        }
        this.status = newStatus;
    }
}

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        LeaveRequest johnReq = new LeaveRequest("John", "Jan 1-5");
        johnReq.approve();

        LeaveRequest janeReq = new LeaveRequest("Jane", "Feb 10-11");
        janeReq.reject();

        johnReq.changeStatus("Pending");
    }
}
