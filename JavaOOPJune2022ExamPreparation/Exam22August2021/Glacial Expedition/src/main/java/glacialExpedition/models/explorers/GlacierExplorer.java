package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer {

    private static final double ENERGY = 40;

    public GlacierExplorer(String name) {
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
