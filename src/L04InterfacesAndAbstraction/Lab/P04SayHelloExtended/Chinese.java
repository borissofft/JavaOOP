package L04InterfacesAndAbstraction.Lab.P04SayHelloExtended;

public class Chinese extends BasePerson {

    protected Chinese(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }

}
