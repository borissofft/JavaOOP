package football.entities.player;

public class Women extends BasePlayer { // I can only play on ArtificialTurf!

    private static final double KILOGRAMS = 60.00;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, strength);
        this.setKg(KILOGRAMS);
    }

    @Override
    public void stimulation() {
        this.setStrength(this.getStrength() + 115);
    }

}
