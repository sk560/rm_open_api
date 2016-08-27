package com.rm_open_api.game.hybrid.interaction.item;

import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.util.calculations.Random;

/**
 * @author Mihael
 *
 *         21. avg. 2016
 */

public class ItemInteraction {

	public static boolean selectItem(SpriteItem item) {
		SpriteItem selectedItem = Inventory.getSelectedItem();
		if (item != null) {
			for (int tries = 0; tries < 3; tries++) {
				if (selectedItem != null) {
					String selectedName = selectedItem.getDefinition().getName();
					String name = item.getDefinition().getName();
					if (selectedName.equals(name)) {
						return true;
					} else {
						selectedItem.click();
					}
				} else {
					if (item.click()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean selectItem(String... i) {
		SpriteItem item = Inventory.newQuery().names(i).results().random();
		SpriteItem selectedItem = Inventory.getSelectedItem();

		if (item != null) {
			for (int tries = 0; tries < 3; tries++) {
				if (selectedItem != null) {
					String selectedName = selectedItem.getDefinition().getName();
					String name = item.getDefinition().getName();
					if (selectedName.equals(name)) {
						return true;
					} else {
						selectedItem.click();
					}
				} else {
					if (item.click()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean interactItem(String action, String... i) {
		SpriteItem item = Inventory.newQuery().names(i).results().random();
		SpriteItem selectedItem = Inventory.getSelectedItem();

		if (item != null) {
			for (int tries = 0; tries < 3; tries++) {
				if (selectedItem != null) {
					selectedItem.click();
				} else {
					if (item.interact(action)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean interactItem(String action, Pattern... pattern) {
		SpriteItem item = Inventory.newQuery().names(pattern).results().random();
		SpriteItem selectedItem = Inventory.getSelectedItem();

		if (item != null) {
			for (int tries = 0; tries < 3; tries++) {
				if (selectedItem != null) {
					selectedItem.click();
				} else {
					if (item.interact(action)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean useOn(SpriteItem j, SpriteItem i, Callable<Boolean> condition) {
		String nameI = i.getDefinition().getName();
		String nameJ = j.getDefinition().getName();
		if (nameI != null && nameJ != null) {
			for (int tries = 0; tries < 3; tries++) {
				SpriteItem selectedItem = Inventory.getSelectedItem();
				if (selectedItem != null) {
					String selectedName = selectedItem.getDefinition().getName();
					if (selectedName.equals(nameI)) {
						selectItem(nameI);
					} else if (selectedName.equals(nameJ)) {
						selectItem(nameJ);
					} else {
						selectedItem.click();
					}
				} else {
					switch (Random.nextInt(0, 5)) {
					case 3:
						selectItem(nameI);
						break;

					default:
						selectItem(nameJ);
						break;
					}
				}

			}
		}
		return false;
	}
}
