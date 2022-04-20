package stepdef.model.page;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.ecosia.org")
public class PageExempleEcosia extends PageObject {

	@FindBy(xpath = "//*[@id=\"__layout\"]/div/div/header/div/div[2]/form/div[1]/input")
	private WebElement champRecherche;
	
	@FindBy(xpath = "//*[@id=\"__layout\"]/div/div/header/div/div[2]/form/div[2]/button[2]")
	private WebElement boutonRecherche;

	public WebElement getChampRecherche() {
		return champRecherche;
	}

	public WebElement getBoutonRecherche() {
		return boutonRecherche;
	}
}
