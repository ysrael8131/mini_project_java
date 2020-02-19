package Testing.Geometris;

import Elements.Camera;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void findIntersections() {
        Camera camera = new Camera(
                new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -1));
        Triangle triangle1 = new Triangle(new Point3D(0, 1, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
        Triangle triangle2 = new Triangle(new Point3D(0, 10, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
        int x=0;
        int y=0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point3D> list1 = triangle1.FindIntersections(camera.constructRayThroughPixel(3, 3, i, j, 1, 9, 9));
                List<Point3D> list2 = triangle2.FindIntersections(camera.constructRayThroughPixel(3, 3, i, j, 1, 9, 9));

                System.out.println("Exampale 1");
                System.out.println("i:" + i + ". j:" + j);
                System.out.println("size: " + list1.size());
                for (Point3D p1 : list1
                        ) {

                    System.out.println(p1);
                    x++;
                }
                System.out.println("Exampale 2");
                System.out.println("size: " + list2.size());
                System.out.println("i:" + i + ". j:" + j);

                for (Point3D p2 : list2
                        ) {
                    System.out.println(p2);
                    y++;
                }
            }
        }
        assertTrue(x==1);
        assertTrue(y==2);
    }
}
