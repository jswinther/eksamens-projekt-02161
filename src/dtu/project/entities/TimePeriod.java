package dtu.project.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 
 * @author Sebastian
 *	Class to specify the format of the time periods (yyy-mm-dd hh:mm).
 *	Additionally it throws the DateTimeParseException if the startDate is set
 *	at a later date/time than the endDate, or calls setter for start- and end-date
 *	if end date is after start date.
 */
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
    	LocalDateTime LDT1 = LocalDateTime.parse(startDate, formatter);
    	LocalDateTime LDT2 = LocalDateTime.parse(endDate, formatter);
    	if(LDT1.isAfter(LDT2)) {
    		throw new DateTimeParseException("End date: " + LDT1 + " must be after start date: " + LDT2, LDT1.toString(), 0);
    	} else {
    		setStartDate(LDT1);
    		setEndDate(LDT2);
    	}
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
                + "startDate=" + startDate
                + ", endDate=" + endDate
                + '}';
    }
}
