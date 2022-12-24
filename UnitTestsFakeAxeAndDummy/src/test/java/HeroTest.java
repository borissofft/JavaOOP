import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Hero;
import rpg_lab.Target;
import rpg_lab.Weapon;

public class HeroTest {

    /**
     * Тук директно имаме за задача да изтестваме клас Hero. Не е нужно да създаваме отделните обекти и да изтестваме всичките им методи за да стигнем накрая до Hero
     * Трябва да си създадем анонимни(фалшиви) обекти с хардкоднати стойности на това което връщат методите им и директно да пристъпим към тестване на функционалност в Hero
     * За да създадем фалшивите обекти е нужно да създадем техни интерфейси и да ги използваме при създаването на обектите, а не да създаваме конкретен обект
     * Използваме Weapon, а не Axe...
     *
     * !!! Ако създадем реални обекти(конкретика) ако се счупи тест на даден метод в hero, няма как да знаем дали проблема е в hero или в някой от другите обекти!!!!!
     *
     * ОСНОВНИЯТ НЕДОСТАТЪК Е ЧЕ АКО ФАЛШИВИТЕ ОБЕКТИ ИМАТ МНОГО НА БРОЙ МЕТОДИ ЗА @Override, СТАВА МНОГО ТРУДОЕМКО
     * Not suitable for big interfaces
     * ИМА И ПО-ДОБЪР ВАРИАНТ -> ВИЖ ЗАДАЧА НОМЕР 6.MOCKING
     */

    private static final int DEFAULT_EX = 10;
    private static final int DEFAULT_TARGET_HEALTH = 0;
    private static final boolean DEFAULT_IS_TARGET_DEAD = true;
    private static final int DEFAULT_WEAPON_ATTACK_POINTS = 10;
    private static final int DEFAULT_WEAPON_DURABILITY_POINTS = 0;
    private static final String HERO_NAME = "Stamat";

    /**
     * Task 05.Dependency Injection
     */

//    @Test
//    public void attackGainsExperienceIfTargetIsDead() {
//
//        /**
//         * Here we make Target anonymous class
//         */
//
//        Target mockTarget = Mockito.mock(Target.class);
//        Mockito.when(mockTarget.giveExperience()).thenReturn(10);
//        Mockito.when(mockTarget.isDead()).thenReturn(true);
//
//
//        Target target = new Target() {
//            @Override
//            public int getHealth() {
//                return DEFAULT_TARGET_HEALTH;
//            }
//
//            @Override
//            public void takeAttack(int attackPoints) {
//
//            }
//
//            @Override
//            public int giveExperience() {
//                return DEFAULT_EX;
//            }
//
//            @Override
//            public boolean isDead() {
//                return DEFAULT_IS_TARGET_DEAD;
//            }
//        };
//
//        /**
//         * Here we make Weapon anonymous class
//         */
//
//        Weapon weapon = new Weapon() {
//            @Override
//            public int getAttackPoints() {
//                return DEFAULT_WEAPON_ATTACK_POINTS;
//            }
//
//            @Override
//            public int getDurabilityPoints() {
//                return DEFAULT_WEAPON_DURABILITY_POINTS;
//            }
//
//            @Override
//            public void attack(Target target) {
//
//            }
//        };
//
//        Hero hero = new Hero(weapon, HERO_NAME); // точно определен клас
//        hero.attack(target); // точно определена функция
//
//        Assert.assertEquals(DEFAULT_EX, hero.getExperience());
//
//    }

    /**
     * Task 06.Mocking
     */

    @Test
    public void attackGainsExperienceIfTargetIsDead() {

        /**
         * Мокваме даден клас и след това можем да кажем на всяка една функционалност какво да ни връща
         * Особено полезно ако трябва да изтестваме даден обект, като използваме само малък брой функционалности(методи) от дадени интерфейси
         */

        Target mockTarget = Mockito.mock(Target.class);
        Mockito.when(mockTarget.giveExperience()).thenReturn(10);
        Mockito.when(mockTarget.isDead()).thenReturn(true);

        Weapon mockWeapon = Mockito.mock(Weapon.class);
        Mockito.when(mockWeapon.getAttackPoints()).thenReturn(DEFAULT_WEAPON_ATTACK_POINTS);
        Mockito.when(mockWeapon.getDurabilityPoints()).thenReturn(DEFAULT_WEAPON_DURABILITY_POINTS);

        Hero hero = new Hero(mockWeapon, HERO_NAME); // точно определен клас
        hero.attack(mockTarget); // точно определена функция

        Assert.assertEquals(DEFAULT_EX, hero.getExperience());

    }


}
