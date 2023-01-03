import java.awt.*;

public class Fruit {
    int x;
    int y;

    public Fruit(Snack snack){ //fruit and snack can't overlap.
        int randomX;
        int randomY;
        boolean overlap;
        do {
            randomX = (int) Math.floor(Math.random() * Main.column) * Main.CELL_SIZE; //random() = double (0,1)
            randomY = (int) Math.floor(Math.random() * Main.row) * Main.CELL_SIZE;
            overlap = checkOverlap(randomX,randomY,snack);
        }while (overlap);//if true,repeat do{} loop until boolean is false, randomX,Y ara valid.

        this.x = randomX;
        this.y = randomY;
    }

    public boolean checkOverlap(int randomX, int randomY, Snack snack) {
        for (int i = 0; i < snack.getSnackBody().size() - 1; i++) {
            if (snack.getSnackBody().get(i).x == randomX && snack.getSnackBody().get(i).y == randomY) {
                return true;
            }
        }
        return false;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void drawFruit(Graphics g){
        g.setColor(Color.red);
        g.fillOval(this.x, this.y, Main.CELL_SIZE, Main.CELL_SIZE);
    }


}

