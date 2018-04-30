import java.util.Random;

public class AIController {

    private Enemy[] enemies;
    private GameField gameField;

    public AIController(Enemy[] enemies, GameField gameField) {
        this.enemies = enemies;
        this.gameField = gameField;
    }

    public void takeTurn() {
        removeDeadEnemies();
        moveEnemies();
    }

    public int countEnemiesLeft() {
        int count = 0;
        for (Enemy enemy : enemies) {
            if (enemy != null && enemy.getHealth() > 0) {
                count++;
            }
        }
        return count;
    }

    private void removeDeadEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null && enemies[i].getHealth() <= 0) {
                enemies[i] = null;
            }
        }
    }

    private void moveEnemies() {
        for (Enemy enemy : enemies) {
            if (enemy != null && !gameField.isSurrounded(enemy.getPosition())) {
                String direction = enemy.getDirections()[new Random().nextInt(8)];
                while (!gameField.moveEntity(enemy.getPosition(), enemy.getPosition().cloneAndMove(direction, enemy.getSpeed()))) {
                    direction = enemy.getDirections()[new Random().nextInt(8)];
                }
                enemy.move(direction);
            }
        }
    }
}
