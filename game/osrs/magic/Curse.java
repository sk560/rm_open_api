package com.rm_open_api.game.osrs.magic;

import com.runemate.game.api.osrs.local.hud.interfaces.Magic;

/**
 * Created by Party
 * A long long time ago...
 **/
public enum Curse implements Spells {
    CONFUSE("Confuse", Magic.CONFUSE, 3),
    WEAKEN("Weaken", Magic.WEAKEN, 11),
    CURSE("Curse", Magic.CURSE, 19),
    BIND("Bind", Magic.BIND, 20),
    SNARE("Snare", Magic.SNARE, 50),
    VULNERABILITY("Vulnerability", Magic.VULNERABILITY, 66),
    ENFEEBLE("Enfeeble", Magic.ENFEEBLE, 73),
    ENTANGLE("Entage", Magic.ENTANGLE, 79),
    STUN("Stun", Magic.STUN, 80);

    private String name;
    private Magic spell;
    private int level;

    Curse(String name, Magic spell, int level) {
        this.name = name;
        this.spell = spell;
        this.level = level;
    }
    @Override
    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public Magic getSpell() {
        return spell;
    }

    public int getLevelRequirement(){
        return level;
    }
}
