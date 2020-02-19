package Primitives;

public class Point2D {
    protected Coordinate _x;
    protected Coordinate _y;

    // ***************** Constructors ********************** //
    public Point2D(){
        this._x=new Coordinate();
        this._y=new Coordinate();

    }
    public Point2D(Coordinate x, Coordinate y){
        this._x=new Coordinate(x);
        this._y=new Coordinate(y);

    }
    //check
    public Point2D(Point2D point2D){
        this._x=new Coordinate(point2D._x);
        this._y=new Coordinate(point2D._y);
    }

    // ***************** Getters/Setters ********************** //

    public Coordinate get_x() {
        return _x;
    }

    public void set_x(Coordinate _x) {
        this._x = _x;
    }

    public Coordinate get_y() {
        return _y;
    }

    public void set_y(Coordinate _y) {
        this._y = _y;
    }

    // ***************** Administration ******************** //
    //check
    public int compareTo(Point2D point2D){

        if(this._x.compareTo(point2D._x)==0
                &&this._y.compareTo(point2D._y)==0)
            return 0;
        return 1;
    }

    @Override
    public String toString() {
        return "(" + _x.getCoordinate() + ", " +
                _y.getCoordinate() + ")" ;
    }


}
