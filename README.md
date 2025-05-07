# 🍲 Database-Driven Recipe Management Application

## Overview  
This is a full-stack Java desktop application for managing recipes, designed with a modular architecture and connected to a secure Oracle SQL backend via JDBC. Users can log in, browse and filter recipes, upload new entries, and perform advanced database queries — all through an interactive GUI built with Java Swing.

The application leverages a normalized relational schema with over 15 entities, supports advanced SQL queries (e.g., projection, division), and follows the MVC design pattern for scalability and maintainability.

---

## ✨ Features

- 🔐 **User Login** with authentication via Oracle DB credentials  
- 📋 **Insert, delete, and update users and recipes**  
- 🔍 **Search, filter, and display recipes** based on ingredients and dietary preferences  
- 📊 **View analytical summaries** like users with the most recipes or above-average contributions  
- 🧠 **Advanced SQL queries**, including:
  - Projection
  - Division
  - Aggregation
  - Multi-table joins
  - Nested subqueries  
- 🔄 **Secure and optimized DB access** via SSL tunneling and JDBC connection pooling  

---

## 🧱 Architecture

- **Model**: Contains entity classes like `Recipe`, `User`, `Ingredient`, `Review`, `NutritionalInformation`, and dietary tags (e.g., `Vegan`, `NonVegan`)  
- **Controller**: `RecipeManager.java` handles application flow, login coordination, and delegates DB logic  
- **Database Access Layer**: Manages Oracle SQL queries, inserts, updates, and user-defined analytics  
- **UI Layer**: Built using Java Swing (`LoginWindow`, `MenuPage`, `InsertRecipe`, `FilterRecipes`, `DisplayResults`, etc.)

---

## 🛠️ Technologies Used

- **Java** (Object-Oriented Design)  
- **Java Swing** (Desktop GUI)  
- **Oracle SQL**  
- **JDBC**  
- **IntelliJ IDEA**  
- **ojdbc8.jar** (Oracle JDBC driver)

---

## 🗃️ Database Design

The application uses a normalized relational schema with 15+ entities, including:

- `Recipe`, `User`, `Review`, `Ingredient`, `Equipment`  
- Dietary tags: `Vegan`, `NonVegan`, `MealType`  
- Supporting tables: `NutritionalInformation`, `IngredientQuantity`, `BranchModel`  
- Relationship mappings handled via `RecipeUser`, `RecipeCreatedby`, etc.

📄 See [`sql.scripts/databaseSetup.sql`](sql.scripts/databaseSetup.sql) for schema setup.

---

## 📁 Project Structure

