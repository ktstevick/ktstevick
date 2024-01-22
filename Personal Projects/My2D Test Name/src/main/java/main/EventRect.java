package main;

import java.awt.*;

// Just exists to extend Rectangle functionality for event flags
public class EventRect extends Rectangle {
    int eventRectDefaultX, eventRectDefaultY;
    boolean eventDone = false;
}
