package model.characters.monsters;

import model.characters.Monster;
import model.characters.kinds.creatures.Ogroid;
import model.characters.kinds.entities.MonsterKind;

public class IceGiant extends Monster {

    private static final int MIN_HP = 1550;
    private static final int MIN_HIT_RATE = 365;
    private static final int FORTUNE_MARK = 3;

    public IceGiant(MonsterKind monsterKind) {
        super(monsterKind, Ogroid.ICE_GIANT.toString(), MIN_HIT_RATE, MIN_HP, FORTUNE_MARK);
    }
}
