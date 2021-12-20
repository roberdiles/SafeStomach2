package modelo;

public class Alimento {

    private String nombre;
    private Tipo tipo;
    private int precio;
    private String ubicacion;
    private Etiqueta etiqueta;
    private String foto;

    /**
     * Objeto modelo.Alimento, recibe los parametros de nombre del producto, precio, donde encontrarlo y su etiqueta vegano/vegetariano
     * @param nombre nombre producto
     * @param tipo categoria producto
     * @param precio valor CLP producto
     * @param ubicacion Tienda en la cual se puede comprar
     * @param etiqueta modelo.Tipo vegano/vegetariano
     */
    public Alimento(String nombre, Tipo tipo, int precio, String ubicacion, Etiqueta etiqueta,String foto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.etiqueta = etiqueta;
        this.foto=foto;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Metodo getPrintData, se encarga de devolver los datos del objeto, listos para impresion, ignorando los simbolos.
     * @return retorna los datos del objeto como String, ignorando los simbolos
     */
    public String getPrintData(){
        //this.nombre+":$"+String.format("%,d", this.precio)+":"+this.etiqueta+":"+this.tipo.toString().replaceAll("_"," ")+":"+this.ubicacion;
        return(this.nombre+";$"+String.format("%,d", this.precio)+";"+this.etiqueta+";"+this.tipo.toString().replaceAll("_"," ")+";"+this.ubicacion+";"+this.foto);
        // 0 -> NOMBRE
        // 1 -> PRECIO
        // 2 -> ETIQUETA
        // 3 -> TIPO
        // 4 -> UBICACION
        // 5 -> FOTO
    }

}

