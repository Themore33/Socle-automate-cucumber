package stepdef;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsqu;
import stepdef.base.BaseTest;
import stepdef.model.page.PageExempleGoogle;
import stepdef.model.page.PageExempleGoogleResultatRecherche;

/**
 * Contient exmple de test sur Google
 *
 * @author fmoresch
 *
 */

public class ExempleGoogleStepDefs extends BaseTest {

	PageExempleGoogle pageGoogle;
	PageExempleGoogleResultatRecherche pageGoogleResultatRecherche;
	
	@Lorsqu("^il saisit dans la zone recherche de Google \"([^\"]*)\"$")
	public void saisir_recherche_google(final String textRecherche) throws Throwable {
		//Acceptation des coockies en navigation privée
		this.clickOn(this.element(By.xpath("//*[@id=\"L2AGLb\"]/div")));
		
		this.typeInto(this.pageGoogle.getChampRecherche(), textRecherche);
	}
	
	@Lorsqu("^il clic sur le bouton recherche de Google$")
	public void clic_bouton_recherche_google() throws Throwable {
		this.clickOn(this.pageGoogle.getBoutonRecherche());
	}
	
	@Alors("^Google fait une recherche$")
	public void google_fait_une_recherche() {
		Assert.assertTrue(this.pageGoogleResultatRecherche.getStatDeResultat().getText().contains("résultats"));
	}
}
