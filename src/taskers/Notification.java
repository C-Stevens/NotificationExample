/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskers;

/**
 *
 * @author dalemusser
 */
public class Notification {
    private Object task;
    private String message;
    private boolean isRunning;
    
    public Notification(Object task, String message, boolean isRunning) {
        this.task = task;
        this.message = message;
        this.isRunning = isRunning;
    }
    
    public Object getTask() {
        return this.task;
    }
    public String getMessage() {
        return this.message;
    }
    public boolean getIsRunning() {
        return this.isRunning;
    }
}