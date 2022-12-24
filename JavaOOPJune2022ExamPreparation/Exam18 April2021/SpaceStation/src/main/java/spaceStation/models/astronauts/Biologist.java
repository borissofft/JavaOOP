package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {

    private static final double OXYGEN = 70;

    public Biologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void breath() {
        if (this.getOxygen() - 5 < 0) {
            this.setOxygen(0);
        } else {
            this.setOxygen(this.getOxygen() - 5);
        }
    }
}


