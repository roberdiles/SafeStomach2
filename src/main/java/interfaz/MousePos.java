package interfaz;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;

public class MousePos extends MouseAdapter implements MouseWheelListener {

    private static boolean rClick;
    private static boolean lClick;
    private static boolean rClickRel;
    private static boolean lClickRel;
    private static Point last=new Point(0,0);
    private static int x;
    private static int y;
    private static double scroll;
    private JFrame frame;

    public MousePos(JFrame frame) {
        this.frame=frame;
    }

    public static int getXPos() {
        return x;
    }

    public static int getYPos() {
        return y;
    }

    public static boolean getHover(int X,int Y,int width,int height) {
        return isMouse(X,Y,width,height);
    }

    public static boolean getClick(int X,int Y,int width,int height) {
        if ((isMouse(X,Y,width,height)) && lClick) {
            return true;
        }else return false;
    }

    public static boolean getRel(int X,int Y,int width,int height) {
        if (isMouse(X,Y,width,height) && lClickRel) {
            //lClickRel=false;
            return true;
        }else return false;
    }

    /*
     *isMouse recibe los datos de posicion del puntero y las de una zona y devuelve un
     *boolean de acuerdo si esta o no el puntero en esa posicion
     **/

    private static boolean isMouse(int X, int Y, int width, int height){
        if(x > X && x < X+ width){
            if (y > Y && y < Y + height) {
                return true;
            }else return false;
        }else return false;
    }

    public void mousePressed(MouseEvent e){

        this.x = e.getX();
        this.y = e.getY();
        if (e.getButton()==1) {
            lClick=true;
            lClickRel=false;
        }else lClick=false;
        if (e.getButton()==3) {
            rClick=true;
            rClickRel=false;
        }else rClick=false;
    }

    public void mouseReleased(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
        if (e.getButton()==1) {
            lClickRel=true;
            lClick=false;
        }else lClickRel=false;
        if (e.getButton()==0) {
            rClickRel=true;
            rClick=false;
        }else rClickRel=false;
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll+=e.getWheelRotation();
    }

    public double getScroll(){
        return this.scroll;
    }

    public static void setScroll(double scroll) {
        MousePos.scroll = scroll;
    }

    public void mouseMoved() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        if (last.equals(p)==false) {
            lClickRel=false;
        }
        last=p;
        x=p.x-frame.getX();
        y=p.y-(frame.getY()+frame.getInsets().top);
    }
}