package word;

public class CutTransform implements TextTransform {

    private StringBuilder lastCut;


    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        this.lastCut = new StringBuilder();  // При всяко ново извикване създаваме празен StringBuilder
        this.lastCut.append(text, startIndex, endIndex);
        text.delete(startIndex, endIndex);
    }

    public StringBuilder getLastCut() {
        return this.lastCut;
    }

}
