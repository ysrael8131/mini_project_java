package Renderer;
import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;
//import Elements.LightSource;
//import Geometries.FlatGeometry;
import Elements.LightSource;
import Geometries.FlatGeometry;
import Geometries.Geometry;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import Scene.Scene;
public class Render
{
    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;
    // ***************** Constructors ********************** //
    public Render(ImageWriter imageWriter, Scene scene){
        _imageWriter=new ImageWriter(imageWriter);
        _scene=new Scene(scene);
    }
    // ***************** Operations ******************** //


    public void renderImage(){
        for (int i = 0;i<_imageWriter.getHeight();i++){
            for (int j=0;j<_imageWriter.getWidth();j++){

                Ray ray = _scene.getCamera().constructRayThroughPixel(
                        _imageWriter.getNx(),
                        _imageWriter.getNy(),j,i,
                        _scene.getScreenDistance(),
                        _imageWriter.getWidth(),
                        _imageWriter.getHeight());
                Entry<Geometry,Point3D> geometryPoint3DEntry =findClosesntIntersection(ray);

                if (geometryPoint3DEntry==null)
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                else {
                    _imageWriter.writePixel(j,i,calcColor(geometryPoint3DEntry.getKey(),geometryPoint3DEntry.getValue(),ray));
                }
            }
        }
    }

    private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray){

        Map<Geometry, List<Point3D>> intersectionPoints=getSceneRayIntersections(ray);
        if (intersectionPoints.isEmpty()) return null;
        Map<Geometry, Point3D> mapClosestPoint = getClosestPoint(intersectionPoints);

        Entry<Geometry, Point3D> entry = mapClosestPoint.entrySet().iterator().next();
        return entry;
    }

    public void printGrid(int interval){

        int height = _imageWriter.getHeight();
        int width = _imageWriter.getWidth();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, 255, 255, 255);

            }
        }
    }
    public void writeToImage(){
        _imageWriter.writeToimage();
    }

    private Color calcColor(Geometry geometry, Point3D point, Ray ray)
    {
        return calcColor(geometry, point, ray, 0);
    }

    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level) {
        if (level == RECURSION_LEVEL) return new Color(0, 0, 0);

        Color ambientLight = _scene.getAmbientLight().getIntensity();
        Color emissionLight = geometry.getEmmission();
        Iterator<LightSource> lights = _scene.getLightsIterator();
        Color diffuseAndSpecular = new Color(0, 0, 0);

        while (lights.hasNext()){
            LightSource light = lights.next();
            if (!occluded(light, point, geometry)) {
                Color lightIntensity = light.getIntensity(point);

                Color lightDiffuse = calcDiffusiveComp(geometry.getMaterial().get_Kd(),
                        geometry.getNormal(point),
                        light.getL(point),
                        lightIntensity);
                Color lightSpecular = calcSpecularComp(geometry.getMaterial().get_Ks(),
                        new Vector(point, _scene.getCamera().getP0()),
                        geometry.getNormal(point),
                        light.getL(point),
                        geometry.getShininess(),
                        lightIntensity);

                Color lightRef = addColors(lightDiffuse, lightSpecular);
                diffuseAndSpecular = addColors(diffuseAndSpecular, lightRef);
            }
        }
        Color ambientAndEmmision=addColors(ambientLight,emissionLight);

        // Recursive call for a reflected ray
        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(reflectedRay);
        Color reflectedLight=new Color(0,0,0);
        if (reflectedEntry != null) {

            Color reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
            double kr = geometry.getMaterial().get_Kr();
            reflectedLight = new Color(
                    (int) (kr * reflectedColor.getRed()),
                    (int) (kr * reflectedColor.getGreen()),
                    (int) (kr * reflectedColor.getBlue()));
        }
        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(refractedRay);

        Color refractedLight=new Color(0,0,0);
        if (refractedEntry != null) {

            Color refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
            double kt = geometry.getMaterial().get_Kt();
            refractedLight = new Color(
                    (int) (kt * refractedColor.getRed()),
                    (int) (kt * refractedColor.getGreen()),
                    (int) (kt * refractedColor.getBlue()));
        }
        Color reflectedAndRefracted=addColors(reflectedLight,refractedLight);
        return addColors(addColors(ambientAndEmmision,diffuseAndSpecular),reflectedAndRefracted);
    }


    // Recursive
    private Ray constructRefractedRay(Geometry geometry, Point3D point,Ray inRay){

        Vector normal = geometry.getNormal(point);
        normal.scale(-2);
        Point3D p=new Point3D(point);
        p.add(normal);

        if (geometry instanceof FlatGeometry){
            return new Ray (p, inRay.get_direction());
        } else {
            // Here, Snell's law can be implemented.
            // The refraction index of both materials had to be derived
            return new Ray (p, inRay.get_direction());
        }
    }
    private Ray constructReflectedRay(Vector normal, Point3D point,Ray inRay){

        Vector l =new Vector( inRay.get_direction());
        l.normalize();

        Vector N=new Vector(normal);
        N.normalize();
        N.scale(-2 * l.dotProduct(normal));
        l.add(N);

        Vector R = new Vector(l);
        R.normalize();
        Point3D p=new Point3D(point);
        p.add(normal);

        Ray reflectedRay = new Ray(p, R);

        return reflectedRay;
    }
    private boolean occluded(LightSource light, Point3D point,Geometry geometry){
        Vector lightDirection = light.getL(point);
        lightDirection.scale(-1);

        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(geometryPoint));
        epsVector.scale(geometry.getNormal(geometryPoint).dotProduct(lightDirection)>0?2:-2);
        geometryPoint.add(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints =
                getSceneRayIntersections(lightRay);

        // Flat geometry cannot self intersect
        if (geometry instanceof FlatGeometry) {
            intersectionPoints.remove(geometry);}

        for (Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
            if (entry.getKey().getMaterial().get_Kt() == 0)
                return true;
        return false;
    }
    private Color calcSpecularComp(double ks, Vector v, Vector normal,Vector l, double shininess, Color lightIntensity){
        Vector V=new Vector(v);
        Vector N =new Vector(normal);
        Vector L=new Vector(l);
        V.normalize();
        N.normalize();
        L.normalize();

        N.scale(2*L.dotProduct(N));
        Vector R =new Vector(L);
        R.normalize();
        R.subtract(N);
        double Shin=0;
        if (V.dotProduct(R)>0) Shin=Math.pow(V.dotProduct(R),shininess)*ks;
        return new Color((int)(lightIntensity.getRed()*Shin),
                (int)(lightIntensity.getGreen()*Shin),
                (int)(lightIntensity.getBlue()*Shin));
    }

    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity){
        Vector N=new Vector(normal);
        Vector L=new Vector(l);
        N.normalize();
        L.normalize();
        double d= Math.abs(kd * N.dotProduct(L));
        return new Color((int)(lightIntensity.getRed()*d),
                (int)(lightIntensity.getGreen()*d),
                (int)(lightIntensity.getBlue()*d));
    }

    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray)
    {
        Map<Geometry, List<Point3D>> intersectionPoints=new HashMap<Geometry, List<Point3D>>();
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();

        while (geometries.hasNext()) {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints =
                    geometry.FindIntersections(ray);

            if (!geometryIntersectionPoints.isEmpty())
                intersectionPoints.put(geometry,geometryIntersectionPoints);
        }
        return intersectionPoints;
    }

    private Map<Geometry, Point3D> getClosestPoint
            (Map<Geometry,List<Point3D>> intersectionPoints) {

        double distance = Double.MAX_VALUE;
        Point3D P0 = this ._scene.getCamera().getP0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry,Point3D>();

        for (Entry<Geometry,List<Point3D>> mapIntersection:intersectionPoints.entrySet()) {
            for (Point3D point : mapIntersection.getValue()){

                if (P0.distance(point) < distance){
                    minDistancePoint.clear();
                    minDistancePoint.put(mapIntersection.getKey(),point);
                    distance = P0.distance(point);
                }
            }
        }
        return minDistancePoint;
    }

    private Color addColors(Color a, Color b){
        int R = a.getRed() + b.getRed();
        if (R > 255) R = 255;

        int G = a.getGreen() + b.getGreen();
        if (G > 255) G = 255;

        int B = a.getBlue() + b.getBlue();
        if (B > 255) B = 255;

        Color I = new Color (R, G, B);
        return I;
    }
}
