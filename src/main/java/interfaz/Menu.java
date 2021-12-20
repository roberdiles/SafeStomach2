package interfaz;
import interfaz.Interfaz.STATE;
import interfaz.image_loader.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu{
    private Interfaz interfaz;
    private MousePos mouse;

    private int counter=0;
    private boolean botones[][] = new boolean[10][10];
    private boolean products[]=new boolean[999];
    private String selectedProduct="";
    private SolRecurso manager;
    private STATE lastState=STATE.Main;
    private double lastScroll=0;
    private int scroll=0;

    private BufferedImageLoader loader = new BufferedImageLoader();
    private BufferedImage loading;

    /**
     * Objeto Menu renderiza toda la GUI
     * @param interfaz Objeto Interfaz con la JFrame sobre la cual trabajar-
     * @param mouse Objeto MousePos con el listener para obtener datos sobre el mouse.
     */
    public Menu(Interfaz interfaz, MousePos mouse){
        this.interfaz = interfaz;
        this.mouse=mouse;

        //Carga de imagen no funciona .... no se por que
        //loading=loader.loadImage("\\Users\\sonur\\Desktop\\SafeStomach2-Roberto\\res\\loadingBack.png"  );
    }

    /**
     * metodo setManager para establecer el SolRecurso del cual obtener los datos a mostrar.
     * @param manager SolRecurso con el Arraylist con los datos de la API a mostrar.
     */
    public void setManager(SolRecurso manager) {
        this.manager = manager;
    }

    /**
     * Metodo tick ejecuta los eventos generados por los botones de la GUI.
     */
    public void tick() {
        counter++;

        parseButtons();
    }

    /**
     * Metodo render renderiza cada instancia del menu correspondientemente.
     * @param g Graficos a utilizar.
     */
    public void render(Graphics g){
        if(interfaz.current == STATE.Loading) renderLoad(g);
        if(interfaz.current == STATE.Main) renderMain(g);
        if(interfaz.current == STATE.Vegan) renderCategory(g,2,"VEGANO");
        if(interfaz.current == STATE.Veget) renderCategory(g,1,"VEGETARIANO");
        if(interfaz.current == STATE.Category) renderCategoryMenu(g);
        if(interfaz.current == STATE.CatOne) renderCategory(g ,3,"LACTEOS");
        if(interfaz.current == STATE.CatTwo) renderCategory(g ,4,"PROTEINAS");
        if(interfaz.current == STATE.CatThree) renderCategory(g ,5,"BEBIDAS");
        if(interfaz.current == STATE.CatFour) renderCategory(g ,6,"OTROS");
        if(interfaz.current == STATE.Product) renderProduct(g);
    }

    // ------------------------ privates ---------------------

    private void renderLoad(Graphics g){
        Font fnt = new Font("berlin sans fb", 1, 100);

        g.setColor(new Color(125, 217, 87));
        g.setFont(fnt);

        g.drawImage(loading,0,0,interfaz.getWidth(), interfaz.getHeight(),null);

        drawCenteredString(g, "Cargando     ", new Rectangle(0, 0, interfaz.getWidth(), interfaz.getHeight()), fnt);

        if (counter >= 0 && counter <50) {
        } else if (counter >= 50 && counter <100) {
            drawCenteredString(g, "                .", new Rectangle(0, 0, interfaz.getWidth(), interfaz.getHeight()), fnt);
        } else if (counter >= 100 && counter <150) {
            drawCenteredString(g, "                 ..", new Rectangle(0, 0, interfaz.getWidth(), interfaz.getHeight()), fnt);
        } else if (counter >= 150 && counter <200) {
            drawCenteredString(g, "                  ...", new Rectangle(0, 0, interfaz.getWidth(), interfaz.getHeight()), fnt);
        }else  counter=0;


        for (int i=0;i<Interfaz.loadPercent;i++) {
            g.fillOval((1+i)*interfaz.getWidth()/10, 4*interfaz.getHeight()/7,50,50 );
        }

    }

    private void renderMain(Graphics g){
        scroll=0;
        Font fnt = new Font("berlin sans fb", 1, 100);
        Font fnt2 = new Font("berlin sans fb", 1, 50);
        Font fnt3 = new Font("berlin sans fb", 1, 43);

        //put logo
        g.drawImage(loading, 0, 100, interfaz.WIDTH, 240, null);
        g.setColor(new Color(125, 217, 87));
        drawCenteredString(g, "Safe Stomach", new Rectangle(0, 0, interfaz.getWidth(), interfaz.getHeight()/3), fnt);


        renderButton(g,new Color(82, 113, 255),"Vegano",fnt2,new Color(125, 217, 87),(Interfaz.WIDTH / 12), ((Interfaz.HEIGHT) / 3), 350, 350);

        //---------------------------

        renderButton(g,new Color(82, 113, 255),"      Vegetariano",fnt3,new Color(125, 217, 87),(11*Interfaz.WIDTH / 12)-350, ((Interfaz.HEIGHT) / 3), 350, 350);

        //---------------------------

        renderButton(g,new Color(125, 217, 87),"Categorías",fnt2,Color.yellow,(Interfaz.WIDTH / 2)-175, ((Interfaz.HEIGHT) / 3), 350, 350);

    }

    private void renderCategory(Graphics g,int category, String title){
        renderList(g,manager.tipodeBusqueda(category));

        renderTitle(g,title);
    }

    private void renderCategoryMenu(Graphics g){
        scroll=0;

        Font fnt2 = new Font("berlin sans fb", 1, 50);

        renderTitle(g,"CATEGORIAS");

        renderButton(g,new Color(82, 113, 255),"LACTEOS",fnt2,new Color(125, 217, 87),interfaz.getWidth()/8, interfaz.getHeight()/8,interfaz.getWidth()/4,interfaz.getWidth()/4);

        renderButton(g,new Color(82, 113, 255),"PROTEINA",fnt2,new Color(125, 217, 87),5*interfaz.getWidth()/8, interfaz.getHeight()/8,interfaz.getWidth()/4,interfaz.getWidth()/4);

        renderButton(g,new Color(82, 113, 255),"BEBIDAS",fnt2,new Color(125, 217, 87),interfaz.getWidth()/8, 5*interfaz.getHeight()/8,interfaz.getWidth()/4,interfaz.getWidth()/4);

        renderButton(g,new Color(82, 113, 255),"OTROS",fnt2,new Color(125, 217, 87),5*interfaz.getWidth()/8, 5*interfaz.getHeight()/8,interfaz.getWidth()/4,interfaz.getWidth()/4);

    }

    private void renderProduct(Graphics g){
        renderTitle(g,"PRODUCTO");

        g.setColor(Color.gray);
        g.drawRect(75,100,300,400);
        drawCenteredString(g, "Insert Image", new Rectangle(75,100,300,400), new Font("berlin sans fb", 1, 20));

        //prueba de carga de imagen con url sacada de la API, tampoco pesca.
        BufferedImage productImage = loader.loadImage(selectedProduct.split(";")[5]);
        g.drawImage(productImage,75,100,300,400,null);

        //Nombre producto
        g.setFont(new Font("berlin sans fb", 1, 35));
        drawWrappedText(g, selectedProduct.split(";")[0],375, 100, interfaz.getWidth()-375, 100);

        //PRECIO
        g.setFont(new Font("berlin sans fb", 1, 100));
        g.setColor(Color.blue.darker());
        FontMetrics metrics = g.getFontMetrics(new Font("berlin sans fb", 1, 100));
        g.drawString(selectedProduct.split(";")[1],interfaz.getWidth()-(100 + metrics.stringWidth(selectedProduct.split(";")[1])),300);

        //Etiqueta (vegano/vegetariano)
        g.setFont(new Font("berlin sans fb", 1, 35));
        g.setColor(new Color(125, 217, 87).darker());
        drawWrappedText(g, selectedProduct.split(";")[2],375, 400, interfaz.getWidth()-375, 100);

        //Tipo (Categoria)
        g.setColor(Color.yellow.darker());
        drawWrappedText(g, selectedProduct.split(";")[3],375, 450, interfaz.getWidth()-375, 100);

        //Ubicacion comprar
        g.setColor(Color.gray);
        drawWrappedText(g, "Este producto puedes comprarlo en: "+selectedProduct.split(";")[4],70, 600, interfaz.getWidth()-100, 100);
    }

    private void renderTitle(Graphics g, String title){
        Font fnt = new Font("berlin sans fb", 1, 75);
        Font fnt2 = new Font("berlin sans fb", 1, 50);

        g.setColor(new Color(125, 217, 87));
        g.setFont(fnt);

        drawCenteredString(g, title, new Rectangle(0, 0, interfaz.getWidth(), interfaz.getHeight()/10), fnt);

        g.setColor(parseHoverColor(new Color(125, 217, 87),25, 25, 200, 50));
        drawCenteredString(g, "<- Volver", new Rectangle(25, 25, 200, 50), fnt2);
    }

    private void renderButton(Graphics g,Color buttonColor,String text,Font font,Color textColor,int x,int y, int w,int h){
        g.setColor(parseHoverColor(buttonColor,x,y,w,h));

        g.fillOval(x,y,w,h);

        g.setColor(textColor);
        drawCenteredString(g, text, new Rectangle(x,y,w,h), font);
    }

    private Color parseHoverColor(Color temp,int x,int y,int w,int h){

        if (mouse.getHover(x,y,w,h)){
            temp = new Color(interfaz.clamp(temp.getRed()-50,0,255),interfaz.clamp(temp.getGreen()-50,0,255),interfaz.clamp(temp.getBlue()-50,0,255));
        }
        if (mouse.getClick(x,y,w,h)){
            temp = new Color(interfaz.clamp(temp.getRed()+100,0,255),interfaz.clamp(temp.getGreen()+100,0,255),interfaz.clamp(temp.getBlue()+100,0,255));
        }

        return temp;
    }

    private void renderList(Graphics g,String[] list){
        int fontSize=30;

        executeScroll((fontSize*list.length)-(interfaz.getHeight()-160));

        g.setFont( new Font("berlin sans fb", 1, fontSize));
        g.setColor(new Color(225,225,225));

        for (int i=0;i<list.length;i++) {
            g.setColor(parseHoverColor(Color.gray,50,100+(i*fontSize)-scrollClamp(scroll,0,(fontSize*(list.length+1))-(interfaz.getHeight()-150)),interfaz.getWidth()-100,fontSize));

            //Nombre Producto

            g.drawString(list[i].split(";")[0],50,100+fontSize+(i*fontSize)-scrollClamp(scroll,0,(fontSize*(list.length)+1)-(interfaz.getHeight()-150)));
            //Precio Producto se imprime a la izquierda
            FontMetrics metrics = g.getFontMetrics(new Font("berlin sans fb", 1, fontSize));

            g.drawString(list[i].split(";")[1],interfaz.getWidth()-(50 + metrics.stringWidth(list[i].split(";")[1])),100+fontSize+(i*fontSize)-scrollClamp(scroll,0,(fontSize*(list.length)+1)-(interfaz.getHeight()-150)));
            parseProductSel(i,list[i],50,100+(i*fontSize)-scrollClamp(scroll,0,(fontSize*(list.length+1))-(interfaz.getHeight()-150)),interfaz.getWidth()-100,fontSize);
        }

        listMargin(g);

    }

    private void listMargin(Graphics g){
        g.setColor(new Color(240,240,240));
        //top
        g.fillRect(0,0,interfaz.getWidth(),100);
        //bottom
        g.fillRect(0, interfaz.getHeight()-45,interfaz.getWidth(),45);
        //left
        g.fillRect(0,0,45, interfaz.getHeight());
        //right
        g.fillRect(interfaz.getWidth()-45,0,45, interfaz.getHeight());

    }

    private void executeScroll(int max){
        this.scroll +=  (int)( mouse.getScroll()-this.lastScroll)*20;
        this.scroll=scrollClamp(scroll,0,max);

        this.lastScroll=mouse.getScroll();
    }

    private void parseButtons(){

            //MAIN MENU

        //VEGANO
        parseInteraction(1,0,(Interfaz.WIDTH / 12), ((Interfaz.HEIGHT) / 3), 350, 350);

        //VEGETARIANO
        parseInteraction(1,1,(11*Interfaz.WIDTH / 12)-350, ((Interfaz.HEIGHT) / 3), 350, 350);

        //CATEGORIAS
        parseInteraction(1,2,(Interfaz.WIDTH / 2)-175, ((Interfaz.HEIGHT) / 3), 350, 350);

            //VEGAN MENU

        //VOLVER
        parseInteraction(2,0,25, 25, 200, 50);

            //VEGETARIAN MENU

        //VOLVER
        parseInteraction(3,0,25, 25, 200, 50);

            //CATEGORY MENU

        //VOLVER
        parseInteraction(4,0,25, 25, 200, 50);

        //LACTEOS
        parseInteraction(4,1,interfaz.getWidth()/8, interfaz.getHeight()/8,interfaz.getWidth()/4,interfaz.getWidth()/4);
        //CARNES
        parseInteraction(4,2,5*interfaz.getWidth()/8, interfaz.getHeight()/8,interfaz.getWidth()/4,interfaz.getWidth()/4);
        //BEBIDAS
        parseInteraction(4,3,interfaz.getWidth()/8, 5*interfaz.getHeight()/8,interfaz.getWidth()/4,interfaz.getWidth()/4);
        //OTROS
        parseInteraction(4,4,5*interfaz.getWidth()/8, 5*interfaz.getHeight()/8,interfaz.getWidth()/4,interfaz.getWidth()/4);

            //LACTEOS MENU

        //VOLVER
        parseInteraction(5,0,25, 25, 200, 50);

            //MEATS MENU

        //VOLVER
        parseInteraction(6,0,25, 25, 200, 50);

            //DRINKS MENU

        //VOLVER
        parseInteraction(7,0,25, 25, 200, 50);

            //OTHERS MENU

        //VOLVER
        parseInteraction(8,0,25, 25, 200, 50);

        //VOLVER
        parseInteraction(9,0,25, 25, 200, 50);


    }

    private void parseInteraction(int state, int button, int x, int y,int w, int h) {
        if (mouse.getClick(x, y, w, h)) botones[state][button] = true;

        if (mouse.getRel(x, y, w, h) && botones[state][button] && parseState(state)) {
            buttonFunc(state, button);
            botones[state][button] = false;
        } else if (mouse.getRel(0, 0, interfaz.getWidth(), interfaz.getHeight())) botones[state][button] = false;
    }

    private void parseProductSel(int product,String data, int x, int y,int w, int h) {
        if (mouse.getClick(x, y, w, h)) products[product] = true;

        if (mouse.getRel(x, y, w, h) && products[product] && mouse.getXPos()>45 && mouse.getXPos()<interfaz.getWidth()-45 && mouse.getYPos()>100 && mouse.getYPos()<interfaz.getHeight()-45) {
            selectedProduct=data;
            lastState=interfaz.current;
            interfaz.current=STATE.Product;
            products[product] =false;
        } else if (mouse.getRel(0, 0, interfaz.getWidth(), interfaz.getHeight())) products[product] =false;
    }

    private void buttonFunc(int state,int button) {
        //MAIN MENU
        if (state == 1) {
            if (button == 0) Interfaz.current=STATE.Vegan;
            if (button == 1) Interfaz.current=STATE.Veget;
            if (button == 2) Interfaz.current=STATE.Category;
        }
        //VEGAN MENU
        if (state == 2) {
            if (button == 0) Interfaz.current=STATE.Main;

        }
        //VEGETARIAN MENU
        if (state == 3) {
            if (button == 0) Interfaz.current=STATE.Main;
        }
        //CATEGORY MENU
        if (state == 4) {
            if (button == 0) Interfaz.current=STATE.Main;
            if (button == 1) Interfaz.current=STATE.CatOne;
            if (button == 2) Interfaz.current=STATE.CatTwo;
            if (button == 3) Interfaz.current=STATE.CatThree;
            if (button == 4) Interfaz.current=STATE.CatFour;
        }
        //LACTEOS MENU
        if (state == 5) {
            if (button == 0) Interfaz.current=STATE.Category;
        }
        //MEATS MENU
        if (state == 6) {
            if (button == 0) Interfaz.current=STATE.Category;
        }
        //DRINKS MENU
        if (state == 7) {
            if (button == 0) Interfaz.current=STATE.Category;
        }
        //OTHERS MENU
        if (state == 8) {
            if (button == 0) Interfaz.current=STATE.Category;
        }
        //PRODUCT MENU
        if (state == 9) {
            if (button == 0) Interfaz.current=lastState;
        }
    }

    private boolean parseState(int state){
        STATE temp=interfaz.current;

        if (state == 0) temp=STATE.Loading;
        if (state == 1) temp=STATE.Main;
        if (state == 2) temp=STATE.Vegan;
        if (state == 3) temp=STATE.Veget;
        if (state == 4) temp=STATE.Category;

        if (state == 5) temp=STATE.CatOne;
        if (state == 6) temp=STATE.CatTwo;
        if (state == 7) temp=STATE.CatThree;
        if (state == 8) temp=STATE.CatFour;

        if (state == 9) temp=STATE.Product;

        return interfaz.current.equals(temp);
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;


        if (metrics.stringWidth(text) > rect.width) {
            double lines = Math.ceil(metrics.stringWidth(text)/ rect.width);
            //System.out.println(lines);

        }

        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    private void drawWrappedText(Graphics g, String text, int x, int y, int w, int h) {
        JTextArea ta = new JTextArea(text);
        ta.setLineWrap(true);
        ta.setBackground( new Color(225,225,225, 0) );
        ta.setWrapStyleWord(true);
        ta.setBounds(0, 0, w, h);
        ta.setForeground(g.getColor());
        ta.setFont(g.getFont());
        ta.setMargin(new Insets(5,10,5,10));
        Graphics g2 = g.create(x, y, w, h); // Use new graphics to leave original graphics state unchanged
        ta.paint(g2);
    }

    private int scrollClamp(int var,int min,int max){
        if(var >= max) {
            return var = max;
        }else if (var <= min) {
            return var = min;
        } else
            return var;
    }
}
