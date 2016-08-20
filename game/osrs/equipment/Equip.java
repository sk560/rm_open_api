package com.rm_open_api.game.osrs.equipment;

import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by PartyShanked
 **/
public class Equip {


    private final static Pattern EQUIP_PATTERN = Pattern.compile("^Wield|Wear$");

    /**
     * Equips a single item within the player's inventory
     *
     * @param name Pattern - the Pattern which will match the name of the item to be equipped
     * @return whether the action of equipping the item was successful
     */
    public static boolean item(Pattern name) {
        SpriteItem item = Inventory.newQuery().names(name).actions(EQUIP_PATTERN).results().first();
        if (item != null) {
                SpriteItem selectedItem = Inventory.getSelectedItem();
                if (selectedItem != null && selectedItem.click()) {
                    System.out.println("Deselected item: " + selectedItem.getDefinition().getName());
                }
                return item.interact(EQUIP_PATTERN);
        }
        return false;
    }

    /**
     * Equips a single item within the player's inventory
     *
     * @param name String - the name of the item to be equipped
     * @return whether the action of equipping the item was successful
     */
    public static boolean item(String name) {
        SpriteItem item = Inventory.newQuery().names(name).actions(EQUIP_PATTERN).results().first();
        if (item != null) {
                SpriteItem selectedItem = Inventory.getSelectedItem();
                if (selectedItem != null && selectedItem.click()) {
                    System.out.println("Deselected item: " + selectedItem.getDefinition().getName());
                }
                return item.interact(EQUIP_PATTERN);
        }
        return false;
    }

    /**
     * Equips a single item within the player's inventory
     *
     * @param item SpriteItem - the item object to be equipped
     * @return whether the action of equipping the item was successful
     */
    public static boolean item(SpriteItem item) {
        if (item != null) {
            SpriteItem selectedItem = Inventory.getSelectedItem();
            if (selectedItem != null && selectedItem.click()) {
                System.out.println("Deselected item: " + selectedItem.getDefinition().getName());
            }
            return item.interact(EQUIP_PATTERN);
        }
        return false;
    }

    /**
     * Pretty useful method for equipping gear sets
     *
     * @param listItems List of Patterns - List of Patterns matching items to be equipped
     * @return whether all items within the list were equipped
     */
    public static boolean itemList(List<Pattern> listItems) {
        listItems
                .stream()
                .filter(s -> !Inventory.newQuery().names(s).results().isEmpty() && Equipment.newQuery().names(s).results().isEmpty())
                .forEach(s -> {
                    SpriteItem item = Inventory.newQuery().names(s).results().first();
                    item(item);
                });

        return Execution.delayUntil(() -> Equipment.newQuery().names(listItems.toArray(new Pattern[0])).results().size() == listItems.size(), 2000, 3000);
    }

}
