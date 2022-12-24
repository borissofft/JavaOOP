package L06SOLID.Lab.p02_OpenClosedPrinciple.p01_FileStream;

public class Progress {

    public Progress() {

    }

    public static int getCurrentPercent(StreamableObject streamableObject) {
        return streamableObject.getSent() * 100 / streamableObject.getLength();
    }

}
