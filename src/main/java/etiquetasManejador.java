import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.util.ArrayList;

public class etiquetasManejador extends HTMLEditorKit.ParserCallback {

    private int hrefCounter ;
    //public static ArrayList<String> nameCSV = new ArrayList<>();

    String nameCSV;

    public etiquetasManejador() {
        super();
        hrefCounter = 0;
    }

    @Override
    public void handleText(char[] data, int pos) {

        String texto = new String(data);
        //System.out.println(texto);
    }

    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {

        if( tag != HTML.Tag.A) {
            return;
        }

        String tagName = tag.toString().toUpperCase();
        int n = attributes.getAttributeCount();
        String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
        //System.out.printf("%d = %s: %s%n",hrefCounter, tagName, href );
        if (href.contains(".csv")){
            System.out.println(href);
            String newUrl = csvCounter.THE_URL + href;
            System.out.println(newUrl);
            downloadThread down = new downloadThread(newUrl);
            down.start();
        }

    }


    public int getCSvCounter() {
        return hrefCounter;
    }

}
