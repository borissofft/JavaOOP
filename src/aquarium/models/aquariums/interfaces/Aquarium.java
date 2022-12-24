package aquarium.models.aquariums.interfaces;

import aquarium.models.decorations.interfaces.Decoration;
import aquarium.models.fish.interfaces.Fish;

import java.util.Collection;

public interface Aquarium {
    int calculateComfort();

    String getName();

    int getCapacity();

    void addFish(Fish fish);

    void removeFish(Fish fish);

    void addDecoration(Decoration decoration);

    void feed();

    String getInfo();

    Collection<Fish> getFish();

    Collection<Decoration> getDecorations();
}
