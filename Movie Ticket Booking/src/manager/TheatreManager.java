package manager;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TheatreManager {
    private Map<String, Theatre> theatres;

    public TheatreManager() {
        theatres = new HashMap<>();
    }

    private static void unlockSeats(List<Seat> selectedSeats) {
        for (Seat selectedSeat : selectedSeats) {
            selectedSeat.unlock();
        }
    }

    private static void lockSeats(List<Seat> selectedSeats) {
        for (Seat selectedSeat : selectedSeats) {
            selectedSeat.lock();
        }
    }

    public void addTheatre(String theatreName, Theatre theatre) {
        theatres.put(theatreName, theatre);
    }

    public Theatre getTheatre(String theatreName) {
        return theatres.get(theatreName);
    }

    public boolean bookSeats(User user, Show show, List<Seat> selectedSeats) {
        List<Seat> overlappingSeats = getOverlappingSeats(selectedSeats);
        if (!overlappingSeats.isEmpty()) {
            return bookSeatWithOverlap(selectedSeats);
        } else {
            return bookTicketWithoutOverlap(selectedSeats);
        }
    }

    private boolean bookTicketWithoutOverlap(List<Seat> selectedSeats) {
        try {
            lockSeats(selectedSeats);
            markSeatStatus(selectedSeats, SeatStatus.TEMPORARILY_UNAVAILABLE);
            boolean paymentSuccess = true;
            if (paymentSuccess) {
                markSeatStatus(selectedSeats, SeatStatus.PERMANENTLY_UNAVAILABLE);
                return true;
            } else {
                releaseSeats(selectedSeats);
                return false;
            }
        } finally {
            unlockSeats(selectedSeats);
        }
    }

    private boolean bookSeatWithOverlap(List<Seat> selectedSeats) {
        List<Seat> overlappingSeats;
        Lock bookingLock = new ReentrantLock();
        bookingLock.lock();
        try {
            overlappingSeats = getOverlappingSeats(selectedSeats);
            if (!overlappingSeats.isEmpty()) {
                return false;
            }
            lockSeats(selectedSeats);
            markSeatStatus(selectedSeats, SeatStatus.TEMPORARILY_UNAVAILABLE);
            boolean paymentSuccess = true;
            if (paymentSuccess) {
                markSeatStatus(selectedSeats, SeatStatus.PERMANENTLY_UNAVAILABLE);
                return true;
            } else {
                releaseSeats(selectedSeats);
                return false;
            }
        } finally {
            unlockSeats(selectedSeats);
            bookingLock.unlock();
        }
    }

    public void markSeatStatus(List<Seat> seats, SeatStatus seatStatus){
        for (Seat seat: seats) {
            seat.setSeatStatus(seatStatus);
        }
    }

    private List<Seat> getOverlappingSeats(List<Seat> selectedSeats) {
        List<Seat> overlappingSeats = new ArrayList<>();
        for (Seat selectedSeat : selectedSeats) {
            if (selectedSeat.getSeatStatus() != SeatStatus.AVAILABLE) {
                overlappingSeats.add(selectedSeat);
            }
        }
        return overlappingSeats;
    }

    private void releaseSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            seat.setSeatStatus(SeatStatus.AVAILABLE);
        }
    }

}
