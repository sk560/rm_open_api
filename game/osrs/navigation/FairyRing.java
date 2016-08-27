package com.rm_open_api.game.osrs.navigation;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;

import java.util.Arrays;

/**
 * Created by Party
 **/
public class FairyRing {

    private static final String TEXT = "Teleport to this location";
    private static final String ACTION = "Confirm";
    private static final Area VICINITY = new Area.Circular(new Coordinate(2412, 4434, 0), 3);

    private static InterfaceComponent getFairyRingInterface() {
        return Interfaces.newQuery().texts(TEXT).actions(ACTION).results().first();
    }

    /**
     * Can be used by other classes to determine whether or not the fairy ring is the central fairy ring or a branch-ring
     * @return if the code selector is valid
     */
    public static boolean isInterfaceActive() {
        InterfaceComponent component = getFairyRingInterface();
        return component != null && component.isVisible();
    }

    /**
     * Determines whether or not code has previously been visited and is thus in travel log
     * @param code - Fairy ring code you wish to travel to in uppercase eg "CKR"
     * @return if the code has previously been visited and is in travel log
     */
    private static boolean isCodeInTravelLog(String code) {
        InterfaceComponent component = getCodeInLog(code);
        return component != null && component.isVisible();
    }

    /**
     * Determines if the desired code is entered
     * @param code - Fairy ring code you wish to travel to in uppercase eg "CKR"
     * @return if code is entered
     */
    private static boolean isCodeEntered(String code) {
        String left = Character.toString(code.charAt(0));
        String middle = Character.toString(code.charAt(1));
        String right = Character.toString(code.charAt(2));

        return Dial.LEFT.isSelected(left) && Dial.CENTER.isSelected(middle) && Dial.RIGHT.isSelected(right);
    }

    /**
     * Enters code either by log or dial selector
     * @param code - Fairy ring code you wish to travel to in uppercase eg "CKR"
     * @return if code entry was successful or isCodeEntered
     */
    private static boolean enterRingCode(String code) {
        if (code.length() != 3) {
            throw new RuntimeException("Invalid fairy-ring code: " + code);
        }
        if (!isCodeEntered(code)) {
            if (isCodeInTravelLog(code)) {
                if(selectLogCode(code)){
                    System.out.println("Selected Ring Code in Travel Log");
                }
            } else {
                String left = Character.toString(code.charAt(0));
                String middle = Character.toString(code.charAt(1));
                String right = Character.toString(code.charAt(2));
                if(!Dial.LEFT.isSelected(left)){
                    if(Dial.LEFT.rotateTo(left)){
                        System.out.println("Rotating left dial");
                    }
                } else if (Dial.CENTER.isSelected(middle)){
                    if(Dial.CENTER.rotateTo(middle)){
                        System.out.println("Rotating center dial");
                    }
                } else if (Dial.RIGHT.isSelected(right)){
                    if(Dial.RIGHT.rotateTo(right)){
                        System.out.println("Rotating right dial");
                    }
                }
            }
        }
        return Execution.delayUntil(() -> isCodeEntered(code), 600, 1200);
    }

    /**
     * Main method of the class, will teleport the user using the other methods given a valid ring code
     * @param code - Fairy ring code you wish to travel to in uppercase eg "CKR"
     * @return If teleport was successful
     */
    public static boolean teleportTo(String code){
        if(!isCodeEntered(code)){
            if(enterRingCode(code)){
                System.out.println("Entering code");
            }
        }

        return Execution.delayUntil(() -> !VICINITY.contains(Players.getLocal()), 3000, 4000);
    }

    /**
     * Selects the code from the Travel log
     * @param code - Fairy ring code you wish to travel to in uppercase eg "CKR"
     * @return if the code was successfully selected from the Log
     */
    private static boolean selectLogCode(String code){
        InterfaceComponent component = getCodeInLog(code);
        if (Interfaces.scrollTo(component, component.getParentComponent())) {
            component.interact("Use code", formattedCode(code));
        }
        return Execution.delayUntil(() -> isCodeEntered(code), 1200, 1800);
    }

    /**
     * Returns the InterfaceComponent which contains the code we need
     * @param code - Fairy ring code you wish to travel to in uppercase eg "CKR"
     * @return the component we need
     */
    private static InterfaceComponent getCodeInLog(String code) {
        String formattedCode = formattedCode(code);
        return Interfaces.newQuery().containers(381).grandchildren(true).texts(formattedCode).results().first();
    }

    /**
     * Returns the formatted code for use in searching the Travel log
     * @param code - Fairy ring code you wish to travel to in uppercase eg "CKR"
     * @return the formatted code with spaces between characters eg. "C K R"
     */
    private static String formattedCode(String code) {
        return code.replaceAll(".(?=.)", "$0 ");
    }

    /**
     * @return if local player is at root fairy-ring node
     */
    public boolean atRootNode(){
        Player player = Players.getLocal();
        return player != null && VICINITY.contains(player);
    }

    private enum Dial {
        LEFT(19, 20, 3985, "A", "D", "C", "B"),
        CENTER(21, 22, 3986, "I", "L", "K", "J"),
        RIGHT(23, 24, 3987, "P", "S", "R", "Q");

        private int clockWise;
        private int counterClockWise;
        private int varBit;
        private String[] values;
        private final String CLOCKWISE = "Rotate clockwise";
        private final String ANTI_CLOCKWISE = "Rotate counter-clockwise";

        Dial(int clockWise, int counterClockWise, int varBit, String... values) {
            this.clockWise = clockWise;
            this.counterClockWise = counterClockWise;
            this.varBit = varBit;
            this.values = values;
        }

        /**
         * Index of position of current dial
         * @return index of position of current dial
         */
        private int getVarbitValue() {
            Varbit var = Varbits.load(varBit);
            if (var == null) {
                return -1;
            }
            return var.getValue();
        }

        /**
         * Index of position of current dial needed for selected letter
         * @return index of position of current dial needed for selected letter
         */
        private int indexOf(String letter) {
            return Arrays.asList(values).indexOf(letter);
        }

        /**
         * Rotates the dial clockwise
         * @return if rotating the dial was successful
         */
        private boolean rotateClockwise() {
            InterfaceComponent component = Interfaces.getAt(398, clockWise);
            return component != null && component.interact(CLOCKWISE);
        }

        /**
         * Rotates the dial anti-clockwise
         * @return if rotating the dial was successful
         */
        private boolean rotateAntiClockwise() {
            InterfaceComponent component = Interfaces.getAt(398, counterClockWise);
            return component != null && component.interact(ANTI_CLOCKWISE);
        }

        /**
         * Determines whether or not the letter is currently selected in the specified dial
         * @param letter - 1 character we need to dial to be positioned at
         * @return if the letter is currently selected
         */
        private boolean isSelected(String letter) {
            return getVarbitValue() == indexOf(letter);
        }

        /**
         * Utilises rotateClockwise and rotateAntiClockWise to turn the dial to the desired position
         * @param letter - 1 character we need to dial to be positioned at
         * @return is the dial is at the desired position
         */
        private boolean rotateTo(String letter) {
            int index = indexOf(letter);
            int current = getVarbitValue();

            if (index == 0) {
                throw new RuntimeException("Value " + letter + " is not facet-valid with respect to options: " + Arrays.toString(values));
            } else if (current == -1) {
                throw new RuntimeException("Could not determine current position of Dial");
            } else if (!isSelected(letter)) {
                int turns = Math.abs(index - current);
                if (turns == 3) {
                    rotateAntiClockwise();
                } else {
                    rotateClockwise();
                }
            }

            return isSelected(letter);
        }
    }
}
