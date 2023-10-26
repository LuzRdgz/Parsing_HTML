import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class etiquetasManejador extends HTMLEditorKit.ParserCallback {

    public static int hrefCounter ;
    public static String href;

    public etiquetasManejador() {
        super();
        hrefCounter = 0;
    }

    @Override
    public void handleText(char[] data, int pos) {

        //String texto = new String(data);
        //System.out.println(texto);
    }

    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {

        if( tag != HTML.Tag.A) {
            return;
        }

        //String tagName = tag.toString().toUpperCase();
        //int n = attributes.getAttributeCount();
        href = (String) attributes.getAttribute(HTML.Attribute.HREF);
        //System.out.printf("%d = %s: %s%n",hrefCounter, tagName, href );
        if (href.contains(".csv")){
            System.out.println("> CSV Name File: " + href);
            String newUrl = csvCounter.THE_URL + href;
            //System.out.println(newUrl);
            downloadThread down = new downloadThread(newUrl);
            down.start();
            contadorLineas();
            hrefCounter = 0;
        }
    }

    public void contadorLineas(){
        try {
            FileReader fr = new FileReader(href);
            BufferedReader bf = new BufferedReader(fr);

            while (bf.readLine() != null){
                hrefCounter++;
            }
            System.out.println("-- Total de registros en el fichero: " + hrefCounter);
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
