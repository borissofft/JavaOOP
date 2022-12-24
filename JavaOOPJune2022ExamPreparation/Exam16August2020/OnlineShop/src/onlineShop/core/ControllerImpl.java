package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Map<Integer, Computer> computers;
    private Map<Integer, Component> components;
    private Map<Integer, Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new LinkedHashMap<>();
        this.components = new LinkedHashMap<>();
        this.peripherals = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer = null;
        switch (computerType) {
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

        if (this.computers.containsKey(id)) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
        }

        this.computers.put(id, computer);
        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }


    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        checkExistingComputerById(computerId);

        Peripheral peripheral = null;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }

        if (this.peripherals.containsKey(id)) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }

        Computer computer = this.computers.get(computerId);
        computer.addPeripheral(peripheral);
        this.peripherals.put(id, peripheral);
        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }


    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        checkExistingComputerById(computerId);
        Computer computer = this.computers.get(computerId);
        Peripheral removedPeripheral = computer.removePeripheral(peripheralType);
        this.peripherals.remove(removedPeripheral.getId());
        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, removedPeripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        checkExistingComputerById(computerId);

        Component component = null;
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        if (this.components.containsKey(id)) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }

        Computer computer = this.computers.get(computerId);
        computer.addComponent(component);
        this.components.put(id, component);
        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        checkExistingComputerById(computerId);
        Computer computer = this.computers.get(computerId);
        Component removedComponent = computer.removeComponent(componentType);
        this.components.remove(removedComponent.getId());
        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, removedComponent.getId());
    }

    @Override
    public String buyComputer(int id) {
        checkExistingComputerById(id);
        Computer computer = this.computers.get(id);
        this.computers.remove(id);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> computersFiltered = this.computers.values().stream().filter(computer -> computer.getPrice() <= budget).collect(Collectors.toList());
        if (this.computers.isEmpty() || computersFiltered.size() == 0) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        }

        Computer bestComputerByBudgetAndOverallPerformance = computersFiltered.stream().sorted((f, s) -> Double.compare(s.getOverallPerformance(), f.getOverallPerformance())).findFirst().orElse(null);
        this.computers.remove(bestComputerByBudgetAndOverallPerformance.getId());
        return bestComputerByBudgetAndOverallPerformance.toString();
    }

    @Override
    public String getComputerData(int id) {
        checkExistingComputerById(id);
        Computer computer = this.computers.get(id);
        return computer.toString();
    }

    private void checkExistingComputerById(int id) {
        boolean computerExist = this.computers.containsKey(id);
        if (!computerExist) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
    }

}
