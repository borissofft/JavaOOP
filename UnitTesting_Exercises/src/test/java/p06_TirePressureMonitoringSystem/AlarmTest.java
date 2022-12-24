package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AlarmTest {

    /**
     * Variant 1
     * За да изтестваме алармата ни е нужно да знаем, че сензорът връща точно определена стойност(ако тестът гръмне, значи проблема е в алармата, а не в сензора).
     * За това си създаваме fake сензор чрез Mockito и задаваме точно определена стойност която искаме да върне метода му popNextPressurePsiValue()
     */

    private static final double LOW_PRESSURE_VALUE = 16.0;
    private static final double HIGH_PRESSURE_VALUE = 22.0;
    private static final double PRESSURE_IN_RANGE = 20.0;

    private Sensor sensor;

    @Before
    public void initSensor() {
        this.sensor = Mockito.mock(Sensor.class);
    }


    @Test
    public void alarmShouldBeOnIfLowePressure() {
        Alarm alarm = new Alarm(this.sensor);
        Mockito.when(this.sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_VALUE);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void alarmShouldBeOnIfHighPressure() {
        Alarm alarm = new Alarm(this.sensor);
        Mockito.when(this.sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_VALUE);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void alarmShouldBeOffIfInRange() {
        Alarm alarm = new Alarm(this.sensor);
        Mockito.when(this.sensor.popNextPressurePsiValue()).thenReturn(PRESSURE_IN_RANGE);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }




    /**
     * Variant 2 Reflection
     *
     * В старите издания на курса в класа Alarm имаме следният конструктор:
     * public Alarm() {
     * Sensor sensor = new Sensor();
     * }
     * При създаването на нов обект ние нямаме достъп до полето и до обекта Sensor sensor
     * Затова е нужно да използваме Reflection. Не е добра практика използването на Reflection в Unit тестовете.
     * Reflection се използва най-вече при писането на фреймуърци и библиотеки. Ако трябва да тестваме по абстрактен начин чужд код, при който нямаме достъп
     * до съответните обекти, а само до сигнатурата на неговите интерфейси.
     * Следва вариант с Reflection(добавил съм споменатият конструктор в класа Alarm):
     */

//    @Test
//    public void alarmShouldBeOffIfInRange() throws NoSuchFieldException, IllegalAccessException {
//        Alarm alarm = new Alarm();
//        Field sensorField = Alarm.class.getDeclaredField("sensor"); // Reflection
//        sensorField.setAccessible(true);
//        Sensor sensor = Mockito.mock(Sensor.class);
//        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(PRESSURE_IN_RANGE);
//        sensorField.set(alarm, sensor); // На коя истанция на обекта(alarm), кое поле да се сетне(sensor)
//        Assert.assertFalse(alarm.getAlarmOn());
//    }

}