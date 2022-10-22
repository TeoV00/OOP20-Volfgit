package vg.view.gameBoard;

import javafx.application.Application;
import javafx.stage.Stage;
import vg.controller.GameControllerImpl;
import vg.controller.gameBoard.GameBoardController;
import vg.controller.menu.MenuController;
import vg.model.StageImpl;
import vg.utils.V2D;
import vg.view.AdaptableView;
import vg.view.ViewFactory;
import vg.view.ViewManager;
import vg.view.ViewManagerImpl;
import vg.view.menu.MenuView;
import vg.view.utils.KeyEventHandler;

public class GameBoard extends Application {

    /**
     * View Manager.
     * {@see ViewManager}
     */
    private ViewManager viewManager;

    @Override
    public void start(final Stage stage) {
        viewManager = new ViewManagerImpl(stage, new KeyEventHandler());

        MenuView menuView = ViewFactory.menuView(viewManager);
        viewManager.addScene(menuView);

        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        stage.setTitle("GameBoard");
        stage.setResizable(false);
        stage.show();
    }
}
