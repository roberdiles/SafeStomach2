package controlador;

import modelo.Alimento;
import modelo.Etiqueta;
import modelo.Tipo;

import java.util.ArrayList;

public class GestorDatos {

    private ArrayList<Alimento> alimentos;

    /**
     * Clase GestorDatos encargada de gestionar los datos recibidos de la API
     */
    public GestorDatos(){
        ApiParser pageRetriever= new ApiParser();
        this.alimentos=pageRetriever.getAlimentos();
    }


    /*public int seleccionar() {
        Scanner teclado = new Scanner(System.in);
        //String[] datos = lista();
        int respuesta;
        do {
            System.out.println("¿Que alimento desea ver?");
            respuesta = teclado.nextInt();
        } while (respuesta > 9 || respuesta<0);
        return respuesta;
    }
    Aun no se implementa
     */

    /**
     * Metodo getEtiquetadas, recibe el parametro etiqueta y devuelve un arreglo de todos los objetos que coincidan con la etiqueta
      * @param etiqueta Tipo (vegano/vegetariano)
     * @return retorna el arreglo con los objetos coincidentes con la etiqueta dada
     */
    public ArrayList<Alimento> getEtiquetadas(Etiqueta etiqueta){
        ArrayList<Alimento> temp= new ArrayList<>();
        for (int i=0;i<alimentos.size();i++) {
            if (alimentos.get(i).getEtiqueta()==etiqueta) {
                temp.add(alimentos.get(i));
            }
        }
        return temp;
    }

    /**
     * Metodo getTipo, recibe el tipo de alimento y devuelve un arreglo con los objetos del tipo especificado
     * @param tipo Tipo segun las 4 categorias seleccionables
     * @return retorna el arreglo con los objetos coincidentes con el tipo elegido por el usuario
     */
    public ArrayList<Alimento> getTipo(Tipo tipo){
        ArrayList<Alimento> temp= new ArrayList<>();
        for (int i=0;i<alimentos.size();i++) {
            if (alimentos.get(i).getTipo()==tipo) {
                temp.add(alimentos.get(i));
            }
        }
        return temp;
    }

    /**
     * Metodo mostrarLista, recibe un Arreglo de objeto alimento e imprime todos los objetos de la lista
     * @param temp Lista de objetos a imprimir
     */
    public String[] parsearLista(ArrayList<Alimento> temp){
        String[] retorno = new String[temp.size()];
        for (int i=0;i<temp.size();i++){
            retorno[i]=temp.get(i).getPrintData();
        }
        return retorno;
    }


}
