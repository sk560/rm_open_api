package com.rm_open_api.game.osrs.skills.runecrafting;

import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    private Pattern pattern;


    Pouch(String name, int varBit, int maxEssence, int levelRequirement){
        this.name = name;
        this.varBit = varBit;
        this.maxEssence = maxEssence;
        this.levelRequirement = levelRequirement;
        this.pattern = Pattern.compile("^" + name + "$");
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

    public Pattern getPattern(){
        return pattern;
    }

    public static boolean fillAvailablePouches(List<Pouch> pouches){
        List<Pouch> fillable = pouches.stream().filter(pouch -> pouch.isUsable() && pouch.getStored() == 0).collect(Collectors.toList());

        fillable.stream().peek(pouch ->{
            SpriteItem item = Inventory.newQuery().names(pouch.getPattern()).results().first();
            if(pouch != null) item.interact("Fill");
        });
        return Execution.delayUntil(() -> fillable.stream().allMatch(pouch -> pouch.getStored() > 0), 2000, 3000);
    }

}
