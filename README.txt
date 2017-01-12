CANDA Antoine
VERSCHAEVE Th�o
PJE B:  Analyse de comportements avec Twitter.

----------------------------------------------------------------------------------------------------- 

04/10/2016 : D�but projet. 
Connexion � Twitter et r�cup�ration de quelques tweets.

----------------------------------------------------------------------------------------------------- 

Design Pattern qu'il faudra utiliser : MVC et Observer. 

----------------------------------------------------------------------------------------------------- 

Structure g�n�rale de l'interface graphique. 

Fen�tre principale contenant plusieurs onglets : 

- Onglet recherche de tweet = contient un champ de recherche de mot cl�. 
Retourne le r�sultat de la recherche sous une forme de liste de tweet. On doit pouvoir choisir de sauvegarder dans un fichier csv le r�sultat et �galement de les nettoyer.

- Onglet apprentissage manuel = le champs permettant de noter des tweets en neutre, positif ou n�gatif afin de constituer une base d'apprentissage. 
Il faut donc pouvoir charger une liste de tweet, une base d'apprentissage, et pouvoir noter des tweets mais pas forc�ment tous. Il y a besoin de boutons positif, neutre et n�gatif.
Mais aussi un bouton permettant de choisir une ou la base d'apprentissage voulu pour un sujet donn�. (Possibilit� d'avoir plusieurs bases). 

- Onglet apprentissage automatique = Permet de choisir un algorithme de classification, �ventuellement modifier la quantit� de tweet, choisir une base d'apprentissage. 
Equivalent � la partie "reglage" de l'algorithme. 

- Onglet statistique ou r�sultat = Pr�sente le r�sultat de l'apprentissage automatique avec plusieurs parties. 
Une partie tendance g�n�rale avec les r�sultats globaux.
Une partie o� on a les r�sultats pour chaque tweets. 

- Onglet �valuation = affiche le r�sultat de l'algorithme ?

Dans view = avoir 1 classe "entr�e" qui sera la fen�tre principale et au moins 1 classe par onglet contenant l'affichage et le contenu de chaque onglet.

N�cessaire d'avoir le design pattern observer. 

http://www.javaquizplayer.com/blogposts/blogpost7.html
http://www.journaldev.com/1739/observer-design-pattern-in-java
http://www.programcreek.com/2009/01/the-steps-involved-in-building-a-swing-gui-application/
https://dzone.com/articles/design-patterns-uncovered
----------------------------------------------------------------------------------------------------- 

11/10/2016 : 

R�fl�chir � une abstraction du r�sultat twitter. 
On a actuellement avec l'API Twitter les classes suivantes: 

Query : requ�te
QueryResult : r�sultat de la requ�te 
Status : tweet et ses informations associ�es. 

Je pense qu'il faudrait cr�er notre propre classe pour manipuler des tweets par exemple Tweet.

Il va �tre n�cessaire de limiter le nombre de tweets qu'on obtient en r�sultat d'une requ�te et donc chercher si il est possible de le faire avec l'API.
Peut �tre �galement s'assurer que l'on peut comprendre les tweets = limiter � la langue fran�aise et anglaise ? 

Peut-�tre regarder du c�t� de l'API Twitter REST ?


-----------------------------------------------------------------------------------------------------

08/11/2016 :

Interface mise � jour en profondeur depuis le debut.

Recherche et affichage des tweets fonctionne.
Nettoyage des tweets fonctionne en partie (RT, # , URL, @... ). Il faut g�rer le cas des emotes et celui des caract�res non ASCII.

Apprentissage manuel fonctionnel : la liste des tweets nettoye est bien affiche sur le panel et on peut noter. On sauvegarde bien dans le fichier csv contenant la Base. 
Attention on �crase la base pr�c�dente : il faudrait plut�t pouvoir concatener pour faire grossir la base. 

Algorithme KNN et Dictionnaire implante mais non teste encore. 

A faire : Creer une table model avec la base d'apprentissage pour les algorithme de notation auto, une avec les tweets nettoy�s pour l'apprentissage manuel eventuellement ? 
Pouvoir choisir les tweets que l'on souhaite ajouter � la base ? 
Ajouter un booleen sous forme de case � cocher/decocher lors de l'apprentissage manuel?

Se decider sur la librairie pour les diagrammes. JFreeChar ?  charts4j ? 

-----------------------------------------------------------------------------------------------------

29/11/2016:

Il faut verifier la validite de l'evaluateur.
Peut �tre retravailler l'interface.

------------------------------------------------------------------------------------------------------

06/12/2016:

Correction erreurs.
L�ger retravail evaluateur = resolution bug + ajout creation aleatoire des ensembles.
Taux d'erreurs probablement du � la base.
+ gros refactoring des noms des classes 
+ debut rapport
+ leger travail interface, reste � finir celle de l'evaluateur avec un diagramme qui sera plus joli. 
+ changement affichage taux erreur evaluateur.

Attention : grosses variations dans le taux d'erreurs des algorithmes du � la cr�ation des ensembles al�atories :
Pour fr�quence simplifi� bigramme j'ai eu de 33% � 48% de succ�s...



10/12/216: 

Refais les m�thodes de nettoyage de tweet + petite analyse des smileys pour �viter les contradictions.


A faire : 


	Preparation donnees
	
[] refaire une base d'apprentissage sur un seul domaine 


	Autre
	
[] finir rapport



