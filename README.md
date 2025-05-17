# Product API avec Spring Boot

## Description
**Product API** est une API RESTful développée avec **Spring Boot 3.4.5** pour une plateforme e-commerce. Elle gère l'authentification des utilisateurs, la gestion des produits, le panier d'achat et la liste de souhaits. L'authentification utilise **JWT**, et les actions administratives (création/modification de produits) sont réservées à l'utilisateur `admin@admin.com`.

## Base de Données
- **MySQL** (base `shopdb`) avec **Spring Data JPA**.
- Entités : `User` (infos utilisateur, mot de passe chiffré avec BCrypt), `Product` (produits), `Cart` (panier), `Wishlist` (liste de souhaits).
- Configuration dans `application.properties` :
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/shopdb?createDatabaseIfNotExist=true
  spring.datasource.username=root
  spring.datasource.password=votre-mot-de-passe

## Swagger
Swagger UI (springdoc-openapi-starter-webmvc-ui:2.6.0) pour la documentation et le test des API.
Accès : http://localhost:8080/swagger-ui.html
Définition OpenAPI : http://localhost:8080/v3/api-docs
Supporte l'authentification JWT via le bouton "Authorize".
## Postman
Test des endpoints avec Postman.
Workflow :
         POST /api/auth/account pour créer un utilisateur.
        POST /api/auth/token pour obtenir un JWT.
Utiliser Authorization: Bearer <token> pour les endpoints protégés.
Importez http://localhost:8080/v3/api-docs dans Postman pour générer une collection.
## Exécution
Assurez-vous que MySQL est en cours d'exécution.
![image](https://github.com/user-attachments/assets/0251bd87-0ccb-4b71-a4b7-5b3673a5258c)
