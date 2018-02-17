# NotificationExample
NotificationExample project for MU-CS4330

# Notes on changes
## General
* The functional interface `Notification` was removed and replaced with a simple class of the same name.
* This new class serves as a more sophisticated form of message transfer across threads. It has persistent values for message and run state (at the time of creation) so that every message is tied to a state of the thread.
* This class has getters so the UI controller can determine information about the Notification, but no setters exist as each Notification is meant to be distinct only to a certain message at creation time (they are not intended to be re-used).
* This class also stores the task that created it. This is stored as type Object, because the Notification does not necesarially know what kind of Task (1, 2, or 3) has created it. This attribute can be checked against known Task types in the UI controller to determine from which task it came from. This is the proper scope for this kind of object checking, and keeps code tidy.
* Classes now create and pass these Notification objects (in three distinct ways as was provided) instead of just passing String data. This improves expandability and future-proofs the application.
* The UI controller's notify method now controls updates from all three tasks. The Task the notification was sent from is determined, and the Notification is then sent to a method specifically designed for handling Notifications from that Task. This centralizes all handling of updates and allows for Task-specific processing on Notifications.
* All Tasks now contain a boolean called called `isRunning`. This stores whether or not the Thread is currently running or not. There were getters added to the Tasks so that the UI controller can determine if the Thread is running or not. This will automatically be set to `True` when the run method is called, and `False` if the end method is called, or the Task is done doing its current run() logic.

### Task 1
* doNotify() was updated to pass a Notification object rather than a String back to the UI controller. This allows the controller to receive both the message string and the run state of the thread at the time in a single object.

### Task 2
* The arrayList of observers was switched to an arrayList of Notifiables.
* This clarifies and sanity-checks the observer model such that the things that are notified are Notifiable (instead of notifying Notifications, which is counter-intuitive)
* Going along with this change, Notifiable's notify() method was changed to use a Notification as argument (again, another sanity check and better logicical arrangement)
* Since the UI controller implements Notifiable, it's notify() method can be used to extract the notification message and run state in this space, just like for Task 1.

### Task 3
* the firePropertyChange on this Task's PropertyChangeSupport was changed to update a Notification, rather than just a message String.
* Accordingly, the event is cast as a Notification in the UI controller and the message is determined with the Notification's getter.