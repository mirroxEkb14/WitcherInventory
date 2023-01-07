package model.characters.monsters;

import model.characters.Monster;
import model.characters.kinds.creatures.Necrophage;
import model.characters.kinds.entities.MonsterKind;

public class Ghoul extends Monster {

    private static final int MIN_HP = 650;
    private static final int MIN_HIT_RATE = 265;
    private static final int FORTUNE_MARK = 7;

    public Ghoul(MonsterKind monsterKind) {
        super(monsterKind, Necrophage.GHOUL.toString(), MIN_HIT_RATE, MIN_HP, FORTUNE_MARK);
    }
}
