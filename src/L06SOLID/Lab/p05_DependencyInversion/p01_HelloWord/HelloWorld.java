package L06SOLID.Lab.p05_DependencyInversion.p01_HelloWord;

import java.time.LocalDateTime;

public class HelloWorld {
    public String greeting(String name, int hour) {
//        if (LocalDateTime.now().getHour() < 12) { // LocalDateTime - не съществува преди Java 8
        if (hour < 12) {
            return "Good morning, " + name;
        }

        if (hour < 18) {
            return "Good afternoon, " + name;
        }

        return "Good evening, " + name;
    }
}
