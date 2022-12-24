package glacialExpedition.models.states;

import java.util.ArrayList;
import java.util.Collection;

public class StateImpl implements State { // Holds information about the exhibits that can be found and explored.

    private String name;
    private Collection<String> exhibits;

    public StateImpl(String name) {
        this.name = name;
        this.exhibits = new ArrayList<>();
    }

    @Override
    public Collection<String> getExhibits() {
        return this.exhibits;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
