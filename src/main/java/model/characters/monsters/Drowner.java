package model.characters.monsters;

import model.characters.Monster;
import model.characters.kinds.creatures.Necrophage;
import model.characters.kinds.entities.MonsterKind;

public class Drowner extends Monster {

    private static final int MIN_HP = 550;
    private static final int MIN_HIT_RATE = 165;
    private static final int FORTUNE_MARK = 5;

    public Drowner(MonsterKind monsterKind) {
        super(monsterKind, Necrophage.DROWNER.toString(), MIN_HIT_RATE, MIN_HP, FORTUNE_MARK);
    }
}
