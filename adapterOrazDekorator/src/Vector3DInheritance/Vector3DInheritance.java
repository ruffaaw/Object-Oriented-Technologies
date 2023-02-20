package Vector3DInheritance;

import Vector2D.Vector2D;
import Vector2D.IVector;

public class Vector3DInheritance extends Vector2D {

    private double z;

    public Vector3DInheritance(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double abs(){
        return Math.sqrt(this.getComponents()[0]*this.getComponents()[0] + this.getComponents()[1]*this.getComponents()[1] + z*z);
    }

    public double cdot(IVector param){
        return getComponents()[0] * param.getComponents()[0] + getComponents()[1] * param.getComponents()[1] + z * 0;
    }

    public double[] getComponents(){
        double[] tmp = new double[3];
        tmp[0] = super.getComponents()[0];
        tmp[1] = super.getComponents()[1];
        tmp[2] = z;
        return tmp;
    }

    public Vector3DInheritance cross(IVector param){
        double newX = getComponents()[1] * param.getComponents()[2] - z * param.getComponents()[1];
        double newY = z * param.getComponents()[0] - getComponents()[0] * param.getComponents()[2];
        double newZ = getComponents()[0] * param.getComponents()[1] - getComponents()[1] * param.getComponents()[0];
        return new Vector3DInheritance(newX, newY, newZ);
    }

}
