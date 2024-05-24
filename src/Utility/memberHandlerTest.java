package Utility;

import static org.junit.Assert.assertEquals;

import Utility.memberHandler;
import org.junit.Before;
import org.junit.Test;


public class memberHandlerTest {

    private memberHandler handler;

    @Before
    public void setUp() {
        handler = new memberHandler();
    }

    @Test
    public void testMinimumKontingent() {
        double expectedMinKontingent = 100.0;

        double actualMinKontingent = handler.calculateMinimumKontingent();

        assertEquals(expectedMinKontingent, actualMinKontingent, 0.01);
    }
}
