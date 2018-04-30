public class GameField {

    private GameFieldEntity[][] field;
    private int maxX, maxY;

    public GameField(int maxX, int maxY) {
        this.field = new GameFieldEntity[maxY][maxX];
        this.maxX = maxX - 1;
        this.maxY = maxY - 1;
    }

    public boolean isEmptyLocation(Coordinates coordinates) {
        boolean isInBounds = isValidPosition(coordinates);
        return isInBounds && !isOccupied(coordinates);
    }

    public boolean isOccupied(Coordinates coordinates) {
        return field[coordinates.getY()][coordinates.getX()] != null;
    }

    public void addEntity(GameFieldEntity entity) {
        if (field[entity.getCoordinates().getY()][entity.getCoordinates().getX()] == null) {
            field[entity.getCoordinates().getY()][entity.getCoordinates().getX()] = entity;
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

    public GameFieldEntity findFirstEntityInDirection(Coordinates startCoordinates, String direction, int range){
        for (int i = 1; i <= range; i++) {
            Coordinates coordinatesToCheck = startCoordinates.cloneAndMove(direction, i);
            GameFieldEntity entity = getEntity(coordinatesToCheck);
            if(entity != null){
                return entity;
            }
        }
        return null;
    }

    public boolean isSurrounded(Coordinates coordinates){
        for (int y = coordinates.getY() - 1; y <= coordinates.getY() + 1; y++) {
            for (int x = coordinates.getX() - 1; x <= coordinates.getX() + 1; x++) {
                if(isEmptyLocation(new Coordinates(x, y))){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean moveEntity(Coordinates fromCoordinates, Coordinates toCoordinates) {
        if(isValidPosition(fromCoordinates) && isEmptyLocation(toCoordinates)){
            GameFieldEntity entity = getEntity(fromCoordinates);
            if(entity != null){
                field[fromCoordinates.getY()][fromCoordinates.getX()] = null;
                field[toCoordinates.getY()][toCoordinates.getX()] = entity;
                return true;
            }
        }
        return false;
    }

    private GameFieldEntity getEntity(Coordinates coordinates){
        if(isValidPosition(coordinates)){
            return field[coordinates.getY()][coordinates.getX()];
        }
        return null;
    }

    public boolean isInAttackRangeOfPlayer(Enemy enemy) {
        for (int y = enemy.getCoordinates().getY() - enemy.getAttackRange(); y <= enemy.getCoordinates().getY() + enemy.getAttackRange(); y++) {
            for (int x = enemy.getCoordinates().getX() - enemy.getAttackRange(); x <= enemy.getCoordinates().getX() + enemy.getAttackRange(); x++) {
                if(isValidPosition(new Coordinates(x, y)) && field[y][x] instanceof Player){
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

    private boolean isValidPosition(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() <= maxX && coordinates.getY() >= 0 && coordinates.getY() <= maxY;
    }
}
