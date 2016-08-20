package com.rm_open_api.game.osrs.magic;

/**
 * Created by Party
 * A long long time ago...
 **/
public enum Rune {

    AIR("Air rune"),
    WATER("Water rune"),
    EARTH("Earth rune"),
    FIRE("Fire rune"),
    MIND("Mind rune"),
    BODY("Body rune"),
    LAW("Law rune"),
    COSMIC("Cosmic rune"),
    CHAOS("Chaos rune"),
    NATURE("Nature rune"),
    DEATH("Death rune"),
    ASTRAL("Astral rune"),
    BLOOD("Blood rune"),
    SOUL("Soul rune"),
    DUST("Dust rune"),
    LAVA("Lava rune"),
    MUD("Mud rune"),
    SMOKE("Smoke rune"),
    STEAM("Steam rune");

    private String name;

    Rune(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
