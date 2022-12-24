package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private SupplementRepository supplement;
    private Collection<Field> fields;    // The Field name passed to the methods will always be valid!

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field = null;
        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }

        this.fields.add(field);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplementCreated = null;
        switch (type) {
            case "Powdered":
                supplementCreated = new Powdered();
                break;
            case "Liquid":
                supplementCreated = new Liquid();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        this.supplement.add(supplementCreated);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement foundSupplement = this.supplement.findByType(supplementType);
        if (foundSupplement == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }

        Field field = getFieldByName(fieldName, this.fields);  /////////////////////
        field.addSupplement(foundSupplement);
        this.supplement.remove(foundSupplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }


    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        if (!"Men".equals(playerType) && !"Women".equals(playerType)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        Player player = null;
        switch (playerType) {
            case "Men":
                player = new Men(playerName, nationality, strength);
                break;
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
        }

        Field field = getFieldByName(fieldName, this.fields);

        if ("NaturalGrass".equals(field.getClass().getSimpleName()) && "Men".equals(playerType)) {
            field.addPlayer(player);
        } else if ("ArtificialTurf".equals(field.getClass().getSimpleName()) && "Women".equals(playerType)) {
            field.addPlayer(player);
        } else {
            return ConstantMessages.FIELD_NOT_SUITABLE;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = getFieldByName(fieldName, this.fields);
        field.drag();
        return String.format(ConstantMessages.PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = getFieldByName(fieldName, this.fields);
        int valueOfTheField = field.getPlayers().stream().mapToInt(Player::getStrength).sum();
        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, valueOfTheField);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        for (Field field : this.fields) {
            sb.append(field.getInfo()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }


    private Field getFieldByName(String fieldName, Collection<Field> fields) {
        return this.fields
                .stream()
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }

}
