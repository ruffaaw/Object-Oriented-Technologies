package Polar2DAdapter;

import Vector2D.Vector2D;
import Vector2D.IVector;

import java.awt.*;

public class Polar2DAdapter implements IPolar2D, IVector{
    private Vector2D srcVector;

    public Polar2DAdapter(Vector2D vector2D){
        this.srcVector = vector2D;
    }
    public double abs(){
        return this.srcVector.abs();
    }

    public double getAngle(){
        return Math.atan(srcVector.getComponents()[1]/srcVector.getComponents()[0]);
    }

    public double cdot(IVector param){
        return this.srcVector.cdot(param);
    }

    public double[] getComponents(){
        return this.srcVector.getComponents();
    }
}
