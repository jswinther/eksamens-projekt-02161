package dtu.project.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimePeriod {

    private LocalDateTime startDate = null;
    private LocalDateTime endDate = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     *
     * @param startDate
     * @param endDate
     */
    public TimePeriod(String startDate, String endDate) throws DateTimeParseException {
    	setStartDate(LocalDateTime.parse(startDate, formatter));
    	setEndDate(LocalDateTime.parse(endDate, formatter));
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
    	this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Event{"
                + "startDate=" + (startDate == null ? "undefined" : startDate.format(formatter))
                + ", endDate=" + (endDate == null ? "undefined" : endDate.format(formatter))
                + '}';
    }
}
