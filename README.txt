CANDA Antoine
VERSCHAEVE Théo
PJE B:  Analyse de comportements avec Twitter.

----------------------------------------------------------------------------------------------------- 

04/10/2016 : Début projet. 
Connexion à Twitter et récupération de quelques tweets.

----------------------------------------------------------------------------------------------------- 

Design Pattern qu'il faudra utiliser : MVC et Observer. 

----------------------------------------------------------------------------------------------------- 

Structure générale de l'interface graphique. 

Fenêtre principale contenant plusieurs onglets : 

- Onglet recherche de tweet = contient un champ de recherche de mot clé. 
Retourne le résultat de la recherche sous une forme de liste de tweet. On doit pouvoir choisir de sauvegarder dans un fichier csv le résultat et également de les nettoyer.

- Onglet apprentissage manuel = le champs permettant de noter des tweets en neutre, positif ou négatif afin de constituer une base d'apprentissage. 
Il faut donc pouvoir charger une liste de tweet, une base d'apprentissage, et pouvoir noter des tweets mais pas forcément tous. Il y a besoin de boutons positif, neutre et négatif.
Mais aussi un bouton permettant de choisir une ou la base d'apprentissage voulu pour un sujet donné. (Possibilité d'avoir plusieurs bases). 

- Onglet apprentissage automatique = Permet de choisir un algorithme de classification, éventuellement modifier la quantité de tweet, choisir une base d'apprentissage. 
Equivalent à la partie "reglage" de l'algorithme. 

- Onglet statistique ou résultat = Présente le résultat de l'apprentissage automatique avec plusieurs parties. 
Une partie tendance générale avec les résultats globaux.
Une partie où on a les résultats pour chaque tweets. 

- Onglet évaluation = affiche le résultat de l'algorithme ?

Dans view = avoir 1 classe "entrée" qui sera la fenêtre principale et au moins 1 classe par onglet contenant l'affichage et le contenu de chaque onglet.

Nécessaire d'avoir le design pattern observer. 

http://www.javaquizplayer.com/blogposts/blogpost7.html
http://www.journaldev.com/1739/observer-design-pattern-in-java
http://www.programcreek.com/2009/01/the-steps-involved-in-building-a-swing-gui-application/
https://dzone.com/articles/design-patterns-uncovered
----------------------------------------------------------------------------------------------------- 

11/10/2016 : 

Réfléchir à une abstraction du résultat twitter. 
On a actuellement avec l'API Twitter les classes suivantes: 

Query : requête
QueryResult : résultat de la requête 
Status : tweet et ses informations associées. 

Je pense qu'il faudrait créer notre propre classe pour manipuler des tweets par exemple Tweet.

Il va être nécessaire de limiter le nombre de tweets qu'on obtient en résultat d'une requête et donc chercher si il est possible de le faire avec l'API.
Peut être également s'assurer que l'on peut comprendre les tweets = limiter à la langue française et anglaise ? 

Peut-être regarder du côté de l'API Twitter REST ?


-----------------------------------------------------------------------------------------------------

08/11/2016 :

Interface mise à jour en profondeur depuis le debut.

Recherche et affichage des tweets fonctionne.
Nettoyage des tweets fonctionne en partie (RT, # , URL, @... ). Il faut gérer le cas des emotes et celui des caractères non ASCII.

Apprentissage manuel fonctionnel : la liste des tweets nettoye est bien affiche sur le panel et on peut noter. On sauvegarde bien dans le fichier csv contenant la Base. 
Attention on écrase la base précédente : il faudrait plutôt pouvoir concatener pour faire grossir la base. 

Algorithme KNN et Dictionnaire implante mais non teste encore. 

A faire : Creer une table model avec la base d'apprentissage pour les algorithme de notation auto, une avec les tweets nettoyés pour l'apprentissage manuel eventuellement ? 
Pouvoir choisir les tweets que l'on souhaite ajouter à la base ? 
Ajouter un booleen sous forme de case à cocher/decocher lors de l'apprentissage manuel?

Se decider sur la librairie pour les diagrammes. JFreeChar ?  charts4j ? 

-----------------------------------------------------------------------------------------------------

29/11/2016:

Il faut verifier la validite de l'evaluateur.
Peut être retravailler l'interface.

------------------------------------------------------------------------------------------------------

06/12/2016:

Correction erreurs.
Léger retravail evaluateur = resolution bug + ajout creation aleatoire des ensembles.
Taux d'erreurs probablement du à la base.
+ gros refactoring des noms des classes 
+ debut rapport
+ leger travail interface, reste à finir celle de l'evaluateur avec un diagramme qui sera plus joli. 
+ changement affichage taux erreur evaluateur.

Attention : grosses variations dans le taux d'erreurs des algorithmes du à la création des ensembles aléatories :
Pour fréquence simplifié bigramme j'ai eu de 33% à 48% de succès...



10/12/216: 

Refais les méthodes de nettoyage de tweet + petite analyse des smileys pour éviter les contradictions.


A faire : 


	Preparation donnees
	
[] refaire une base d'apprentissage sur un seul domaine 


	Autre
	
[] finir rapport



