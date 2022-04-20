package stepdef.base;

import static org.junit.Assert.fail;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.SystemUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class BaseTest extends BasePage {

	private static final String DATA_DATA_XML = "Data/DATA";
	private static ThreadLocal<Document> docXML= new ThreadLocal<>();

	public static Document getdocXML() {
		return docXML.get();
	}

	public synchronized static void ouvrirJeuxDeDonnee(final String classDeTest) throws IOException {
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		try {
			final DocumentBuilder builder = dbf.newDocumentBuilder();
			final InputSource is = new InputSource(cheminCompletRessourceTest(DATA_DATA_XML+"_"+classDeTest+".xml"));
			docXML.set(builder.parse(is));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized static String cheminCompletRessourceTest(final String fichier) {
		String path = BaseTest.class.getClassLoader().getResource(fichier).getPath();
		if (SystemUtils.IS_OS_WINDOWS) {
			// les dossiers sous windows doivent être séparés par des \, et non pas par des
			// / comme ce qui est retourné par getPath() ci-dessus
			path = path.replaceAll("^\\/", "").replace('/', '\\');
		}
		System.out.println(String.format("Ouverture du fichier : %s", path));
		return path;
	}
	
	public synchronized String recupererBaliseXML(final String classTest, final String nomTest, final String attribut) throws IOException {

		final NodeList ClassDeTest = getdocXML().getChildNodes();
		for(int a = 0; a<ClassDeTest.getLength(); a++) {
//			System.out.println("Node Class : " + ClassDeTest.item(a).getNodeName());
			if (ClassDeTest.item(a).getNodeName().equals(classTest)){
				final NodeList NomDeTest = ClassDeTest.item(a).getChildNodes();
				for(int b = 0; b<NomDeTest.getLength(); b++) {
//					System.out.println("Node Test : " + NomDeTest.item(b).getNodeName());
					if (NomDeTest.item(b).getNodeName().equals(nomTest)){
						final NodeList AttributDeTest = NomDeTest.item(b).getChildNodes();
						for(int c = 0; c<AttributDeTest.getLength(); c++) {
//							System.out.println("Node Attribut : " + AttributDeTest.item(c).getNodeName());
							if (AttributDeTest.item(c).getNodeName().equals(attribut)){
//								System.out.println("Valeur Attribut : " + AttributDeTest.item(c).getTextContent());
								return AttributDeTest.item(c).getTextContent();
							}
						}
					}
				}
			}
		}
		fail("Dans le fichier : " + classTest +" La donne du champ : "+attribut+" n'a pas été trouvé pour le cas de test : "+nomTest);
		return "";
	}
	
	public synchronized void stockerBaliseXML(final String classTest, final String nomTest, final String attribut,final String value) throws IOException {
		final NodeList ClassDeTest = getdocXML().getChildNodes();
		for(int a = 0; a<ClassDeTest.getLength(); a++) {
//			System.out.println("Node Class : " + ClassDeTest.item(a).getNodeName());
			if (ClassDeTest.item(a).getNodeName().equals(classTest)){
				final NodeList NomDeTest = ClassDeTest.item(a).getChildNodes();
				for(int b = 0; b<NomDeTest.getLength(); b++) {
//					System.out.println("Node Test : " + NomDeTest.item(b).getNodeName());
					if (NomDeTest.item(b).getNodeName().equals(nomTest)){
						final NodeList AttributDeTest = NomDeTest.item(b).getChildNodes();
						for(int c = 0; c<AttributDeTest.getLength(); c++) {
//							System.out.println("Node Attribut : " + AttributDeTest.item(c).getNodeName());
							if (AttributDeTest.item(c).getNodeName().equals(attribut)){
//								System.out.println("Valeur Attribut : " + AttributDeTest.item(c).getTextContent());
								AttributDeTest.item(c).setNodeValue(value);
							}
						}
					}
				}
			}
		}
		fail("Dans le fichier : " + classTest +" La donne du champ : "+attribut+" n'a pas été trouvé pour le cas de test : "+nomTest);
	}
	
	public boolean recupereBaliseXMLBoolean(final String classTest, final String nomTest, final String attribut) throws IOException {
		final String stringBaliseXMLValeur = this.recupererBaliseXML(classTest, nomTest, attribut);
		
		final boolean booleanBaliseXMLValeur = Boolean.parseBoolean(stringBaliseXMLValeur);
		
		return booleanBaliseXMLValeur;
	}
}
