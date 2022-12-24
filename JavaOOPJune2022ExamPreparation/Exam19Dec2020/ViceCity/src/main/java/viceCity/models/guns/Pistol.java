package viceCity.models.guns;

public class Pistol extends BaseGun {

    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (this.getBulletsPerBarrel() == 0 && this.getTotalBullets() > 0) {
            this.reload();
        }

        if (this.getBulletsPerBarrel() > 0) {
            this.setBulletsPerBarrel(this.getBulletsPerBarrel() - 1);
        }

        return 1;
    }

    private void reload() { // It's added by me
        this.setTotalBullets(this.getTotalBullets() - BULLETS_PER_BARREL);
        this.setBulletsPerBarrel(BULLETS_PER_BARREL);
    }

}
