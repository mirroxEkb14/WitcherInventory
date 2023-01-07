package model.characters.monsters;

import model.characters.Monster;
import model.characters.kinds.creatures.Ogroid;
import model.characters.kinds.entities.MonsterKind;

public class Nekker extends Monster {
    private static final int MIN_HP = 450;
    private static final int MIN_HIT_RATE = 105;
    private static final int FORTUNE_MARK = 10;

    public Nekker(MonsterKind monsterKind) {
        super(monsterKind, Ogroid.NEKKER.toString(), MIN_HIT_RATE, MIN_HP, FORTUNE_MARK);
    }
}
