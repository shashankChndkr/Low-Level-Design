package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Show {

    private final int id;
    private final String movieName;
    private final Date startTime;
    private final int duration;
    private final List<Seat> seats;

    public Show(int id, String movieName, Date startTime, int duration, int totalSeats) {
        this.id = id;
        this.movieName = movieName;
        this.startTime = startTime;
        this.duration = duration;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.getSeatStatus() == SeatStatus.AVAILABLE) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
