package Model;

import org.junit.jupiter.api.Test;

import javax.xml.catalog.Catalog;

import static java.lang.Double.POSITIVE_INFINITY;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    // test at the maximum aperture
        // aperture = 1.8; COC = 0.029; HyperFocal = 47.89; distance = 1.1;
        // nearFocal = 1.08; farFocal = 1.12; DOF = 0.05;
    Lens lens0 = new Lens("test0", 1.8, 50);

    // test at the narrower aperture
        // aperture = 8; COC = 0.029; HyperFocal = 10.78; distance = 1;
        // nearFocal = 0.92; farFocal = 1.10; DOF = 0.18;
    Lens lens1 = new Lens("test1", 1.8, 50);

    // test at the distance is futer than the hyperfocal distance
        // aperture = 11; COC = 0.029; HyperFocal = 7.84; distance = 15;
        // nearFocal = 5.16; farFocal = ∞; DOF = ∞;
    Lens lens2 = new Lens("test2", 1.8, 50);

    @Test
    void testGetHyperFocal() {
        Calculator testHyperFocal = new Calculator();

        // test at the maximum aperture
        assertEquals(47.89, testHyperFocal.getHyperFocal(lens0, 1.8, 0.029), 0.01);

        // test at the narrower aperture
        assertEquals(10.78, testHyperFocal.getHyperFocal(lens1, 8, 0.029),0.01 );

        // test at distance is futher than the hyperfocal distance
        assertEquals(7.84,testHyperFocal.getHyperFocal(lens2,11, 0.029),0.01);
    }

    @Test
    void testGetNearFocal() {
        Calculator testNearFocal = new Calculator();

        // test at the maximum aperture
        assertEquals(1.08, testNearFocal.getNearFocal(lens0, 47.89, 1.1), 0.01);

        // test at the narrower aperture
        assertEquals(0.92, testNearFocal.getNearFocal(lens1, 10.78, 1),0.01 );

        // test at distance is futher than the hyperfocal distance
        assertEquals(5.16, testNearFocal.getNearFocal(lens2, 7.84, 15),0.01 );
    }

    @Test
    void testGetFarFocal() {
        Calculator testFarFocal = new Calculator();

        // test at the maximum aperture
        assertEquals(1.12, testFarFocal.getFarFocal(lens0, 47.89, 1.1), 0.01);

        // test at the narrower aperture
        assertEquals(1.10, testFarFocal.getFarFocal(lens1, 10.78, 1),0.01 );

        // test at distance is futher than the hyperfocal distance
        assertEquals(POSITIVE_INFINITY, testFarFocal.getFarFocal(lens2, 7.84, 15),0.01 );
    }

    @Test
    void testGetDepthOfField() {
        Calculator testDepthOfField = new Calculator();

        // test at the maximum aperture
        assertEquals(0.05, testDepthOfField.getDepthOfField(1.12, 1.08), 0.01);

        // test at the narrower aperture
        assertEquals(0.18, testDepthOfField.getDepthOfField(1.10, 0.92),0.01 );

        // test at distance is futher than the hyperfocal distance
        assertEquals(POSITIVE_INFINITY, testDepthOfField.getDepthOfField(POSITIVE_INFINITY, 5.16),0.01 );
    }

}