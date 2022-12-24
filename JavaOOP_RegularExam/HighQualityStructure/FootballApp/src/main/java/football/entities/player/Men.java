package football.entities.player;

public class Men extends BasePlayer { // I can only play on NaturalGrass!

    private static final double KILOGRAMS = 85.50;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, strength);
        this.setKg(KILOGRAMS);
    }

    @Override
    public void stimulation() {
        this.setStrength(this.getStrength() + 145);
    }

}
