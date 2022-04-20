# language: fr
Fonctionnalité: Faire une recherche Sur Internet 1
  
  Dans le but de faire un exemple de cas de test automatique sur ce starter kit automate
  Je souhaite pouvoir faire un recherche Google
    
	@RechercheGoogle
  Scénario: Recherche d un site sur l automatisation cucumber avec Google
  	Etant donné un utilisateur sur le moteur de recherche "Google"
    Lorsqu'il saisit dans la zone recherche de Google "Documentation Serenity"
    Et il clic sur le bouton recherche de Google
    Alors Google fait une recherche