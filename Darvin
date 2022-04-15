package darvin;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.IOException;

public class Darvin {

    public static void main(String[] args) throws IOException {
        try ( WebClient client = new WebClient()) {
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            HtmlPage page;

            for (int i = 0; i < 1000; i++) {
                page = client.getPage("https://dar.vin/");
                HtmlInput linkURL = (HtmlInput) page.getElementByName("link-url");
                HtmlInput customEnding = (HtmlTextInput) page.getElementByName("custom-ending");
                HtmlInput shorten = (HtmlSubmitInput) page.getElementById("shorten");
                linkURL.setAttribute("value", "https://duckduckgo.com/?q=lol");
                customEnding.setAttribute("value", "anil" + i);
                shorten.click();
            }
        }
    }
}
