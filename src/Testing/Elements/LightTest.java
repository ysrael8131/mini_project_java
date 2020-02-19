package Testing.Elements;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.PointLight;
import Elements.SpotLight;
import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LightTest {
    @Test
    public void spotLightTest(){

        Scene scene = new Scene();
        scene.setScreenDistance(100);
        Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),
                new Vector(2, 2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter( "Spot test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void pointLightTest(){

        Scene scene = new Scene();
        scene.setScreenDistance(100);
        Sphere sphere = new Sphere (800, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        scene.addLight(new PointLight(new Color(255,100,100), new Point3D(-200, -200, -100),
                0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter( "Point test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();


    }


    @Test
    public void pointLightTest2(){

        Scene scene = new Scene();
     scene.setScreenDistance(100);
        Triangle triangle = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -1000));

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("Point test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }
    @Test
    public void spotLightTest2(){

        Scene scene = new Scene();
        scene.setScreenDistance(100);
        Sphere sphere = new Sphere(800, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));

        Triangle triangle = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -1000));

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("spot test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }
    @Test
    public void spotLightTest3(){
        Scene scene = new Scene();
       // scene.setCamera(new Camera(new Point3D(0,0,10),new Vector(1,0,0),new Vector(0,0,-1)));
        scene.setScreenDistance(200);
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(
                new Point3D(-105, -205, -200),
                new Point3D(-205, -105, -200),
                new Point3D(-205, -205, -190));

        triangle.setEmmission(new Color (0, 0, 100));
        triangle.setShininess(4);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-500, -500, -100),
                new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Spot test 3", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void shadowTest(){

        Scene scene = new Scene();
       // scene.setCamera(new Camera(new Point3D(0,0,100),new Vector(0,1,0),new Vector(0,0,-1)));
        scene.setScreenDistance(100);
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));

        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -1000));

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("shadow test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }


}
