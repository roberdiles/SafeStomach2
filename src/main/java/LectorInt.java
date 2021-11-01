import java.util.Scanner;

public class LectorInt {

    static Scanner intro = new Scanner(System.in);
    private static int top,bot;


    /**
     * Metodo input recibe @param Bot y @param Top para almacenarlas en la clase y
     * @return el input recibido de err.
     */
    public static int input(int Bot,int Top){
        top=Top;
        bot=Bot;
        return err(intro.next());
    }

    /**
     * Metodo err intenta transformar a int el String recibido de
     * @param input, valida el input mediante ValidInt y
     * @return in ya validado
     */
    private static int err(String input){
        int in=0;
        try {
            in=validInt(Integer.parseInt(input));
        } catch (Exception ex) {
            System.out.println("Favor ingresar Dígitos y no carácteres");
            in=err(intro.next());
        }
        return in;
    }

    /**
     * Metodo ValidInt recibe @param in y revisa si esta entre losbot y  top establecidos en la clase, si es asi
     * @return in
     */
    private static int validInt(int in){
        if (in<bot || in >top) {
            System.out.println("Favor ingresar un número entre "+bot+" y "+top);
            in=input(bot,top);
        }
        return in;
    }
}