package controlador;

import interfaz.Interfaz;
import modelo.Alimento;
import modelo.Etiqueta;
import modelo.Tipo;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ApiParser {

    private String[] data = new String[8];
    private ArrayList<Alimento> alimentos = new ArrayList<>();

    public ApiParser() {
        try {

            //d -> 1 == vegano.
            data[0] = ApiRequest.ApiCall("lacteos", 1, 1);
            Interfaz.loadPercent++;
            data[1] = ApiRequest.ApiCall("lacteos", 1, 2);
            Interfaz.loadPercent++;
            data[2] = ApiRequest.ApiCall("frutas-y-verduras", 1, 1);
            Interfaz.loadPercent++;
            data[3] = ApiRequest.ApiCall("frutas-y-verduras", 1, 2);
            Interfaz.loadPercent++;
            data[4] = ApiRequest.ApiCall("bebidas-aguas-y-jugos", 1, 1);
            Interfaz.loadPercent++;
            data[5] = ApiRequest.ApiCall("bebidas-aguas-y-jugos", 1, 2);
            Interfaz.loadPercent++;
            data[6] = ApiRequest.ApiCall("despensa", 1, 1);
            Interfaz.loadPercent++;
            data[7] = ApiRequest.ApiCall("despensa", 1, 2);
            Interfaz.loadPercent++;

            Interfaz.current=Interfaz.STATE.Main;


        }catch (Exception e){
            System.out.println(e);
        }

        parseData();
    }

    private void parseData() {
        String[] temp;
        for (int i=0;i<8;i++) {

            temp=data[i].split("\\{");

            for (int j = 0;j<temp.length/11;j++) {
                String nombreProducto=temp[(11*j)+2].split(",")[1].replace("\"productName\":", "").replace("\"", "");

                int precio = Integer.parseInt(temp[(11*j)+8].split(",")[0].replace("\"Price\":", ""));

                String ubicacion = temp[(11*j)+7].split(",")[1].replace("\"sellerName\":", "").replace("\"", "");

                String foto = temp[(11*j)+5].split(Pattern.quote("?"))[0].replace("\"imageUrl\":\"", "");

                alimentos.add(new Alimento(nombreProducto,parseTipo(i),precio,ubicacion,parseEtiqueta(i),foto));
            }

        }
    }

    private Etiqueta parseEtiqueta(int i){

        if ((i % 2) == 0) {
            return Etiqueta.Vegano;
        }else  return Etiqueta.Vegetariano;
    }

    private Tipo parseTipo(int i){
        switch ((int)i/2) {
            case 0:
                return Tipo.Leche_Y_Derivados;
            case 1:
                return Tipo.Carnes_Vegetales;
            case 2:
                return Tipo.Bebestibles;
            default :
                return Tipo.Otros;
        }
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
}

