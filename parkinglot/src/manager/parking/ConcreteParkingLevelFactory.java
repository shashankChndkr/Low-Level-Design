package manager.parking;

public class ConcreteParkingLevelFactory extends ParkingLevelFactory {
    @Override
    public ParkingLevel createParkingLevel(int spot) {
        return new ParkingLevel(spot);
    }
}
