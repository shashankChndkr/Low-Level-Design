package controller;

import model.MeetingDetails;
import model.Room;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * The {@code SchedulerController} class manages the scheduling and completion of meetings.
 * It interacts with the RoomController to allocate meeting rooms and check for completed meetings.
 */
public class SchedulerController {

    private RoomController roomController;
    private static SchedulerController schedulerController;

    HashMap<Room, MeetingDetails> meetingDetailsHashMap;

    /**
     * Constructs a new SchedulerController object with the given RoomController instance.
     *
     * @param roomController the RoomController instance to manage meeting rooms
     */
    private SchedulerController(RoomController roomController) {
        this.roomController = roomController;
    }

    /**
     * Returns the instance of the SchedulerController class.
     * If an instance doesn't exist, it creates a new one.
     *
     * @param roomController the RoomController instance to manage meeting rooms
     * @return the instance of SchedulerController
     */
    public static SchedulerController getInstance(RoomController roomController) {
        if (schedulerController == null) {
            synchronized (SchedulerController.class) {
                if (schedulerController == null) {
                    schedulerController = new SchedulerController(roomController);
                }
            }
        }

        return schedulerController;
    }

    /**
     * Schedules a meeting by allocating a meeting room using the RoomController.
     *
     * @param meetingDetails the details of the meeting to be scheduled
     * @return the allocated room for the meeting
     * @throws Exception if no free rooms are available
     */
    public Room scheduleMeeting(MeetingDetails meetingDetails) throws Exception {
        Room room = roomController.allocateMeeting(meetingDetails);
        return room;
    }

    /**
     * Checks for completed meetings and frees up the corresponding rooms.
     * It removes completed meetings from the occupied rooms set and adds the rooms to the free rooms set.
     */
    public void checkCompletedMeeting() {
        synchronized (this) {
            Set<Room> occupiedRooms = roomController.getOccupiedRooms();
            Set<Room> freeRooms = roomController.getFreeRooms();

            if (occupiedRooms.isEmpty()) {
                return;
            }
            Iterator<Room> iterator = occupiedRooms.iterator();
            while (iterator.hasNext()) {
                Room room = iterator.next();
                if (room.getCurrentMeetingDetails().getEnd().isBefore(LocalDateTime.now())) {
                    System.out.println(room.getName());
                    iterator.remove();
                    freeRooms.add(room);
                }
            }
        }
    }
}
