package Elements;


import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.Map;
public class Camera {
    //Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;
    //Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;
    // ***************** Constructors ********************** //
    public Camera(){
        _P0=new Point3D(0.0,0.0,10);
        _vUp=new Vector(0.0,1.0,0.0);
        _vTo=new Vector(0.0,0.0,-1.0);
        _vRight=_vTo.crossProduct(_vUp);
    }
    public Camera (Camera camera){
        _P0=new Point3D(camera.getP0());
        _vUp=new Vector(camera.get_vUp());
        _vTo=new Vector(camera.get_vTo());
        _vRight=new Vector(camera.get_vRight());
    }
    public Camera (Point3D P0, Vector vUp, Vector vTo){
        _P0=new Point3D(P0);
        _vUp=new Vector(vUp);
        _vTo=new Vector(vTo);
        _vRight=_vTo.crossProduct(_vUp);
        _vUp=_vRight.crossProduct(_vTo);

        _vUp.normalize();
        _vTo.normalize();
        _vRight.normalize();
    }
    public Camera (Map<String, String> attributes){
        String[] P0params = attributes
                .get("P0").split("\\s+");

        _P0 = new Point3D(Double.valueOf(P0params[0]),
                Double.valueOf(P0params[1]),
                Double.valueOf(P0params[2]));

        String[] vToParam = attributes
                .get("vTo").split("\\s+");
        _vTo = new Vector(Double.valueOf(vToParam[0]),
                Double.valueOf(vToParam[1]),
                Double.valueOf(vToParam[2]));

        String[] vUpParam =  attributes
                .get("vUp").split("\\s+");
        _vUp = new Vector(Double.valueOf(vUpParam[0]),
                Double.valueOf(vUpParam[1]),
                Double.valueOf(vUpParam[2]));

        _vRight = _vUp.crossProduct(_vTo);
        _vUp = _vTo.crossProduct(_vRight);

        _vUp.normalize();
        _vTo.normalize();
        _vRight.normalize();
    }
// ***************** Getters/Setters *********999999999999999999999999999999978************* //
public Vector get_vUp(){
    return new Vector(_vUp);
}
    public void set_vUp(Vector vUp){
        _vUp=new Vector(vUp);
    }
    public Vector get_vTo(){
        return new Vector(_vTo);
    }
    public void set_vTo(Vector vTo){
        _vTo=new Vector(vTo);
    }
    public Point3D getP0(){
        return new Point3D(_P0);
    }
    public void setP0(Point3D P0){
        _P0=new Point3D(P0);
    }
    public Vector get_vRight(){
        return new Vector(_vRight);
    }
    // ***************** Administration ********************** //

    public String toString(){
        return "Vto: "   + _vTo + "\n" +
                "Vup: "   + _vUp + "\n" +
                "Vright:" + _vRight + ".";
    }
    // ***************** Operations ******************** //
    public Ray constructRayThroughPixel (int Nx, int Ny,
                                         double x, double y,
                                         double screenDist,
                                         double screenWidth,
                                         double screenHeight){
        //calculate the image center
        Point3D pc=new Point3D(getP0());
        Vector vTo=new Vector(get_vTo());
        vTo.scale(screenDist);
        pc.add(vTo);

//calculate the rato
        double Rx=screenWidth/Nx;
        double Ry=screenHeight/Ny;
//calculate the point intersection
        Point3D P=new Point3D(pc);
        Vector vRight=new Vector(get_vRight());
        Vector vUp=new Vector(get_vUp());

        vRight.scale(((x-(Nx/2.0))*Rx + Rx/2.0));
        vUp.scale(((y-(Ny/2.0))*Ry+Ry/2.0));

        vRight.subtract(vUp);



        P.add(vRight);
       //P.subtract(vUp);

        Vector v=new Vector(getP0(),P);
        v.normalize();
        Ray ray=new Ray(getP0(),v);

        return ray;
    }
}


