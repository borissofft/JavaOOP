package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        double computerOverallPerformance = super.getOverallPerformance();
        if (this.components.isEmpty()) {
            return computerOverallPerformance;
        } else {
            double averagePerformance = this.components.stream().mapToDouble(Product::getOverallPerformance).average().orElse(0.00);
            return computerOverallPerformance + averagePerformance;
        }
    }

    @Override
    public double getPrice() {
        double computerPrice = super.getPrice();
        double componentsPrice = this.components.stream().mapToDouble(Product::getPrice).sum();
        double peripheralPrice = this.peripherals.stream().mapToDouble(Product::getPrice).sum();
        return computerPrice + componentsPrice + peripheralPrice;
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        Component foundComponent = this.components.stream().filter(component1 -> component1.getClass().getSimpleName().equals(component.getClass().getSimpleName())).findFirst().orElse(null);
        if (foundComponent != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT, component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component foundComponent = this.components.stream().filter(component -> component.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);
        if (this.components.isEmpty() || foundComponent == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT, componentType, this.getClass().getSimpleName(), this.getId()));
        }
        this.components.remove(foundComponent);
        return foundComponent;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        Peripheral foundPeripheral = this.peripherals.stream().filter(peripheral1 -> peripheral1.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())).findFirst().orElse(null);
        if (foundPeripheral != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral foundPeripheral = this.peripherals.stream().filter(peripheral -> peripheral.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);
        if (this.peripherals.isEmpty() || foundPeripheral == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL, peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.remove(foundPeripheral);
        return foundPeripheral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append(System.lineSeparator());
        sb.append(String.format(" Components (%d):", this.components.size())).append(System.lineSeparator());
        for (Component component : this.components) {
            sb.append("  ").append(component.toString()).append(System.lineSeparator());
        }

        double averagePerformance = this.peripherals.stream().mapToDouble(Product::getOverallPerformance).average().orElse(0.00);

        sb.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f):", this.peripherals.size(), averagePerformance)).append(System.lineSeparator());

        for (Peripheral peripheral : this.peripherals) {
            sb.append("  ").append(peripheral.toString());
        }

        return sb.toString().trim();
    }

}
