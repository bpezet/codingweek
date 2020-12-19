# TelecomNancy Hiking
![alt text](https://gitlab.telecomnancy.univ-lorraine.fr/codingweek2k20/project-grp12/-/raw/developpement/BDD/Logo.png)

## Introduction
L’objectif de ce projet est de réaliser une application permettant de gérer des parcours de promenades ou de randonnées.

## L'application
### Principales fonctionnalités
- Création/mise à jour d’un parcours en fournissant diverses informations telles qu’un titre, une description courte, une description détaillée, une photo, difficulté.
- Recherche parmi les différnets parcours disponibles. La recherche peut se faire selon différents critères - titre, difficulté, distance, durée -.
- Visualisation d’une fiche parcours. Les différents informations de la fiche sont présentées à l’utilisateur.
- Création d’un parcours géo-localisé : l'utilisateur peut choisir les différentes étapes de son parcours à créer.
- Importation/exportation des parcours dans un format de fichier adapté.

## Utilisation de l'application
### Exécution
L'application repose sur une archive Java (.jar), mais on peut aussi l'ouvrir en mode développeur via intelliJ par exemple.

Afin que l'application s'exécute, il vous faudra commencer par récuperer JavaFx:
* JavaFx - Vous trouverez un liens pour le télécharger ici, il nous faudra le path du dossier.
https://gluonhq.com/products/javafx/

Pour l'executer à partir du dépôt, on peut utiliser intelliji avec les bonnes librairies importées: mapJfx, javaFx et Junit

Pour executer le .jar en ligne de commande on execute une de ces commandes :
--- Sur Windows
```sh
./java.exe --module-path ["Indiquez le path de votre librairie Javafx"]\javafx-sdk-11.0.2\lib --add-modules javafx.controls,javafx.fxml,javafx.web -Dfile.encoding=windows-1252 -jar ["Insérez le path du projet.jar"]
```
--- Sur Mac/linux
```sh
java --module-path ["Indiquez le path de votre librairie Javafx"]/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml,javafx.web -Dfile.encoding=UTF-8 -jar ["Insérer le path du projet.jar"]
```
(Attention ! ceci n'est valable seulement si on execute le .jar depuis le dépôt, en effet, le programme recherche dans les différents dossiers des informations pour se mettre à jour.
Si on veut executer le .jar ailleurs, il faudra re-créer les repertoires dont dépend l'application: un repertoire BDD avec un fichier app localSave pour l'initilisation, et un repertoire src/TN_Hiking/Ressources/import pour les image)


### Précisions
La base de données de l'application contient quelques parcours pour permettre à l'utilisateur de se familiariser avec l'environnement de l'application afin de créer ses propres parcours.

#### Home
L'accueil de l'application permet de visualiser les 4 derniers parcours crées par l'utilisateur (2 par page ; navigation avec les boutons en bas).
Pour visualiser un de ces 4 derniers parcours, l'utilsateur peut cliquer sur la photo correspondant au parcours : une fenêtre présentant le parcours s'ouvre alors.

##### Visualisation d'un parcours
Sur la gauche, une carte géographique centrée sur le parcous est affichée. Sur la droite on peut retrouver les informations sommaires du parcours ansi que son image. Des informations plus complètes concernant le parcours sont visualisables en déroulant le volet *plus d'informations*. Le tracé du parcours s'affiche alors sur la carte en même temps que les informations. 
Le volet étant déroulé, il est possible d'afficher toutes les étapes du parcours en cliquant sur le bouton *Voir toutes les étapes*.

###### Visualisation des étapes
Les différentes étapes du parcours sont listées à gauche : 
--- en haut, l'étape de départ,
--- les étapes composants le parcours,
--- en bas, l'étape d'arrivée (pouvant être la même que celle de départ si le parcours est une boucle).

#### Home : barre de menus
Au niveau de la fenêtre d'accueil on peut observer une barre composé de différents menus.
Il est possible de fermer l'application en cliquant sur *Home* puis *Close*

##### Outils
Dans ce menu, il est possible de *Créer un parcours* en appuyant sur le menu associé. L'application est redirigée vers la vue correspondante. L'abandon de la création et la redirection vers le Home sont faite avec le bouton *Annuler*.
Tous les champs doivent être remplis avant de *Tracer le parcours*. Le bouton *Créer une étape* permet de créer une étape (premier clic correspond à l'étape de départ, deuxième clic correspond à l'étape suiante, [...], dernier clic correspond à l'étape d'arrivée).
Une fois que toutes les étapes sont créées, le bouton *Finir le parcours* redirige vers une vue demandant des informations supplémentaires sur le parcours.
**ATTENTION :** L'importation d'une image du parcours est obligatoire si l'utilisateur compte exporter ses parcours par la suite.

##### Randonnées
Le menu *Trouver un parcours* permet à l'utilisateur de trouver des parcours en fonction de certains critères. L'utilisateur peut rechercher un parcours à son titre (exact). Autrement il peut ou non ajouter *+ de critère* :
Si un des critères ne l'intéresse pas il faut qu'il coche la case *Indifférent*. Pour les critères voulus, les jauges permettent de fixer le **maximum** (la distance maximum, la durée maximale ou encore la difficulté maximale). Il ne faut pas oublier de *Valider* puis de __cliquer sur le bouton *Recherche*__.
Les parcours correspondant à tous les critères sont affichés : un pourcours peut être vu ou modifier. Pour cela, il faut selectionner le parcours souhaité puis choisir l'action (*Visualiser le parcours* ou *Modifier le parcours*).
###### Modifier le parcours
Cette fonctionnalité permet de modifier une ou plusieurs informations d'un parcours. Il suffit de remplir les champs concernés par une modification avec la nouvelle information puis d'*Enregistrer*.
Il est possible dans la modification d'un parcours de modifier aussi les étapes qui le composent en cliquant sur le bouton *Modifier les étapes*.
###### Modifier les étapes
La vue affichée nous montre sur la gauche les étapes composant le parcours et sur la droite une map permettant de les situer (marqueur bleu). Il est possible de :
--- supprimer une étape sélectionnée avec le bouton *Supprimer étape*,
--- tracer le parcours composé des étapes de la liste (à gauche),
--- ajouter une nouvelle étape, pour cela il faut :
* choisir sa position sur la carte en cliquant dessus (marqueur vert),
* renseigner dans le champs *Numéro de la nouvelle étape*, la place de l'étape dans le parcours (``0`` pour que l'étape soit la première du parcours),
* renseigner le champs *Nom de la nouvelle étape*
* cliquer sur le bouton *Ajouter Etape* : on voit alors la nouvelle étape dans la liste.

##### Gestion
Ce dernier menu permet à un utilisateur d'*Afficher tous les parcours*. De la même façon que la recherche d'un parcours, l'utilisateur choisi le parcours dans la liste puis clique sur le bouton (__>__) pour le visualiser.

##### File : Utilisation des Fonctions Import/Exporti

__A lire :__ Nous voulions reproduire le comportement d'une application connectée à une base de donnée, nous avons décidé de  "simuler" l'existence d'une base de donnée à l'aide d'un répertoire nommé "BDD". On part du postulat que l'utilisateur est connecté à cette base de donnée et qu'il peut push ou pull des données dessus. Par exemple, à l'ouverture de l'application, il charge automatiquement les données de cette BD.

![alt text](https://gitlab.telecomnancy.univ-lorraine.fr/codingweek2k20/project-grp12/-/raw/master/src/TN_Hiking/Ressources/file.PNG)

On a dans la toolBar menu la possiblité d'avoir accès à un onglet "File"
On a accès à différents boutons:
--- Refresh: Permet de pull les données depuis la BDD.
--- SaveBDD: Permet de push sur la base de donnée.
--- SaveAs...: Permet de sauvegarder tout nos parcours dans un répertoire qu'on va pouvoir s'échanger entre utilisateur en faisant un .zip par exemple.
--- OpenFrom... : Permet d'importer une base de donnée depuis un répertoire
--- OpenCSVFrom...: Permet d'ouvrir un fichier .csv en local et l'ajouter automatiquement à nos parcours. Le fichier doit contenir 2 colonnes lattitude et longitude en °.

###### Précisions: 
Le bouton "SaveAs..." va créer un fichier "localSave" contenant toutes les informations de nos parcours. De plus on va "telecharger" toutes les images de notre base de donnée dans ce répertoire. 
Ce répertoire va pouvoir être échangé avec d'autre utilisateur et ils pourront avoir accès à tous nos parcours grâce au boutton "OpenFrom...". En effet, le boutton "OpenFrom..." va décrire le fichier localSave et récuperer toutes les images pour recréer les parcours.

###### Exemple d'utilisation:
- En faisant un "Refresh" je peux récuper des parcours directement depuis ma base de donnée. Je peux les modifiers, en ajouter puis faire un "SaveBDD" pour enregistrer mes modifications sur ma base de donnée. Je peux aussi faire un "SaveAs..." pour enregistrer mes parcours dans un répertoire (vide absolument). Je pourrai partager mes parcours à un amis en lui envoyant ce répertoire. 
- Mon ami m'envoie un repertoire généré depuis son application. Je fais un "OpenFrom..." et selectionne ce repertoire via la pop up gestionnaire de dossier. J'ai récupéré tous les parcours de ce repertoire, je peux faire des modifications ou rajouter des parcours. Lorsque j'ai finis, je peux cliquer sur "SaveBDD" pour mettre à jour la base de donnée et fermer mon application.

###### Exécution des Tests

Les tests sont dans la branche test du git. Il est important d'avoir installé les librairies JUnit, MapFX et JavaFX pour la bonne exécution des tests. Les tests se trouve dans le fichier "FICHIER_TEST" dans le package BDD. L'exécution des tests se fait en exécutant test par test.

## Vidéo de démonstration
Une vidéo montrant l'utilisation de l'application est disponible à https://www.youtube.com/channel/UChM9XeipvUvL-5j5J9CSi3w?view_as=subscriber.
La vidéo s'appelle CodingWeek2k20 présentation.
