import controller.RoomController;
import controller.SchedulerController;
import model.MeetingDetails;

import java.time.LocalDateTime;

/**
 * The {@code Main} class is the entry point of the application.
 * It demonstrates the usage of the RoomController and SchedulerController classes to schedule and manage meetings.
 */
public class Main {

    /**
     * The main method of the application.
     *
     * @param args the command line arguments
     * @throws Exception if an error occurs during the execution
     */
    public static void main(String[] args) throws Exception {
        // Create an instance of RoomController and SchedulerController
        RoomController roomController = RoomController.getInstance();
        SchedulerController schedulerController = SchedulerController.getInstance(roomController);

        // Add 20 meeting rooms to the roomController
        for (int i = 0; i < 20; i++) {
            roomController.addMeetingRoom("Room-" + i);
        }

        // Schedule 20 meetings with incrementing end times
        LocalDateTime localDateTime = LocalDateTime.now();
        for (int i = 0; i < 20; i++) {
            MeetingDetails meetingDetails = new MeetingDetails(localDateTime, localDateTime.plusSeconds(i + 25));
            schedulerController.scheduleMeeting(meetingDetails);
        }

        // Create a new thread to continuously check for completed meetings
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    schedulerController.checkCompletedMeeting();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
