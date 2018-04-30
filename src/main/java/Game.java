import java.io.IOException;
import java.util.Scanner;

public class Game {

    private static final String EXIT_COMMAND = "exit";
    private static final String MOVE = "move";
    private static final String ATTACK = "attack";


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to simple RPG");

        System.out.println("Enter field size X: ");
        int fieldSizeX = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter field size Y: ");
        int fieldSizeY = Integer.parseInt(scanner.nextLine());

        Player player = new Player(1, 1, 1, 100);
        GameField gameField = new GameField(fieldSizeX, fieldSizeY);

        Enemy enemy1 = new Enemy(5, 5, 1, 10);
        Enemy enemy2 = new Enemy(6, 8, 1, 10);
        Enemy enemy3 = new Enemy(9, 2, 1, 10);

        gameField.addEntity(player);
        gameField.addEntity(enemy1);
        gameField.addEntity(enemy2);
        gameField.addEntity(enemy3);

        PlayerController playerController = new PlayerController(player, gameField);
        AIController aiController = new AIController(new Enemy[]{enemy1, enemy2, enemy3}, gameField);

        System.out.println("Field created!");
        System.out.println(gameField.toString());
        System.out.println("Let's play!");

        String command = "";

        while (!command.equalsIgnoreCase(EXIT_COMMAND)) {
            command = scanner.nextLine();
            if(command.equalsIgnoreCase(EXIT_COMMAND)){
                break;
            }
            String[] split = command.split(" ");
            command = split[0];
            String direction = split[1];

            playerController.executeCommand(command, direction);
            aiController.takeTurn();
            gameField.removeDeadEntities();

            System.out.println(gameField.toString());
            System.out.println("HEALTH: " + player.getHealth());
            if (player.getHealth() <= 0) {
                System.out.println("YOU LOSE");
                break;
            }
            if (aiController.countEnemiesLeft() == 0) {
                System.out.println("YOU WIN");
                break;
            }
        }

        System.out.println("bye bye");
    }
}
