package chess;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return this.x == position.x && this.y == position.y;
    }

    public Position add(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position add(Position position) {
        return this.add(position.getX(), position.getY());
    }

    public boolean isInBoard() {
        return this.x >= 0 && this.x <= 7
                && this.y >= 0 && this.y <= 7;
    }
}