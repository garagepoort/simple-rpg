public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates cloneAndMove(String direction, int speed) {
        switch (direction) {
            case "N":
                return new Coordinates(getX(), getY() - speed);
            case "S":
                return new Coordinates(getX(), getY() + speed);
            case "W":
                return new Coordinates(getX() - speed, getY());
            case "E":
                return new Coordinates(getX() + speed, getY());
            case "NE":
                return new Coordinates(getX() + speed, getY() - speed);
            case "SE":
                return new Coordinates(getX() + speed, getY() + speed);
            case "SW":
                return new Coordinates(getX() - speed, getY() + speed);
            case "NW":
                return new Coordinates(getX() - speed, getY() - speed);
        }
        return null;
    }

}
