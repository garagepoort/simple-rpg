public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position cloneAndMove(String direction, int speed) {
        switch (direction) {
            case "N":
                return new Position(getX(), getY() - speed);
            case "S":
                return new Position(getX(), getY() + speed);
            case "W":
                return new Position(getX() - speed, getY());
            case "E":
                return new Position(getX() + speed, getY());
            case "NE":
                return new Position(getX() + speed, getY() - speed);
            case "SE":
                return new Position(getX() + speed, getY() + speed);
            case "SW":
                return new Position(getX() - speed, getY() + speed);
            case "NW":
                return new Position(getX() - speed, getY() - speed);
        }
        return null;
    }

}
