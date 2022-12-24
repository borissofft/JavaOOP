package L06SOLID.Lab.p05_DependencyInversion.p01_HelloWord;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {

        HelloWorld helloWorld = new HelloWorld();
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance(); // creates new calendar
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        System.out.println(helloWorld.greeting("Ivan", hour));

        System.out.println(helloWorld.greeting("Milena", LocalDateTime.now().getHour()));

    }
}
