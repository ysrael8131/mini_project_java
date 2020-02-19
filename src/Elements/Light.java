package Elements;

import Primitives.*;
import java.awt.Color;


  public abstract class   Light extends Exception {

    protected Color _color;
    // ***************** Constructors ********************** //
    public Light(){
        this._color=new Color(0,0,0);
    }
    public Light (Color color){
        this._color=color;
    }

    // ***************** Getters/Setters ********************** //
    public Color getIntensity(){
        return new Color(_color.getRed(),_color.getGreen(),_color.getBlue());
    }

}
