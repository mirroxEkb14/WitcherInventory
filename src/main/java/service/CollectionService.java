package service;

import model.items.questfinds.maps.GriffinSchoolGearMaps;
import model.items.questfinds.maps.WolvenSchoolGearMaps;
import utils.collections.Collectable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static service.logger.LoggerHandler.getLogger;

/**
 * Contains arrays with items that can be collected
 * Contains methods to interact with these arrays
 */
public final class CollectionService {

    private static WolvenSchoolGearMaps[] wolvenGearMaps = new WolvenSchoolGearMaps[1];
    private static GriffinSchoolGearMaps[] griffinGearMaps = new GriffinSchoolGearMaps[1];

    private static final Logger log =
            getLogger(CollectionService.class.getSimpleName()).orElseThrow(() -> new IllegalArgumentException("class.getName() not passed"));

    public static void addCollectionItem(Collectable item) {
        if (item instanceof WolvenSchoolGearMaps) {
            wolvenGearMaps = getUpdatedArray(wolvenGearMaps);
            insertItem(wolvenGearMaps, item);

        } else if (item instanceof GriffinSchoolGearMaps) {
            griffinGearMaps = getUpdatedArray(griffinGearMaps);
            insertItem(griffinGearMaps, item);
        }
    }

    public static boolean isCollectable(Object item) {
        return item instanceof Collectable;
    }

    /**
     * Generic/Parameterized method (upper bound -> any Collectable and its subclasses)
     * Check the length of the array and return either the original one,
     *      or copied array with the length incremented by one
     *
     * @param array     contains collectable elements
     * @param <T>       array type (the array implements Collectable)
     * @return          original array or an array with the same elements but length incremented by one
     */
    private static <T extends Collectable> T[] getUpdatedArray(T[] array) {
        return UtilityClass.isArrayFull(array) ? getExtendedArray(array) : array;
    }

    /**
     * Generic/Parameterized method
     * Extends the length of the passed array that implements Collectable
     *
     * @param array     array that contains item to collect
     * @param <T>       type if the array (each array must implement Collectable)
     * @return          the same array with the length that is bigger by one
     */
    private static <T extends Collectable> T[] getExtendedArray(T[] array) {
        return Arrays.copyOf(array, array.length + 1);
    }

    /**
     * Generic/Parameterized method
     * Add an item to the array
     *
     * @param array     collectable array to which we add the item
     * @param item      collectable item to add
     * @param <T>       collectable type
     */
    private static <T extends Collectable> void insertItem(T[] array, T item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = item;
                break;
            }
        }
    }

    public static List<String> getWolvenGearMapsString() {
        if (UtilityClass.isArrayEmpty(wolvenGearMaps)) {
            log.severe("wolvenGearMaps is null.");
            return Collections.emptyList();
        }
        return Arrays.stream(wolvenGearMaps).map(Enum::toString).collect(Collectors.toList());
    }

    public static List<String> getGriffinGearMapsString() {
        if (UtilityClass.isArrayEmpty(griffinGearMaps)) {
            log.severe("griffinGearMaps is null.");
            return Collections.emptyList();
        }
        return Arrays.stream(griffinGearMaps).map(Enum::toString).collect(Collectors.toList());
    }
}
