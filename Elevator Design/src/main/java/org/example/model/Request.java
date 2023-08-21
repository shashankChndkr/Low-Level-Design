package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {

    int currentFloor;
    int desiredFloor;
    Direction direction;
    Location location;

    public Request(int currentFloor, int desiredFloor, Direction direction, Location location) {
        this.currentFloor = currentFloor;
        this.desiredFloor = desiredFloor;
        this.direction = direction;
        this.location = location;
    }
}
