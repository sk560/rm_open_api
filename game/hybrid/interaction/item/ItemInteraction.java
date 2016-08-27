package com.rm_open_api.game.hybrid.interaction.item;

import java.util.regex.Pattern;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;

/**
 * @author Mihael
 *
 *         21. avg. 2016
 */

public class ItemInteraction {

	/**
	 * Selects item
	 * 
	 * @param item
	 *            - SpriteItem you want to select
	 * @return if item is selected
	 */

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

	/**
	 * Selects item
	 * 
	 * @param names
	 *            - Items names eg: Orange, Apple
	 * @return if any random item from name array is selected
	 */

	public static boolean selectItem(String... names) {
		SpriteItem item = Inventory.newQuery().names(names).results().random();
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

	/**
	 * Interacts item using specified action
	 * @param action - Action to interact item with
	 * @param names - Items (names) you want to interact with
	 * @return if interaction with any item in array of names is successful.
	 */

	public static boolean interactItem(String action, String... names) {
		SpriteItem item = Inventory.newQuery().names(names).results().random();
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
	
	/**
	 * Interacts item using specified action
	 * @param action - Action to interact item with
	 * @param pattern - Patterns of item names you want to interact with. Eg: *.logs
	 * @return if interaction with any item in array of names is successful.
	 */

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
	
	/**
	 * Uses items on each other
	 * @param firstItem - first item to use
	 * @param secondItem - second item to use
	 * @return when interaction with items is successful.
	 */

	public static boolean useOn(SpriteItem firstItem, SpriteItem secondItem) {
		String nameFirst = firstItem.getDefinition().getName();
		String nameSecond = secondItem.getDefinition().getName();
		if (nameFirst != null && nameSecond != null) {
			for (int tries = 0; tries < 3; tries++) {
				SpriteItem selectedItem = Inventory.getSelectedItem();
				if (selectedItem != null) {
					String selectedName = selectedItem.getDefinition().getName();
					if (selectedName.equals(nameFirst)) {
						if(selectItem(nameFirst)) return true;
					} else if (selectedName.equals(nameSecond)) {
						if(selectItem(nameSecond)) return true;
					} else {
						selectedItem.click();
					}
				} else {
					selectItem(nameFirst, nameSecond);
				}

			}
		}
		return false;
	}
}
