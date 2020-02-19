package Primitives;


public class Coordinate implements Comparable<Coordinate>{
    private double coordiannte;

// ***************** Constructors ********************** //

    public Coordinate(double coordiannte) {
        this.coordiannte = coordiannte;
    }

    public Coordinate() {
        this.coordiannte=0.0;
    }

    public Coordinate(Coordinate coordiannte) {
        this.coordiannte=coordiannte.coordiannte;

    }
    // ***************** Getters/Setters ********************** //
    public double getCoordinate() {
        return coordiannte;
    }

    public void setCoordinate(double coordiannte) {
        this.coordiannte = coordiannte;
    }

    // ***************** Administration ******************** //
    @Override
    public int compareTo(Coordinate coor) {
        return Double.compare(this.coordiannte, coor.coordiannte);
    }

    // ***************** Operations ******************** //
    public void add(Coordinate coordinate){
        this.coordiannte+=coordinate.coordiannte;

    }
    public void subtract (Coordinate coordinate){
        this.coordiannte-=coordinate.coordiannte;

    }
}
