package model.characters;

import model.characters.kinds.entities.HeroKind;
import utils.Fighter;

/**
 * Contains attributes and methods that have all the heroes
 */
public abstract class Hero extends Entity implements Fighter {

    private final HeroKind heroKind;
    private int level;

    public Hero(HeroKind heroKind, int hitRate, int hp) {
        super(hp, hitRate);
        this.heroKind = heroKind;
    }

    /**
     * Take random number in range 0-hitRate, then add 7% of hp and check
     * either the number is greater than 50% of hitRate (then hit),
     * or the number is less than 50% of hitRate (then miss)
     *
     * @return      either hitRate (successful attack), or 0 (miss)
     */
    @Override
    public int hit() {
        int randomNumber = getRandom();
        return (randomNumber + getHp() * 0.07 > getHitRate() / 2) ? getHitRate() : 0;
    }

    public HeroKind getHeroKind() {
        return heroKind;
    }

    public int getLevel() {
        return level;
    }
}
