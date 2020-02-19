package Testing.Primitives;

import Primitives.Point3D;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {
Point3D temp=new Point3D(10,20,30);
Vector temp2 = new Vector(3,4,5);
    @Test
    void add() {
        temp.add(temp2);
    assertTrue(temp.get_x().getCoordinate()==13&&temp.get_y().getCoordinate()==24&&temp.get_z().getCoordinate()==35);
    }

    @Test
    void subtract() {
        temp.subtract(temp2);
        assertTrue(temp.get_x().getCoordinate()==7&&temp.get_y().getCoordinate()==16&&temp.get_z().getCoordinate()==25);
    }

    @Test
    void distance() {
        assertEquals(0,temp.distance(temp));
        assertEquals(30.49,temp.distance(temp2.getHead()),0.05);
    }
}
