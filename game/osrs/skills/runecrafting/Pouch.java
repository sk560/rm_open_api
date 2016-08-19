package com.rm_open_api.game.osrs.skills.runecrafting;

import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;

/**
 * Created by PartyShanked
 **/
public enum Pouch {

    SMALL("Small pouch", 603, 3, 1),
    MEDIUM("Medium pouch", 604, 6, 25),
    LARGE("Large pouch", 605, 9, 50),
    GIANT("Giant pouch", 606, 12, 75);

    private String name;
    private int varBit;
    private int maxEssence, levelRequirement;

    Pouch(String name, int varBit, int maxEssence, int levelRequirement){
        this.name = name;
        this.varBit = varBit;
        this.maxEssence = maxEssence;
        this.levelRequirement = levelRequirement;
    }
    public int getStored(){
        return Varbits.load(varBit).getValue();
    }

    public int getMaxEssence(){
        return maxEssence;
    }

    public boolean isUsable(){
        return Skill.RUNECRAFTING.getBaseLevel() >= levelRequirement;
    }

}
