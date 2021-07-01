package figures;

public class Square extends Figures {
    int x1;
    int x2;
    int y1;
    int y2;
    int side;

    public int getSide() {
        return side;
    }

    public void setSide(int newSide) {
        this.side = newSide;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int calculateArea(int x1, int x2, int y1, int y2) {
        int side;
        if (x1 == x2) {
            side = Math.abs(y1 - y2);
        } else {
            if (y1 == y2) {
                side = Math.abs(x1 - x2);
            } else {
                side = 0;
            }
        }
        return side*side;
    }
}
