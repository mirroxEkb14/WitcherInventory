package model.categories;

/**
 * Contains all item types that can be contained in the Backpack. The class is used
 * for creating a map containing the lists from Backpack.java
 *
 * The amount of elements always matches the amount of lists in Backpack.java, because
 * each list there represents type of elements that can be contained in the Backpack
 */
public enum BackpackCategory {
    WEAPON,
    ARMOR,
    ROACH_ITEM,
    FOOD_AND_DRINK,
    POTION,
    OIL,
    BOMB,
    QUEST_ITEM,
    OTHER_ITEM,
    CRAFTING_COMPONENT,
    ALCHEMY_INGREDIENT
}
