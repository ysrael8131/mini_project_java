package Testing.Renderer;


import Elements.PointLight;
import Elements.SpotLight;
import Scene.Scene;

import static org.junit.jupiter.api.Assertions.*;
import Geometries.*;
import Primitives.*;
import Renderer.ImageWriter;
import Renderer.Render;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Iterator;

class RenderTest {
    @Test
    public void basicRendering(){

        Scene scene = new Scene();

        scene.addGeometry(new Sphere(50, new Point3D(0.0, 0.0, -150)));

        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));

        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50);
        render.writeToImage();

    }
    @Test
    public void emmissionTest(){

        Scene scene = new Scene();

        scene.addGeometry(new Sphere(50, new Point3D(0.0, 0.0, -150)));

        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));
        triangle2.setEmmission(Color.blue);
        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));
        triangle3.setEmmission(Color.green);
        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));
        triangle4.setEmmission(Color.red);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("emmission test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50);
        render.writeToImage();
    }

    @Test
    public void squreTest(){
        Scene scene=new Scene();
        scene.setScreenDistance(100);

        Quadrangle squre=new Quadrangle(
                new Point3D( -3500, -3500, -1000),
                new Point3D( -3500,  3500, -2000),
                new Point3D(  3500,  3500, -2000),
                new Point3D(  3500, -3500, -1000));
        squre.setEmmission(Color.red);
        squre.setShininess(20);
        scene.addGeometry(squre);
        ImageWriter imageWriter = new ImageWriter("squre test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();


    }
}


