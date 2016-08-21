package com.rm_open_api.game.osrs.combat;

import com.runemate.game.api.hybrid.local.Varps;

/**
 * Created by PartyShanked
 **/
public class Poison {

    public static boolean isVenomed(){
        return Varps.getAt(102).getValue() > 0;
    }

}
