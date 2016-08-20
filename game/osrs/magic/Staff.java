package com.rm_open_api.game.osrs.magic;

/**
 * Created by Party
 * A long long time ago...
 **/
public enum Staff {

    WATER(new String[]{"Staff of water", "Water battlestaff", "Mystic water staff"}, new Rune[]{Rune.WATER}),
    FIRE(new String[]{"Staff of fire", "Fire battlestaff", "Mystic fire staff"}, new Rune[]{Rune.FIRE}),
    AIR(new String[]{"Staff of air", "Air battlestaff", "Mystic air staff"}, new Rune[]{Rune.AIR}),
    EARTH(new String[]{"Staff of earth", "Earth battlestaff", "Mystic earth staff"}, new Rune[]{Rune.EARTH}),
    MUD(new String[]{"Mud battlestaff", "Mystic mud staff"}, new Rune[]{Rune.MUD, Rune.WATER, Rune.EARTH}),
    LAVA(new String[]{"Lava battlestaff", "Mystic lava staff"}, new Rune[]{Rune.FIRE, Rune.LAVA, Rune.EARTH}),
    STEAM(new String[]{"Steam battlestaff", "Mystic steam staff"}, new Rune[]{Rune.FIRE, Rune.WATER, Rune.STEAM}),
    SMOKE(new String[]{"Smoke battlestaff", "Mystic smoke staff"}, new Rune[]{Rune.FIRE, Rune.AIR, Rune.SMOKE});

    private String[] names;
    private Rune[] replacements;

    Staff(String[] names, Rune[] replacement){
        this.names = names;
        this.replacements = replacement;
    }

    public String[] getNames(){
        return names;
    }

    public Rune[] getReplacements(){
        return replacements;
    }
}
