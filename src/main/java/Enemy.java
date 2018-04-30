public class Enemy extends GameFieldEntity {


    public Enemy(int x, int y, int speed, int health) {
        super(x, y, speed, health);
        setAttack(1, 5);
    }

    @Override
    public String toString() {
        return " E ";
    }
}
