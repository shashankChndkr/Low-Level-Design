package model;

import java.util.ArrayList;
import java.util.List;

public class BookingSession {

    private final int sessionId;
    private final User user;
    private final Show show;
    private final List<Seat> selectedSeats;
    private boolean paymentComplete;

    public BookingSession(int sessionId, User user, Show show) {
        this.sessionId = sessionId;
        this.user = user;
        this.show = show;
        this.selectedSeats = new ArrayList<>();
        this.paymentComplete = false;
    }

    public int getSessionId() {
        return sessionId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSelectedSeats() {
        return selectedSeats;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }

    public void addSelectedSeat(Seat seat) {
        selectedSeats.add(seat);
    }

    public void removeSelectedSeat(Seat seat) {
        selectedSeats.remove(seat);
    }

    public void completePayment() {
        paymentComplete = true;
    }
}
