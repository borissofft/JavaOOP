package L04InterfacesAndAbstraction.Lab.P04SayHelloExtended;

public class Bulgarian extends BasePerson {

    protected Bulgarian(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }

}
