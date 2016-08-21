package com.rm_open_api.game.osrs.skills.slayer;

import com.runemate.game.api.hybrid.local.Varbits;

/**
 * Created by PartyShanked
 **/
public class Slayer {

    public static int getPoints(){
        return Varbits.load(4068).getValue();
    }

}
