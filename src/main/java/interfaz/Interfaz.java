package interfaz;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Interfaz extends Canvas implements Runnable{
  
    private static final long serialVersionUID = -7360988675525082953L;
   
    public static final int WIDTH =1080, HEIGHT = (WIDTH /12*9);
    private Thread thread;
    private boolean running = false;

    public static STATE current = STATE.Loading;
    public static int loadPercent=0;

    private MousePos mouse;
    private Menu menu;

    /**
     * Enum STATE variable que se usa para ver las instancias de la gui
     */
    public static enum STATE {
        Loading,
        Main,
        Vegan,
        Veget,
        Category,
        CatOne,
        CatTwo,
        CatThree,
        CatFour,
        Product,
    };


    /**
     * Objeto Interfaz Genera una ventana mediante JFrame y corre el ciclo para ejecutar
     * la GUI por cada fotograma.
     */
    public Interfaz(){
        Window ventana =new Window(WIDTH, HEIGHT, "Safe Stomach",this);

        this.mouse = new MousePos(ventana.GetFrame());
        this.addMouseListener(mouse);
        this.addMouseWheelListener(mouse);

        this.menu=new Menu(this,mouse);
        
    }

    /**
     * @param manager - Recibe atributo SolRecurso y le da el atributo al objeto actual
     */
    public void setManager(SolRecurso manager){
        menu.setManager(manager);
    }

    /**
     * Metodo start inicia el Thread que correrá los fotogramas de la GUI
     */
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Metodo Stop detiene la Thread actual y finaliza el programa.
     */
    public synchronized void stop(){
       try {
           thread.join();
           running =false;
        }catch (Exception e){
        e.printStackTrace();
        }
    }

    /**
     * Método run posee lo necesario para ejectuar el bucle de la GUI
     * a 60 fotogramas por segundo.
     */
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1) {
                tick();
                delta--;
            }
            if(running)
                render();
                frames++;
                            
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: "+ frames);
                frames = 0;
            }
        } stop();
    }

    /**
     * Método tick ejecuta los algoritmos de la GUI una vez por fotograma
     */
    private void tick() {
        mouse.mouseMoved();
        menu.tick();
    }

    /**
     * Método render ejecuta las renderizaciones de la GUI una vez por fotograma
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if  (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g=bs.getDrawGraphics();
        
        g.setColor(new Color(225,225,225));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (this.menu!=null) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    /**
     * Método clamp ejecuta una verificacion de parámetro dentro de unos margenes a dar.
     * @param var - parámetro a verificar.
     * @param min - minimo valor posible
     * @param max - máximo valor posible
     * @return el valor recortado para estar dentro de los margenes.
     */
    public static int clamp(int var,int min,int max){
        if(var >= max) {
            return var = max;
        }else if (var <= min) {
            return var = min;
        } else
            return var;
    }

}