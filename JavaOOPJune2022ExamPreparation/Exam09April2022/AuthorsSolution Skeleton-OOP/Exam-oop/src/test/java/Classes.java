import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Classes {

    public static HashMap<String, Class> allClasses = new HashMap<>();

    static {
        try {
            initClasses();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void initClasses() throws IOException {
        String basePath = System.getProperty("user.dir") + "/src";
        String dir = basePath + "/main/java/fairyShop/";
     //   Files.walk(Paths.get(dir)).filter(f->f.getFileName().toString().endsWith(".java")).forEach(a-> System.out.println(a));
        Files.walk(Paths.get(dir))
                .filter(f -> f.getFileName().toString().endsWith(".java"))
                .forEach(f -> {
                    try {
                        //System.out.println(f.getParent().toString());
                        String packageR = f.getParent().toString().replaceAll("(.*?src\\\\main\\\\java\\\\?)", "");

                      //  packageR.replaceAll("(.*?src\\\\main?)", "");
                        packageR = packageR.replaceAll("\\\\|/", ".");
                        String fl = f.getFileName().toString().replaceAll("\\.java", "");
                        allClasses.put(fl, Class.forName((!packageR.equals("") ? packageR + "." : "") + fl));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
    }
}
