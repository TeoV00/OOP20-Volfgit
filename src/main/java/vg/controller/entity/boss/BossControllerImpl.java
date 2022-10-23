package vg.controller.entity.boss;

import vg.controller.gameBoard.GameBoardController;
import vg.model.Boss.BossModel;
import vg.view.entity.EntityBlock;

public class BossControllerImpl implements BossController {
    private final BossModel model;
    private final EntityBlock view;

    public BossControllerImpl(final BossModel model, final EntityBlock view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void setInParentNode(GameBoardController gameController) {
        this.view.setInParentNode(gameController.getGameAreaNode());
    }
}