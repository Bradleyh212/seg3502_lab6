# SEG3502 — Laboratoire 6 — API REST Calculator

Service Web REST de calculatrice développé avec Kotlin et Spring Boot pour l'exercice du laboratoire 6. Le projet est adapté de l'exemple [converter-api](https://github.com/stephanesome/converter-api).

## Prérequis

- JDK 21.
- Une connexion Internet lors du premier lancement pour télécharger les dépendances.

Le wrapper Gradle est inclus : aucune installation séparée de Gradle n'est nécessaire.

## Démarrage

Dans un terminal ouvert à la racine du projet :

```bash
./gradlew bootRun
```

Le service écoute sur `http://localhost:8080`. Ouvrez [Swagger UI](http://localhost:8080/swagger-ui/index.html) pour consulter et essayer les opérations avec **Try it out**. Aucune page d'accueil n'est définie à la racine. Arrêtez le serveur avec `Ctrl+C`.

## Opérations REST

Toutes les opérations utilisent HTTP GET et retournent un nombre dans le corps de la réponse.

| Point de terminaison | Opération | Exemple | Résultat |
| --- | --- | --- | --- |
| `/calculator/add/{nombre1}/{nombre2}` | Addition | `/calculator/add/2/3` | `5.0` |
| `/calculator/subtract/{nombre1}/{nombre2}` | Soustraction | `/calculator/subtract/2/3` | `-1.0` |
| `/calculator/multiply/{nombre1}/{nombre2}` | Multiplication | `/calculator/multiply/-2/3.5` | `-7.0` |
| `/calculator/divide/{nombre1}/{nombre2}` | Division | `/calculator/divide/7/2` | `3.5` |

La soustraction et la division appliquent l'opération à `nombre1`, puis `nombre2`. Les nombres négatifs et décimaux sont acceptés; utilisez un point comme séparateur décimal.

```bash
curl http://localhost:8080/calculator/add/2/3
curl http://localhost:8080/calculator/divide/7/2
```

Une division par zéro retourne HTTP `400 Bad Request` avec le message `Cannot divide by zero`. Un paramètre non numérique retourne également HTTP `400`, avec le nom du paramètre invalide.

## Documentation OpenAPI

Les réponses `200` (résultat numérique) et `400` (paramètre invalide ou division par zéro) sont décrites dans OpenAPI.

La documentation est générée automatiquement par `springdoc-openapi-starter-webmvc-ui` à partir du contrôleur et de ses annotations.

- Swagger UI : <http://localhost:8080/swagger-ui/index.html>
- Spécification JSON : <http://localhost:8080/v3/api-docs>
- Spécification YAML : <http://localhost:8080/v3/api-docs.yaml>

## Vérification et construction

Exécuter les tests :

```bash
./gradlew test
```

Les tests vérifient les quatre opérations, les valeurs négatives et décimales, la division par zéro, les paramètres invalides, la présence des opérations dans OpenAPI et l'accès à Swagger UI.

Construire et lancer le fichier JAR exécutable :

```bash
./gradlew bootJar
java -jar build/libs/temp-converter-api-0.0.1-SNAPSHOT.jar
```

Le nom Gradle et le package Kotlin du projet de départ sont conservés.

## Structure du projet

- `src/main/kotlin/seg3x02/tempconverterapi/TempConverterApiApplication.kt` : point d'entrée Spring Boot.
- `src/main/kotlin/seg3x02/tempconverterapi/controller/CalculatorController.kt` : quatre opérations de calcul et annotations OpenAPI.
- `src/main/kotlin/seg3x02/tempconverterapi/controller/ControllerExceptionHandler.kt` : gestion des paramètres invalides et de la division par zéro.
- `src/main/resources/application.properties` : configuration de l'application.
- `src/test/` : tests du projet.
- `build.gradle.kts` : dépendances et configuration de construction.

## Remise

Seul l'exercice de la section 6 de l'énoncé est évalué. Enregistrez le code dans le dépôt GitHub Classroom avant la date limite indiquée pour le cours.
