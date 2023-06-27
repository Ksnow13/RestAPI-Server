package com.keyin.action;

import java.util.Map;

public class Action {
    private String objectName;
    private String operation;
    private Map<String, Object> parameters;

    public Action(String objectName, String operation, Map<String, Object> parameters) {
        this.objectName = objectName;
        this.operation = operation;
        this.parameters = parameters;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
