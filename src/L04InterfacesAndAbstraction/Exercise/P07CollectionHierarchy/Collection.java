package L04InterfacesAndAbstraction.Exercise.P07CollectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {

    private static final int MAX_SIZE = 100;

    protected int maxSize;
    protected List<String> items;

    public Collection() {
        this.maxSize = MAX_SIZE;
        items = new ArrayList<>();
    }

}
