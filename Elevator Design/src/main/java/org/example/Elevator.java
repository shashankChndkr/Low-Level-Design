package org.example;

import org.example.model.Direction;
import org.example.model.ElevatorState;
import org.example.model.Location;
import org.example.model.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.PriorityQueue;

public class Elevator extends ElevatorState {

    PriorityQueue<Request> upQ;
    PriorityQueue<Request> downQ;
    private PropertyChangeSupport propertyChangeSupport;

    private static final String DIRECTION = "direction";

    private static final String CURRENT_FLOOR = "currentFloor";

    public Elevator(int currentFloor){
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        upQ = new PriorityQueue<>((a,b) -> a.getDesiredFloor() - b.getDesiredFloor());
        downQ = new PriorityQueue<>((a,b) -> -(a.getDesiredFloor() - b.getDesiredFloor()) );
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeSupportListener(PropertyChangeListener pcl) {
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }


    public void processRequest(Request request){
        if(request.getLocation() == Location.OUTSIDE_ELEVATOR) {
            if (request.getDirection() == Direction.UP) {
                Request stopRequest = new Request(request.getCurrentFloor(),
                        request.getCurrentFloor(), Direction.UP, Location.OUTSIDE_ELEVATOR);
                upQ.add(stopRequest);
            } else {
                Request stopRequest = new Request(request.getCurrentFloor(),
                        request.getCurrentFloor(), Direction.DOWN, Location.OUTSIDE_ELEVATOR);
                downQ.add(stopRequest);
            }
        }
        if(request.getDirection() == Direction.UP){
            upQ.add(request);
        }else {
            downQ.add(request);
        }
    }

    public void processUpQ(){
        if(!upQ.isEmpty()){
            propertyChangeSupport.firePropertyChange(DIRECTION, this.direction , Direction.UP);
        }
        while (!upQ.isEmpty()){
            Request request = upQ.poll();
            this.currentFloor = request.getDesiredFloor();
            propertyChangeSupport.firePropertyChange(CURRENT_FLOOR, null, this.currentFloor);
        }
        if(!downQ.isEmpty()){
            propertyChangeSupport.firePropertyChange(DIRECTION, this.direction , Direction.DOWN);
            this.direction = Direction.DOWN;
            processDownQ();
        }else {
            propertyChangeSupport.firePropertyChange(DIRECTION, this.direction , Direction.IDLE);
            this.direction = Direction.IDLE;
        }
    }

    public void processDownQ(){
        if (!downQ.isEmpty()){
            propertyChangeSupport.firePropertyChange(DIRECTION, this.direction , Direction.DOWN);
        }
        while (!downQ.isEmpty()){
            Request request = downQ.poll();
            this.currentFloor = request.getDesiredFloor();
            propertyChangeSupport.firePropertyChange(CURRENT_FLOOR, null, this.currentFloor);
        }
        if(!upQ.isEmpty()){
            propertyChangeSupport.firePropertyChange(DIRECTION, this.direction , Direction.UP);
            this.direction = Direction.UP;
            processUpQ();
        }else {
            propertyChangeSupport.firePropertyChange(DIRECTION, this.direction , Direction.IDLE);
            this.direction = Direction.IDLE;
        }
    }

    public void processRequest(){
        if(this.direction == Direction.UP || this.direction == Direction.IDLE){
            processUpQ();
        }else{
            processDownQ();
        }
    }

    public void run(){
        while (true){
            if(this.direction == Direction.IDLE)
                processRequest();
        }
    }
}
