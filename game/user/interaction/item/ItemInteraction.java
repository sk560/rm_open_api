package com.rm_open_api.game.user.interaction.item;

import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;

/**
 * @author Mihael
 *
 * 21. avg. 2016
 */

public class ItemInteraction {

	public boolean selectItem(SpriteItem i) {
		if (i != null) {
			for (int o = 0; o < 3; o++) {

				if (Inventory.getSelectedItem() != null) {
					String selectedName = Inventory.getSelectedItem().getDefinition().getName();
					String name = i.getDefinition().getName();
					if (selectedName.equals(name)) {
						return true;
					} else {
						if (Inventory.getSelectedItem().click()) {
							// return selectItem(i);
						}
					}
				} else {
					if (i.click()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean selectItem(String i) {
		SpriteItem item = Inventory.getItems(i).random();
		if (item != null) {
			for (int o = 0; o < 3; o++) {

				if (Inventory.getSelectedItem() != null) {
					String selectedName = Inventory.getSelectedItem().getDefinition().getName();
					String name = item.getDefinition().getName();
					if (selectedName.equals(name)) {
						return true;
					} else {
						if (Inventory.getSelectedItem().click()) {
							// return selectItem(i);
						}
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

	public boolean selectItem(String... i) {
		SpriteItem item = Inventory.getItems(i).random();

		if (item != null) {
			for (int o = 0; o < 3; o++) {
				if (Inventory.getSelectedItem() != null) {
					String selectedName = Inventory.getSelectedItem().getDefinition().getName();
					String name = item.getDefinition().getName();
					if (selectedName.equals(name)) {
						return true;
					} else {
						if (Inventory.getSelectedItem().click()) {
							// return selectItem(i);
						}
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

	public boolean interactItem(String action, Pattern name) {
		SpriteItem item = Inventory.newQuery().names(name).results().random();
		if (item != null) {
			for (int o = 0; o < 3; o++) {
				SpriteItem selectedItem = Inventory.getSelectedItem();
				if (selectedItem != null) {
					if (selectedItem.click()) {

					}
				}
				if (item.interact(action)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean interactItem(String action, SpriteItem item) {
		if (item != null) {
			for (int o = 0; o < 3; o++) {
				SpriteItem selectedItem = Inventory.getSelectedItem();
				if (selectedItem != null) {
					if (selectedItem.click()) {

					}
				}
				if (item.interact(action)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean useOn(SpriteItem j, SpriteItem i, Callable<Boolean> condition) {
		String nameI = i.getDefinition().getName();
		String nameJ = j.getDefinition().getName();
		if (nameI != null && nameJ != null) {
			for (int o = 0; o < 3; o++) {

				if (Inventory.containsAllOf(nameI, nameJ)) {
					SpriteItem selected = Inventory.getSelectedItem();
					if (selected != null) {
						String selectedName = selected.getDefinition().getName();
						if (selectedName.equals(nameI)) {
							i = Inventory.newQuery().names(nameJ).results().random();
							if (i != null) {
								if (i.interact("Use")) {
									return true;
								}
							}
						} else if (selectedName.equals(nameJ)) {
							j = Inventory.newQuery().names(nameI).results().random();
							if (j != null) {
								if (j.interact("Use")) {
									return true;
								}
							}
						} else {
							selected.click();
						}
					} else {
						if (i != null) {
							if (i.interact("Use")) {
								// return useOn(i, j, condition);
							}
						}
					}
				}
			}
		}
		return false;
	}
}
