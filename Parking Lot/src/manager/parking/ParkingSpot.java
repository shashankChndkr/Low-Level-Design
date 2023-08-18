package manager.parking;

import model.Vehicle;
import model.VehileType;

import java.util.Set;
import java.util.UUID;

public class ParkingSpot {

    private boolean isOccupied;
    private UUID id;
    private Set<VehileType> allowedType;

    public ParkingSpot(Set<VehileType> allowedType) {
        this.allowedType = allowedType;
        this.id = UUID.randomUUID();
        this.isOccupied = false;
    }

    public boolean isAvailable() {
        return isOccupied;
    }

    public boolean isAllowed(Vehicle vehicle) {
        return allowedType.contains(vehicle.vehileType);
    }

    public void occupy(Vehicle vehicle) throws RuntimeException {
        if(!isAllowed(vehicle)){
            throw new RuntimeException("Vehicle type not allowed");
        }
        if (this.isOccupied) {
            throw new RuntimeException("Spot already occupied");
        }
        this.isOccupied = true;
    }

    public void release() throws RuntimeException {
        if (!this.isOccupied) {
            throw new RuntimeException("Spot is already free");
        }
        this.isOccupied = false;
    }

    public UUID getId() {
        return id;
    }
}
