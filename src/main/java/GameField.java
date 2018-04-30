public class GameField {

    private GameFieldEntity[][] field;
    private int maxX, maxY;

    public GameField(int maxX, int maxY) {
        this.field = new GameFieldEntity[maxY][maxX];
        this.maxX = maxX - 1;
        this.maxY = maxY - 1;
    }

    public boolean isEmptyLocation(Position position) {
        boolean isInBounds = isValidPosition(position);
        return isInBounds && !isOccupied(position);
    }

    public boolean isOccupied(Position position) {
        return field[position.getY()][position.getX()] != null;
    }

    public void addEntity(GameFieldEntity entity) {
        if (field[entity.getPosition().getY()][entity.getPosition().getX()] == null) {
            field[entity.getPosition().getY()][entity.getPosition().getX()] = entity;
        }
    }

    public void removeDeadEntities() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                GameFieldEntity entity = field[y][x];
                if (entity != null && entity.getHealth() <= 0) {
                    field[y][x] = null;
                }
            }
        }
    }

    public GameFieldEntity findFirstEntityInDirection(Position startPosition, String direction, int range){
        for (int i = 1; i <= range; i++) {
            Position positionToCheck = startPosition.cloneAndMove(direction, i);
            GameFieldEntity entity = getEntity(positionToCheck);
            if(entity != null){
                return entity;
            }
        }
        return null;
    }

    public boolean isSurrounded(Position position){
        for (int y = position.getY() - 1; y <= position.getY() + 1; y++) {
            for (int x = position.getX() - 1; x <= position.getX() + 1; x++) {
                if(isEmptyLocation(new Position(x, y))){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean moveEntity(Position fromPosition, Position toPosition) {
        if(isValidPosition(fromPosition) && isEmptyLocation(toPosition)){
            GameFieldEntity entity = getEntity(fromPosition);
            if(entity != null){
                field[fromPosition.getY()][fromPosition.getX()] = null;
                field[toPosition.getY()][toPosition.getX()] = entity;
                return true;
            }
        }
        return false;
    }

    private GameFieldEntity getEntity(Position position){
        if(isValidPosition(position)){
            return field[position.getY()][position.getX()];
        }
        return null;
    }

    public boolean isInAttackRangeOfPlayer(Enemy enemy) {
        for (int y = enemy.getPosition().getY() - enemy.getAttackRange(); y <= enemy.getPosition().getY() + enemy.getAttackRange(); y++) {
            for (int x = enemy.getPosition().getX() - enemy.getAttackRange(); x <= enemy.getPosition().getX() + enemy.getAttackRange(); x++) {
                if(isValidPosition(new Position(x, y)) && field[y][x] instanceof Player){
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] != null) {
                    stringBuilder.append(field[y][x].toString());
                } else {
                    stringBuilder.append(" . ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() <= maxX && position.getY() >= 0 && position.getY() <= maxY;
    }
}
