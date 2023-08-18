import manager.parking.ParkingLot;
import manager.ticket.ParkingTicket;
import model.Vehicle;
import model.VehileType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Map<UUID, UUID> spotToPayment = new HashMap<UUID, UUID>();
        ParkingLot parkingLot = new ParkingLot(5, 5);
        ParkingTicket parkingTicket = new ParkingTicket(parkingLot);
        parkingLot.addPropertyChangeSupportListener(parkingTicket);
        Vehicle vehicle = new Vehicle(VehileType.SEDAN);
        System.out.println(parkingTicket.getCurrentAvailableSpots());
        System.out.println(parkingTicket.getTicket(vehicle));
        System.out.println(parkingTicket.getCurrentAvailableSpots());
        System.out.println(parkingTicket.getTicket(vehicle));
        System.out.println(parkingTicket.getCurrentAvailableSpots());
        System.out.println(parkingTicket.getTicket(vehicle));
        System.out.println(parkingTicket.getCurrentAvailableSpots());

    }
}