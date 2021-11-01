import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Alimento {

    private String nombre;
    private Tipo tipo;
    private String precio;
    private String ubicacion;
    private Etiqueta etiqueta;

    /**
     * Objeto Alimento, recibe los parametros de nombre del producto, precio, donde encontrarlo y su etiqueta vegano/vegetariano
     * @param nombre nombre producto
     * @param tipo categoria producto
     * @param precio valor CLP producto
     * @param ubicacion Tienda en la cual se puede comprar
     * @param etiqueta Tipo vegano/vegetariano
     */
    public Alimento(String nombre, Tipo tipo, String precio, String ubicacion, Etiqueta etiqueta) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.etiqueta = etiqueta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * Metodo getPrintData, se encarga de devolver los datos del objeto, listos para impresion, ignorando los simbolos.
     * @return retorna los datos del objeto como String, ignorando los simbolos
     */
    public String getPrintData(){
        return(this.nombre+" | "+this.tipo.toString().replaceAll("_"," ")+" | "+this.precio+" | "+this.ubicacion+" | "+this.etiqueta);
    }

}

