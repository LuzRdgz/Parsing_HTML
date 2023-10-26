import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class csvCounter {

    public static final String TAG = csvCounter.class.getSimpleName();

    public static final Logger LOG = Logger.getLogger(TAG);
    public static final String THE_URL = "https://people.sc.fsu.edu/~jburkardt/data/csv/csv.html";

    public static void main(String[] args) {

        URL webPage = null;
        try {
            webPage = new URL(THE_URL);
        } catch (MalformedURLException ex) {
            LOG.severe(ex.getMessage());
        }

        // Crear un flujo para leer datos del URL
        BufferedReader htmlReader = null;
        try {
            htmlReader = new BufferedReader(
                    new InputStreamReader( webPage.openStream() ));
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }

        HTMLparser p  = new HTMLparser();
        HTMLEditorKit.Parser procesador = p.getParser();
        etiquetasManejador contador = new etiquetasManejador();
        try {
            // procesador.parse( fileReader, new ManejadorEtiquetas(), true);
            procesador.parse( htmlReader, contador, true);
        } catch (IOException e) {
            LOG.severe("No se puede leer documento HTML");
            System.exit(2);
        }
        System.out.println("Total de imagenes: " +  contador.getImgCounter());
    }

}
