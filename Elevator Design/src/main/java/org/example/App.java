package org.example;

import org.example.model.Direction;
import org.example.model.DisplayBoard;
import org.example.model.Location;
import org.example.model.Request;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main( String[] args ) throws InterruptedException {
        Elevator elevator =  new Elevator(0);
        DisplayBoard displayBoard = new DisplayBoard();
        elevator.addPropertyChangeSupportListener(displayBoard);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Elevator started");
                elevator.run();
                System.out.println("Elevator Stopped");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();


        Request request = new Request(1, 10, Direction.UP, Location.OUTSIDE_ELEVATOR);
        Request request5 = new Request(2, 7, Direction.UP, Location.OUTSIDE_ELEVATOR);
        Request request3 = new Request(2, 8, Direction.UP, Location.INSIDE_ELEVATOR);
        elevator.processRequest(request);
        elevator.processRequest(request5);
        TimeUnit.SECONDS.sleep(5);
        elevator.processRequest(request3);
        TimeUnit.SECONDS.sleep(5);
        Request request4 = new Request(7, 4, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        Request request1 = new Request(9, 5, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        Request request2 = new Request(3, 0, Direction.DOWN, Location.INSIDE_ELEVATOR);
        elevator.processRequest(request1);
        elevator.processRequest(request2);
        TimeUnit.SECONDS.sleep(5);
        elevator.processRequest(request4);
        TimeUnit.SECONDS.sleep(5);
        Request request6 = new Request(5, 9, Direction.UP, Location.OUTSIDE_ELEVATOR);
        Request request7 = new Request(9, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        elevator.processRequest(request6);
        TimeUnit.SECONDS.sleep(5);
        elevator.processRequest(request7);


    }
}
