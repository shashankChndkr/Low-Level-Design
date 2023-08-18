package model;

import java.util.UUID;

public class Vehicle {

    public UUID id;
    public VehileType vehileType;

    public Vehicle(VehileType vehileType) {
        this.id = UUID.randomUUID();
        this.vehileType = vehileType;
    }
}
