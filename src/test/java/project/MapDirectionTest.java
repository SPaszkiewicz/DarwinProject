package project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next45()
    {
        assertEquals(MapDirection.NORTHEAST, MapDirection.NORTH.next45(), "Issue with rotation in test 1");
        assertEquals(MapDirection.EAST, MapDirection.NORTHEAST.next45(), "Issue with rotation in test 2");
        assertEquals(MapDirection.SOUTHEAST, MapDirection.EAST.next45(), "Issue with rotation in test 3");
        assertEquals(MapDirection.SOUTH, MapDirection.SOUTHEAST.next45(), "Issue with rotation in test 4");
        assertEquals(MapDirection.SOUTHWEST, MapDirection.SOUTH.next45(), "Issue with rotation in test 5");
        assertEquals(MapDirection.WEST, MapDirection.SOUTHWEST.next45(), "Issue with rotation in test 6");
        assertEquals(MapDirection.NORTHWEST, MapDirection.WEST.next45(), "Issue with rotation in test 7");
        assertEquals(MapDirection.NORTH, MapDirection.NORTHWEST.next45(), "Issue with rotation in test 8");
    }

    @Test
    void next90() {
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next90(), "Issue with rotation in test 1");
        assertEquals(MapDirection.SOUTHEAST, MapDirection.NORTHEAST.next90(), "Issue with rotation in test 2");
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next90(), "Issue with rotation in test 3");
        assertEquals(MapDirection.SOUTHWEST, MapDirection.SOUTHEAST.next90(), "Issue with rotation in test 4");
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next90(), "Issue with rotation in test 5");
        assertEquals(MapDirection.NORTHWEST, MapDirection.SOUTHWEST.next90(), "Issue with rotation in test 6");
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next90(), "Issue with rotation in test 7");
        assertEquals(MapDirection.NORTHEAST, MapDirection.NORTHWEST.next90(), "Issue with rotation in test 8");
    }

    @Test
    void previous45() {
        assertEquals(MapDirection.NORTHWEST, MapDirection.NORTH.previous45(), "Issue with rotation in test 1");
        assertEquals(MapDirection.NORTH, MapDirection.NORTHEAST.previous45(), "Issue with rotation in test 2");
        assertEquals(MapDirection.NORTHEAST, MapDirection.EAST.previous45(), "Issue with rotation in test 3");
        assertEquals(MapDirection.EAST, MapDirection.SOUTHEAST.previous45(), "Issue with rotation in test 4");
        assertEquals(MapDirection.SOUTHEAST, MapDirection.SOUTH.previous45(), "Issue with rotation in test 5");
        assertEquals(MapDirection.SOUTH, MapDirection.SOUTHWEST.previous45(), "Issue with rotation in test 6");
        assertEquals(MapDirection.SOUTHWEST, MapDirection.WEST.previous45(), "Issue with rotation in test 7");
        assertEquals(MapDirection.WEST, MapDirection.NORTHWEST.previous45(), "Issue with rotation in test 8");
    }

    @Test
    void previous90() {
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous90(), "Issue with rotation in test 1");
        assertEquals(MapDirection.NORTHWEST, MapDirection.NORTHEAST.previous90(), "Issue with rotation in test 2");
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous90(), "Issue with rotation in test 3");
        assertEquals(MapDirection.NORTHEAST, MapDirection.SOUTHEAST.previous90(), "Issue with rotation in test 4");
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous90(), "Issue with rotation in test 5");
        assertEquals(MapDirection.SOUTHEAST, MapDirection.SOUTHWEST.previous90(), "Issue with rotation in test 6");
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous90(), "Issue with rotation in test 7");
        assertEquals(MapDirection.SOUTHWEST, MapDirection.NORTHWEST.previous90(), "Issue with rotation in test 8");
    }

    @Test
    void toUnitVector()
    {
        assertEquals(new Vector2d(0, 1), MapDirection.NORTH.toUnitVector(), "Issue with vector in test 1");
        assertEquals(new Vector2d(1, 1), MapDirection.NORTHEAST.toUnitVector(), "Issue with vector in test 2");
        assertEquals(new Vector2d(1, 0), MapDirection.EAST.toUnitVector(), "Issue with vector in test 3");
        assertEquals(new Vector2d(1, -1), MapDirection.SOUTHEAST.toUnitVector(), "Issue with vector in test 4");
        assertEquals(new Vector2d(0, -1), MapDirection.SOUTH.toUnitVector(), "Issue with vector in test 5");
        assertEquals(new Vector2d(-1, -1), MapDirection.SOUTHWEST.toUnitVector(), "Issue with vector in test 6");
        assertEquals(new Vector2d(-1, 0), MapDirection.WEST.toUnitVector(), "Issue with vector in test 7");
        assertEquals(new Vector2d(-1, 1), MapDirection.NORTHWEST.toUnitVector(), "Issue with vector in test 8");
    }

}