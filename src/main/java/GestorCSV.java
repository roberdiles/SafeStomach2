import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GestorCSV {

    private String file=("alimentos.csv"); //SET FILE PATH
    private ArrayList<Alimento> alimentos = new ArrayList<>();

    // no se usa en esta instancia
    /*
    public static void addComida(Alimento comida){
        //set string to the data to rewrite (not useful in this case)
        //String line=(comida.getID()+","+comida.getDate()+","+tckt.getMealsString()+","+tckt.getTotal());
        tryWrite(line);
    }
     */

    public GestorCSV(){
        tryRead();
    }

    // no se usa en esta instancia
    /*
    private static void tryWrite(String line){
        String text="null";
        try {
            text=new String(Files.readAllBytes(file));
            text =text+ "\n" + line;
            Files.write(file, text.getBytes(), new OpenOption[0]);
        } catch (Exception ex) {
            System.out.println("Error, no se pudo agregar la nueva línea.");
        }
    }
    */

    private String tryRead(){
        String text="null";
        try {
            reader(new BufferedReader(new FileReader(file)));
        } catch (Exception ex) {
            System.out.println("Error, no se pudo leer el archivo.");
            System.out.println(ex.getMessage());
        }
        return text;
    }

    private void reader(BufferedReader csvReader) throws Exception{
        String row;
        while ((row = csvReader.readLine()) != null){
            String[] data = row.split(";");
            interpreter(data);
        }
        csvReader.close();
    }

    private void interpreter(String[] data){
        if (!data[4].equals("Etiqueta")) {
            Etiqueta etiqueta = Etiqueta.valueOf(data[4]);
            Tipo tipo = Tipo.valueOf(data[1]);
            alimentos.add(new Alimento(data[0],tipo,data[2],data[3],etiqueta));
        }
    }

    public ArrayList<Alimento> getAlimentos(){
        return this.alimentos;
    }

}

