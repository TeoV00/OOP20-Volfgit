package vg.model.timedObject;

public interface TimedObject {

    /**
     * @return True if timeout expired.
     */
    Boolean isTimeOver();

    /**
    * Call this method in order to update timer of bonus.
    * @param elapsedTime time elapsed from previous game loop cycle to current one
     */
    void updateTimer(double elapsedTime);

    /**
     * @return remaining time of timer
     * */
    double getRemainingTime();
}
