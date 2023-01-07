package model.characters.heroes;

import model.characters.Hero;
import model.characters.kinds.entities.HeroKind;

public class Geralt extends Hero {

    private static final int MAX_HP = 7525;
    private static final int MAX_HIT_RATE = 560;
    private static final int MAX_LEVEL = 70;

    private static final int MIN_HP = 3500;
    private static final int MIN_HIT_RATE = 250;
    private static final int MIN_LEVEL = 1;

    public Geralt(HeroKind heroKind) {
        super(heroKind, MIN_HIT_RATE, MIN_HP);
    }
}
