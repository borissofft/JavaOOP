package military;

import military.interfaces.LieutenantGeneral;
import military.interfaces.Soldier;

import java.util.ArrayList;
import java.util.List;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {

    private List<Soldier> soldiers;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.soldiers = new ArrayList<>();
    }

    @Override
    public void addPrivate(Soldier p) {
        this.soldiers.add(p);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append("Privates:").append(System.lineSeparator());
        soldiers.sort((f, s) -> s.getId() - f.getId());
        for (Soldier soldier : soldiers) {
            sb.append("  ").append(soldier.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
