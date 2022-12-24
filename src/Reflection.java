import java.io.Serializable;

/**
 * При Reflection API не е нужно да създаваме инстанция от даден обект, за да имаме достъп до него и всичките му полета, методи, конструктори ...
 * можем да променим например дефолтни стойности на полета, още преди да сме компилирали дадения Class, това директно чупи всякаква енкапсулация
 */

    public class Reflection implements Serializable {

    private static final String nickName = "Pinguin";
    public String name;
    protected String webAddress;
    String email;
    private int zip;

    public Reflection() {
        this.setName("Java");
        this.setWebAddress("oracle.com");
        this.setEmail("mail@oracle.com");
        this.setZip(1407);
    }

    private Reflection(String name, String webAddress, String email) {
        this.setName(name);
        this.setWebAddress(webAddress);
        this.setEmail(email);
        this.setZip(2300);
    }

    protected Reflection(String name, String webAddress, String email, int zip) {
        this.setName(name);
        this.setWebAddress(webAddress);
        this.setEmail(email);
        this.setZip(2300);
    }

    public final String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    protected String getWebAddress() {
        return this.webAddress;
    }

    private void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected final int getZip() {
        return this.zip;
    }

    private void setZip(int zip) {
        this.zip = zip;
    }

    public String toString() {
        String result = "Name: " + getName() + "\n";
        result += "WebAddress: " + getWebAddress() + "\n";
        result += "email: " + getEmail() + "\n";
        result += "zip: " + getZip() + "\n";
        return result;
    }

}
