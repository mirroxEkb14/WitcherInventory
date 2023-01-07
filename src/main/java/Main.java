
import model.characters.Hero;
import model.characters.Monster;
import model.characters.heroes.Geralt;
import model.characters.kinds.entities.HeroKind;
import model.characters.kinds.entities.MonsterKind;
import model.characters.monsters.*;
import model.items.*;
import model.items.otherfinds.Currency;
import model.items.questfinds.maps.GriffinSchoolGearMaps;
import model.items.questfinds.maps.WolvenSchoolGearMaps;
import model.items.weapons.*;
import model.items.armors.*;
import model.items.roachequipment.*;
import model.items.eatable.*;
import model.items.questfinds.*;
import model.items.otherfinds.*;
import model.items.craftings.*;
import model.items.alchemies.*;
import inventory.Backpack;
import inventory.Equipment;
import service.CollectionService;
import service.Printer;
import service.output.FileService;
import utils.collections.Collectable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zea1ot 1/5/2023
 */
public class Main {

    private static final Backpack witcherBackpack = Backpack.getInstance();
    private static final Equipment witcherEquipment = Equipment.getInstance();

    public static void main(String[] args) {
        // show Backpack and Equipment
        fillBackpack();
        System.out.printf("\n\nWitcher's backpack before equipping: \n%s\n", witcherBackpack.getBackpackString());
        equipCharacter();
        System.out.printf("\n\nWitcher's equipped: \n%s\n", witcherEquipment.getEquipmentString());
        System.out.printf("\n\nWitcher's backpack after equipping: \n%s\n", witcherBackpack.getBackpackString());

        // show stats
        System.out.printf("\n\nStats before drinking potion: \nHP %d\tXP %,.2f\n",
                witcherEquipment.getVitality(), witcherEquipment.getToxicity());
        imitatePotionDrinking();
        System.out.printf("Stats after drinking potion : \nHP %d\tXP %,.2f%s\n",
                witcherEquipment.getVitality(), witcherEquipment.getToxicity(), "%");

        // write/read the inventory
        FileService.saveBackpack();
        System.out.println("\n\nBackpack - file data:\n" + FileService.readBackpack());

        // add collectable items to Backpack
        imitateTakingItems();
        System.out.printf("\n\nWitcher's backpack after taking items: \n%s\n", witcherBackpack.getBackpackString());

        // show collectable items from Backpack
        System.out.printf("\n\nWolven gear collection: %s\n", CollectionService.getWolvenGearMapsString());
        System.out.printf("Griffin gear collection: %s\n", CollectionService.getGriffinGearMapsString());

        // init monsters and heroes
        List<Monster> monsters = getMonsters();
        Geralt witcher = new Geralt(HeroKind.GERALT_OF_RIVIA);

        // print all the monsters and heroes to the console
        System.out.println("\n");
        Printer<Geralt> geraltPrinter = new Printer<>(witcher);
        geraltPrinter.print();
        for (Monster monster : monsters) {
            Printer<Monster> monsterPrinter = new Printer<>(monster);
            monsterPrinter.print();
        }

        // count each kind of monster
        Map<MonsterKind, Long> groupedMonsters = monsters.stream()
                .collect(Collectors.groupingBy(Monster::getMonsterKind, Collectors.counting()));
        System.out.println("\n\nMonsters amount according to their kind: " + groupedMonsters);

        // sort all the monsters in alphabetical order by their name (Comparable<T>)
        Collections.sort(monsters);
        System.out.println("\n\nMonsters after sorted in alphabetical order: ");
        for (Monster monster : monsters) {
            Printer<Monster> monsterPrinter = new Printer<>(monster);
            monsterPrinter.print();
        }

        // sort the monsters by HP
        monsters.sort(new Monster.HPComparator());
        System.out.println("\n\nMonsters after sorted by their HP: ");
        for (Monster monster : monsters) {
            Printer<Monster> monsterPrinter = new Printer<>(monster);
            monsterPrinter.print();
        }

        // sort the monsters by hitRate
        monsters.sort(new Monster.HitRateComparator());
        System.out.println("\n\nMonsters after sorted by the degree of danger (their attack amount): ");
        for (Monster monster : monsters) {
            Printer<Monster> monsterPrinter = new Printer<>(monster);
            monsterPrinter.print();
        }

        // print the hero and monster after the battle
        System.out.println("\n\n============ BattleField ============");
        Monster fightingMonster = monsters.get(0);
        imitateBattle(witcher, monsters.get(0));
        System.out.print("\nHero after the battle: ");
        geraltPrinter.print();
        System.out.print("Monster after the battle: ");
        new Printer<>(fightingMonster).print();
        System.out.println("\n============ BattleField ============");
    }

    private static void imitateBattle(Hero hero, Monster monster) {

        // FIGHT!
        while (hero.isAlive() && monster.isAlive()) {
            int hitChance = Randomizer.getHitChange();

            if (hitChance == 0) {
                boolean hitResult = monster.hit(hero);
                if (hitResult) {
                    System.out.printf("\n%s hits %s\n%dhp - %dhp\n",
                            monster.getName(), hero.getHeroKind(), monster.getHp(), hero.getHp());
                } else {
                    System.out.printf("\n%s missed!\n", hero.getHeroKind());
                }

            } else if (hitChance == 1) {
                boolean hitResult = hero.hit(monster);
                if (hitResult) {
                    System.out.printf("\n%s hits %s\n%dhp - %dhp\n",
                            hero.getHeroKind(), monster.getName(), hero.getHp(), monster.getHp());
                } else {
                    System.out.printf("\n%s missed!\n", hero.getHeroKind());
                }
            }
        }

        // who's won?
        if (hero.isDead()) {
            System.out.printf("\n%s died bravely.\n", hero.getHeroKind());
        } else {
            System.out.printf("\n%s defeated %s.\n", hero.getHeroKind(), monster.getName());
        }
    }

    private static ArrayList<Monster> getMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();

        Nekker nekker = new Nekker(MonsterKind.OGROID);
        Drowner drowner = new Drowner(MonsterKind.NECROPHAGE);
        Ghoul ghoul = new Ghoul(MonsterKind.NECROPHAGE);
        Alghoul alghoul = new Alghoul(MonsterKind.NECROPHAGE);
        IceGiant iceGiant = new IceGiant(MonsterKind.OGROID);

        monsters.add(nekker);
        monsters.add(drowner);
        monsters.add(ghoul);
        monsters.add(alghoul);
        monsters.add(iceGiant);

        return monsters;
    }

    private static void imitateTakingItems() {
        Object[] drops = new Object[]{
            WolvenSchoolGearMaps.WELLPRESERVED_NOTES_BY_HIERONYMUS_ON_THE_WITCHER_ELGAR,
            WolvenSchoolGearMaps.SLIGHTLY_TORN_NOTES_BY_HIERONYMUS_ON_THE_WITCHER_ELGAR,
            WolvenSchoolGearMaps.DAMP_MOLDY_NOTES_BY_HIERONYMUS_ON_THE_WITCHER_ELGAR,
            WolvenSchoolGearMaps.HEAVILY_FADED_NOTES_BY_HIERONYMUS_ON_THE_WITCHER_ELGAR,
            WolvenSchoolGearMaps.WORNOUT_AND_FADED_NOTES_BY_HIERONYMUS_ON_THE_WITCHER_ELGAR,
            WolvenSchoolGearMaps.NOTES_ON_PARCHMENT_BY_HIERONYMUS_ON_ELGAR_THE_WITCHER,

            GriffinSchoolGearMaps.EDWIN_GRELOFF_FIRST_MAP,
            GriffinSchoolGearMaps.EDWIN_GRELOFF_SECOND_MAP,
            GriffinSchoolGearMaps.EDWIN_GRELOFF_THIRD_MAP,
            GriffinSchoolGearMaps.EDWIN_GRELOFF_FOURTH_MAP
        };

        for (Object drop : drops) {
            if (CollectionService.isCollectable(drop)) {
                CollectionService.addCollectionItem((Collectable) drop);
            }
            witcherBackpack.addObject(drop);
        }
    }

    private static void imitatePotionDrinking() {
        witcherEquipment.increaseToxicity(84);
        witcherEquipment.decreaseToxicity(10);
    }

    private static void equipCharacter() {
        witcherEquipment.setWeaponSlot(0, SteelWeapon.TEIGR);
        witcherEquipment.setWeaponSlot(1, SilverWeapon.WOLVEN_MASTERCRAFTED);
        witcherEquipment.setWeaponSlot(2, Bolts.EXPLODING_BOLT);
        witcherEquipment.setWeaponSlot(3, RangedWeapon.URSINE_CROSSBOW);

        witcherEquipment.setArmorSlot(0, ChestArmor.WOLVEN_ARMOR);
        witcherEquipment.setArmorSlot(1, Gloves.GLOVES_FOR_PICKING_MANDRAKE);
        witcherEquipment.setArmorSlot(2, Trousers.FELINE_TROUSERS);
        witcherEquipment.setArmorSlot(3, Boots.WOLVEN_BOOTS);

        witcherEquipment.setConsumablesSlot(0, Food.HONEY_CROISSANT);
        witcherEquipment.setConsumablesSlot(1, Potion.SUPERIOR_CAT);
        witcherEquipment.setConsumablesSlot(2, Drink.WHITE_WOLF);
        witcherEquipment.setConsumablesSlot(3, Potion.SUPERIOR_GOLDEN_ORIOLE);

        witcherEquipment.setRoachSlot(0, Trophy.SILVER_BASILISK_TROPHY);
        witcherEquipment.setRoachSlot(1, SaddleBags.UNDVIK_SADDLEBAGS);
        witcherEquipment.setRoachSlot(2, Saddle.SIR_GERALT_OF_RIVIAS_SADDLE);
        witcherEquipment.setRoachSlot(3, HorseBlinders.ZERRIKANIAN_BLINDERS);

        witcherEquipment.setBombSlot(0, Bomb.DIMERITUM_BOMB);
        witcherEquipment.setBombSlot(1, Bomb.DEVILS_PUFFBALL);

        witcherEquipment.setPocketsSlot(0, Mask.CEREMONIAL_MASK);
        witcherEquipment.setPocketsSlot(1, Key.KEY_TO_YENNEFERS_ROOM);
    }

    private static void fillBackpack() {
        // add weapons
        witcherBackpack.add(SteelWeapon.WOLVEN_STEEL_SWORD);
        witcherBackpack.add(SteelWeapon.MANTICORE_STEEL_SWORD);
        witcherBackpack.add(SteelWeapon.TEIGR);
        witcherBackpack.add(SilverWeapon.WOLVEN_MASTERCRAFTED);
        witcherBackpack.add(SilverWeapon.AERONDIGHT);
        witcherBackpack.add(SilverWeapon.HARPY);
        witcherBackpack.add(Bolts.TRACKING_BOLT);
        witcherBackpack.add(Bolts.EXPLODING_BOLT);
        witcherBackpack.add(Bolts.SPLIT_BOLT);
        witcherBackpack.add(RangedWeapon.URSINE_CROSSBOW);
        witcherBackpack.add(RangedWeapon.FELINE_CROSSBOW);

        // add armors
        witcherBackpack.add(ChestArmor.WOLVEN_ARMOR);
        witcherBackpack.add(ChestArmor.URSINE_ARMOR);
        witcherBackpack.add(ChestArmor.FELINE_ARMOR);
        witcherBackpack.add(Gloves.WOLVEN_GAUNTLETS);
        witcherBackpack.add(Gloves.URSINE_GAUNTLETS);
        witcherBackpack.add(Gloves.FELINE_GAUNTLETS);
        witcherBackpack.add(Gloves.GLOVES_FOR_PICKING_MANDRAKE);
        witcherBackpack.add(Trousers.WOLVEN_TROUSERS);
        witcherBackpack.add(Trousers.URSINE_TROUSERS);
        witcherBackpack.add(Trousers.FELINE_TROUSERS);
        witcherBackpack.add(Boots.WOLVEN_BOOTS);
        witcherBackpack.add(Boots.URSINE_BOOTS);
        witcherBackpack.add(Boots.FELINE_BOOTS);

        // add roach items
        witcherBackpack.add(Trophy.SILVER_BASILISK_TROPHY);
        witcherBackpack.add(Trophy.GRIFFIN_TROPHY);
        witcherBackpack.add(Trophy.ARACHAS_TROPHY);
        witcherBackpack.add(SaddleBags.TEMERIAN_SADDLEBAGS);
        witcherBackpack.add(SaddleBags.UNDVIK_SADDLEBAGS);
        witcherBackpack.add(Saddle.SIR_GERALT_OF_RIVIAS_SADDLE);
        witcherBackpack.add(Saddle.NILFGAARDIAN_SADDLE);
        witcherBackpack.add(HorseBlinders.ZERRIKANIAN_BLINDERS);
        witcherBackpack.add(HorseBlinders.UNDVIK_HORSE_BLINDERS);

        // add eatable items
        witcherBackpack.add(Food.HONEY_CROISSANT);
        witcherBackpack.add(Food.JAR_OF_HONEY);
        witcherBackpack.add(Food.APPLE);
        witcherBackpack.add(Food.FISH);
        witcherBackpack.add(Drink.GERALT_OF_RIVIA);
        witcherBackpack.add(Drink.BUTCHER_OF_BLAVIKEN);
        witcherBackpack.add(Drink.WHITE_WOLF);
        witcherBackpack.add(Drink.RIVIAN_KRIEK);

        // add potions
        witcherBackpack.add(Potion.SUPERIOR_CAT);
        witcherBackpack.add(Potion.SUPERIOR_GOLDEN_ORIOLE);
        witcherBackpack.add(Potion.FOGLET_DECOCTION);
        witcherBackpack.add(Potion.COCKATRICE_DECOCTION);

        // add oils
        witcherBackpack.add(Oil.SPECTER_OIL);
        witcherBackpack.add(Oil.ELEMENTAL_OIL);
        witcherBackpack.add(Oil.CURSED_OIL);
        witcherBackpack.add(Oil.SUPERIOR_HANGED_MANS_VENOM);

        // add bombs
        witcherBackpack.add(Bomb.SUPERIOR_NOTHERN_WIND);
        witcherBackpack.add(Bomb.DEVILS_PUFFBALL);
        witcherBackpack.add(Bomb.DIMERITUM_BOMB);

        // add quest items
        witcherBackpack.add(Book.A_MIRACULOUS_GUIDE_TO_GWENT);
        witcherBackpack.add(Mask.TESHAM_MUTNA_MASK);
        witcherBackpack.add(Mask.CEREMONIAL_MASK);
        witcherBackpack.add(Key.KEY_TO_YENNEFERS_ROOM);
        witcherBackpack.add(Key.BEDROOM_KEY);
        witcherBackpack.add(Key.GATE_KEY);
        witcherBackpack.add(Key.CELL_KEY);
        witcherBackpack.add(Key.LABORATORY_KEY);

        // add other items
        witcherBackpack.add(Currency.ORENS);
        witcherBackpack.add(Currency.FLORENS);
        witcherBackpack.add(Junk.SILVER_RUBY_NECKLACE);
        witcherBackpack.add(Junk.GOLD_RUBY_RING);
        witcherBackpack.add(Junk.TABLEWARE);
        witcherBackpack.add(Other.XENOVOX);
        witcherBackpack.add(Other.LETTER_TO_TAMARA);

        // add crafting items
        witcherBackpack.add(Ingot.STEEL_INGOT);
        witcherBackpack.add(Ingot.SILVER_INGOT);
        witcherBackpack.add(Plate.DIMERITIUM_PLATE);
        witcherBackpack.add(Plate.DARK_STEEL_PLATE);
        witcherBackpack.add(MonsterDrop.VENOM_EXTRACT);
        witcherBackpack.add(MonsterDrop.MONSTER_BRAIN);
        witcherBackpack.add(MonsterDrop.MONSTER_TOOTH);
        witcherBackpack.add(MonsterDrop.MONSTER_BLOOD);

        // add alchemy items
        witcherBackpack.add(Plant.WOLFSBANE);
        witcherBackpack.add(Plant.VERBENA);
        witcherBackpack.add(Plant.BALISSE_FRUIT);
        witcherBackpack.add(Mutagen.BLUE_MUTAGEN);
        witcherBackpack.add(Mutagen.LESSER_GREEN_MUTAGEN);
        witcherBackpack.add(Mutagen.GREATER_RED_MUTAGEN);
    }

    /**
     * Inner/Nested class
     * Contains methods that are not related to the game topic
     */
    private static class Randomizer {

        private static final Random random = new Random();

        // returns either 0 or 1
        // 0 - monster hits, 1 - hero hits
        public static int getHitChange() {
            return random.nextInt(2);
        }
    }
}
