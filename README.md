# SEG3502 — Laboratoire 6 — API REST Calculator

Ce projet contient le code de départ Spring Boot du laboratoire 6. Il est basé sur l'exemple de convertisseur de température fourni dans le dépôt [converter-api](https://github.com/stephanesome/converter-api).

L'objectif du livrable est d'adapter cet exemple pour développer un service Web REST de calculatrice avec documentation OpenAPI.

## Prérequis

- JDK 21, conformément à la configuration du projet.
- Une connexion Internet lors du premier lancement pour télécharger les dépendances.

Le wrapper Gradle est inclus : aucune installation séparée de Gradle n'est nécessaire.

## Démarrage

Dans un terminal ouvert à la racine du projet :

```bash
./gradlew bootRun
```

L'API est accessible à l'adresse suivante :

```text
http://localhost:8080
```

Il s'agit d'un service REST : aucune page d'accueil n'est définie à la racine. Utilisez les points de terminaison ci-dessous ou Swagger UI. Arrêtez le serveur avec `Ctrl+C`.

## État actuel : convertisseur de température

Le code de départ fournit actuellement ces opérations :

| Méthode | Point de terminaison | Exemple de résultat |
| --- | --- | --- |
| GET | `/temperature-converter/celsius-fahrenheit/100` | `212.0` |
| GET | `/temperature-converter/fahrenheit-celsius/32` | `0.0` |

Exemple de requête après le démarrage du serveur :

```bash
curl http://localhost:8080/temperature-converter/celsius-fahrenheit/100
```

Les valeurs sont retournées directement dans le corps de la réponse. Un gestionnaire d'exceptions retourne une réponse HTTP `400 Bad Request` avec le message `Unable to process request` lorsqu'une requête provoque une exception.

## Travail à réaliser : calculatrice REST

L'exercice évalué demande les quatre opérations suivantes :

| Méthode | Point de terminaison | Opération |
| --- | --- | --- |
| GET | `/calculator/add/{nombre1}/{nombre2}` | Addition |
| GET | `/calculator/subtract/{nombre1}/{nombre2}` | Soustraction |
| GET | `/calculator/multiply/{nombre1}/{nombre2}` | Multiplication |
| GET | `/calculator/divide/{nombre1}/{nombre2}` | Division |

Ces points de terminaison ne sont pas encore implémentés dans le code de départ. La documentation OpenAPI doit également décrire les opérations de la calculatrice.

Seul l'exercice de la section 6 de l'énoncé est évalué. Le code doit être enregistré dans le dépôt GitHub Classroom avant la date limite indiquée pour le cours.

## Documentation OpenAPI

La dépendance `springdoc-openapi-starter-webmvc-ui` est déjà présente. Une fois le serveur démarré, consultez :

- Swagger UI : <http://localhost:8080/swagger-ui.html>
- Spécification JSON : <http://localhost:8080/v3/api-docs>
- Spécification YAML : <http://localhost:8080/v3/api-docs.yaml>

La documentation est générée à partir des contrôleurs présents dans le projet.

## Vérification et construction

Exécuter les tests :

```bash
./gradlew test
```

Construire le fichier JAR exécutable :

```bash
./gradlew bootJar
```

Le fichier est généré dans `build/libs/`. Avec le nom et la version actuels du projet, il peut être lancé ainsi :

```bash
java -jar build/libs/temp-converter-api-0.0.1-SNAPSHOT.jar
```

## Structure du projet

- `src/main/kotlin/seg3x02/tempconverterapi/TempConverterApiApplication.kt` : point d'entrée Spring Boot.
- `src/main/kotlin/seg3x02/tempconverterapi/controller/ConverterController.kt` : opérations REST du convertisseur de départ.
- `src/main/kotlin/seg3x02/tempconverterapi/controller/ControllerExceptionHandler.kt` : gestion des exceptions.
- `src/main/resources/application.properties` : configuration de l'application.
- `src/test/` : tests du projet.
- `build.gradle.kts` : dépendances et configuration de construction.
