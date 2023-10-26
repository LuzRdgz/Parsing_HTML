import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class etiquetasManejador extends HTMLEditorKit.ParserCallback {

    private int hrefCounter ;

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

        hrefCounter++ ;

        String tagName = tag.toString().toUpperCase();
        int n = attributes.getAttributeCount();
        String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
        //System.out.printf("%d = %s: %s%n",hrefCounter, tagName, href );
        if (href.contains(".csv")){

            System.out.println(href);
        }
    }


    public int getImgCounter() {
        return hrefCounter;
    }

}
