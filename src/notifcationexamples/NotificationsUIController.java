/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifcationexamples;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import taskers.*;

/**
 * FXML Controller class
 *
 * @author dalemusser
 */
public class NotificationsUIController implements Initializable, Notifiable {

    @FXML
    private TextArea textArea;
    @FXML
    private Button task1Button;
    @FXML
    private Button task2Button;
    @FXML
    private Button task3Button;
    
    private Task1 task1;
    private Task2 task2;
    private Task3 task3;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void start(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if (task1 != null) task1.end();
                if (task2 != null) task2.end();
                if (task3 != null) task3.end();
            }
        });
    }

    private void processTask1Notification(Notification notification) {
        if(notification.getIsRunning()) {
            task1Button.setText("Stop Task 1");
        } else {
            task1Button.setText("Start Task 1");
        }
        textArea.appendText(notification.getMessage() + "\n");
    }
    private void processTask2Notification(Notification notification) {
        if(notification.getIsRunning()) {
            task2Button.setText("Stop Task 2");
        } else {
            task2Button.setText("Start Task 2");
        }
        textArea.appendText(notification.getMessage() + "\n");
    }
    private void processTask3Notification(Notification notification) {
        if(notification.getIsRunning()) {
            task3Button.setText("Stop Task 3");
        } else {
            task3Button.setText("Start Task 3");
        }
        textArea.appendText(notification.getMessage() + "\n");
    }
    
    @Override
    public void notify(Notification notification) {
        if(notification.getTask() == task1) {
            processTask1Notification(notification);
        } else if(notification.getTask() == task2) {
            processTask2Notification(notification);
        } else if(notification.getTask() == task3) {
            processTask3Notification(notification);
        }
    }
    
    /*
    @Override
    public void notify(String message) {
        if (message.equals("Task1 done.")) {
            task1 = null;
        }
        textArea.appendText(message + "\n");
    }
    */
    
    @FXML
    public void handleTask1(ActionEvent event) {
        if(task1 != null && task1.getIsRunning()) {
            task1.end();
        } else if(task1 == null || !task1.getIsRunning()) {
            System.out.println("start task 1");
            task1 = new Task1(2147483647, 1000000);
            task1.setNotificationTarget(this);
            task1.start();
        }
        /*
        if (task1 == null) {
            //task1 = new Task1(2147483647, 1000000);
            task1 = new Task1(21474837, 1000000);
            task1.setNotificationTarget(this);
            task1.start();
        }
        */
    }
        
    @FXML
    public void handleTask2(ActionEvent event) {
        if(task2 != null && task2.getIsRunning()) {
            task2.end();
        } else if(task2 == null || !task2.getIsRunning()) {
            System.out.println("start task 2");
            task2 = new Task2(2147483647, 1000000);
            task2.setOnNotification(this);
            task2.start();
        }
        /*
        System.out.println("start task 2");
        
        if (task2 == null) {
            task2 = new Task2(2147483647, 1000000);

            task2.setOnNotification((String message, boolean isRunning) -> {
                textArea.appendText(message + "\n");
            });
            
            task2.start();
        }
        */
    }
    
    @FXML
    public void handleTask3(ActionEvent event) {
        if(task3 != null && task3.getIsRunning()) {
            task3.end();
        } else if(task3 == null || !task3.getIsRunning()) {
            System.out.println("start task3");
            task3 = new Task3(2147483647, 1000000);
            // this uses a property change listener to get messages
            task3.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                processTask3Notification((Notification)evt.getNewValue());
            });
            task3.start();
        }
        /*
        System.out.println("start task 3");
        if (task3 == null) {
            task3 = new Task3(2147483647, 1000000);
            // this uses a property change listener to get messages
            task3.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                Notification notification = (Notification)evt.getNewValue();
                processTask3Notification(notification);
            });
            
            task3.start();
        }
        */
    } 
}
