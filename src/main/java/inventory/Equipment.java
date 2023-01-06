package inventory;

import model.items.Bomb;
import model.items.Potion;
import utils.*;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static service.UtilityClass.isIntegerNegative;
import static service.UtilityClass.validateSlot;
import static service.logger.LoggerHandler.getLogger;

/**
 * Represents the right side of Witcher inventory
 */

public final class Equipment {

    private static final byte WEAPON_SLOTS_AMOUNT = 4;
    private static final byte ARMOR_SLOTS_AMOUNT = 4;
    private static final byte CONSUMABLES_SLOTS_AMOUNT = 4;
    private static final byte ROACH_SLOTS_AMOUNT = 4;
    private static final byte BOMB_SLOTS_AMOUNT = 2;
    private static final byte QUESTFIND_SLOTS_AMOUNT = 2;

    private final List<Weapon> weaponSlots = Arrays.asList(new Weapon[WEAPON_SLOTS_AMOUNT]);
    private final List<Armor> armorSlots = Arrays.asList(new Armor[ARMOR_SLOTS_AMOUNT]);
    private final List<Consumables> consumablesSlots = Arrays.asList(new Consumables[CONSUMABLES_SLOTS_AMOUNT]);
    private final List<Roach> roachSlots = Arrays.asList(new Roach[ROACH_SLOTS_AMOUNT]);
    private final List<Bomb> bombSlots = Arrays.asList(new Bomb[BOMB_SLOTS_AMOUNT]);
    private final List<QuestFind> pocketSlots = Arrays.asList(new QuestFind[QUESTFIND_SLOTS_AMOUNT]);

    private static final Logger log =
            getLogger(Equipment.class.getSimpleName()).orElseThrow(() -> new IllegalArgumentException("class.getName() not passed"));

    // it's a singleton class - only one instance and a private constructor
    private static Equipment characterInstance = null;

    private int vitality = 100;
    private double toxicity = 0.00;

    private Equipment() {}

    public static Equipment getInstance() {
        if (characterInstance == null)
            characterInstance = new Equipment();

        return characterInstance;
    }

    public String getEquipmentString() {
        return String.format("""
                        Steel weapon: %s
                        Silver weapon: %s
                        Bolts: %s
                        Ranged weapon: %s

                        Chest armor: %s
                        Gloves: %s
                        Trousers: %s
                        Boots: %s

                        Consumables slot1: %s
                        Consumables slot2: %s
                        Consumables slot3: %s
                        Consumables slot4: %s

                        Trophy: %s
                        SaddleBags: %s
                        Saddle: %s
                        Horse blinders: %s

                        Bombs slot1: %s
                        Bombs slot2: %s

                        Pocket slo1: %s
                        Pocket slot2: %s

                        VITALITY %d\tTOXICITY %,.2f""",
                weaponSlots.get(0), weaponSlots.get(1), weaponSlots.get(2), weaponSlots.get(3),
                armorSlots.get(0), armorSlots.get(1), armorSlots.get(2), armorSlots.get(3),
                consumablesSlots.get(0), consumablesSlots.get(1), consumablesSlots.get(2), consumablesSlots.get(3),
                roachSlots.get(0), roachSlots.get(1), roachSlots.get(2), roachSlots.get(3),
                bombSlots.get(0), bombSlots.get(1),
                pocketSlots.get(0), pocketSlots.get(1),
                getVitality(), getToxicity()) + "%";
    }

    public int getVitality() {
        return vitality;
    }

    public void increaseVitality(int degree) {
        if (isIntegerNegative(degree)) {
            log.severe("Integer is negative.");
            return;
        }
        vitality += degree;
    }

    public void decreaseVitality(int degree) {
        if (isIntegerNegative(degree)) {
            log.severe("Integer is negative.");
            return;
        } else if (vitality <= 0) {
            System.err.println("\nThe Witcher is dead:\nVitality: " + vitality);
            System.exit(0);
        }
        vitality -= degree;
    }

    public double getToxicity() {
        return toxicity;
    }

    public void increaseToxicity(int degree) {
        if (isIntegerNegative(degree)) {
            log.severe("Integer is negative.");
            return;
        }
        toxicity += degree;
    }

    public void decreaseToxicity(int degree) {
        if (isIntegerNegative(degree)) {
            log.severe("Integer is negative.");
        } else if (toxicity - degree <= 0) {
            toxicity = 0;
        } else {
            toxicity -= degree;
        }
    }

    public void setWeaponSlot(int slotIndex, Weapon weaponItem) {
        if (validateSlot(slotIndex, weaponItem)) {
            // if there is some item in the slot, put it in the backpack
            if (weaponSlots.get(slotIndex) != null) {
                Backpack.getInstance().add(weaponSlots.get(slotIndex));
            }
            Backpack.getInstance().drop(weaponItem); // remove the item that we want to put in equipment
            weaponSlots.set(slotIndex, weaponItem); // set the item from the backpack
            return;
        }
        log.severe("slotIndex or weaponItem bad input.");
    }

    public void setArmorSlot(int slotIndex, Armor armorItem) {
        if (validateSlot(slotIndex, armorItem)) {
            if (armorSlots.get(slotIndex) != null) {
                Backpack.getInstance().add(armorSlots.get(slotIndex));
            }
            Backpack.getInstance().drop(armorItem);
            armorSlots.set(slotIndex, armorItem);
            return;
        }
        log.severe("slotIndex or armorItem bad input.");
    }

    public void setRoachSlot(int slotIndex, Roach roachItem) {
        if (validateSlot(slotIndex, roachItem)) {
            if (roachSlots.get(slotIndex) != null) {
                Backpack.getInstance().add(roachSlots.get(slotIndex));
            }
            Backpack.getInstance().drop(roachItem);
            roachSlots.set(slotIndex, roachItem);
            return;
        }
        log.severe("slotIndex or roachItem bad input.");
    }

    // NOTE: Consumables can contain either Eatable (Food, Drink) or Potion
    public void setConsumablesSlot(int slotIndex, Consumables consumableItem) {
        if (validateSlot(slotIndex, consumableItem)) {
            if (consumablesSlots.get(slotIndex) != null) {

                if (consumablesSlots.get(slotIndex) instanceof Eatable) {
                    Backpack.getInstance().add((Eatable) consumablesSlots.get(slotIndex));
                } else {
                    Backpack.getInstance().add((Potion) consumablesSlots.get(slotIndex));
                }
            }

            if (consumableItem instanceof Eatable) {
                Backpack.getInstance().drop((Eatable) consumableItem);
            } else {
                Backpack.getInstance().drop((Potion) consumableItem);
            }
            consumablesSlots.set(slotIndex, consumableItem);
            return;
        }
        log.severe("slotIndex or consumableItem bad input.");
    }

    public void setBombSlot(int slotIndex, Bomb bombItem) {
        if (validateSlot(slotIndex, bombItem)) {
            if (bombSlots.get(slotIndex) != null) {
                Backpack.getInstance().add(bombSlots.get(slotIndex));
            }
            Backpack.getInstance().drop(bombItem);
            bombSlots.set(slotIndex, bombItem);
            return;
        }
        log.severe("slotIndex or bombItem bad input.");
    }

    public void setPocketsSlot(int slotIndex, QuestFind questItem) {
        if (validateSlot(slotIndex, questItem)) {
            if (pocketSlots.get(slotIndex) != null) {
                Backpack.getInstance().add(pocketSlots.get(slotIndex));
            }
            Backpack.getInstance().drop(questItem);
            pocketSlots.set(slotIndex, questItem);
            return;
        }
        log.severe("slotIndex or questItem bad input.");
    }

    public List<Weapon> getWeaponSlots() {
        return weaponSlots;
    }

    public List<Armor> getArmorSlots() {
        return armorSlots;
    }

    public List<Roach> getRoachSlots() {
        return roachSlots;
    }

    public List<Consumables> getConsumablesSlots() {
        return consumablesSlots;
    }

    public List<Bomb> getBombSlots() {
        return bombSlots;
    }

    public List<QuestFind> getPocketSlots() {
        return pocketSlots;
    }
}
