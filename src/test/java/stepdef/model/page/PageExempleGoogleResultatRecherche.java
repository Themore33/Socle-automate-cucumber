package stepdef.model.page;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.google.com/")
public class PageExempleGoogleResultatRecherche extends PageObject {

	@FindBy(id = "result-stats")
	private WebElement statDeResultat;

	public WebElement getStatDeResultat() {
		return statDeResultat;
	}
}
