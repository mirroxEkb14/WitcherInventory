package model.characters;

import model.characters.kinds.entities.MonsterKind;
import utils.Fighter;

/**
 * Contains attributes and methods that all the monsters have
 */
public abstract class Monster extends Entity implements Fighter {

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
    public int hit() {
        int randomNumber = getRandom();
        return (randomNumber + fortuneMark > getHitRate() / 2) ? getHitRate() : 0;
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
}
