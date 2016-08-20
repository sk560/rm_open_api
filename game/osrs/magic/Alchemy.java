package com.rm_open_api.game.osrs.magic;

import com.runemate.game.api.osrs.local.hud.interfaces.Magic;

/**
 * Created by Party
 * A long long time ago...
 **/
public enum Alchemy implements Spells {

    LOW_ALCHEMY("Low Level Alchemy", Magic.LOW_LEVEL_ALCHEMY, 21, 712),
    HIGH_ALCHEMY("High Level Alchemy", Magic.HIGH_LEVEL_ALCHEMY, 55, 713);

    private String name;
    private Magic spell;
    private int level;
    private int animationId;

    Alchemy(String name, Magic spell, int level, int animationId) {
        this.name = name;
        this.spell = spell;
        this.level = level;
        this.animationId = animationId;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public Magic getSpell() {
        return spell;
    }

    public int getAnimationId() { return animationId; }

    public int getLevel() {
        return level;
    }
}
