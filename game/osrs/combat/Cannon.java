package com.rm_open_api.game.osrs.combat;

import com.runemate.game.api.hybrid.local.Varps;

/**
 * Created by PartyShanked
 **/
public class Cannon {

    public static boolean isCannonLoaded(){
        return Varps.getAt(1).getValue() > 0;
    }

}
