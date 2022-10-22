package vg.controller.menu;

import javafx.application.Platform;
import vg.controller.Controller;
import vg.controller.GameControllerImpl;
import vg.controller.gameBoard.GameBoardController;
import vg.controller.prompt.PromptObserver;
import vg.controller.prompt.PromptOption;
import vg.model.StageImpl;
import vg.utils.V2D;
import vg.view.AdaptableView;
import vg.view.ViewFactory;
import vg.view.ViewManager;
import vg.view.menu.MenuView;
import vg.view.menu.prompt.PromptView;
import vg.view.utils.KeyAction;

public class MenuController extends Controller<MenuView> implements PromptObserver {
    /**
     * Menu selection cursor.
     */
    private int idxSelection = 0;

    public MenuController(final MenuView view, final ViewManager viewManager) {
        super(view, viewManager);
        this.getView().getViewController().highlightSelectedButton(idxSelection);
    }

    /**
     * Depending on selected button start corresponding view.
     */
    private void launchScreen() {
        if (idxSelection == MenuOption.PLAY.ordinal()) {
            playGame();
        } else if (idxSelection == MenuOption.LEADERBOARDS.ordinal()) {
            this.getViewManager().addScene(ViewFactory.leaderBoardView(getViewManager()));
        } else if (idxSelection == MenuOption.SETTINGS.ordinal()) {
            System.out.println("settings");
        } else if (idxSelection == MenuOption.QUIT.ordinal()) {
            PromptView promptView = ViewFactory.promptView(this.getViewManager(), this);
            this.getViewManager().addScene(promptView);
        }
    }

    /**
     * Load game model then show gameboard and start game session.
     * {@see GameControllerImpl}
     * {@see Game}
     */
    private void playGame() {
        vg.model.Stage<V2D> stageModel = null;
        try {
            stageModel = new StageImpl<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 1) CREATE view
        AdaptableView<GameBoardController> gameView = ViewFactory.newGameBoardView();
        // 2) CREATE createMysteryBox logic controller
        GameControllerImpl gameController = new GameControllerImpl(gameView, stageModel, this.getViewManager());
        // 3) set logic controller in view
        gameView.setIoLogicController(gameController);
        this.getViewManager().addScene(gameView);
        //5) run game-loop
        gameController.launchGameSession();
    }

    @Override
    public void keyTapped(final KeyAction k) {
        if (k == KeyAction.DOWN && idxSelection < MenuOption.values().length-1) {
            idxSelection++;
        } else if (k == KeyAction.UP && idxSelection > 0) {
             idxSelection--;
        } else if (k == KeyAction.ENTER) {
            launchScreen();
        }
        this.getView().getViewController().highlightSelectedButton(idxSelection);
    }

    @Override
    public void keyPressed(final KeyAction k) {
        keyTapped(k);
    }

    @Override
    public void keyReleased(final KeyAction k) {
    }

    @Override
    public void notifyDialogAnswer(final PromptOption answer) {
        if (answer == PromptOption.CONFIRM) {
            Platform.exit();
        } else if (answer == PromptOption.DENY) {
            this.getViewManager().popScene();
        }
    }
}