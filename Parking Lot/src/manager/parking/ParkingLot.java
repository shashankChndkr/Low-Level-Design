package manager.parking;

import model.Vehicle;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ParkingLot {

    private ParkingLevelFactory parkingLevelFactory;
    private List<ParkingLevel> parkingLevels;
    private PropertyChangeSupport propertyChangeSupport;

    public ParkingLot(int totalLevels, int spots) {
        parkingLevelFactory = new ConcreteParkingLevelFactory();
        parkingLevels = new ArrayList<>(totalLevels);
        for (int i = 0; i < totalLevels; i++) {
            parkingLevels.add(parkingLevelFactory.createParkingLevel(spots));
        }
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeSupportListener(PropertyChangeListener pcl) {
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }

    public ParkingSpot reverseSpot(Vehicle vehicle) {
        ParkingSpot parkingSpot = null;
        for (ParkingLevel parkingLevel : parkingLevels) {
            if (parkingLevel.isAvailable()) {
                 parkingSpot = parkingLevel.occupy(vehicle);
                 break;
            }
        }
        availableSpot();
        return parkingSpot;
    }

    public boolean isAvailable() {
        for (ParkingLevel parkingLevel : parkingLevels) {
            if (parkingLevel.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void freeSpot(UUID uuid){
        for (ParkingLevel parkingLevel: parkingLevels) {
            if(parkingLevel.uuidToSpotMap.containsKey(uuid)){
                ParkingSpot parkingSpot = parkingLevel.uuidToSpotMap.get(uuid);
                parkingLevel.release(parkingSpot);
            }
        }
        availableSpot();
    }

    public int availableSpot() {
        int spots = 0;
        for (ParkingLevel parkingLevel : parkingLevels) {
            spots += parkingLevel.availableSpots();
        }
        propertyChangeSupport.firePropertyChange("Current available spot", null, spots);
        return spots;
    }
}
