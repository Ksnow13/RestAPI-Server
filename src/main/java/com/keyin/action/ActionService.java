package com.keyin.action;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftService;
import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class ActionService {

    private Stack<Action> actionStack = new Stack<>();

    private Stack<Action> redoActionStack = new Stack<>();

    @Autowired
    public AircraftService aircraftService; // Autowired to get the data from AircraftService

    public Stack<Action> getActionStack() {

        return actionStack;
    }

    public void addAction(String objectName, String operation, Map<String, Object> theObject) {
        Action action = new Action(objectName, operation, theObject);
        actionStack.push(action);
        System.out.println("the action has been added to the stack " + actionStack.peek().getObjectName() + " " + actionStack.peek().getOperation() + " object id: " + actionStack.peek().getParameters().get("id"));
    }

    public void undoAction() {

        // works somewhat correctly, doesn't like deleting last object in stack
        // map it out on paper to understand the undo method better

        // maybe use a switch statement instead of if's


        if(actionStack.size() > 0) {
            if (actionStack.peek().getOperation() == "create") {
                if (actionStack.peek().getObjectName() == "aircraft") {
                    aircraftService.deleteAircraftById((Integer) actionStack.peek().getParameters().get("id"));
                    actionStack.pop();
                }
            }

            if (actionStack.peek().getOperation() == "delete") {
                if (actionStack.peek().getObjectName() == "aircraft"){
                    Aircraft aircraftToReAdd = new Aircraft();
                    aircraftToReAdd.setId((Integer) actionStack.peek().getParameters().get("id"));
                    aircraftToReAdd.setType((String) actionStack.peek().getParameters().get("type"));
                    aircraftToReAdd.setAirlineName((String) actionStack.peek().getParameters().get("airlineName"));
                    aircraftToReAdd.setNumberOfPassengers((Integer) actionStack.peek().getParameters().get("numberOfPassengers"));
                    aircraftToReAdd.setAllowedAirportList((List<Airport>) actionStack.peek().getParameters().get("allowedAirportList"));
                    aircraftService.addAircraft(aircraftToReAdd);
                    actionStack.pop();
                }
            }

            if (actionStack.peek().getOperation() == "update"){
                if (actionStack.peek().getObjectName() == "aircraft"){
                    Aircraft aircraftUndoUpdate = new Aircraft();
                    aircraftUndoUpdate.setId((Integer) actionStack.peek().getParameters().get("id"));
                    aircraftUndoUpdate.setType((String) actionStack.peek().getParameters().get("type"));
                    aircraftUndoUpdate.setAirlineName((String) actionStack.peek().getParameters().get("airlineName"));
                    aircraftUndoUpdate.setNumberOfPassengers((Integer) actionStack.peek().getParameters().get("numberOfPassengers"));
                    aircraftUndoUpdate.setAllowedAirportList((List<Airport>) actionStack.peek().getParameters().get("allowedAirportList"));
                    aircraftService.updateAircraft(aircraftUndoUpdate.getId(), aircraftUndoUpdate);
                    actionStack.pop();
                }
            }

        } else {
            System.out.println("Sorry the stack is empty.");
        }

    }
}
