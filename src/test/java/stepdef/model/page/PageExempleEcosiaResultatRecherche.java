package stepdef.model.page;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

public class PageExempleEcosiaResultatRecherche extends PageObject {

	@FindBy(xpath = "//span[contains(text(),\"résultats\")]")
	private WebElement statDeResultat;

	public WebElement getStatDeResultat() {
		return statDeResultat;
	}
}
