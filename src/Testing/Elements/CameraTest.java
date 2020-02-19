package Testing.Elements;

import Elements.Camera;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {

    @Test
    void constructRayThroughPixel() {
        assertEquals(-1,new Camera(new Camera(new Point3D(),new Vector(0,-1,0),new Vector(0,0,-1))).get_vRight().getHead().get_x().getCoordinate());
        assertEquals(-1,new Camera(new Point3D(),new Vector(0,-1,0),new Vector(0,0,-1)).get_vRight().getHead().get_x().getCoordinate());
        assertEquals(1,new Camera(new Camera()).get_vRight().getHead().get_x().getCoordinate());


        assertEquals(0,new Camera().constructRayThroughPixel(3,3,0,0,1,9,9).get_POO().get_x().getCoordinate());
        assertEquals(0,new Camera().constructRayThroughPixel(3,3,0,0,1,9,9).get_POO().get_y().getCoordinate());
        assertEquals(0,new Camera().constructRayThroughPixel(3,3,0,0,1,9,9).get_POO().get_z().getCoordinate());



        final int WIDTH  = 3;
        final int HEIGHT = 3;

        Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                new Vector (0.0, -1.0, 0.0),
                new Vector (0.0, 0.0, -1.0));

        System.out.println("Camera:\n" + camera);

        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++) {

                Ray ray = camera.constructRayThroughPixel(
                        WIDTH, HEIGHT, i, j, 1, 3 * WIDTH, 3 * HEIGHT);

                Vector vector=new Vector(new Point3D(0, 0, 0), new Point3D(3 - (3 * i), -3 + (3 * j), -1));
                vector.normalize();

                assertEquals(vector.getHead().get_x().getCoordinate(), ray.get_direction().getHead().get_x().getCoordinate(), 0.00000005);

                System.out.println("i:"+i+". j:"+j);
                System.out.println("Expected"+new Vector(new Point3D(0, 0, 0), new Point3D(3 - (3 * i), -3 + (3 * j), -1)));
                System.out.println("actual"+ new Camera().constructRayThroughPixel(3, 3, i, j, 1, 9, 9).get_direction()+'\n');
                //  System.out.println( new Camera().constructRayThroughPixel(3, 3, i, j, 1, 9, 9).get_POO());

            }
        }
    }
}
