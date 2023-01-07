package service;

import inventory.Equipment;
import model.items.Bomb;
import utils.collections.Collectable;
import utils.items.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * Utility/Helper class contains only static methods that are reused across
 * the application. It's stateless and it cannot be instantiated
 */
public final class UtilityClass {

    // private constructor to prevent instantiation
    private UtilityClass() {
        throw new UnsupportedOperationException();
    }

    // generic method
    // if the array is either 'null' or [null]
    public static <Thing> boolean isArrayEmpty(Thing[] array) {
        return array == null || Arrays.stream(array).allMatch(Objects::isNull);
    }

    /**
     * Generic/Parameterized method
     * Check either all the array elements are non-nulls or there are nulls
     *
     * @param array     array with items to collect
     * @param <T>       type of the array (implements Collectable)
     * @return          true if the array elements are non-null, false if there is at least one null element
     */
    public static <T extends Collectable> boolean isArrayFull(T[] array) {
        return array[array.length - 1] != null;
    }

    // if the string is either 'null', empty "" or blank " "
    public static boolean isStringEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    // if the integer is < 0
    public static boolean isIntegerNegative(int integer) {
        return integer < 0;
    }

    // generic method
    // if slotIndex in range from 0 to the length of the list
    public static <Thing> boolean validateSlot(int slotIndex, Thing slotItem) {
        Equipment equipmentInstance = Equipment.getInstance();
        return slotItem instanceof Weapon && (slotIndex >= 0 && slotIndex < equipmentInstance.getWeaponSlots().size()) ||
                slotItem instanceof Armor && (slotIndex >= 0 && slotIndex < equipmentInstance.getArmorSlots().size()) ||
                slotItem instanceof Consumables && (slotIndex >= 0 && slotIndex < equipmentInstance.getConsumablesSlots().size()) ||
                slotItem instanceof Roach && (slotIndex >= 0 && slotIndex < equipmentInstance.getRoachSlots().size()) ||
                slotItem instanceof Bomb && (slotIndex >= 0 && slotIndex < equipmentInstance.getBombSlots().size()) ||
                slotItem instanceof QuestFind && (slotIndex >= 0 && slotIndex < equipmentInstance.getPocketSlots().size());
    }
}
