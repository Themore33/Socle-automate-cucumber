package stepdef.model.page;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.google.com/")
public class PageExempleGoogle extends PageObject {

	@FindBy(name = "q")
	private WebElement champRecherche;
	
	@FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[2]/div[5]/center/input[1]")
	private WebElement boutonRecherche;

	public WebElement getChampRecherche() {
		return champRecherche;
	}

	public WebElement getBoutonRecherche() {
		return boutonRecherche;
	}
}
