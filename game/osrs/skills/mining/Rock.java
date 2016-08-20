package com.rm_open_api.game.osrs.skills.mining;

import java.awt.*;

/**
 * Created by PartyShanked
 **/
public enum Rock {

    TIN("Tin", new Color(106, 106, 106), new String[]{"Tin ore"}),
    COPPER("Copper", new Color(74, 48, 32), new String[]{"Copper ore"}),
    IRON("Iron", new Color(32, 17, 14), new String[]{"Iron ore"}),
    COAL("Coal", new Color(24,24,17), new String[]{"Coal"}),
    MITHRIL("Mithril", new Color(47,47,66), new String[]{"Mithril ore"}),
    ADAMANTITE("Adamantite", new Color(52,60,52), new String[]{"Adamantite ore"}),
    RUNITE("Runite", new Color(73,98,102), new String[]{"Runite ore"});

    private final String name;
    private final String[] oreNames;
    private final Color colorSub;
    private final static Color EMPTY_ROCK = new Color(48, 48, 48);

    Rock(String name, Color colorSub, String[] oreNames){
        this.name = name;
        this.colorSub = colorSub;
        this.oreNames = oreNames;
    }

    public String getName(){
        return name;
    }

    public Color rockColor(){
        return colorSub;
    }

    public static Color emptyRockColor(){
        return EMPTY_ROCK;
    }

    public String[] getOreNames(){
        return oreNames;
    }

}
