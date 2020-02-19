package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.List;
import java.util.Map;

public class Quadrangle extends Geometry implements FlatGeometry {

    private Triangle triangle1;
    private  Triangle triangle2;

    public Quadrangle(){
        triangle1 = new Triangle(); //clockwise direction
        triangle2 = new Triangle(); //clockwise direction
    }

    public Quadrangle(Point3D p1, Point3D p2, Point3D p3, Point3D p4){
        triangle1 = new Triangle(p1,p2,p4); //clockwise direction
        triangle2 = new Triangle(p2,p3,p4); //clockwise direction
    }

    public Quadrangle(Quadrangle quadrangle){
        triangle1 = new Triangle(quadrangle.triangle1);
        triangle2 = new Triangle(quadrangle.triangle2);    }

    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        List <Point3D> intersections1 = triangle1.FindIntersections(ray);
        List <Point3D> intersections2 = triangle2.FindIntersections(ray);

        intersections1.addAll(intersections2);
        return intersections1;
    }
// ***************** Getters/Setters ********************** //

    public Point3D getP1() { return new Point3D(triangle1.getP1()); }
    public Point3D getP2() { return new Point3D(triangle1.getP2());}
    public Point3D getP3() { return new Point3D(triangle2.getP2()); }
    public Point3D getP4() { return new Point3D(triangle1.getP3()); }

    public void setP1(Point3D p1) {	this.triangle1.setP1(new Point3D(p1)); }
    public void setP2(Point3D p2) {	this.triangle1.setP2(new Point3D(p2));this.triangle2.setP1(new Point3D(p2));	}
    public void setP3(Point3D p3) {	this.triangle2.setP2(new Point3D(p3));}
    public void setP4(Point3D p4) {	this.triangle1.setP3(new Point3D(p4));this.triangle2.setP3(new Point3D(p4));}

    public Quadrangle(Map<String, String> attributes){

        Point3D _p1,_p2,_p3,_p4;
        String[] P0coordinates = attributes
                .get("P0" ).split("\\s+");

        _p1 = new Point3D(Double.valueOf(P0coordinates[0]),
                Double.valueOf(P0coordinates[1]),
                Double.valueOf(P0coordinates[2]));

        String[] P1coordinates = attributes
                .get("P1" ).split("\\s+");

        _p2 = new Point3D(Double.valueOf(P1coordinates[0]),
                Double.valueOf(P1coordinates[1]),
                Double.valueOf(P1coordinates[2]));

        String[] P2coordinates = attributes
                .get("P2" ).split("\\s+");

        _p3 = new Point3D(Double.valueOf(P2coordinates[0]),
                Double.valueOf(P2coordinates[1]),
                Double.valueOf(P2coordinates[2]));

        String[] P3coordinates = attributes
                .get("P3" ).split("\\s+");

        _p4 = new Point3D(Double.valueOf(P2coordinates[0]),
                Double.valueOf(P2coordinates[1]),
                Double.valueOf(P2coordinates[2]));

        triangle1 = new Triangle(_p1,_p2,_p4); //clockwise direction
        triangle2 = new Triangle(_p2,_p3,_p4); //clockwise direction
    }


    @Override
    public Vector getNormal(Point3D point) {
        return triangle1.getNormal(point);
    }





}
