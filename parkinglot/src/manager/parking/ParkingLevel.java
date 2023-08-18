package manager.parking;

import model.Vehicle;
import model.VehileType;

import java.util.*;

public class ParkingLevel implements IDisplayBoard {

    HashMap<UUID, ParkingSpot> uuidToSpotMap;
    private Set<ParkingSpot> availableSpots;

    private UUID id;

    private Set<ParkingSpot> occupiedSpots;
    private Integer totalSpots;

    public ParkingLevel(int totalSpots) {
        this.id = UUID.randomUUID();
        availableSpots = new HashSet<>(totalSpots);
        occupiedSpots = new HashSet<>(totalSpots);
        uuidToSpotMap = new HashMap<>();
        this.totalSpots = totalSpots;
        for (int i = 0; i < totalSpots; i++) {
            ParkingSpot parkingSpot = new ParkingSpot(getDefaultVehicleAllowed());
            availableSpots.add(parkingSpot);
            uuidToSpotMap.put(parkingSpot.getId(), parkingSpot);
        }
    }

    private static Set<VehileType> getDefaultVehicleAllowed() {
        Set<VehileType> allowedType = new HashSet<>();
        allowedType.add(VehileType.EV);
        allowedType.add(VehileType.COMPACT);
        allowedType.add(VehileType.SEDAN);
        allowedType.add(VehileType.SUV);
        return allowedType;
    }

    public boolean isAvailable() {
        return !availableSpots.isEmpty();
    }

    public ParkingSpot occupy(Vehicle vehicle) throws RuntimeException {
        if (!isAvailable()) {
            throw new RuntimeException("No spot is available");
        }
        Iterator<ParkingSpot> it = availableSpots.iterator();
        ParkingSpot parkingSpot = null;
        while (it.hasNext()) {
            parkingSpot = (ParkingSpot) it.next();
            if (parkingSpot.isAllowed(vehicle)) {
                break;
            }
        }
        if (parkingSpot == null) return null;
        occupiedSpots.add(parkingSpot);
        parkingSpot.occupy(vehicle);
        availableSpots.remove(parkingSpot);
        return parkingSpot;
    }

    public void release(ParkingSpot parkingSpot) throws RuntimeException {
        if (!occupiedSpots.contains(parkingSpot)) {
            throw new RuntimeException("Spot not found");
        }
        occupiedSpots.remove(parkingSpot);
        parkingSpot.release();
        availableSpots.add(parkingSpot);
    }

    @Override
    public void displayBoard() {
        for (ParkingSpot parkingSpot : occupiedSpots) {
            System.out.println(parkingSpot.getId());
        }

    }

    @Override
    public int availableSpots() {
        return availableSpots.size();
    }
}
