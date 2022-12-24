package models;

public class RepairImpl implements Repair {

    private String part;
    private int hoursWorked;

    public RepairImpl(String part, int hoursWorked) {
        this.part = part;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return String.format("Part Name: %s Hours Worked: %d", this.part, this.hoursWorked);
    }

}
