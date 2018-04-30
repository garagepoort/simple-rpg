public class PlayerController {
    private static final String MOVE = "move";
    private static final String ATTACK = "attack";

    private Player player;
    private GameField gameField;

    public PlayerController(Player player, GameField gameField) {
        this.player = player;
        this.gameField = gameField;
    }

    public void executeCommand(String command, String direction) {
        switch (command) {
            case MOVE:
                movePlayer(direction);
                break;
            case ATTACK:
                letPlayerAttack(direction);
                break;
        }
    }

    private void movePlayer(String direction) {
        boolean playerMoved = gameField.moveEntity(player.getCoordinates(), player.getCoordinates().cloneAndMove(direction, player.getSpeed()));
        if (playerMoved) {
            player.move(direction);
        } else {
            throw new CommandException("Player could not be moved in that direction");
        }

    }

    private void letPlayerAttack(String direction) {
        GameFieldEntity entity = gameField.findFirstEntityInDirection(player.getCoordinates(), direction, player.getAttackRange());

        if (entity != null && entity instanceof Enemy) {
            player.attack(entity);
        } else {
            System.out.println("No enemy in that direction");
        }
    }
}
