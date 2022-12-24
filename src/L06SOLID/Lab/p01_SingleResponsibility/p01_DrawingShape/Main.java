package L06SOLID.Lab.p01_SingleResponsibility.p01_DrawingShape;

import L06SOLID.Lab.p01_SingleResponsibility.p01_DrawingShape.interfaces.DrawingManager;
import L06SOLID.Lab.p01_SingleResponsibility.p01_DrawingShape.interfaces.Shape;

public class Main {
    public static void main(String[] args) {

        DrawingManager drawingManager = new DrawingManagerImpl();
        Shape shape = new Rectangle(1.5, 20);
        drawingManager.draw(shape);

    }
}
