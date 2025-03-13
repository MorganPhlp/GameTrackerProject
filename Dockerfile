# Utilise Amazon Corretto 21.0.6 (JDK 21)
FROM amazoncorretto:21.0.6

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le JAR généré
COPY target/*.jar app.jar

# Expose le port utilisé par l'application (correspond à server.port=8081 dans votre YAML)
EXPOSE 8081

# Commande de démarrage avec optimisations JVM
ENTRYPOINT ["java", "-jar", "app.jar"]