package L04InterfacesAndAbstraction.Lab.P04SayHelloExtended;

public interface Person {

    String getName();

    default String sayHello() {
        return "Hello";
    }

}
