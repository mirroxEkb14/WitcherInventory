package model.characters;

import model.characters.kinds.entities.MonsterKind;
import utils.Fighter;

import java.util.Comparator;
import java.util.Objects;

/**
 * Contains attributes and methods that all the monsters have
 */
public abstract class Monster extends Entity implements Fighter, Comparable<Monster> {

    private final MonsterKind monsterKind;
    private final String name;
    private final int fortuneMark;

    public Monster(MonsterKind monsterKind, String name, int hitRate, int hp, int fortuneMark) {
        super(hp, hitRate);
        this.monsterKind = monsterKind;
        this.name = name;
        this.fortuneMark = fortuneMark;
    }

    /**
     * Take random number in range 0-hitRate, then add fortuneMark and check
     * either the number is greater than 50% of hitRate (then hit),
     * or the number is less than 50% of hitRate (then miss)
     *
     * @return      either hitRate (successful attack), or 0 (miss)
     */
    @Override
    public boolean hit(Entity entity) {
        int randomNumber = getRandom();
        int attackPower = (randomNumber + fortuneMark > getHitRate() / 2) ? getHitRate() : 0;

        if (attackPower == 0) {
            return false;
        } else {
            entity.setHp(entity.getHp() - attackPower);
            return true;
        }
    }

    // Comparable<T> is used when we want to sort objects
    // compare all monster objects according to their name (in alphabetical order)
    @Override
    public int compareTo(Monster o) {
        // this > o = +
        // this < o = -
        // this == o = 0
        return this.getName().compareTo(o.getName());
    }

    public MonsterKind getMonsterKind() {
        return monsterKind;
    }

    public String getName() {
        return name;
    }

    public int getFortuneMark() {
        return fortuneMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return monsterKind == monster.monsterKind && name.equals(monster.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monsterKind, name);
    }

    /**
     * Comparator<T> is used then we want to sort one object in different ways
     * Contains one single method to compare all monster objects according to their HP
     */
    public static class HPComparator implements Comparator<Monster> {
        @Override
        public int compare(Monster o1, Monster o2) {
            return Integer.compare(o1.getHp(), o2.getHp());
        }
    }

    /**
     * To compare monsters according to the hitRate
     */
    public static class HitRateComparator implements Comparator<Monster> {
        @Override
        public int compare(Monster o1, Monster o2) {
            return Integer.compare(o1.getHitRate(), o2.getHitRate());
        }
    }
}
