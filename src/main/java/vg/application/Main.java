package vg.application;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception an exception tha will be thrown
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        System.out.println("da qua in poi parte la view in modo corretto");
        //per uscire: System.exit(-1);
    }
    public static void main(final String[] args) {
        Application.launch(Main.class);
    }
}
