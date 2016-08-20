package com.rm_open_api.game.common_tasks;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.framework.task.Task;

/**
 * Credit to @SlashnHax @ runemate.com
 **/
public class ChatDialogContinuer extends Task {
    private ChatDialog.Continue continueButton;

    @Override
    public boolean validate() {
        //Get the continue button
        return (continueButton = ChatDialog.getContinue()) != null;
    }

    @Override
    public void execute() {
        //bot.setStatus("Continuing dialog");
        System.out.println("Continuing dialog");
        //press the continue button
        continueButton.select();
    }
}
