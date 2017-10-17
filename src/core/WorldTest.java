package core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorldTest {
    @Test
    public void rectangleWorldToString() throws Exception {
        String input = "*..\n.**\n..*";
        World world = new World(input);
        assertEquals(input, world.toString());
    }

    @Test
    public void oneCellWorldNextGeneration() throws Exception {
        World world = new World("*");
        assertEquals(".", world.nextGeneration().toString());
    }

    @Test
    public void horizontalCellWorldNextGenerationToString() throws Exception {
        World world = new World("****");
        assertEquals(".**.", world.nextGeneration().toString());
    }

    @Test
    public void verticalCellWorldNextGenerationToString() throws Exception {
        World world = new World("*\n*\n*\n*");
        assertEquals(".\n*\n*\n.", world.nextGeneration().toString());
    }

    @Test
    public void rectangleCellWorldNextGenerationToString() throws Exception {
        World world = new World("*.*\n" +
                                "...\n" +
                                "*..");
        assertEquals("...\n" +
                     ".*.\n" +
                     "...", world.nextGeneration().toString());

        world = new World("*.*\n" +
                          "...\n" +
                          "..*");
        assertEquals("...\n" +
                     ".*.\n" +
                     "...", world.nextGeneration().toString());
    }
}
