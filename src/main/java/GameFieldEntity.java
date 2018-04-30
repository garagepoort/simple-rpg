public class GameFieldEntity {

    private String[] directions = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
    private Coordinates coordinates;
    private int speed;
    private int health;
    private int attackRange;
    private int attackPower;

    public GameFieldEntity(int x, int y, int speed, int health) {
        this.health = health;
        coordinates = new Coordinates(x, y);
        this.speed = speed;
    }

    public void setAttack(int range, int power) {
        this.attackRange = range;
        this.attackPower = power;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getSpeed() {
        return speed;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String[] getDirections() {
        return directions;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void attack(GameFieldEntity gameFieldEntity){
        gameFieldEntity.setHealth(gameFieldEntity.getHealth() - getAttackPower());
    }

    public void move(String direction) {
        setCoordinates(getCoordinates().cloneAndMove(direction, speed));
    }
}
