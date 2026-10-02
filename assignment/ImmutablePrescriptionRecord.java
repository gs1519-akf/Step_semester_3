import java.util.Arrays;

class PrescriptionRecord {
    private final String prescriptionId;
    private final String[] medications;

    public PrescriptionRecord(String prescriptionId, String[] medications) {
        this.prescriptionId = prescriptionId;
        // Defensive copying in
        this.medications = (medications != null) ? medications.clone() : new String[0];
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    // Defensive copying out
    public String[] getMedications() {
        return medications.clone();
    }

    // Wither pattern: returns new instance
    public PrescriptionRecord withUpdatedMedication(int index, String newMed) {
        String[] updated = this.medications.clone();
        if (index >= 0 && index < updated.length) {
            updated[index] = newMed;
        }
        return new PrescriptionRecord(this.prescriptionId, updated);
    }
}

class EmergencyPrescriptionRecord extends PrescriptionRecord {
    private final int priorityLevel;

    public EmergencyPrescriptionRecord(String prescriptionId, String[] medications, int priorityLevel) {
        super(prescriptionId, medications);
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}

public class ImmutablePrescriptionRecord {

    public static String processNightlyPrescriptionAudit(PrescriptionRecord[] records) {
        if (records == null) return "0 processed | 0 null skipped | 0 emergency | 0 routine";

        int processed = 0;
        int nullSkipped = 0;
        int emergency = 0;
        int routine = 0;

        for (PrescriptionRecord r : records) {
            if (r == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (r instanceof EmergencyPrescriptionRecord) {
                emergency++;
            } else {
                routine++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               emergency + " emergency | " + routine + " routine";
    }

    public static void main(String[] args) {
        PrescriptionRecord rx = new PrescriptionRecord("RX-101", new String[]{"Paracetamol", "Amoxicillin"});
        String[] meds = rx.getMedications();
        meds[0] = "Tampered";
        System.out.println("Defensive copy verified: " + rx.getMedications()[0]);

        PrescriptionRecord updated = rx.withUpdatedMedication(1, "Ibuprofen");
        System.out.println("Original: " + Arrays.toString(rx.getMedications()));
        System.out.println("Updated: " + Arrays.toString(updated.getMedications()));

        PrescriptionRecord[] batch = {
            new EmergencyPrescriptionRecord("RX-201", new String[]{"Epinephrine"}, 1),
            null,
            new PrescriptionRecord("RX-301", new String[]{"Vitamin C"})
        };
        System.out.println("Audit: " + processNightlyPrescriptionAudit(batch));
    }
}
