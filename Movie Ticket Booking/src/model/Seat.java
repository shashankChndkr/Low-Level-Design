package model;


import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seat {

    private final int seatNumber;
    private SeatStatus seatStatus;
    private Lock seatLock;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.seatStatus = SeatStatus.AVAILABLE;
        this.seatLock = new ReentrantLock();
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Lock getSeatLock() {
        return seatLock;
    }

    public void lock() {
        seatLock.lock();
    }

    public void unlock() {
        seatLock.unlock();
    }

}
