# Problem Statement: Meeting Scheduler

You are tasked with developing a meeting scheduler system that efficiently manages the allocation and freeing of rooms based on meeting availability. The system should meet the following requirements:

    Room Management:
    a. The system should allow the addition of new meeting rooms, each identified by a unique name.
    b. Each meeting room has a fixed capacity, typically set to accommodate 20 participants.
    c. The system should maintain a record of both free rooms and occupied rooms, keeping track of their current availability status.

    Meeting Scheduling:
    a. The system should provide a scheduling mechanism that allocates a suitable meeting room when requested.
    b. If no suitable rooms are available for scheduling a meeting, the scheduler should generate an appropriate notification or exception indicating the unavailability of rooms.

    Meeting Completion:
    a. The system should regularly monitor scheduled meetings and identify those that have already concluded.
    b. A meeting should be considered completed if its scheduled end time is before the current time.
    c. Once a meeting is completed, the corresponding room should be marked as available, and the room's status should be updated accordingly.
    

## Getting Started

To run the Meeting Scheduler Application, follow the steps below:

1. Ensure you have Java installed on your system.
2. Clone the project repository to your local machine.
3. Open the project in your preferred Java development environment.

## Usage

The application's entry point is the `Main` class, which contains the `main` method. This method demonstrates the usage of the RoomController and SchedulerController classes to schedule and manage meetings.

### Scheduling Meetings

The application performs the following steps to schedule meetings:

1. Creates an instance of the RoomController and SchedulerController classes.
2. Adds 20 meeting rooms to the RoomController using the `addMeetingRoom` method.
3. Schedules 20 meetings with incrementing end times using the `scheduleMeeting` method. Each meeting is assigned a unique MeetingDetails object with a start time and an end time.

### Checking Completed Meetings

The application continuously checks for completed meetings using a separate thread. The steps involved are as follows:

1. Creates a new thread and defines a runnable that runs indefinitely.
2. Within the runnable, calls the `checkCompletedMeeting` method of the SchedulerController in a loop to identify completed meetings.
3. Once a meeting is completed (its end time has passed), it is considered as checked and processed.
4. The completed meeting's details are printed to the console, and the meeting room is marked as free for future meetings.

## Error Handling

The application throws an `Exception` if any errors occur during the execution. Proper exception handling and error messages are recommended for robustness and better user experience.

## Dependencies

The Meeting Scheduler Application has the following dependencies:

- Java 8 or higher

Please ensure that Java 8 or a later version is installed on your system before running the application.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to modify and distribute it according to the terms of the license.

