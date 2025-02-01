import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class BookingManagement {
    private List<Booking> bookings = new ArrayList<>();
    private ClassManagement classManagement;

    public BookingManagement(ClassManagement classManagement) {
        this.classManagement = classManagement;
    }

    public String bookClass(String memberName, String className, String participationDateStr) {
        try {
            LocalDate participationDate = LocalDate.parse(participationDateStr, DateTimeFormatter.ISO_DATE);
            if (participationDate.isBefore(LocalDate.now())) {
                return "Participation date must be in the future";
            }

            List<Class> availableClasses = classManagement.getClasses().stream()
                    .filter(c -> c.getName().equals(className) && c.isDateWithinRange(participationDate)) // Check date range
                    .collect(Collectors.toList());

            if (availableClasses.isEmpty()) {
                return "Class not found for the given date and time";
            }

            Class targetClass = availableClasses.get(0);

            long bookedCount = bookings.stream()
                    .filter(b -> b.getGymClass().equals(targetClass) && b.getBookingDate().equals(participationDate)) //Check for the same date
                    .count();

            if (bookedCount >= targetClass.getCapacity()) {
                return "Class is full for this date";
            }

            Booking booking = new Booking(memberName, targetClass, participationDate);
            bookings.add(booking);
            return "Booking successful";
        } catch (Exception e) {
            return "Invalid input format: " + e.getMessage();
        }
    }

    public List<Map<String, String>> searchBookings(String memberName, String startDateStr, String endDateStr) {
        List<Map<String, String>> results = new ArrayList<>();
        try {
            LocalDate startDate = startDateStr == null ? null : LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
            LocalDate endDate = endDateStr == null ? null : LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);

            for (Booking booking : bookings) {
                boolean memberMatch = memberName == null || booking.getMemberName().equals(memberName);
                boolean dateMatch = (startDate == null && endDate == null) ||
                        (startDate != null && endDate != null && booking.getBookingDate().isAfter(startDate.minusDays(1)) && booking.getBookingDate().isBefore(endDate.plusDays(1))) ||
                        (startDate != null && endDate == null && booking.getBookingDate().isAfter(startDate.minusDays(1))) ||
                        (startDate == null && endDate != null && booking.getBookingDate().isBefore(endDate.plusDays(1)));

                if (memberMatch && dateMatch) {
                    Map<String, String> bookingDetails = new HashMap<>();
                    bookingDetails.put("className", booking.getGymClass().getName());
                    bookingDetails.put("classStartTime", booking.getGymClass().getStartTime().toString());
                    bookingDetails.put("bookingDate", booking.getBookingDate().toString());
                    bookingDetails.put("member", booking.getMemberName());
                    results.add(bookingDetails);
                }
            }
        } catch (Exception e) {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("error", "Invalid date format provided");
            results.add(errorDetails);
        }
        return results;
    }
}