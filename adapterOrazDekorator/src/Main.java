import Polar2DAdapter.Polar2DAdapter;
import Vector2D.Vector2D;
import Vector3DDecorator.Vector3DDecorator;
import Vector3DInheritance.Vector3DInheritance;
import p2DPolarinheritance.p2DPolarIngeritance;

public class Main {
    public static void main(String[] args) {
        Vector2D vector2D = new Vector2D(1,2);
        Polar2DAdapter polar2DAdapter = new Polar2DAdapter(vector2D);
        p2DPolarIngeritance polarIngeritance= new p2DPolarIngeritance(3,4);
        Vector3DDecorator vector3DDecorator = new Vector3DDecorator(1, 2, 3);
        Vector3DInheritance vector3DInheritance = new Vector3DInheritance(4,5,6);

        System.out.println("Vector2D 1 ["+polar2DAdapter.getComponents()[0] + ","+ polar2DAdapter.getComponents()[1] + "]" );
        System.out.println("Vector2D 2 ["+polarIngeritance.getComponents()[0] + "," + polarIngeritance.getComponents()[1] + "]");
        System.out.println("Vector 3D Dekorator ["+vector3DDecorator.getComponents()[0] + "," + vector3DDecorator.getComponents()[1] + "," + vector3DDecorator.getComponents()[2] + "]");
        System.out.println("Vector 3D Inheritance ["+vector3DInheritance.getComponents()[0] + "," + vector3DInheritance.getComponents()[1] + "," + vector3DInheritance.getComponents()[2] + "]");


        System.out.println("\nVector 2D 1 Dlugosc: " + polar2DAdapter.abs() + " Kat: " + polar2DAdapter.getAngle());
        System.out.println("Vector 2D 2 Dlugosc : " + polarIngeritance.abs() + " Kat: " + polarIngeritance.getAngle());
        System.out.println("Vector 3D Dlugosc: " + vector3DDecorator.abs());
        System.out.println("Vector 3D Dlugosc: " + vector3DInheritance.abs());


        System.out.println("\nIloczyn skalarny Vectora2D 1 i Vectora2D 2: " + polar2DAdapter.cdot(polarIngeritance));
        System.out.println("Iloczyn skalarny Vectora3DDecoratora i Vectora3DInheritance: " + vector3DDecorator.cdot(vector3DInheritance));
        System.out.println("Iloczyn skalarny Vector2D 1 i Vectora3DDecoratora: " + polar2DAdapter.cdot(vector3DDecorator));
        System.out.println("Iloczyn skalarny Vectora2D 1 i Vectora3DInheritance: " + polar2DAdapter.cdot(vector3DInheritance));
        System.out.println("Iloczyn skalarny Vectora2D 2 i Vectora3DInheritance: " + polarIngeritance.cdot(vector3DInheritance));
        System.out.println("Iloczyn skalarny Vectora2D 2 i Vectora3DDecoratora: " + polarIngeritance.cdot(vector3DDecorator));

        Vector3DDecorator vector3DDecorator1 = vector3DDecorator.cross(vector3DInheritance);
        System.out.println("\nIloczyn wektorowy Vectora3DDecoratora i Vectora3DInheritance: x:" + vector3DDecorator1.getComponents()[0] + " y:" + vector3DDecorator1.getComponents()[1] + " z:"+vector3DDecorator1.getComponents()[2]);
        Vector3DInheritance vector3DInheritance1 = vector3DInheritance.cross(vector3DDecorator);
        System.out.println("Iloczyn wektorowy Vectora3DInheritance i Vectora3DDecoratora: x:" + vector3DInheritance1.getComponents()[0] + " y:" + vector3DInheritance1.getComponents()[1] + " z:"+vector3DInheritance1.getComponents()[2]);

    }
}