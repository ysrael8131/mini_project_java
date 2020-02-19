package Primitives;

public class Point3D extends Point2D {
    protected Coordinate _z;

    public Point3D(){
        super();
        this._z=new Coordinate();
    }
    public Point3D(Coordinate x, Coordinate y, Coordinate z){
        super(x,y);
        this._z=new Coordinate(z);
    }
    public Point3D(double x, double y, double z){
        super(new Coordinate(x), new Coordinate(y));
        _z = new Coordinate(z);

    }
    public Point3D(Point3D point3D){
        super(point3D._x,point3D._y);
        this._z=new Coordinate(point3D._z);
    }

    public Coordinate get_z() {
        return _z;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }
    public int compareTo(Point3D point3D){
        if(this._x.compareTo(point3D._x)==0&&
                this._y.compareTo(point3D._y)==0&&
                this._z.compareTo(point3D._z)==0)
            return 0;
        return 1;

    }

    public String toString(){
        return "(" + _x.getCoordinate() + ", " +
                _y.getCoordinate() + ", " +
                _z.getCoordinate() + ")";
    }
    public void add(Vector vector){
        this._x.add(vector.getHead()._x);
        this._y.add(vector.getHead()._y);
        this._z.add(vector.getHead()._z);
    }
    public void subtract(Vector vector){
        this._x.subtract(vector.getHead()._x);
        this._y.subtract(vector.getHead()._y);
        this._z.subtract(vector.getHead()._z);

    }
    public double distance(Point3D point){
        return Math.sqrt(
                Math.pow(this._x.getCoordinate()-point.get_x().getCoordinate(),2)+
                        Math.pow(this._y.getCoordinate()-point.get_y().getCoordinate(),2)+
                        Math.pow(this._z.getCoordinate()-point.get_z().getCoordinate(),2));

    }

}
