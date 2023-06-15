package controller;

import model.MeetingDetails;
import model.Room;
import util.RoomStatus;

import java.util.HashSet;
import java.util.Iterator;

/**
 * The {@code RoomController} class manages the allocation and availability of meeting rooms.
 * It keeps track of free and occupied rooms and provides methods to add meeting rooms,
 * allocate meetings to rooms, and retrieve the status of rooms.
 */
public class RoomController {

    private final static int ROOM_CAPACITY = 20;
    private HashSet<Room> freeRooms;
    private HashSet<Room> occupiedRooms;
    private static RoomController roomController;

    /**
     * Constructs a new RoomController object.
     * Initializes the sets for free and occupied rooms.
     */
    private RoomController() {
        freeRooms = new HashSet<>(ROOM_CAPACITY);
        occupiedRooms = new HashSet<>(ROOM_CAPACITY);
    }

    /**
     * Returns the instance of the RoomController class.
     * If an instance doesn't exist, it creates a new one.
     *
     * @return the instance of RoomController
     */
    public static RoomController getInstance() {
        if (roomController == null) {
            synchronized (RoomController.class) {
                if (roomController == null) {
                    roomController = new RoomController();
                }
            }
        }
        return roomController;
    }

    /**
     * Adds a new meeting room with the specified name to the set of free rooms.
     *
     * @param name the name of the meeting room
     * @return the newly added room
     * @throws Exception if the room capacity is full
     */
    public Room addMeetingRoom(String name) throws Exception {
        Room room = new Room(name);
        if (freeRooms.size() == ROOM_CAPACITY) {
            throw new Exception("Room capacity full");
        }
        this.freeRooms.add(room);
        return room;
    }

    /**
     * Retrieves a free meeting room from the set of free rooms.
     *
     * @return a free meeting room, or null if no rooms are available
     */
    private Room getFreeMeetingRoom() {
        if (freeRooms.isEmpty()) {
            return null;
        }
        Iterator<Room> iterator = freeRooms.iterator();
        return iterator.next();
    }

    /**
     * Returns the set of occupied rooms.
     *
     * @return the set of occupied rooms
     */
    public HashSet<Room> getOccupiedRooms() {
        return occupiedRooms;
    }

    public HashSet<Room> getFreeRooms() {
        return freeRooms;
    }

    /**
     * Allocates a meeting to a free room by removing it from the set of free rooms,
     * updating its status, assigning the meeting details, and adding it to the set of occupied rooms.
     *
     * @param meetingDetails the details of the meeting to be allocated
     * @return the allocated room
     * @throws Exception if no free rooms are available
     */
    public Room allocateMeeting(MeetingDetails meetingDetails) throws Exception {
        Room room = getFreeMeetingRoom();
        if (room == null) {
            throw new Exception("No free room available");
        }

        freeRooms.remove(room);
        room.setStatus(RoomStatus.OCCUPIED);
        room.setCurrentMeetingDetails(meetingDetails);
        occupiedRooms.add(room);
        return room;
    }
}
