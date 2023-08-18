package manager;

import model.Show;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookingManager {

    private final Map<Integer, Show> shows;
    private final Map<Integer, UserBookingManager> sessions;
    private final Lock lock = new ReentrantLock();

    public BookingManager() {
        shows = new HashMap<>();
        sessions = new HashMap<>();
    }

    public void addShow(Show show) {
        shows.put(show.getId(), show);
    }

    public Show getShow(int showId) {
        return shows.get(showId);
    }

    public UserBookingManager createBookingSession(int sessionId, User user, Show show) {
        UserBookingManager session = new UserBookingManager(sessionId, user, show);
        sessions.put(sessionId, session);
        return session;
    }

    public UserBookingManager getSession(int sessionId) {
        return sessions.get(sessionId);
    }

    public void removeSession(int sessionId) {
        sessions.remove(sessionId);
    }
}
