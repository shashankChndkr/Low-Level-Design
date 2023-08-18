package manager.ticket;

import manager.parking.ParkingLot;
import manager.parking.ParkingSpot;
import model.Vehicle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.UUID;

public class ParkingTicket implements PropertyChangeListener {

    private ParkingLot parkingLot;
    private Integer currentAvailableSpots;

    public ParkingTicket(ParkingLot parkingLot) {
        this.currentAvailableSpots = parkingLot.availableSpot();
        this.parkingLot = parkingLot;
    }

    public UUID getTicket(Vehicle vehicle) {
        if (parkingLot.isAvailable()) {
            ParkingSpot parkingSpot = parkingLot.reverseSpot(vehicle);
            return parkingSpot.getId();
        }
        return null;
    }

    public void markExit(UUID uuid){
        parkingLot.freeSpot(uuid);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.currentAvailableSpots = (Integer) evt.getNewValue();
    }

    public Integer getCurrentAvailableSpots() {
        return currentAvailableSpots;
    }
}
