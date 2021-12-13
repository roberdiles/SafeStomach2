import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GestorInput {

    private String[] data = new String[8];
    private ArrayList<Alimento> alimentos = new ArrayList<>();

    public GestorInput() {
        try {

            //d -> 1 == vegano.

            data[0] = ApiRequest.ApiCall("lacteos", 1, 1);
            data[1] = ApiRequest.ApiCall("lacteos", 1, 2);
            data[2] = ApiRequest.ApiCall("frutas-y-verduras", 1, 1);
            data[3] = ApiRequest.ApiCall("frutas-y-verduras", 1, 2);
            data[4] = ApiRequest.ApiCall("bebidas-aguas-y-jugos", 1, 1);
            data[5] = ApiRequest.ApiCall("bebidas-aguas-y-jugos", 1, 2);
            data[6] = ApiRequest.ApiCall("despensa", 1, 1);
            data[7] = ApiRequest.ApiCall("despensa", 1, 2);
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

                alimentos.add(new Alimento(nombreProducto,parseTipo(i),precio,ubicacion,parseEtiqueta(i)));
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

