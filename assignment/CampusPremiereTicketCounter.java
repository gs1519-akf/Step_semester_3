import java.util.*;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() { return seatNumber; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 150.0; }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 250.0; }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 400.0; }
}

class ShowBooking {
    private String customer;
    private List<Seat> bookedSeats;
    private double totalAmount;

    public ShowBooking(String customer, List<Seat> seats) {
        this.customer = customer;
        this.bookedSeats = new ArrayList<>(seats);
        this.totalAmount = 0;
        for (Seat s : seats) {
            totalAmount += s.getPrice();
        }
    }

    public String getCustomer() { return customer; }
    public List<Seat> getBookedSeats() { return bookedSeats; }
    public double getTotalAmount() { return totalAmount; }
}

class AuditoriumShow {
    private String time;
    private Set<String> reservedSeatNumbers = new HashSet<>();

    public AuditoriumShow(String time) {
        this.time = time;
    }

    public ShowBooking book(String customer, List<Seat> seats) {
        if (seats.size() > 6) {
            System.out.println("Maximum 6 seats allowed per booking.");
            return null;
        }

        for (Seat s : seats) {
            if (reservedSeatNumbers.contains(s.getSeatNumber())) {
                System.out.printf("Seat %s is already booked for this show.%n", s.getSeatNumber());
                return null;
            }
        }

        for (Seat s : seats) {
            reservedSeatNumbers.add(s.getSeatNumber());
        }

        ShowBooking booking = new ShowBooking(customer, seats);
        List<String> seatIds = new ArrayList<>();
        for (Seat s : seats) {
            seatIds.add(s.getSeatNumber());
        }

        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                customer, String.join(", ", seatIds), booking.getTotalAmount());
        return booking;
    }

    public void cancel(ShowBooking booking) {
        if (booking == null) return;
        List<String> seatIds = new ArrayList<>();
        for (Seat s : booking.getBookedSeats()) {
            reservedSeatNumbers.remove(s.getSeatNumber());
            seatIds.add(s.getSeatNumber());
        }
        System.out.printf("%s's booking cancelled. Seats %s released.%n",
                booking.getCustomer(), String.join(", ", seatIds));
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        AuditoriumShow show = new AuditoriumShow("7 PM");

        List<Seat> ashaSeats = Arrays.asList(new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5"));
        ShowBooking ashaBooking = show.book("Asha", ashaSeats);

        show.book("Ravi", Collections.singletonList(new RegularSeat("A2")));

        ShowBooking raviBooking = show.book("Ravi", Collections.singletonList(new ReclinerSeat("R1")));

        show.cancel(ashaBooking);

        show.book("Neha", Collections.singletonList(new RegularSeat("A2")));
    }
}
