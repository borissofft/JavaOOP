package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {

    private static final double ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, ENERGY);
    }

    @Override
    public void search() {
//        double energy = this.getEnergy() - 7;
//        if (energy < 0) {
//            energy = 0;
//        }
//        this.setEnergy(energy);
        this.setEnergy(Math.max(0, this.getEnergy() - 7));
    }

}
