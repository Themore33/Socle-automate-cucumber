package stepdef;

import org.junit.Assert;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsqu;
import stepdef.base.BaseTest;
import stepdef.model.page.PageExempleEcosia;
import stepdef.model.page.PageExempleEcosiaResultatRecherche;

/**
 * Contient exmple de test sur Google
 *
 * @author fmoresch
 *
 */

public class ExempleEcosiaStepDefs extends BaseTest {
	
	PageExempleEcosia pageEcosia;
	PageExempleEcosiaResultatRecherche pageEcosiaResultatRecherche;
	
	@Lorsqu("^il saisit dans la zone recherche d écosia \"([^\"]*)\"$")
	public void il_saisit_recherche_ecosia(final String textRecherche) throws Throwable {
		this.typeInto(this.pageEcosia.getChampRecherche(), textRecherche);
	}
	
	@Lorsqu("^il clic sur le bouton recherche d ecosia$")
	public void clic_bouton_recherche_ecosia() throws Throwable {
		this.clickOn(this.pageEcosia.getBoutonRecherche());
	}
	
	@Alors("^Ecosia fait une recherche$")
	public void ecosia_fait_une_recherche() {
		Assert.assertEquals("résultats", this.pageEcosiaResultatRecherche.getStatDeResultat().getText());
	}
}
