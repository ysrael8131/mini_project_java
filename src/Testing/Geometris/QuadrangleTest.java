package Testing.Geometris;

import Elements.Camera;
import Elements.DirectionalLight;
import Geometries.Quadrangle;
import Geometries.Sphere;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class QuadrangleTest {
    //function that get 8 points and return a cube
    public List<Quadrangle> cube(Point3D A, Point3D B, Point3D C, Point3D D, Point3D E, Point3D F, Point3D G, Point3D H) {

        
        Quadrangle up = new Quadrangle(A, B, F, E);
        up.setEmmission(new Color(70, 39, 128));

        Quadrangle down = new Quadrangle(D, C, G, H);
        down.setEmmission(new Color(74, 228, 100));

        Quadrangle right = new Quadrangle(B, C, G, F);
        right.setEmmission(new Color(132, 24, 16));

        Quadrangle left = new Quadrangle(A, D, H, E);
        left.setEmmission(new Color(132, 24, 16));

        Quadrangle front = new Quadrangle(A, D, C, B);
        front.setEmmission(new Color(83, 73, 228));

        Quadrangle beak = new Quadrangle(E, H, G, F);
        beak.setEmmission(new Color(228, 9, 6));

        List<Quadrangle> temp = new ArrayList<Quadrangle>();
        temp.add(up);
        temp.add(down);
        temp.add(right);
        temp.add(left);
        temp.add(front);
        temp.add(beak);

        return temp;
    }

    @Test
    public void test() {
        Scene scene = new Scene();
        scene.setScreenDistance(100);
    //    scene.setCamera(new Camera(new Point3D(0,0,-6000),new Vector(0,1,0),new Vector(0,0,1)));
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -2000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        List<Quadrangle> table = cube(
                new Point3D(-3000, -1000, -2000),
                new Point3D(-3000, -1000, -3000),
                new Point3D(-3000, -1500, -3000),
                new Point3D(-3000, -1500, -2000),
                new Point3D(3000, -1000, -2000),
                new Point3D(3000, -1000, -3000),
                new Point3D(3000, -1500, -3000),
                new Point3D(3000, -1500, -2000));
        for (Quadrangle quadrangle : table) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> leg1 = cube(
                new Point3D(2000, -1500, -2100),
                new Point3D(2000, -1500, -2200),
                new Point3D(2000, -4000, -2200),
                new Point3D(2000, -4000, -2100),
                new Point3D(2500, -1500, -2100),
                new Point3D(2500, -1500, -2200),
                new Point3D(2500, -4000, -2200),
                new Point3D(2500, -4000, -2100));
        for (Quadrangle quadrangle : leg1) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> leg2 = cube(
                new Point3D(-2500, -1500, -2100),
                new Point3D(-2500, -1500, -2200),
                new Point3D(-2500, -4000, -2200),
                new Point3D(-2500, -4000, -2100),
                new Point3D(-2000, -1500, -2100),
                new Point3D(-2000, -1500, -2200),
                new Point3D(-2000, -4000, -2200),
                new Point3D(-2000, -4000, -2100));
        for (Quadrangle quadrangle : leg2) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> leg3 = cube(
                new Point3D(2000, -1500, -2800),
                new Point3D(2000, -1500, -2900),
                new Point3D(2000, -4000, -2900),
                new Point3D(2000, -4000, -2800),
                new Point3D(2500, -1500, -2800),
                new Point3D(2500, -1500, -2900),
                new Point3D(2500, -4000, -2900),
                new Point3D(2500, -4000, -2800));
        for (Quadrangle quadrangle : leg3) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> leg4 = cube(
                new Point3D(-2500, -1500, -2800),
                new Point3D(-2500, -1500, -2900),
                new Point3D(-2500, -4000, -2900),
                new Point3D(-2500, -4000, -2800),
                new Point3D(-2000, -1500, -2800),
                new Point3D(-2000, -1500, -2900),
                new Point3D(-2000, -4000, -2900),
                new Point3D(-2000, -4000, -2800));
        for (Quadrangle quadrangle : leg4) {
            scene.addGeometry(quadrangle);
        }
        Quadrangle floor = new Quadrangle(
                new Point3D(-12000, -4000, 1000),
                new Point3D(-12000, -4000, -12000),
                new Point3D(12000, -4000, -12000),
                new Point3D(12000, -4000, 1000));
        floor.setEmmission(new Color(63, 31, 25));
        scene.addGeometry(floor);
        List<Quadrangle> cheir1 = cube(
                new Point3D(-800, 250, -3200),
                new Point3D(-800, 250, -3450),
                new Point3D(-800, 0, -3450),
                new Point3D(-800, 0, -3200),
                new Point3D(800, 250, -3200),
                new Point3D(800, 250, -3450),
                new Point3D(800, 0, -3450),
                new Point3D(800, 0, -3200));
        for (Quadrangle quadrangle : cheir1) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> cheir2 = cube(
                new Point3D(-800, 0, -3200),
                new Point3D(-800, 0, -3450),
                new Point3D(-800, -4000, -3450),
                new Point3D(-800, -4000, -3200),
                new Point3D(-550, 0, -3200),
                new Point3D(-550, 0, -3450),
                new Point3D(-550, -4000, -3450),
                new Point3D(-550, -4000, -3200));
        for (Quadrangle quadrangle : cheir2) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> cheir3 = cube(
                new Point3D(550, 0, -3200),
                new Point3D(550, 0, -3450),
                new Point3D(550, -4000, -3450),
                new Point3D(550, -4000, -3200),
                new Point3D(800, 0, -3200),
                new Point3D(800, 0, -3450),
                new Point3D(800, -4000, -3450),
                new Point3D(800, -4000, -3200));
        for (Quadrangle quadrangle : cheir3) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> cheir4 = cube(
                new Point3D(-550, -750, -2600),
                new Point3D(-550, -750, -3800),
                new Point3D(-550, -1000, -3800),
                new Point3D(-550, -1000, -2600),
                new Point3D(550, -750, -2600),
                new Point3D(550, -750, -3800),
                new Point3D(550, -1000, -3800),
                new Point3D(550, -1000, -2600));
        for (Quadrangle quadrangle : cheir4) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> cheir5 = cube(
                new Point3D(-800, -2000, -3000),
                new Point3D(-800, -2000, -3450),
                new Point3D(-800, -2250, -3450),
                new Point3D(-800, -2250, -3000),
                new Point3D(800, -2000, -3000),
                new Point3D(800, -2000, -3450),
                new Point3D(800, -2250, -3450),
                new Point3D(800, -2250, -3000));
        for (Quadrangle quadrangle : cheir5) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> cheir6 = cube(
                new Point3D(-800, -2250, -3000),
                new Point3D(-800, -2250, -3250),
                new Point3D(-800, -4000, -3250),
                new Point3D(-800, -4000, -3000),
                new Point3D(-550, -2250, -3000),
                new Point3D(-550, -2250, -3250),
                new Point3D(-550, -4000, -3250),
                new Point3D(-550, -4000, -3000));
        for (Quadrangle quadrangle : cheir6) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> cheir7 = cube(
                new Point3D(550, -2250, -3000),
                new Point3D(550, -2250, -3250),
                new Point3D(550, -4000, -3250),
                new Point3D(550, -4000, -3000),
                new Point3D(800, -2250, -3000),
                new Point3D(800, -4000, -3250),
                new Point3D(800, -2250, -3250),
                new Point3D(800, -4000, -3000));
        for (Quadrangle quadrangle : cheir7) {
            scene.addGeometry(quadrangle);
        }

        //        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -1100),
//                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));
        scene.addLight(new DirectionalLight(new Color(200, 188, 105), new Vector(-5, -5, -3)));
        ImageWriter imageWriter = new ImageWriter("cube test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
        new Color(1,1,1);
    }

    @Test
    public void test2() {
        Scene scene = new Scene();
        scene.setScreenDistance(100);
//        scene.setCamera(new Camera(new Point3D(0,0,-6000),new Vector(0,1,0),new Vector(0,0,1)));
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -2000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        List<Quadrangle> table = cube(
                new Point3D(-3000, -1000, -2000),
                new Point3D(-3000, -1000, -3000),
                new Point3D(-3000, -1500, -3000),
                new Point3D(-3000, -1500, -2000),
                new Point3D(3000, -1000, -2000),
                new Point3D(3000, -1000, -3000),
                new Point3D(3000, -1500, -3000),
                new Point3D(3000, -1500, -2000));
        for (Quadrangle quadrangle : table) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> leg1 = cube(
                new Point3D(2000, -1500, -2100),
                new Point3D(2000, -1500, -2200),
                new Point3D(2000, -4000, -2200),
                new Point3D(2000, -4000, -2100),
                new Point3D(2500, -1500, -2100),
                new Point3D(2500, -1500, -2200),
                new Point3D(2500, -4000, -2200),
                new Point3D(2500, -4000, -2100));
        for (Quadrangle quadrangle : leg1) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> leg2 = cube(
                new Point3D(-2500, -1500, -2100),
                new Point3D(-2500, -1500, -2200),
                new Point3D(-2500, -4000, -2200),
                new Point3D(-2500, -4000, -2100),
                new Point3D(-2000, -1500, -2100),
                new Point3D(-2000, -1500, -2200),
                new Point3D(-2000, -4000, -2200),
                new Point3D(-2000, -4000, -2100));
        for (Quadrangle quadrangle : leg2) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> leg3 = cube(
                new Point3D(2000, -1500, -2800),
                new Point3D(2000, -1500, -2900),
                new Point3D(2000, -4000, -2900),
                new Point3D(2000, -4000, -2800),
                new Point3D(2500, -1500, -2800),
                new Point3D(2500, -1500, -2900),
                new Point3D(2500, -4000, -2900),
                new Point3D(2500, -4000, -2800));
        for (Quadrangle quadrangle : leg3) {
            scene.addGeometry(quadrangle);
        }
        List<Quadrangle> leg4 = cube(
                new Point3D(-2500, -1500, -2800),
                new Point3D(-2500, -1500, -2900),
                new Point3D(-2500, -4000, -2900),
                new Point3D(-2500, -4000, -2800),
                new Point3D(-2000, -1500, -2800),
                new Point3D(-2000, -1500, -2900),
                new Point3D(-2000, -4000, -2900),
                new Point3D(-2000, -4000, -2800));
        for (Quadrangle quadrangle : leg4) {
            scene.addGeometry(quadrangle);
        }
        Quadrangle floor = new Quadrangle(
                new Point3D(-12000, -4000, 1000),
                new Point3D(-12000, -4000, -12000),
                new Point3D(12000, -4000, -12000),
                new Point3D(12000, -4000, 1000));
        floor.setEmmission(new Color(63, 31, 25));
        scene.addGeometry(floor);
        Quadrangle wall1=new Quadrangle(
                new Point3D(-8000, 4000, 1000),
                new Point3D(-8000, 4000, -12000),
                new Point3D(-8000, -4000, -12000),
                new Point3D(-8000, -4000, 1000));
        wall1.setEmmission(new Color(63, 31, 25));
        scene.addGeometry(wall1);
        Quadrangle wall2=new Quadrangle(
                new Point3D(-8000, 4000, -8000),
                new Point3D(8000, 4000, -8000),
                new Point3D(8000, -4000, -8000),
                new Point3D(-8000, -4000, -8000));
        wall1.setEmmission(new Color(95, 93, 110));
        scene.addGeometry(wall2);
        Quadrangle wall3=new Quadrangle(
                new Point3D(-8000, 4000, 1000),
                new Point3D(-8000, 4000, -8000),
                new Point3D(8000, 4000, -8000),
                new Point3D(8000, 4000, 1000));
        wall1.setEmmission(new Color(95, 93, 110));
        scene.addGeometry(wall3);


        scene.addLight(new DirectionalLight(new Color(200, 188, 105), new Vector(-10, -5, -3)));
        ImageWriter imageWriter = new ImageWriter("cube test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

}
