public class PatientPortalProfileBean {

    private String patientName;
    private boolean insuranceActive;
    private String securityPin; // Write-only security credential

    public PatientPortalProfileBean() {
    }

    public PatientPortalProfileBean(String patientName) {
        this();
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public boolean isInsuranceActive() {
        return insuranceActive;
    }

    public void setInsuranceActive(boolean insuranceActive) {
        this.insuranceActive = insuranceActive;
    }

    // Write-only property: NO matching getter exists
    public void setSecurityPin(String securityPin) {
        this.securityPin = securityPin;
    }

    public static void main(String[] args) {
        PatientPortalProfileBean p = new PatientPortalProfileBean("John Doe");
        System.out.println("Patient Name: " + p.getPatientName());
        p.setInsuranceActive(true);
        System.out.println("Insurance Active: " + p.isInsuranceActive());
        p.setSecurityPin("8492");
        System.out.println("(Security PIN set safely - write-only property)");
    }
}
