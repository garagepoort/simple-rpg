public class Player extends GameFieldEntity {

    public Player(int x, int y, int speed, int health) {
        super(x, y, speed, health);
        setAttack(1, 10);
    }

    @Override
    public String toString() {
        return " P ";
    }
}
