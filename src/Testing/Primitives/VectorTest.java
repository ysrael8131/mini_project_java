package Testing.Primitives;

import Primitives.Point3D;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    Point3D point1=new Point3D(-1,2,-3);
    Vector vec1=new Vector(point1);
    Vector vec2=new Vector(3,4,5);

    @Test
    void add() {
        vec1.add(vec2);
        assertTrue(vec1.getHead().get_x().getCoordinate()==1&&vec1.getHead().get_y().getCoordinate()==2
        &&vec1.getHead().get_z().getCoordinate()==3);
    }

    @Test
    void subtract() {
        vec1.subtract(vec2);
        vec2.subtract(vec1);
        assertTrue(vec2.getHead().get_x().getCoordinate()==-1&&vec2.getHead().get_y().getCoordinate()==-2
                &&vec2.getHead().get_z().getCoordinate()==-3);
    }

    @Test
    void scale() {

        vec2.scale(5);
        assertTrue(vec2.getHead().get_x().getCoordinate()==15&&vec2.getHead().get_y().getCoordinate()==20
                &&vec2.getHead().get_z().getCoordinate()==25);

    }

    @Test
    void crossProduct() {

      Vector vec3=new Vector( vec1.crossProduct(vec2)) ;
        assertTrue(vec3.getHead().get_x().getCoordinate()==0&&vec3.getHead().get_y().getCoordinate()==0
                &&vec3.getHead().get_z().getCoordinate()==0);


    }

    @Test
    void length() {

        assertEquals(3.74,vec1.length(),0.5);
        assertEquals(7.07,vec2.length(),0.5);
    }

    @Test
    void normalize() throws Exception {

        try {

            vec1.normalize();
            vec2.normalize();
            assertEquals(1, vec1.length());
        } catch (Exception e) {
            e.toString();
        }
    }


    @Test
    void dotProduct() {

        assertEquals(-10,vec1.dotProduct(vec2));
    }
}
