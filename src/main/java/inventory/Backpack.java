package inventory;

import model.categories.BackpackCategory;
import model.items.Bomb;
import model.items.Oil;
import model.items.Potion;
import utils.*;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static service.logger.LoggerHandler.getLogger;

/**
 * Represents the left side of Witcher inventory
 */

public final class Backpack {

    private final ArrayList<Weapon> weaponItems = new ArrayList<>();
    private final ArrayList<Armor> armorItems = new ArrayList<>();
    private final ArrayList<Roach> roachItems = new ArrayList<>();
    private final ArrayList<Eatable> eatables = new ArrayList<>();
    private final ArrayList<Potion> potionItems = new ArrayList<>();
    private final ArrayList<Oil> oilItems = new ArrayList<>();
    private final ArrayList<Bomb> bombItems = new ArrayList<>();
    private final ArrayList<QuestFind> questItems = new ArrayList<>();
    private final ArrayList<OtherFind> otherItems = new ArrayList<>();
    private final ArrayList<CraftingComponent> craftingItems = new ArrayList<>();
    private final ArrayList<AlchemyIngredient> alchemyItems = new ArrayList<>();

    private static final Logger log =
            getLogger(Backpack.class.getSimpleName()).orElseThrow(() -> new IllegalArgumentException("class.getName() not passed"));

    // while playing the game we can only have one backpack that is created at the beginning
    // of the game, so it's a singleton class - only one instance and the constructor is 'private'
    private static Backpack backpackInstance = null;

    private Backpack() {}

    public static Backpack getInstance() {
        if (backpackInstance == null) {
            backpackInstance = new Backpack();
        }
        return backpackInstance;
    }

    public Map<BackpackCategory, List<String>> getBackpack() {
        Map<BackpackCategory, List<String>> backpack = new HashMap<>();
        backpack.put(BackpackCategory.WEAPON, weaponItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.ARMOR, armorItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.ROACH_ITEM, roachItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.FOOD_AND_DRINK, eatables.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.POTION, potionItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.OIL, oilItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.BOMB, bombItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.QUEST_ITEM, questItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.OTHER_ITEM, otherItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.CRAFTING_COMPONENT, craftingItems.stream().map(Object::toString).collect(Collectors.toList()));
        backpack.put(BackpackCategory.ALCHEMY_INGREDIENT, alchemyItems.stream().map(Object::toString).collect(Collectors.toList()));
        return backpack;
    }

    public String getBackpackString() {
        return String.format("""
                        Weapons: %s\t\tArmor: %s
                        Roach: %s
                        Food and drinks: %s\t\tPotions: %s
                        Oils: %s
                        Bombs: %s
                        Quest items: %s\t\tOther items: %s
                        Crafting: %s\t\tAlchemy: %s""",
                Arrays.toString(weaponItems.toArray()), Arrays.toString(armorItems.toArray()),
                Arrays.toString(roachItems.toArray()),
                Arrays.toString(eatables.toArray()), Arrays.toString(potionItems.toArray()),
                Arrays.toString(oilItems.toArray()),
                Arrays.toString(bombItems.toArray()),
                Arrays.toString(questItems.toArray()), Arrays.toString(otherItems.toArray()),
                Arrays.toString(craftingItems.toArray()), Arrays.toString(alchemyItems.toArray()));
    }

    // when we have some array of different objects we need to determine the type
    public void addObject(Object item) {
        if (item instanceof Weapon) {
            weaponItems.add((Weapon) item);
        } else if (item instanceof Armor) {
            armorItems.add((Armor) item);
        } else if (item instanceof Roach) {
            roachItems.add((Roach) item);
        } else if (item instanceof Eatable) {
            eatables.add((Eatable) item);
        } else if (item instanceof Potion) {
            potionItems.add((Potion) item);
        } else if (item instanceof Oil) {
            oilItems.add((Oil) item);
        } else if (item instanceof Bomb) {
            bombItems.add((Bomb) item);
        } else if (item instanceof QuestFind) {
            questItems.add((QuestFind) item);
        } else if (item instanceof OtherFind) {
            otherItems.add((OtherFind) item);
        } else if (item instanceof CraftingComponent) {
            craftingItems.add((CraftingComponent) item);
        } else if (item instanceof AlchemyIngredient) {
            alchemyItems.add((AlchemyIngredient) item);
        }
    }

    public void add(Weapon weaponItem) {
        if (weaponItem == null) {
            log.severe("weaponItem is 'null'");
            return;
        }
        weaponItems.add(weaponItem);
    }

    public void add(Armor armorItem) {
        if (armorItem == null) {
            log.severe("armorItem is 'null'");
            return;
        }
        armorItems.add(armorItem);
    }

    public void add(Roach roachItem) {
        if (roachItem == null) {
            log.severe("roachItem is 'null'");
            return;
        }
        roachItems.add(roachItem);
    }

    public void add(Eatable eatableItem) {
        if (eatableItem == null) {
            log.severe("eatableItem is 'null'");
            return;
        }
        eatables.add(eatableItem);
    }

    public void add(Potion potionItem) {
        if (potionItem == null) {
            log.severe("potionItem is 'null'");
            return;
        }
        potionItems.add(potionItem);
    }

    public void add(Oil oilItem) {
        if (oilItem == null) {
            log.severe("oilItem is 'null'");
            return;
        }
        oilItems.add(oilItem);
    }

    public void add(Bomb bombItem) {
        if (bombItem == null) {
            log.severe("bombItem is 'null'");
            return;
        }
        bombItems.add(bombItem);
    }

    public void add(QuestFind questItem) {
        if (questItem == null) {
            log.severe("questItem is 'null'");
            return;
        }
        questItems.add(questItem);
    }

    public void add(OtherFind otherItem) {
        if (otherItem == null) {
            log.severe("otherItem is 'null'");
            return;
        }
        otherItems.add(otherItem);
    }

    public void add(CraftingComponent craftingItem) {
        if (craftingItem == null) {
            log.severe("craftingItem is 'null'");
            return;
        }
        craftingItems.add(craftingItem);
    }

    public void add(AlchemyIngredient alchemyItem) {
        if (alchemyItem == null) {
            log.severe("alchemyItem is 'null'");
            return;
        }
        alchemyItems.add(alchemyItem);
    }

    public void drop(Weapon weaponItem) {
        if (weaponItem == null) {
            log.severe("weaponItem is 'null'");
            return;
        }
        weaponItems.remove(weaponItem);
    }

    public void drop(Armor armorItem) {
        if (armorItem == null) {
            log.severe("armorItem is 'null'");
            return;
        }
        armorItems.remove(armorItem);
    }

    public void drop(Roach roachItem) {
        if (roachItem == null) {
            log.severe("roachItem is 'null'");
            return;
        }
        roachItems.remove(roachItem);
    }

    public void drop(Eatable eatableItem) {
        if (eatableItem == null) {
            log.severe("eatableItem is 'null'");
            return;
        }
        eatables.remove(eatableItem);
    }

    public void drop(Potion potionItem) {
        if (potionItem == null) {
            log.severe("potionItem is 'null'");
            return;
        }
        potionItems.remove(potionItem);
    }

    public void drop(Oil oilItem) {
        if (oilItem == null) {
            log.severe("oilItem is 'null'");
            return;
        }
        oilItems.remove(oilItem);
    }

    public void drop(Bomb bombItem) {
        if (bombItem == null) {
            log.severe("bombItem is 'null'");
            return;
        }
        bombItems.remove(bombItem);
    }

    public void drop(QuestFind questItem) {
        if (questItem == null) {
            log.severe("questItem is 'null'");
            return;
        }
        questItems.remove(questItem);
    }

    public void drop(OtherFind otherItem) {
        if (otherItem == null) {
            log.severe("otherItem is 'null'");
            return;
        }
        otherItems.remove(otherItem);
    }

    public void drop(CraftingComponent craftingItem) {
        if (craftingItem == null) {
            log.severe("craftingItem is 'null'");
            return;
        }
        craftingItems.remove(craftingItem);
    }

    public void drop(AlchemyIngredient alchemyItem) {
        if (alchemyItem == null) {
            log.severe("alchemyItem is 'null'");
            return;
        }
        alchemyItems.remove(alchemyItem);
    }
}
