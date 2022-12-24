package L06SOLID.Lab.p02_OpenClosedPrinciple.p01_FileStream;

public abstract class StreamableObject {

    private int length;
    private int sent;

    public final int getLength() {
        return this.length;
    }

    public final int getSent() {
        return this.sent;
    }

}
