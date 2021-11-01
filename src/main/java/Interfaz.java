public class Interfaz {

    private GestorDatos manager;

    /**
     * Clase Interfaz, se encarga de mostrar la interfaz de usuario, recibe un gestorDatos para ejecutar las acciones
     * @param manager gestor de datos que posea el Arraylist obtenido del CSV
     */
    public Interfaz(GestorDatos manager){
        this.manager=manager;
        iniciarMenu();
    }


    private void iniciarMenu() {
        int opcion;
        do {
            opcion = opcionPrincipal();
            tipodeBusqueda(opcion);
        } while (opcion !=4);
        System.out.println("Está seguro");
        if (menuBinario()) iniciarMenu();
    }


    private int opcionPrincipal() {
        System.out.println("**********************************************************");
        System.out.println("*                Bienvenido a SaveStomach                *");
        System.out.println("**********************************************************");
        System.out.println("Por favor, seleccione una de las opcíones");
        System.out.println("[1] Ver Comida de tipo Vegetariana");
        System.out.println("[2] Ver Comida de Tipo Vegana");
        System.out.println("[3] Ver por Categoría");
        System.out.println("[4] Salir del Programa");
        return LectorInt.input(1,4);
    }


    private boolean menuBinario() {
        System.out.println("[1] Si");
        System.out.println("[2] No");
        if (LectorInt.input(1,2) == 1) {
            return false;
        } else return true;
    }


    private void tipodeBusqueda(int ingresoDatos) {
        switch (ingresoDatos) {
            case 1 -> {
                do {
                    System.out.println("Lista de Comidas Vegetarianas");

                    manager.mostrarLista(manager.getEtiquetadas(Etiqueta.Vegetariano));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
            }
            case 2 -> {
                do {
                    System.out.println("Lista de Comida Vegana");

                    manager.mostrarLista(manager.getEtiquetadas(Etiqueta.Vegano));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
                break;
            }
            case 3 -> {
                do {
                    menuCategoria();
                } while (menuBinario());
                break;
            }
        }
    }

    private void menuCategoria(){
        int opcion;
        do {
            opcion = opcionCategoria();
            mostrarCategoria(opcion);
        } while (opcion !=9);
    }

    private int opcionCategoria() {
        System.out.println("**********************************************************");
        System.out.println("*                       Categorías                       *");
        System.out.println("**********************************************************");
        System.out.println("Por favor, seleccione una de las opcíones");
        System.out.println("[1] ver Leche y Derivados");
        System.out.println("[2] ver Carnes Vegetales");
        System.out.println("[3] ver Aceites y Grasas");
        System.out.println("[4] ver Snacks");
        System.out.println("[5] ver Bebestibles");
        System.out.println("[6] ver Pan");
        System.out.println("[7] ver Cereales y Pastas");
        System.out.println("[8] ver Otros");
        System.out.println("[9] Volver");
        return LectorInt.input(1,9);
    }

    private void mostrarCategoria(int opcion){
        switch(opcion) {
            case 1 -> {
                do {
                    System.out.println("Lista de Leches y Derivados");

                    manager.mostrarLista(manager.getTipo(Tipo.Leche_Y_Derivados));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
                break;
            }
            case 2 -> {
                do {
                    System.out.println("Lista de Carnes Vegetales");

                    manager.mostrarLista(manager.getTipo(Tipo.Carnes_Vegetales));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
            }
            case 3 -> {
                do {
                    System.out.println("Lista de Aceites y Grasas");

                    manager.mostrarLista(manager.getTipo(Tipo.Aceites_Y_Grasas));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
            }
            case 4 -> {
                do {
                    System.out.println("Lista de Snacks");

                    manager.mostrarLista(manager.getTipo(Tipo.Snacks));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
            }
            case 5 -> {
                do {
                    System.out.println("Lista de Bebestibles");

                    manager.mostrarLista(manager.getTipo(Tipo.Bebestibles));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
            }
            case 6 -> {
                do {
                    System.out.println("Lista de Pan");

                    manager.mostrarLista(manager.getTipo(Tipo.Pan));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
            }
            case 7 -> {
                do {
                    System.out.println("Lista de Cereales y Pastas");

                    manager.mostrarLista(manager.getTipo(Tipo.Cereales_Y_Pastas));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
            }
            case 8 -> {
                do {
                    System.out.println("Lista de Otros");

                    manager.mostrarLista(manager.getTipo(Tipo.Otros));

                    System.out.println("Desea volver al Menú?");
                } while (menuBinario());
            }
        }
    }
}
