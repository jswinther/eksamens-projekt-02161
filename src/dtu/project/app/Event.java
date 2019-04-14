package dtu.project.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	/**
	 *
	 * @param startDate
	 * @param endDate
	 */
	public Event(String startDate, String endDate) {
		this.startDate = LocalDateTime.parse(startDate, formatter);
		this.endDate = LocalDateTime.parse(endDate, formatter);
	}

	public LocalDateTime getStartDate() {return this.startDate;}
	public void setStartDate(LocalDateTime startDate) {this.startDate = startDate;}
	public LocalDateTime getEndDate() {return this.endDate;}
	public void setEndDate(LocalDateTime endDate) {this.endDate = endDate;}

	@Override
	public String toString() {
		return "Event{" +
				"startDate=" + startDate.format(formatter) +
				", endDate=" + endDate.format(formatter) +
				'}';
	}
}