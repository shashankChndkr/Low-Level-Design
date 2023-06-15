package model;

import util.RoomStatus;

import java.util.UUID;

/**
 * The {@code Room} class represents a meeting room.
 * It contains information such as room ID, name, status, and current meeting details.
 */
public class Room {

    private String id;
    private String name;
    private RoomStatus status;
    private MeetingDetails currentMeetingDetails;

    /**
     * Constructs a new Room object with the specified name.
     * The room ID is generated using a random UUID.
     * The initial status is set to FREE and the current meeting details are set to null.
     *
     * @param name the name of the room
     */
    public Room(String name) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.status = RoomStatus.FREE;
        this.currentMeetingDetails = null;
    }

    /**
     * Returns the ID of the room.
     *
     * @return the ID of the room
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the room.
     *
     * @param id the ID of the room
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the room.
     *
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the room.
     *
     * @param name the name of the room
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the status of the room.
     *
     * @return the status of the room
     */
    public RoomStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the room.
     *
     * @param status the status of the room
     */
    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    /**
     * Returns the details of the current meeting in the room.
     *
     * @return the details of the current meeting in the room
     */
    public MeetingDetails getCurrentMeetingDetails() {
        return currentMeetingDetails;
    }

    /**
     * Sets the details of the current meeting in the room.
     *
     * @param currentMeetingDetails the details of the current meeting in the room
     */
    public void setCurrentMeetingDetails(MeetingDetails currentMeetingDetails) {
        this.currentMeetingDetails = currentMeetingDetails;
    }
}
