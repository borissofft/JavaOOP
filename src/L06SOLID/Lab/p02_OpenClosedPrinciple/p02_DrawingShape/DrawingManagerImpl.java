package L06SOLID.Lab.p02_OpenClosedPrinciple.p02_DrawingShape;

import L06SOLID.Lab.p01_SingleResponsibility.p01_DrawingShape.interfaces.DrawingManager;
import L06SOLID.Lab.p01_SingleResponsibility.p01_DrawingShape.interfaces.Shape;


public class DrawingManagerImpl implements DrawingManager {

    @Override
    public void draw(Shape shape) {
        shape.draw();
    }

}
