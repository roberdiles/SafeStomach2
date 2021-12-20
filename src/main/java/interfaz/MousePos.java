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

    /**
     * Objeto MousePos genera un listener para el mouse y sus componentes.
     * Almacena todas las variables del mouse necesarias.
     * @param frame - JFrame de la ventana sobre la que se necesitan los datos de mouse.
     */
    public MousePos(JFrame frame) {
        this.frame=frame;
    }

    /**
     * @return posicion en x del mouse
     */
    public static int getXPos() {
        return x;
    }

    /**
     * @return posicion en y del mouse
     */
    public static int getYPos() {
        return y;
    }

    /**
     * Método getHover verifica si el mouse esta posicionado sobre un determiado espacio de la ventana
     * @param X - posicion de JFrame en x del espacio a verificar
     * @param Y - posicion de JFrame en y del espacio a verificar
     * @param width - Ancho del espacio a verificar
     * @param height - Alto del espacio a verificar
     * @return boolean true si el mouse esta sobre la zona, y false si no lo está
     */
    public static boolean getHover(int X,int Y,int width,int height) {
        return isMouse(X,Y,width,height);
    }

    /**
     * Método getClick verifica si el mouse hace click izq sobre un determiado espacio de la ventana
     * @param X - posicion de JFrame en x del espacio a verificar
     * @param Y - posicion de JFrame en y del espacio a verificar
     * @param width - Ancho del espacio a verificar
     * @param height - Alto del espacio a verificar
     * @return boolean true si el mouse esta sobre la zona y hace click izq, y false si no lo está o no hace click.
     */
    public static boolean getClick(int X,int Y,int width,int height) {
        if ((isMouse(X,Y,width,height)) && lClick) {
            return true;
        }else return false;
    }

    /**
     * Método getRel verifica si el mouse suelta el click izq sobre un determiado espacio de la ventana
     * @param X - posicion de JFrame en x del espacio a verificar
     * @param Y - posicion de JFrame en y del espacio a verificar
     * @param width - Ancho del espacio a verificar
     * @param height - Alto del espacio a verificar
     * @return boolean true si el mouse esta sobre la zona y suelta el click izq, y false si no lo está o no suelta el click.
     */
    public static boolean getRel(int X,int Y,int width,int height) {
        if (isMouse(X,Y,width,height) && lClickRel) {
            //lClickRel=false;
            return true;
        }else return false;
    }

    private static boolean isMouse(int X, int Y, int width, int height){
        if(x > X && x < X+ width){
            if (y > Y && y < Y + height) {
                return true;
            }else return false;
        }else return false;
    }

    /**
     * Metodo mousePressed recive MouseEvent y ejecuta las tomas de medidas sobre el click del mouse.
     * @param e - cualquier interaccion de click con el mouse.
     */
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

    /**
     * Metodo mouseReleased recive MouseEvent y ejecuta las tomas de medidas sobre el la liberacion del click mouse.
     * @param e - cualquier interaccion de soltar un click con el mouse.
     */
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

    /**
     * Metodo mouseWheelMoved recive MouseWheelEvent y ejecuta la toma de medida sobre la rueda de el mouse.
     * @param e - cualquier interaccion de soltar un click con el mouse.
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll+=e.getWheelRotation();
    }

    /**
     * Metod getScroll devuelve el valor numerico de la rueda del mouse.
     * @return double con la magnitud de rotacion de la rueda del mouse.
     */
    public double getScroll(){
        return this.scroll;
    }

    /**
     * Metodo mouseMoved toma la posicion actual de el mouse y la almacena en los atributos del objeto.
     */
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