import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



import java.io.Serializable;

public class ApiRequest {

    /*
    public static void main(String[] var0) throws IOException {
        ApiCall("lacteos",1,3);
        // variable linkText del response para llamar al producto por nombre
        //CallByNameApi("leche-descremada-colun-1-l-2");
    }
    */


    // metodo recibe la categoria(c), la pagina(p) y subcategoria(d)
    // 1 vegano
    // 2 vegetariano
    // 3 ambos
    //
    // (c)categorias permitidas :
    // despensa
    // lacteos
    // frutas-y-verduras
    // bebidas-aguas-y-jugos
    // pagina puede variar, si retorna null no hay disponibilidad
    // puede crear metodo que se encarge de contar el total que llega al final de la respuesta de la pagina 1
    public static String ApiCall(String c, int page, int d) throws IOException {
        //obtenemos la apikey del server
        //GetApiKeyResponse getApiKeyResponse = client.security().getApiKey(getApiKeyRequest, RequestOptions.DEFAULT);

        String url = "https://apijumboweb.smdigital.cl/catalog/api/v2/products/";
        String apikey = "IuimuMneIKJd3tapno2Ag1c1WcAES97j";
        String colaurl = "";
        String urlfinal = url+c;
        String varresponse = "";

        if (d == 1){
            //vegano
            colaurl = "?s=condiciones%20alimentarias:Vegano";
        }else if(d == 2){
            // vegetariano
            colaurl = "?s=condiciones%20alimentarias:Vegetariano";
        }else{
            //ambos
            colaurl = "?s=condiciones%20alimentarias:Vegano,condiciones%20alimentarias:Vegetariano";
        }

        // url que sera llamada
        urlfinal = urlfinal+colaurl;
        URL apilink = new URL(urlfinal);

        // conectamos a la url
        HttpURLConnection reuqestapi = (HttpURLConnection)apilink.openConnection();

        // headers y method
        reuqestapi.setRequestMethod("GET");
        reuqestapi.setRequestProperty("page", String.valueOf(page));
        reuqestapi.setRequestProperty("sc", String.valueOf(11));
        reuqestapi.setRequestProperty("x-api-key", apikey);

        // obtenemos codigo de respuesta
        int responsecode = reuqestapi.getResponseCode();

        System.out.println(urlfinal);
        if (responsecode != 200){
            // llamamos al servidor pero la respuesta no fue la esperada
            // posible error en url o apikey
            System.out.println("GET NOT WORKED");
            return "Null";

        }else{
            // ejecucion del get method
            BufferedReader var7 = new BufferedReader(new InputStreamReader(reuqestapi.getInputStream()));
            StringBuilder var8 = new StringBuilder();

            // no me acuerdo que hace esta linea xd
            while((varresponse = var7.readLine()) != null){
                var8.append(varresponse);
            }

            // cerramos la conexion
            var7.close();

            // salida
            System.out.println("GET WORKED");
            //System.out.println(var8.toString());
            return var8.toString();
        }
    }

    public static String CallByNameApi(String n) throws IOException {
        String url = "https://apijumboweb.smdigital.cl/catalog/api/v1/product/";
        String apikey = "IuimuMneIKJd3tapno2Ag1c1WcAES97j";

        String varresponse;
        String nn = n.replace(" ", "-");
        String urlfinal = url+nn;
        URL apilink = new URL(urlfinal);
        //System.out.println(n+" nueva: "+nn);

        // conectamos a la url
        HttpURLConnection requestapi = (HttpURLConnection)apilink.openConnection();

        // headers y method
        requestapi.setRequestMethod("GET");
        requestapi.setRequestProperty("x-api-key", apikey);

        // obtenemos codigo de respuesta
        int responsecode = requestapi.getResponseCode();

        System.out.println(urlfinal);
        if (responsecode != 200){
            // llamamos al servidor pero la respuesta no fue la esperada
            // posible error en url o apikey
            System.out.println("GET NOT WORKED");
            return "Null";

        }else{
            // ejecucion del get method
            BufferedReader var7 = new BufferedReader(new InputStreamReader(requestapi.getInputStream()));
            StringBuilder var8 = new StringBuilder();

            // no me acuerdo que hace esta linea xd
            while((varresponse = var7.readLine()) != null){
                var8.append(varresponse);
            }

            // cerramos la conexion
            var7.close();

            // salida
            System.out.println("GET WORKED");
            //System.out.println(var8.toString());
            return var8.toString();
        }
    }

}
