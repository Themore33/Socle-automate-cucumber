package stepdef.base;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.PageObject;


public class BasePage extends PageObject {
	
	public static Long defaultImplicitlyWaitTimeOut;
	private final int TimeOutParDefaut = 20;
	private Random randomizer;

	@SuppressWarnings("deprecation")
	protected void waitForElement(final By locator) throws Exception {
		try {
			(new WebDriverWait(super.getDriver(), this.TimeOutParDefaut))
			.until(ExpectedConditions.presenceOfElementLocated(locator));

		} catch (final Exception e) {
			throw new Exception("Erreur sur la page " + this.getClass().getSimpleName() + " Element non trouvé : "
					+ locator.toString());
		}

		try {
			(new WebDriverWait(super.getDriver(), this.TimeOutParDefaut))
			.until(ExpectedConditions.visibilityOfElementLocated(locator));

		} catch (final Exception e) {
			throw new Exception("Erreur sur la page " + this.getClass().getSimpleName()
					+ " Element présent, mais non visible : " + locator.toString());
		}
	}

	@SuppressWarnings("deprecation")
	protected void waitToBeClickable(final By locator) throws Exception {
		try {
			(new WebDriverWait(super.getDriver(), this.TimeOutParDefaut))
			.until(ExpectedConditions.presenceOfElementLocated(locator));

		} catch (final Exception e) {
			throw new Exception("Erreur sur la page " + this.getClass().getSimpleName() + " Element non trouvé : "
					+ locator.toString());
		}

		try {
			(new WebDriverWait(super.getDriver(), this.TimeOutParDefaut))
			.until(ExpectedConditions.elementToBeClickable(locator));

		} catch (final Exception e) {
			throw new Exception("Erreur sur la page " + this.getClass().getSimpleName()
					+ " Element présent, mais non visible : " + locator.toString());
		}
	}

	@SuppressWarnings("deprecation")
	protected void waitToBeClickable(final WebElement champ) throws Exception {
		try {
			(new WebDriverWait(super.getDriver(), this.TimeOutParDefaut))
			.until(ExpectedConditions.elementToBeClickable(champ));

		} catch (final Exception e) {
			throw new Exception("Erreur sur la page " + this.getClass().getSimpleName()
					+ " Element présent, mais non visible : " + champ.toString());
		}
	}

	protected void verifierTexte(final By champ, final String texteAVerifier) throws Exception {
		this.waitForElement(champ);
		final WebElement elementAControler = super.getDriver().findElement(champ);
		this.verifierTexte(elementAControler, texteAVerifier);
	}

	protected void verifierValeurInput(final By champ, final String texteAVerifier) throws Exception {
		this.waitForElement(champ);
		final WebElement elementAControler = super.getDriver().findElement(champ);
		this.verifierValeurInput(elementAControler, texteAVerifier);
	}

	protected void verifierValeurInput(final WebElement champ, final String texteAVerifier) throws Exception {
		Assert.assertEquals(texteAVerifier, champ.getAttribute("value"));
	}

	protected void verifierTexte(final WebElement champ, final String texteAVerifier) throws Exception {
		Assert.assertEquals(texteAVerifier, champ.getText());
	}

	protected void verifierCheckbox(final By checkbox, final boolean etatAVerifier) throws Exception {
		this.waitForElement(checkbox);
		final WebElement elementAControler = super.getDriver().findElement(checkbox);
		this.verifierCheckbox(elementAControler, etatAVerifier);
	}

	protected void verifierCheckbox(final WebElement checkbox, final boolean etatAVerifier) throws Exception {
		Assert.assertEquals(checkbox.isSelected(), etatAVerifier);
	}

	protected void verifierChampActive(final By champ, final boolean etatAVerifier) throws Exception {
		this.waitForElement(champ);
		final WebElement elementAControler = super.getDriver().findElement(champ);
		Assert.assertEquals(elementAControler.isEnabled(), etatAVerifier);
	}

	protected String recupererInnerText(final By champ) throws Exception {
		this.waitForElement(champ);
		return super.getDriver().findElement(champ).getAttribute("innerText");
	}

	protected String recupererText(final By champ) throws Exception {
		this.waitForElement(champ);
		return super.getDriver().findElement(champ).getAttribute("text");
	}

	protected String recupererValue(final By champ) throws Exception {
		this.waitForElement(champ);
		return super.getDriver().findElement(champ).getAttribute("value");
	}

	protected Boolean estPresent(final By element) throws Exception {
		final List<WebElement> presence = super.getDriver().findElements(element);
		return (presence.size() > 0);
	}

	protected Boolean verifierPresenceTousLesChampsAttendus(final List<By> champsAttendus) throws Exception {
		Boolean tousLesChampsSontPresents = true;
		for (final By champAttendu : champsAttendus) {
			tousLesChampsSontPresents = tousLesChampsSontPresents && this.estPresent(champAttendu);
		}

		return tousLesChampsSontPresents;
	}

	@SuppressWarnings("deprecation")
	protected Boolean estPresentImmediat(final By element) throws Exception {
		super.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		final List<WebElement> presence = super.getDriver().findElements(element);
		super.getDriver().manage().timeouts().implicitlyWait(defaultImplicitlyWaitTimeOut, TimeUnit.SECONDS);
		return (presence.size() > 0);
	}

	protected String recupererTextContent(final By champ) throws Exception {
		this.waitForElement(champ);
		return super.getDriver().findElement(champ).getAttribute("textContent");
	}
	
	public void selectionnerOptionComboboxParTexte(final By combobox, final String texte) throws IOException {
		final Select selectCombobox = new Select(super.getDriver().findElement(combobox));
		selectCombobox.selectByVisibleText(texte);
	}

	public boolean verifierOptionSeletionnee(final By combobox, final String texte) throws IOException {
		final Select selectCombobox = new Select(super.getDriver().findElement(combobox));
		final String test = selectCombobox.getFirstSelectedOption().getText();
		if (test.equals(texte)) {
			return true;
		}
		return false;
	}
	
	protected String recupererInnerText(final WebElement we) throws Exception {
		return we.getAttribute("innerText");
	}

	public void scrollerEtCentrerElement(final WebElement we) throws Exception {
		((JavascriptExecutor) super.getDriver()).executeScript("arguments[0].scrollIntoView(true);", we);
	}

	public boolean unElementCheck(final List<WebElement> listeTypeVehicule) {
		for (final WebElement webElement : listeTypeVehicule) {
			if (webElement.getAttribute("checked") != null) {
				return true;
			}
		}
		return false;
	}

	public void scrollToElement(final By element) throws Exception {
		final WebElement prod = super.getDriver().findElement(element);
		this.scrollToElement(prod);
	}

	public void scrollToElement(final WebElement element) throws Exception {
		((JavascriptExecutor) super.getDriver()).executeScript("window.scrollTo(0," + (element.getLocation().y - 200) + ")");
		Thread.sleep(500);
	}

	public Alert recupererAlert() throws Exception {
		final Alert alert = super.getDriver().switchTo().alert();
		return alert;
	}

	protected Boolean estPresentImmediatSafe(final By element) {
		try {
			return this.estPresentImmediat(element);
		} catch (final Exception e) {
			System.out.println(String.format("Erreur à la récupération de l'élément [%s], ignoré", element));
			e.printStackTrace();
			return false;
		}
	}

	protected Boolean estPresentSafe(final By element) {
		try {
			return this.estPresent(element);
		} catch (final Exception e) {
			System.out.println(String.format("Erreur à la récupération de l'élément [%s], ignoré", element));
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isAlertPresent() {
		try {
			super.getDriver().switchTo().alert();
			return true;
		} catch (final Exception e) {
			return false;
		}
	}
	
	public void changerFenetreFocusParLAutomate(String urlCible) throws IOException {
		final Set<String> handles = super.getDriver().getWindowHandles();
		Boolean trouve=false;

		for (final String handle : handles) {
			super.getDriver().switchTo().window(handle);
			System.out.println("Focus : " + super.getDriver().getCurrentUrl());
			System.out.println("Cible : " + urlCible);
			if (super.getDriver().getCurrentUrl().equals(urlCible)) {
				trouve=true;
				break;
			}
		}
		if(trouve==false){
			System.out.println("Attention Url de destination non trouvée position sur la derniere fenêtre ouverte");
		}
	}
	
	public void changerFrameAutomate(final By element) throws Exception {
		this.waitForElement(element);
		final WebElement frameCible = super.getDriver().findElement(element);
		this.getDriver().switchTo().frame(frameCible);
	}
	
	public void resetFrameAutomate(final By element) throws Exception {
		this.getDriver().switchTo().frame(0);
	}

	protected void cliquerSurElementDUneListe(final By list, final String messageErreur, int indexList)	throws Exception {
		int essai =  0;
		
		final List<WebElement> listeElements = super.getDriver().findElements(list);
		if (listeElements.size() > indexList) {
			
			this.scrollToElement(listeElements.get(indexList));
			this.waitToBeClickable(listeElements.get(indexList));

			while(essai < 5) {
				try {
					super.getDriver().findElements(list).get(indexList).click();
					Thread.sleep(500);
					break;
				} catch(StaleElementReferenceException e) {
					System.out.println("Attention l'élément a été mise à jour dans le dom : Tentative de clic : " + essai);
				}
				essai++;
			}
		} else {
			fail(messageErreur);
		}

	}

	protected void cliquerSurPremierElementDUneListe(final By list, final String messageErreur)	throws Exception {
		int essai =  0;
		
		final List<WebElement> listeElements = super.getDriver().findElements(list);
		if (listeElements.size() > 0) {
			
			this.scrollToElement(listeElements.get(0));
			this.waitToBeClickable(listeElements.get(0));

			while(essai < 5) {
				try {
					super.getDriver().findElements(list).get(0).click();
					Thread.sleep(500);
					break;
				} catch(StaleElementReferenceException e) {
					System.out.println("Attention l'élément a été mise à jour dans le dom : Tentative de clic : " + essai);
				}
				essai++;
			}
		} else {
			fail(messageErreur);
		}

	}

	protected void cliquerSurUnElementAleatoireDUneListe(final By list,final String messageErreur) throws Exception {
		int random = 0;
		int essai =  0;
		
		final List<WebElement> listeElements = super.getDriver().findElements(list);
		if (listeElements.size() > 0) {
			this.randomizer = new Random();
			random = this.randomizer.nextInt(listeElements.size());
						
			this.scrollToElement(listeElements.get(random));
			this.waitToBeClickable(listeElements.get(random));

			while(essai < 5) {
				try {
					super.getDriver().findElements(list).get(random).click();
					Thread.sleep(500);
					break;
				} catch(StaleElementReferenceException e) {
					System.out.println("Attention l'élément a été mise à jour dans le dom : Tentative de clic : " + essai);
				}
				essai++;
			}
		} else {
			fail(messageErreur);
		}

	}

	public List<String> recupererTexteListeWebElements(List<WebElement> listeElements) {
		final List<String> listeTextes = new ArrayList<>();

		for (final WebElement e : listeElements) {
			listeTextes.add(e.getText());
		}

		return listeTextes;
	}

	protected void viderValeurChamp(final By champ) throws Exception {
		this.waitForElement(champ);
		final WebElement elementAVider = super.getDriver().findElement(champ);
		this.viderValeurChamp(elementAVider);
	}

	protected void viderValeurChamp(final WebElement champ) throws Exception {
		champ.clear();
	}
	
	protected boolean comparerListeString(List<String> liste1, List<String> liste2) throws Exception {
		for (final String o : liste1) {
			if (!(liste2.contains(o))) {
				System.out.println("1 : " + o);
				return false;
			}
		}
		for (final String o : liste2) {
			if (!(liste1.contains(o))) {
				System.out.println("2 : " + o);
				return false;
			}
		}
		return true;
	}

	public boolean comparerListeDisponibleEtListeAttendue(final List<WebElement> listeDisponible,
			final List<String> listeAttendue) {
		final List<String> listeObtenue = new ArrayList<>();
		for (int i = 0; i < listeDisponible.size(); i++) {
			listeObtenue.add(listeDisponible.get(i).getText());
		}

		return listeAttendue.equals(listeObtenue);
	}
}
