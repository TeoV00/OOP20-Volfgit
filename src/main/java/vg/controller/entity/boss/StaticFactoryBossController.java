package vg.controller.entity.boss;

import vg.model.entity.boss.BossModel;
import vg.model.entity.boss.StaticFactoryBoss;
import vg.view.entity.EntityBlock;
import vg.view.entity.StaticFactoryEntityBlock;

public final class StaticFactoryBossController {
    private StaticFactoryBossController() {
    }

    public static BossController createRound1() {
        final BossModel model = StaticFactoryBoss.createRound1();
        final EntityBlock view = StaticFactoryEntityBlock.createBoss(model.getPosition(), model.getDimension());
        return new BossControllerImpl(model, view);
    }

}
