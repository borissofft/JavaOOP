package L04InterfacesAndAbstraction.Lab.P03SayHello;

public interface Person {

    String getName();

    default String sayHello() { // In this way you don't have to override it in European class
        return "Hello";
    }

}
