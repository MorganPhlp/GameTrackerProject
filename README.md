Configuration de l'environnement GameTracker
Étape 1 : Lancer la base de données avec Docker

Avant de démarrer l'application GameTracker, vous devez d'abord initialiser la base de données en utilisant Docker Compose.

# Naviguez vers le dossier racine du projet où se trouve le fichier compose.yaml
cd /chemin/vers/le/projet

# Lancez le conteneur Docker pour la base de données (ou simplement run le compose.yaml)
docker-compose up -d

Cette commande va créer et démarrer un conteneur Docker avec la base de données configurée selon les paramètres définis dans le fichier compose.yaml.

Note : Assurez-vous que Docker Desktop est installé et en cours d'exécution sur votre machine avant de lancer cette commande.
#

Étape 2 : Démarrer l'application Spring Boot

Une fois la base de données lancée et disponible, vous pouvez démarrer le backend de l'application GameTracker.

# Naviguez vers le dossier du projet Spring Boot
cd /chemin/vers/le/backend

# Démarrez l'application Spring Boot
./mvnw spring-boot:run

Ou si vous utilisez un IDE comme IntelliJ IDEA ou Eclipse, vous pouvez ouvrir le projet et exécuter la classe principale GameTrackerProjectApplication.
