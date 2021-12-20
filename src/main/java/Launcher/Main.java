package Launcher;

import controlador.GestorDatos;
import interfaz.SolRecurso;
import interfaz.Interfaz;

public class Main {
    public static void main(String[] args) {
        Interfaz gui =new Interfaz();

        GestorDatos manager = new GestorDatos();

        SolRecurso gestorFuncion = new SolRecurso(manager);

        gui.setManager(gestorFuncion);
    }
}
