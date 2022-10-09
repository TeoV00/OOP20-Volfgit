package vg.view;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public interface View<C> {
    C getController();
    void setController(C controller);
    KeyEvent getKeyEvent();
    KeyCode getKeyPressed();
    KeyCode getKeyReleased();
    void receiveEventFromController(ControllerEvent event);
    void draw();
    void reset();
}