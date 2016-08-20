package com.rm_open_api.game.osrs.combat;

import com.prime_bots.api.osrs.combat.Combat;
import com.runemate.game.api.hybrid.local.Varps;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.osrs.local.hud.interfaces.OSRSTab;
import com.runemate.game.api.script.Execution;

import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by PartyShanked
 **/
public class SpecialAttack {

    private static final Pattern SPECIAL_ATTACK_PATTERN = Pattern.compile("^Special Attack: (\\d+)%$");
    private static final Color SPECIAL_SELECTED = new Color(255, 255, 0);

    public static int getSpecialAttackPercentage(){
        return Varps.getAt(300).getValue()/10;
    }

    private static InterfaceComponent getSpecialAttackButton(){
        return Interfaces.newQuery().texts(SPECIAL_ATTACK_PATTERN).containers(593).grandchildren(false).results().first();
    }

    private static boolean isSpecialAttackEnabled(){
        return getSpecialAttackButton().getTextColor().equals(SPECIAL_SELECTED);
    }

    public static boolean useSpecialAttack(){
        OSRSTab menu = OSRSTab.COMBAT_OPTIONS;
        if(!menu.isOpen()){
            if(menu.open()){
                return false;
            }
        }
        else if (!isSpecialAttackEnabled()){
            if(getSpecialAttackButton().click()){
                return Execution.delayUntil(SpecialAttack::isSpecialAttackEnabled, 600, 1200);
            }
        }
        return false;
    }

    public static boolean isWearingSpecialAttackGear(List<Pattern> specialItems) {
        return specialItems.stream().allMatch(s -> !Equipment.newQuery().names(s).results().isEmpty());
    }

    public static boolean isWearingStandardAttackGear(List<Pattern> standardItems) {
        return standardItems.stream().allMatch(s -> !Equipment.newQuery().names(s).results().isEmpty());
    }

    public static boolean isWearingSpecialAttackGear(String... specialItems) {
        return Equipment.containsAllOf(specialItems);
    }

    public static boolean isWearingStandardAttackGear(String... standardItems) {
        return Equipment.containsAllOf(standardItems);
    }



}
