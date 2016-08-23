package com.rm_open_api.game.hybrid.user;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;

/**
 * @author Mihael & Party (Party only updated a couple things)
 *
 * 21. avg. 2016
 */

public class User {

	public static boolean isAnimating() {
		Player player = Players.getLocal();
		return player != null && player.getAnimationId() != -1;
	}

	public static boolean isAnimating(Player p) {
		return p.getAnimationId() != -1;
	}

	public static boolean isTargeting() {
        	Player player = Players.getLocal();
		return player != null && player.getTarget() != null;
	}

}
