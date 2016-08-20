package com.rm_open_api.game.osrs.magic;

import com.runemate.game.api.osrs.local.hud.interfaces.Magic;

/**
 * Created by Party
 * A long long time ago...
 **/
public enum Teleports implements Spells {

    VARROCK("Varrock", Magic.VARROCK_TELEPORT, 25),
    LUMBRIDGE("Lumbridge", Magic.LUMBRIDGE_TELEPORT, 31),
    FALADOR("Faldor", Magic.FALADOR_TELEPORT, 37),
    HOUSE("House", Magic.TELEPORT_TO_HOUSE, 40),
    CAMELOT("Camelot", Magic.CAMELOT_TELEPORT, 45),
    ARDOUGNE("Ardougne", Magic.ARDOUGNE_TELEPORT, 41),
    WATCHTOWER("Watchtower", Magic.WATCHTOWER_TELEPORT, 58),
    TROLLHEIM("Trollheim", Magic.TROLLHEIM_TELEPORT, 61),
    APE_ATOLL("Ape Atoll", Magic.TELEPORT_TO_APE_ATOLL, 64);

    private String name;
    private Magic spell;
    private int level;

    Teleports(String name, Magic spell, int level){
        this.name = name;
        this.spell = spell;
        this.level = level;
    }
    @Override
    public String getName() {
        return name;
    }

    public String toString() { return name; }

    /**
     * Grabs the spell
     * @return Magic.Spell - Returns official RM API Magic.Spell
     */
    public Magic getSpell(){
        return spell;
    }

    /**
     * Gets level requirement of spell
     * @return int - levelrequirement of spell
     */
    public int getLevel(){
        return level;
    }
}
