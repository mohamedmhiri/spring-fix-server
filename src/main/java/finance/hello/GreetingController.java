package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/getCotations", method = RequestMethod.GET, produces = "application/json")
    public List<Cotation> greeting() {
        String marche_url="http://www.ilboursa.com/marches/aaz.aspx";
        Document page_marche = null;
        ArrayList<Cotation> list_marche = new ArrayList<Cotation>();
        try {


            // Connect to the web site
            page_marche = Jsoup.connect(marche_url).get();

            Elements table = page_marche.select("table[id=tabQuotes]");
            //System.out.println("chaine +++++++++ "+table.get(0).toString());

            Element tablebody = table.select("tbody").get(0);

            for (Element row : tablebody.select("tr")) {
                Cotation c = new Cotation();

                // Identify all the table cell's(td)
                Elements tds = row.select("td");
                c.setNom(tds.get(0).text());
                c.setOuverture(tds.get(1).text());
                c.setHaut(tds.get(2).text());
                c.setBas(tds.get(3).text());
                c.setVolume(tds.get(5).text());
                c.setDernier(tds.get(6).text());
                c.setVariation(tds.get(7).text());


                list_marche.add(c);






            }


        }catch (IOException e) {
            e.printStackTrace();
        }

        return list_marche;
    }
}
