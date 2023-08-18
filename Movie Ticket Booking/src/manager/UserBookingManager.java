package manager;

import model.Seat;
import model.Show;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserBookingManager {

    private final int sessionId;
    private final User user;
    private final Show show;
    private final List<Seat> selectedSeats;
    private boolean paymentComplete;


    public UserBookingManager(int sessionId, User user, Show show) {
        this.sessionId = sessionId;
        this.user = user;
        this.show = show;
        this.selectedSeats = new ArrayList<>();
        this.paymentComplete = false;
    }


    private void markPaymentComplete() {
        this.paymentComplete = true;
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

}
