package p2DPolarinheritance;

import Vector2D.Vector2D;

public class p2DPolarIngeritance extends Vector2D {

    public p2DPolarIngeritance(double x, double y){
        super(x, y);
    }
    public double getAngle(){
        return Math.atan(getComponents()[1]/getComponents()[0]);
    }
}
