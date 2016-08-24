package com.rm_open_api.game.rs3.interaction.actionbar;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.rs3.local.hud.Powers.Magic;
import com.runemate.game.api.rs3.local.hud.Powers.Prayer;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.rs3.queries.results.ActionBarQueryResults;
import com.sun.glass.events.KeyEvent;


/**
 * @author Mihael
 *
 * 24. avg. 2016
 */


public class ActionBarInteraction {
	
	/*
	 * Lines: 78, 99, 120 -> I forgot what that was. If anyone can find out what that interface is, please change it with query.
	 */

	public static boolean usePower(Magic a) {
		String name = a.getName().replaceAll("([a-z])([A-Z])", "$1 $2");
		ActionBarQueryResults result = ActionBar.newQuery().names(name).results();
		System.out.println("Result: " + result + " size: " + result.size());
		if (!a.isSelected()) {
			if (!result.isEmpty()) {
				if (result.get(0).activate()) {
					return true;
				}
			} else {
				if (InterfaceWindows.getMagic().isOpen()) {
					if (a.activate()) {
						return true;
					}
				} else {
					if (InterfaceWindows.getMagic().open()) {
						return usePower(a);
					}
				}
			}
		} else {
			return true;
		}
		return false;
	}

	public static boolean usePower(Prayer a) {
		String name = a.getName().replaceAll("([a-z])([A-Z])", "$1 $2");
		ActionBarQueryResults result = ActionBar.newQuery().names(name).results();
		if (!a.isActivated()) {
			if (!result.isEmpty()) {
				if (result.get(0).activate()) {
					return true;
				}
			} else {
				if (InterfaceWindows.getPrayer().isOpen()) {
					if (a.activate()) {
						return true;
					}
				}
			}
		} else {
			return true;
		}
		return false;
	}

	public static boolean useItem(String name) {
		if (Environment.isRS3()) {
			if (Interfaces.getAt(1186, 2) != null) {
				if (Keyboard.typeKey(KeyEvent.VK_SPACE)) {

				}
			}
			ActionBarQueryResults result = ActionBar.newQuery().names(name).results();
			if (!result.isEmpty()) {
				if (result.get(0).activate()) {
					return true;
				}
			}
		} else {
			if (!Inventory.getItems(name).isEmpty()) {
				Inventory.getItems(name).first().click();
			}
		}
		return false;
	}

	public static boolean useItem(String... name) {
		if (Environment.isRS3()) {
			if (Interfaces.getAt(1186, 2) != null) { // I don't know what this is. But it works.
				if (Keyboard.typeKey(KeyEvent.VK_SPACE)) {

				}
			}
			ActionBarQueryResults result = ActionBar.newQuery().names(name).results();
			if (!result.isEmpty()) {
				if (result.get(0).activate()) {
					return true;
				}
			}
		} else {
			if (!Inventory.getItems(name).isEmpty()) {
				Inventory.getItems(name).first().click();
			}
		}
		return false;
	}

	public static boolean useItem(SpriteItem name) {
		if (Environment.isRS3()) {
			if (Interfaces.getAt(1186, 2) != null) { // I don't know what this
														// is
				if (Keyboard.typeKey(KeyEvent.VK_SPACE)) {

				}
			}
			ActionBarQueryResults result = ActionBar.newQuery().names(name.getDefinition().getName()).results();
			if (!result.isEmpty()) {
				if (result.get(0).activate(false)) {
					return true;
				}
			}
		} else {
			if (name != null) {
				name.click();
			}
		}
		return false;
	}

}
