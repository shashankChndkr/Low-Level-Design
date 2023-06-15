package model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The {@code MeetingDetails} class represents the details of a meeting.
 * It contains information such as meeting ID, start time, and end time.
 */
public class MeetingDetails {

    private String id;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new MeetingDetails object with the specified start and end times.
     * The meeting ID is generated using a random UUID.
     *
     * @param start the start time of the meeting
     * @param end   the end time of the meeting
     */
    public MeetingDetails(LocalDateTime start, LocalDateTime end) {
        this.id = String.valueOf(UUID.randomUUID());
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the ID of the meeting.
     *
     * @return the ID of the meeting
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the meeting.
     *
     * @param id the ID of the meeting
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the start time of the meeting.
     *
     * @return the start time of the meeting
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets the start time of the meeting.
     *
     * @param start the start time of the meeting
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Returns the end time of the meeting.
     *
     * @return the end time of the meeting
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets the end time of the meeting.
     *
     * @param end the end time of the meeting
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
