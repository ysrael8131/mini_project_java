package Elements;

import Primitives.*;

import java.awt.*;


public class DirectionalLight extends Light implements LightSource  {
    private Vector _direction;
    // ***************** Constructors ********************** //
    public DirectionalLight(Color color, Vector direction){
        super(color);
        _direction=new Vector(direction);
        this._direction.normalize();
    }

    // ***************** Getters/Setters ********************** //
    @Override
    public Color getIntensity(Point3D point){
        return  super.getIntensity();
    }
    public Vector getDirection(){
        return new Vector(_direction);
    }
    public void setDirection(Vector _direction)
    {
        this._direction=new Vector(_direction);
        this._direction.normalize();
    }
    @Override
    public Vector getL(Point3D point)
    {
        Vector vector=new Vector(this.getDirection());
        vector.normalize();
        return vector;
    }

}
