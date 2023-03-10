package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player {

    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
        this.isAlive = true;
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.getHealth() > 0;
    }

    @Override
    public void takeDamage(int points) {
        int leftDamage = 0;
        if (this.armor != 0 && this.armor - points >= 0) {

            this.armor -= points;

        } else if (this.armor != 0 && this.armor - points < 0) {

            leftDamage = points - this.armor;
            this.armor = 0;
            if (this.health - leftDamage > 0) {
                this.health -= leftDamage;
            } else if (this.health - leftDamage <= 0) {
                this.health -= leftDamage;
                this.health = 0;
                isAlive = false;
            }

        } else if (this.armor == 0) {

            this.health -= points;
            if (this.health <= 0) {
                this.health = 0;
                isAlive = false;
            }

        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s", this.getClass().getSimpleName(), this.username)).append(System.lineSeparator());
        sb.append(String.format("--Health: %d", this.health)).append(System.lineSeparator());
        sb.append(String.format("--Armor: %d", this.armor)).append(System.lineSeparator());
        sb.append(String.format("--Gun: %s", this.gun.getName()));
        return sb.toString().trim();
    }

}
