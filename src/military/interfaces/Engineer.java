package military.interfaces;

import models.Repair;

import java.util.Collection;


public interface Engineer {

    void addRepair(Repair repair);

    Collection<Repair> getRepairs();

}
