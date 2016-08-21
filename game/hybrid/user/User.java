package com.rm_open_api.game.hybrid.user;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;

/**
 * @author Mihael
 *
 * 21. avg. 2016
 */

public class User {

	public boolean isAnimating() {
		return Players.getLocal().getAnimationId() != -1;
	}

	public boolean isAnimating(Player p) {
		return p.getAnimationId() != -1;
	}

	public boolean isTargeting() {
		return Players.getLocal().getTarget() != null;
	}

}
