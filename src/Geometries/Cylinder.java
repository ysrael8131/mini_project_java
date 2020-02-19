package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.List;

public class Cylinder extends RadialGeometry{
    private Point3D _axisPoint;
    private Vector _axisDirection;
    // ***************** Constructors ********************** //
    public Cylinder() {
        super(0.0);
        this._axisPoint=new Point3D();
        this._axisDirection=new Vector();
    }
    public Cylinder(Cylinder cylinder){
        super(cylinder._radius);
        this._axisPoint=new Point3D(cylinder._axisPoint);
        this._axisDirection=new Vector(cylinder._axisDirection);
    }
    public Cylinder(double radius, Point3D axisPoint, Vector axisDirection){
        super(radius);
        this._axisPoint=new Point3D(axisPoint);
        this._axisDirection=new Vector(axisDirection);
    }
    // ***************** Getters/Setters ********************** //
    public Point3D getAxisPoint(){
        return new Point3D(this._axisPoint);
    }
    public Vector getAxisDirection(){
        return new Vector(this._axisDirection);
    }
    public void setAxisPoint(Point3D axisPoint){
        this._axisPoint=new Point3D(axisPoint);
    }
    public void setAxisDirection(Vector axisDirection){
        this._axisDirection=new Vector(axisDirection);
    }
    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray){
        return null;
    }
    public Vector getNormal(Point3D point){
        return null;
    }
}
