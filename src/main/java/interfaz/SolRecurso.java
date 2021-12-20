package interfaz;

import controlador.GestorDatos;
import modelo.Etiqueta;
import modelo.Tipo;

public class SolRecurso {

    private GestorDatos manager;

    /**
     * Clase interfaz.GestorFuncion, se encarga de mostrar la interfaz de usuario, recibe un gestorDatos para ejecutar las acciones
     * @param manager gestor de datos que posea el Arraylist obtenido del CSV
     */
    public SolRecurso(GestorDatos manager){
        this.manager=manager;
        //iniciarMenu();
    }

    public String[] tipodeBusqueda(int ingresoDatos) {
        String[] temp = new String[200];
        switch (ingresoDatos) {
            case 1 -> {
                //do {
                    //System.out.println("Lista de Comidas Vegetarianas");

                    temp=manager.parsearLista(manager.getEtiquetadas(Etiqueta.Vegetariano));

                    //System.out.println("Desea volver al Menú?");
                //} while (menuBinario());
            }
            case 2 -> {
                //do {
                    //System.out.println("Lista de Comida Vegana");

                    temp=manager.parsearLista(manager.getEtiquetadas(Etiqueta.Vegano));

                    //System.out.println("Desea volver al Menú?");
                //} while (menuBinario());
                break;
            }
            case 3 -> {
                //do {
                //System.out.println("Lista de Leches y Derivados");

                temp=manager.parsearLista(manager.getTipo(Tipo.Leche_Y_Derivados));

                //System.out.println("Desea volver al Menú?");
                //} while (menuBinario());
                break;
            }
            case 4 -> {
                //do {
                //System.out.println("Lista de Carnes Vegetales");

                temp=manager.parsearLista(manager.getTipo(Tipo.Carnes_Vegetales));

                //System.out.println("Desea volver al Menú?");
                //} while (menuBinario());
            }
            case 5 -> {
                //do {
                //System.out.println("Lista de Aceites y Grasas");

                temp=manager.parsearLista(manager.getTipo(Tipo.Bebestibles));

                //System.out.println("Desea volver al Menú?");
                //} while (menuBinario());
            }
            case 6 -> {
                //do {
                //System.out.println("Lista de Snacks");

                temp=manager.parsearLista(manager.getTipo(Tipo.Otros));

                //System.out.println("Desea volver al Menú?");
                //} while (menuBinario());
            }
        }
        return temp;
    }

}
