package interfaz;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas{

    private static final long serialVersionUID = 318126280877336490L;
    private static JFrame frame;

    /**
     * Inicializa un JFrame con el titulo dado.
     * @param width - Ancho del JFrame a generar
     * @param height - Alto del JFrame a generar
     * @param title - Titulo del JFrame
     * @param interfaz - Objeto Interfaz a inicializar dentro del JFrame.
     */
    public Window(int width,int height, String title, Interfaz interfaz) {
        this.frame = new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(interfaz);
        frame.setVisible(true);
        interfaz.start();
    }

    public static JFrame GetFrame() {
        return frame;
    }
}