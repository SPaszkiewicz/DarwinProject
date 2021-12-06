package project.orientation;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public String toString() {
        switch (this) {
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case EAST:
                return "E";
            case WEST:
                return "W";
            case NORTHEAST:
                return "NE";
            case SOUTHEAST:
                return "SE";
            case SOUTHWEST:
                return "SW";
            case NORTHWEST:
                return "NW";
            default:
                return "HOW";
        }
    }

    public MapDirection next45() {
        switch (this) {
            case NORTH:
                return NORTHEAST;
            case NORTHEAST:
                return EAST;
            case EAST:
                return SOUTHEAST;
            case SOUTHEAST:
                return SOUTH;
            case SOUTH:
                return SOUTHWEST;
            case SOUTHWEST:
                return WEST;
            case WEST:
                return NORTHWEST;
            case NORTHWEST:
                return NORTH;
            default:
                return NORTH;
        }
    }

    public MapDirection next90() {
        switch (this) {
            case NORTH:
                return EAST;
            case NORTHEAST:
                return SOUTHEAST;
            case EAST:
                return SOUTH;
            case SOUTHEAST:
                return SOUTHWEST;
            case SOUTH:
                return WEST;
            case SOUTHWEST:
                return NORTHWEST;
            case WEST:
                return NORTH;
            case NORTHWEST:
                return NORTHEAST;
            default:
                return NORTH;
        }
    }

    public MapDirection previous45() {
        switch (this) {
            case NORTH:
                return NORTHWEST;
            case NORTHEAST:
                return NORTH;
            case EAST:
                return NORTHEAST;
            case SOUTHEAST:
                return EAST;
            case SOUTH:
                return SOUTHEAST;
            case SOUTHWEST:
                return SOUTH;
            case WEST:
                return SOUTHWEST;
            case NORTHWEST:
                return WEST;
            default:
                return NORTH;
        }
    }

    public MapDirection previous90() {
        switch (this) {
            case NORTH:
                return WEST;
            case NORTHEAST:
                return NORTHWEST;
            case EAST:
                return NORTH;
            case SOUTHEAST:
                return NORTHEAST;
            case SOUTH:
                return EAST;
            case SOUTHWEST:
                return SOUTHEAST;
            case WEST:
                return SOUTH;
            case NORTHWEST:
                return SOUTHWEST;
            default:
                return NORTH;
        }
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case SOUTH:
                return new Vector2d(0, -1);
            case EAST:
                return new Vector2d(1, 0);
            case WEST:
                return new Vector2d(-1, 0);
            case NORTHEAST:
                return new Vector2d(1, 1);
            case NORTHWEST:
                return new Vector2d(-1, 1);
            case SOUTHEAST:
                return new Vector2d(1, -1);
            case SOUTHWEST:
                return new Vector2d(-1, -1);
            default:
                return new Vector2d(0, 0);
        }
    }

}
