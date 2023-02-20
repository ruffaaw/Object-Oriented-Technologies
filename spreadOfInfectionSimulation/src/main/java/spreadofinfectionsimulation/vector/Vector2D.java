package spreadofinfectionsimulation.vector;

import lombok.Setter;

@Setter
public class Vector2D implements IVector {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[] getComponents() {
        double[] temp = new double[2];
        temp[0] = x;
        temp[1] = y;
        return temp;
    }

    public double abs() {
        return Math.sqrt(x * x + y * y);
    }

    public double cdot(IVector param) {
        return x * param.getComponents()[0] + y * param.getComponents()[1];
    }

}
