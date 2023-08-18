## Movie Ticket Booking System Design

Design and implement a comprehensive Movie Ticket Booking Application for a Theatre. 
The application should handle various aspects of booking movie tickets, ensuring user authentication, seat availability, 
payment processing, and session management.

### Functional Requirements

1. **Theatre and Shows**
    - The theatre has multiple screens, each capable of running shows for different movies.
    - Each show is associated with a specific movie, start time, and duration.
    - Shows are presented on a particular screen.

2. **User Authentication**
    - All users must be registered, authenticated, and logged into the application before booking tickets.

3. **Booking Flow**
    - Upon selecting a show, a UserBookingSession starts for the user.
    - Users can view available seats for the selected show.
    - Users can select a group of seats for booking.
    - A limit on the number of seats a user can book may be implemented.
    - Selected seats become TEMPORARILY_UNAVAILABLE for other users.

4. **Payment Processing**
    - Users proceed to make payments after selecting seats.
    - Payment can result in either SUCCESS or FAILURE.

5. **Booking Confirmation**
    - For successful payments, booking confirmations are generated.
    - Successful bookings lead to PERMANENTLY_UNAVAILABLE seats.
    - Users who fail to complete payment see their selected seats become available again.

6. **User Control**
    - Users can explicitly close their booking sessions before making payments.
    - Closed sessions result in seat availability restoration.

7. **Concurrency Handling**
    - Users in concurrent sessions should not interfere with each other's seat selection or availability.

8. **Overlapping Seat Selection**
    - Prevent users from selecting overlapping groups of seats during concurrent bookings.

9. **Bonus: Session Timeout**
    - Implement a configurable timeout for UserBookingSessions.
    - Uncompleted sessions expire, restoring seat availability.
    - UserBookingSession timeout: If a user selects seats but does not complete payment within the configurable timeout,
        the session is closed, and the selected seats become available again.

