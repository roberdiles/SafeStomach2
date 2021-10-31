import java.util.ArrayList;
import java.util.Scanner;

public class GestorDatos {

    private ArrayList<Alimento> alimentos;

    public GestorDatos(){
        GestorCSV csvManager= new GestorCSV();
        this.alimentos=csvManager.getAlimentos();
    }

    public int seleccionar() { //REVISAR LA VALIDACION
        Scanner teclado = new Scanner(System.in);
        //String[] datos = lista();
        int respuesta;
        do {
            System.out.println("¿Que alimento desea ver?");
            respuesta = teclado.nextInt();
        } while (respuesta > 9 || respuesta<0);
        return respuesta;
    }

    public ArrayList<Alimento> getEtiquetadas(Etiqueta etiqueta){
        ArrayList<Alimento> temp= new ArrayList<>();
        for (int i=0;i<alimentos.size();i++) {
            if (alimentos.get(i).getEtiqueta()==etiqueta) {
                temp.add(alimentos.get(i));
            }
        }
        return temp;
    }

    public ArrayList<Alimento> getTipo(Tipo tipo){
        ArrayList<Alimento> temp= new ArrayList<>();
        for (int i=0;i<alimentos.size();i++) {
            if (alimentos.get(i).getTipo()==tipo) {
                temp.add(alimentos.get(i));
            }
        }
        return temp;
    }

    public void mostrarLista(ArrayList<Alimento> temp){
        for (int i=0;i<temp.size();i++){
            System.out.println(temp.get(i).getPrintData());
        }
    }


}
