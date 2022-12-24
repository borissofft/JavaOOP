package L06SOLID.Lab.p02_OpenClosedPrinciple.p01_FileStream;

public class Main {
    public static void main(String[] args) {

        StreamableObject file = new File();
        StreamableObject music = new Music();

        System.out.println(Progress.getCurrentPercent(file));
        System.out.println(Progress.getCurrentPercent(music));

    }
}

