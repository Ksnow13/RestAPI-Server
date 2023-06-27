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

    //------------------------------------------------------------------------------------------------------------------
    private Aircraft aircraftPlaceHolder;

    public void setAircraftPlaceHolder(Aircraft aircraftPlaceHolder) {
        this.aircraftPlaceHolder = aircraftPlaceHolder;
    }
   //-------------------------------------------------------------------------------------------------------------------

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
                    redoActionStack.push(actionStack.peek());
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
                    redoActionStack.push(actionStack.peek());
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
                    redoActionStack.push(actionStack.peek());
                    actionStack.pop();
                }
            }

        } else {
            System.out.println("Sorry the stack is empty.");
        }

    }

    public void redoAction(){
        if (redoActionStack.size() > 0) {
            if(redoActionStack.peek().getOperation() == "create"){
                if(redoActionStack.peek().getObjectName() == "aircraft"){
                    Aircraft aircraftToAdd = new Aircraft();
                    aircraftToAdd.setId((Integer) redoActionStack.peek().getParameters().get("id"));
                    aircraftToAdd.setType((String) actionStack.peek().getParameters().get("type"));
                    aircraftToAdd.setAirlineName((String) actionStack.peek().getParameters().get("airlineName"));
                    aircraftToAdd.setNumberOfPassengers((Integer) actionStack.peek().getParameters().get("numberOfPassengers"));
                    aircraftToAdd.setAllowedAirportList((List<Airport>) actionStack.peek().getParameters().get("allowedAirportList"));
                    aircraftService.addAircraft(aircraftToAdd);
                    actionStack.push(redoActionStack.peek());
                    redoActionStack.pop();
                }
            }

            if (redoActionStack.peek().getOperation() == "delete"){
                if(redoActionStack.peek().getObjectName() == "aircraft") {
                    aircraftService.deleteAircraftById((Integer) redoActionStack.peek().getParameters().get("id"));
                    actionStack.push(redoActionStack.peek());
                    redoActionStack.pop();
                }
            }

            // not working correctly (aircraft not found)
            if (redoActionStack.peek().getOperation() == "update") {
                if (redoActionStack.peek().getObjectName() == "aircraft"){
                    System.out.println("here is the id: " + aircraftPlaceHolder.getId());
                    aircraftService.updateAircraft(aircraftPlaceHolder.getId(), aircraftPlaceHolder);
                    actionStack.push(redoActionStack.peek());
                    redoActionStack.pop();
                }
            }

        } else {
            System.out.println("There is nothing to redo");
        }
    }
}
