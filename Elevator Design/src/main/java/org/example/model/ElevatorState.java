package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ElevatorState {

    public int currentFloor;
    public Direction direction;

}
