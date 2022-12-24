package military;

import military.interfaces.Private;

public class PrivateImpl extends SoldierImpl implements Private { // според входните данни създаваме обекти, съответно класа си остава public,
    // за да може да се създават инстанции от него

    private double salary;

    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Salary: %.2f", this.salary);
    }

}
