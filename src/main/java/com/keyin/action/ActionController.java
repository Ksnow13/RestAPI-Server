package com.keyin.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ActionController {

    @Autowired
    ActionService actionService;

    @DeleteMapping("/undo")
    public void undoAction(){
        actionService.undoAction();
    }

}
