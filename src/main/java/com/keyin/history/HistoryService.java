package com.keyin.history;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

@Service
public class HistoryService {

    private Stack<History> historyStack = new Stack<>();
    private Stack<History> redoStack = new Stack<>();

    public void addToHistory(String calledMethod, String url, LocalDateTime timestamp) {
        History history = new History(calledMethod, url, timestamp);
        historyStack.push(history);
    }

    public History getPeekHistory() {
        if (historyStack.isEmpty()){
            System.out.println("the stack is empty");
            return null;
        } else {
            return historyStack.peek();
        }
    }

    // what if stack is empty ??
    public List<History> getAllHistory() {
        List<History> historyList = new ArrayList<>();
        historyList.addAll(historyStack);

        if (historyList.isEmpty()){
            System.out.println("the stack is empty");
            return null;
        }
        return historyList;
    }

    public History undoHistory(){
        if(historyStack.isEmpty()){
            System.out.println("The stack is empty");
            return null;
        }
        redoStack.push(historyStack.peek());
        historyStack.pop();
        return historyStack.peek();
    }

    public History redoHistory() {
        if (redoStack.isEmpty()){
            System.out.println("There is nothing to redo");
        }
        historyStack.push(redoStack.peek());
        redoStack.pop();
        return historyStack.peek();
    }
}

