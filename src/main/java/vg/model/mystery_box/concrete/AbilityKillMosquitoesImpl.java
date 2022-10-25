package vg.model.mystery_box.concrete;

import vg.model.Stage;
import vg.model.mystery_box.AbstractAbilityInstant;
import vg.model.mystery_box.EAbility;
import vg.utils.V2D;

public class AbilityKillMosquitoesImpl extends AbstractAbilityInstant {
    private static final long serialVersionUID = 1L;

    public AbilityKillMosquitoesImpl() {
        super(EAbility.KILL_ALL_MOSQUITOES);
    }

    @Override
    public void activate(final Stage<V2D> stage) {
        this.activated();
        stage.getToDestroySet().addAll(stage.getDynamicEntitySet());
    }
}
