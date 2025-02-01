import java.time.LocalDate;

class Booking {
    private String memberName;
    private Class gymClass;
    private LocalDate bookingDate;

    public Booking(String memberName, Class gymClass, LocalDate bookingDate) {
        this.memberName = memberName;
        this.gymClass = gymClass;
        this.bookingDate = bookingDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public Class getGymClass() {
        return gymClass;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }
}