package org.example.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayBoard extends ElevatorState implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()){
            case "direction": {
                this.direction = (Direction) evt.getNewValue();
                System.out.println("Current direction " + this.direction);
                break;
            }
            case "currentFloor":{
                this.currentFloor = (int) evt.getNewValue();
                System.out.println("Current Floor "+ this.currentFloor);
                break;
            }
        }

    }
}
