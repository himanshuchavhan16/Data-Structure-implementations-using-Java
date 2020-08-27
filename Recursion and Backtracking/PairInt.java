import java.util.Objects;

public class PairInt {
    private int x;
    private int y;

    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairInt pairInt = (PairInt) o;
        return x == pairInt.x && y == pairInt.y;
    }

    @Override
    public String toString() {
        return "(" + x + ","+ y +')';
    }

    public PairInt copy()
    {
        return new PairInt(this.x,this.y);
    }
}
