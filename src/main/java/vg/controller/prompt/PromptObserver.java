package vg.controller.prompt;

import vg.utils.PromptOption;

/**
 * Interface to notify controller that implements that interface which was the selection of confirmation dialog launched by controller.
 */
public interface PromptObserver {
    /**
     * In base of prompt's answer reflects on logic or domain.
     * @param answer {@link PromptOption} value of selected button.
     */
    void notifyDialogAnswer(PromptOption answer);
}
