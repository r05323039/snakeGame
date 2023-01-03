import java.awt.*;
import java.util.ArrayList;

public class Snack {
    private ArrayList<Node> snackBody;//蛇的身體是一個陣列清單，清單內的元素是另一個物件，物件為平面圖上座標(x,y)

    public Snack(){
        snackBody = new ArrayList<Node>();//初始化蛇的身體，加入四個座標
        snackBody.add(new Node(80,0));
        snackBody.add(new Node(60,0));
        snackBody.add(new Node(40,0));
        snackBody.add(new Node(20,0));
        snackBody.add(new Node(0,0));
    }

    public ArrayList<Node> getSnackBody(){//物件蛇，可從其他class可以訪問蛇的陣列
      return snackBody;
    }

    public void drawSnack(Graphics g){//物件蛇，可使用畫圖的method，將蛇身體的座標圖形化

        for (int i = 0 ; i < snackBody.size() - 1; i++){
            if (i == 0){//head is black.
                g.setColor(Color.black);
            }else {
                g.setColor(Color.orange);
            }
            Node n = snackBody.get(i); //index由0開始，將陣列清單snackBody內的物件Node放入n
            if (n.x > Main.width){
                n.x = 0;
            }
            if (n.x < 0){
                n.x = Main.width - Main.CELL_SIZE;
            }
            if (n.y > Main.height){
                n.y = 0;
            }
            if (n.y < 0){
                n.y = Main.height -Main.CELL_SIZE;
            }
            g.fillOval(n.x, n.y,Main.CELL_SIZE, Main.CELL_SIZE);//物件n中的x,y座標，用來畫蛇
        }
    }
}
