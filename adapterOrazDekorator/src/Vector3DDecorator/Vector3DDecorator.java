package Vector3DDecorator;

import Vector2D.IVector;
import Vector2D.Vector2D;

public class Vector3DDecorator implements IVector{
    private IVector srcVector;
    private double z;

    public Vector3DDecorator(double x, double y, double z){
        this.srcVector = new Vector2D(x, y);
        this.z = z;
    }

    public double abs(){
        return Math.sqrt(this.srcVector.getComponents()[0]*this.srcVector.getComponents()[0] + this.srcVector.getComponents()[1]*this.srcVector.getComponents()[1] + z*z);
    }

    public double cdot(IVector param){
        return this.srcVector.getComponents()[0] * param.getComponents()[0] + this.srcVector.getComponents()[1] * param.getComponents()[1] + this.z * param.getComponents()[2];
    }

    public double[] getComponents(){
        double[] tmp = new double[3];
        tmp[0] = srcVector.getComponents()[0];
        tmp[1] = srcVector.getComponents()[1];
        tmp[2] = z;
        return tmp;

    }

    public Vector3DDecorator cross(IVector param){
        double newX = srcVector.getComponents()[1] * param.getComponents()[2] - z * param.getComponents()[1];
        double newY = z * param.getComponents()[0] - srcVector.getComponents()[0] * param.getComponents()[2];
        double newZ = srcVector.getComponents()[0] * param.getComponents()[1] - srcVector.getComponents()[1] * param.getComponents()[0];
        return new Vector3DDecorator(newX, newY, newZ);
    }

    public IVector getSrcV(){
        return srcVector;
    }
}
