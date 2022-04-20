package stepdef;

import io.cucumber.java.fr.Etantdonné;
import stepdef.base.BaseTest;
import stepdef.model.page.PageExempleEcosia;
import stepdef.model.page.PageExempleGoogle;

/**
 * Contient exmple de test sur Google
 *
 * @author fmoresch
 *
 */

public class ExempleMoteurDeRechercheStepDefs extends BaseTest {

	PageExempleGoogle pageGoogle;
	PageExempleEcosia pageEcosia;

	@Etantdonné("^un utilisateur sur le moteur de recherche \"([^\"]*)\"$")
	public void utilisateur_sur_moteur_recherche(final String nomMoteurRecherche) throws Throwable {
		this.getDriver().manage().window().maximize();
		switch(nomMoteurRecherche){
			case "Google" :
				this.pageGoogle.open();
				break;
			case "Ecosia" :
				this.pageEcosia.open();
				break;
		}
	}
}
