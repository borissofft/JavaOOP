package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer {

    private static final double ENERGY = 100;

    public AnimalExplorer(String name) {
        super(name, ENERGY);
    }

    @Override
    public void search() {
//        double energy = this.getEnergy() - 15;
//        if (energy < 0) {
//            energy = 0;
//        }
//        this.setEnergy(energy);
        this.setEnergy(Math.max(0, this.getEnergy() - 15));
    }

}
