import java.util.Scanner;

public class LectorInt {

    static Scanner intro = new Scanner(System.in);
    private static int top,bot;


    public static int input(int Bot,int Top){
        top=Top;
        bot=Bot;
        return err(intro.next());
    }


    public static int err(String input){
        int in=0;
        try {
            in=validInt(Integer.parseInt(input));
        } catch (Exception ex) {
            System.out.println("Favor ingresar Dígitos y no carácteres");
            in=err(intro.next());
        }
        return in;
    }


    public static int validInt(int in){
        if (in<bot || in >top) {
            System.out.println("Favor ingresar un número entre "+bot+" y "+top);
            in=input(bot,top);
        }
        return in;
    }
}