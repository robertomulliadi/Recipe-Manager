DROP TABLE requires;
DROP TABLE Equipment;
DROP TABLE mayContain;
DROP TABLE includes;
DROP TABLE Review_has;
DROP TABLE Recipe_has_NutritionalInformation;
DROP TABLE Recipe;
DROP TABLE Recipe_isOf;
DROP TABLE MealType;
DROP TABLE Recipe_has_DifficultyLevel;
DROP TABLE DifficultyLevel;
DROP TABLE Non_Vegan;
DROP TABLE Vegan;
DROP TABLE IngredientQuantity;
DROP TABLE Ingredient_has_AllergenInformation;
DROP TABLE Ingredient;
DROP TABLE AllergenInformation;
DROP TABLE Review_leaves;
DROP TABLE recipe_CreatedBy;
DROP TABLE RecipeUsers;
DROP TABLE NutritionalInformation;

CREATE TABLE Equipment
(
    EquipmentID INTEGER PRIMARY KEY,
    Category    VARCHAR(20),
    Name        VARCHAR(255) NOT NULL
);

CREATE TABLE Recipe
(
    RecipeID     INTEGER PRIMARY KEY,
    Title        VARCHAR(255),
    Instructions VARCHAR(255) NOT NULL,
    CookingTime  INTEGER,
    ServingSize  INTEGER
);

CREATE TABLE MealType
(
    Type VARCHAR(255) PRIMARY KEY
);

CREATE TABLE DifficultyLevel
(
    LEVELS INTEGER PRIMARY KEY
);


CREATE TABLE Ingredient
(
    IngredientID    INTEGER PRIMARY KEY,
    Name            VARCHAR(255) NOT NULL,
    MeasurementUnit VARCHAR(255) NOT NULL
);

CREATE TABLE Non_Vegan
(
    IngredientID    INTEGER PRIMARY KEY,
    Name            VARCHAR(255) NOT NULL,
    MeasurementUnit VARCHAR(255) NOT NULL,
    FOREIGN KEY (IngredientID) REFERENCES Ingredient (IngredientID)
);

CREATE TABLE Vegan
(
    IngredientID    INTEGER PRIMARY KEY,
    Name            VARCHAR(255) NOT NULL,
    MeasurementUnit VARCHAR(255) NOT NULL,
    FOREIGN KEY (IngredientID) REFERENCES Ingredient (IngredientID)
);

CREATE TABLE IngredientQuantity
(
    Quantity     INTEGER,
    IngredientID INTEGER,
    PRIMARY KEY (Quantity, IngredientID)
);

CREATE TABLE AllergenInformation
(
    AllergenID   INTEGER PRIMARY KEY,
    AllergenType VARCHAR(255) NOT NULL
);

CREATE TABLE RecipeUsers
(
    UserID      INTEGER PRIMARY KEY,
    Username    VARCHAR(255) NOT NULL,
    Password    VARCHAR(255) NOT NULL,
    Preferences VARCHAR(255),
    Email       VARCHAR(255) NOT NULL,
    UNIQUE (Username),
    UNIQUE (Email)
);


CREATE TABLE Review_has
(
    ReviewID   INTEGER PRIMARY KEY,
    ReviewText VARCHAR(255),
    "DATE"       DATE,
    RecipeID   INTEGER,
    FOREIGN KEY (RecipeID) REFERENCES Recipe (RecipeID)
);

CREATE TABLE Review_leaves
(
    ReviewID   INTEGER PRIMARY KEY,
    ReviewText VARCHAR(255),
    "DATE"       DATE,
    UserID     INTEGER,
    FOREIGN KEY (UserID) REFERENCES RecipeUsers (UserID)
    on delete cascade
);

CREATE TABLE NutritionalInformation
(
    NutritionID      INTEGER PRIMARY KEY,
    Calories         INTEGER,
    Macronutrients   VARCHAR(255),
    VitaminsMinerals VARCHAR(255)
);


CREATE TABLE Recipe_has_DifficultyLevel
(
    RecipeID     INTEGER PRIMARY KEY,
    Title        VARCHAR(255),
    Instructions VARCHAR(255) NOT NULL,
    CookingTime  INTEGER,
    ServingSize  INTEGER,
    LEVELs        INTEGER,
    FOREIGN KEY (LEVELs) REFERENCES DifficultyLevel (LEVELS)
);

CREATE TABLE Recipe_isOf
(
    RecipeID     INTEGER PRIMARY KEY,
    Title        VARCHAR(255),
    Instructions VARCHAR(255) NOT NULL,
    CookingTime  INTEGER,
    ServingSize  INTEGER,
    Type         VARCHAR(255),
    FOREIGN KEY (Type) REFERENCES MealType (Type)
);

CREATE TABLE includes
(
    RecipeID     INTEGER,
    IngredientID INTEGER,
    PRIMARY KEY (RecipeID, IngredientID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe (RecipeID),
    FOREIGN KEY (IngredientID) REFERENCES Ingredient (IngredientID)
);

CREATE TABLE Ingredient_has_AllergenInformation
(
    IngredientID INTEGER,
    AllergenID   INTEGER,
    PRIMARY KEY (IngredientID, AllergenID),
    FOREIGN KEY (IngredientID) REFERENCES Ingredient (IngredientID),
    FOREIGN KEY (AllergenID) REFERENCES AllergenInformation (AllergenID)
);


CREATE TABLE mayContain
(
    RecipeID   INTEGER,
    AllergenID INTEGER,
    PRIMARY KEY (RecipeID, AllergenID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe (RecipeID),
    FOREIGN KEY (AllergenID) REFERENCES AllergenInformation (AllergenID)
);

CREATE TABLE recipe_CreatedBy
(
    RecipeID     INTEGER PRIMARY KEY,
    Title        VARCHAR(255),
    Instructions VARCHAR(255) NOT NULL,
    CookingTime  INTEGER,
    ServingSize  INTEGER,
    UserID       INTEGER,
    FOREIGN KEY (UserID) REFERENCES RecipeUsers (UserID)
    ON DELETE CASCADE
);

CREATE TABLE Recipe_has_NutritionalInformation
(
    RecipeID    INTEGER,
    NutritionID INTEGER,
    PRIMARY KEY (RecipeID, NutritionID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe (RecipeID),
    FOREIGN KEY (NutritionID) REFERENCES NutritionalInformation (NutritionID)
);

CREATE TABLE requires
(
    equipmentID INTEGER,
    RecipeID    INTEGER,
    PRIMARY KEY (equipmentID, RecipeID),
    FOREIGN KEY (equipmentID) REFERENCES Equipment (EquipmentID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe (RecipeID)
);


INSERT INTO Equipment VALUES (1, 'Cutting', 'Paring Knife');
INSERT INTO Equipment VALUES (2, 'Cooking', 'Microwave');
INSERT INTO Equipment VALUES (3, 'Cooking', 'Oven');
INSERT INTO Equipment VALUES (4, 'Pots and Pans', 'Frying Pan');
INSERT INTO Equipment VALUES (5, 'Measuring', 'Liquid Measure');


INSERT INTO Recipe VALUES (1, 'Best Buttermilk Biscuits', 'insert instruction', 50, 4);
INSERT INTO Recipe VALUES (2, 'Oatmeal', 'boil oats', 30, 1);
INSERT INTO Recipe VALUES (3, 'Good soup', 'boil things', 30, 5);
INSERT INTO Recipe VALUES (4, 'Smoothie', 'blend fruits', 10, 4);
INSERT INTO Recipe VALUES (5, 'Apple Juice', 'juice apples', 10, 2);
INSERT INTO Recipe VALUES (8, 'Good soup', 'boil things', 30, 5);
INSERT INTO Recipe VALUES (9, 'Smoothie', 'blend fruits', 10, 4);
INSERT INTO Recipe VALUES (7, 'Oatmeal', 'boil oats', 30, 1);
INSERT INTO Recipe VALUES (10, 'Apple Juice', 'juice apples', 10, 2);

INSERT INTO MealType VALUES ('Breakfast');
INSERT INTO MealType VALUES ('Lunch');
INSERT INTO MealType VALUES ('Dinner');
INSERT INTO MealType VALUES ('Dessert');
INSERT INTO MealType VALUES ('Beverage');


INSERT INTO Recipe_isOf VALUES (1, 'Best Buttermilk Biscuits', 'insert instruction', 50, 4, 'Breakfast');
INSERT INTO Recipe_isOf VALUES (2, 'Oatmeal', 'boil oats', 30, 1, 'Breakfast');
INSERT INTO Recipe_isOf VALUES (3, 'Good soup', 'boil things', 30, 5, 'Lunch');
INSERT INTO Recipe_isOf VALUES (4, 'Smoothie', 'blend fruits', 10, 4, 'Beverage');
INSERT INTO Recipe_isOf VALUES (5, 'Apple Juice', 'juice apples', 10, 2, 'Beverage');
INSERT INTO Recipe_isOf VALUES (8, 'Good soup', 'boil things', 30, 5, 'Lunch');
INSERT INTO Recipe_isOf VALUES (9, 'Smoothie', 'blend fruits', 10, 4, 'Beverage');

INSERT INTO DifficultyLevel VALUES (1);
INSERT INTO DifficultyLevel VALUES (2);
INSERT INTO DifficultyLevel VALUES (3);
INSERT INTO DifficultyLevel VALUES (4);
INSERT INTO DifficultyLevel VALUES (5);

INSERT INTO RecipeUsers VALUES (1, 'John Smith', 'supersecretpassword123', 'Italian cuisine', 'smith_john@gmail.com');
INSERT INTO RecipeUsers VALUES (2, 'Ben Simmons', 'iamtall4019', 'Healthy eating', 'bensimmons@gmail.com');
INSERT INTO RecipeUsers VALUES (3, 'Alexander Bell', 'imakeTelephonesringring3303', 'Desserts', 'bellringer_alex@yahoo.com');
INSERT INTO RecipeUsers VALUES (4, 'Lionel Messi', 'iamthegoat', 'Asian dishes', 'greatestofall_time@gmail.com');
INSERT INTO RecipeUsers VALUES (5, 'Billy Joel', 'pianoman!', 'Vegetarian', 'billythejoelreal@gmail.com');

INSERT INTO Ingredient VALUES (1, 'Eggs', 'unit');
INSERT INTO Ingredient VALUES (2, 'Rice', 'gram');
INSERT INTO Ingredient VALUES (3, 'Chicken Breast', 'gram');
INSERT INTO Ingredient VALUES (4, 'Potato', 'unit');
INSERT INTO Ingredient VALUES (5, 'Cow Milk', 'mL');
INSERT INTO Ingredient VALUES (6, 'Steak', 'gram');
INSERT INTO Ingredient VALUES (7, 'Salmon', 'gram');
INSERT INTO Ingredient VALUES (8, 'Apple', 'unit');
INSERT INTO Ingredient VALUES (9, 'Orange', 'unit');
INSERT INTO Ingredient VALUES (10, 'Flour', 'mL');
INSERT INTO Ingredient VALUES (11, 'Peanut', 'unit');
INSERT INTO Ingredient VALUES (12, 'Almond', 'unit');

INSERT INTO Non_Vegan VALUES (1, 'Eggs', 'unit');
INSERT INTO Non_Vegan VALUES (3, 'Chicken Breast', 'gram');
INSERT INTO Non_Vegan VALUES (5, 'Cow Milk', 'mL');
INSERT INTO Non_Vegan VALUES (6, 'Steak', 'gram');
INSERT INTO Non_Vegan VALUES (7, 'Salmon', 'gram');

INSERT INTO Vegan VALUES (2, 'Rice', 'gram');
INSERT INTO Vegan VALUES (4, 'Potato', 'unit');
INSERT INTO Vegan VALUES (8, 'apple', 'unit');
INSERT INTO Vegan VALUES (9, 'Orange', 'unit');
INSERT INTO Vegan VALUES (10, 'Flour', 'ml');
INSERT INTO Vegan VALUES (11, 'Peanut', 'unit');
INSERT INTO Vegan VALUES (12, 'Almond', 'unit');

INSERT INTO IngredientQuantity VALUES (2, 300);
INSERT INTO IngredientQuantity VALUES (2, 400);
INSERT INTO IngredientQuantity VALUES (1, 1);
INSERT INTO IngredientQuantity VALUES (1, 2);
INSERT INTO IngredientQuantity VALUES (1, 3);


INSERT INTO AllergenInformation VALUES (1, 'Gluten');
INSERT INTO AllergenInformation VALUES (2, 'Dairy');
INSERT INTO AllergenInformation VALUES (3, 'Nuts');
INSERT INTO AllergenInformation VALUES (4, 'Eggs');
INSERT INTO AllergenInformation VALUES (5, 'Soy');

INSERT INTO Recipe_has_DifficultyLevel VALUES (1, 'Best Buttermilk Biscuits', 'insert instruction', 50, 4, 4);
INSERT INTO Recipe_has_DifficultyLevel VALUES (2, 'Oatmeal', 'boil oats', 30, 1, 2);
INSERT INTO Recipe_has_DifficultyLevel VALUES (3, 'Good soup', 'boil things', 30, 5, 3);
INSERT INTO Recipe_has_DifficultyLevel VALUES (4, 'Smoothie', 'blend fruits', 10, 4, 2);
INSERT INTO Recipe_has_DifficultyLevel VALUES (5, 'Apple Juice', 'juice apples', 10, 2, 1);

INSERT INTO includes VALUES (1, 1);
INSERT INTO includes VALUES (1, 10);
INSERT INTO includes VALUES (4, 8);
INSERT INTO includes VALUES (4, 9);
INSERT INTO includes VALUES (5, 8);

INSERT INTO Ingredient_has_AllergenInformation VALUES (10, 1);
INSERT INTO Ingredient_has_AllergenInformation VALUES (1, 4);
INSERT INTO Ingredient_has_AllergenInformation VALUES (11, 3);
INSERT INTO Ingredient_has_AllergenInformation VALUES (5, 2);
INSERT INTO Ingredient_has_AllergenInformation VALUES (12, 3);


INSERT INTO mayContain VALUES (1,1);
INSERT INTO mayContain VALUES (1,2);
INSERT INTO mayContain VALUES (1,4);
INSERT INTO mayContain VALUES (1,3);
INSERT INTO mayContain VALUES (2,3);


INSERT INTO recipe_CreatedBy VALUES (1, 'Best Buttermilk Biscuits', 'insert instruction', 50, 4, 1);
INSERT INTO recipe_CreatedBy VALUES (2, 'Oatmeal', 'boil oats', 30, 1, 2);
INSERT INTO recipe_CreatedBy VALUES (3, 'Good soup', 'boil things', 30, 5, 3);
INSERT INTO recipe_CreatedBy VALUES (4, 'Smoothie', 'blend fruits', 10, 4, 4);
INSERT INTO recipe_CreatedBy VALUES (5, 'Apple Juice', 'juice apples', 10, 2, 5);
INSERT INTO recipe_CreatedBy VALUES (7, 'Oatmeal', 'boil oats', 30, 1, 1);
INSERT INTO recipe_CreatedBy VALUES (8, 'Good soup', 'boil things', 30, 5, 1);
INSERT INTO recipe_CreatedBy VALUES (9, 'Smoothie', 'blend fruits', 10, 4, 1);
INSERT INTO recipe_CreatedBy VALUES (10, 'Apple Juice', 'juice apples', 10, 2, 1);

INSERT INTO Review_leaves VALUES (1, 'Tastes great!', TO_DATE('2024-03-01', 'YYYY-MM-DD'), 2);
INSERT INTO Review_leaves VALUES (2, 'I like the process of doing it.', TO_DATE('2024-03-02', 'YYYY-MM-DD'), 3);
INSERT INTO Review_leaves VALUES (3, 'It would be better if it can taste sweeter', TO_DATE('2024-03-05', 'YYYY-MM-DD'), 1);
INSERT INTO Review_leaves VALUES (4, 'Took too long to make it.', TO_DATE('2024-04-02', 'YYYY-MM-DD'), 4);
INSERT INTO Review_leaves VALUES (5, 'Nice!',TO_DATE('2024-03-01','YYYY-MM-DD'), 5);

INSERT INTO Review_has VALUES (1, 'Tastes great!', TO_DATE('2024-03-01', 'YYYY-MM-DD'), 3);
INSERT INTO Review_has VALUES (2, 'I like the process of doing it.', TO_DATE('2024-03-02', 'YYYY-MM-DD'), 4);
INSERT INTO Review_has VALUES (3, 'It would be better if it can taste sweeter ', TO_DATE('2024-03-05', 'YYYY-MM-DD'), 1);
INSERT INTO Review_has VALUES (4, 'Took too long to make it.', TO_DATE('2024-04-02', 'YYYY-MM-DD'), 1);
INSERT INTO Review_has VALUES (5, 'Nice!',TO_DATE('2024-03-01','YYYY-MM-DD'), 5);

INSERT INTO NutritionalInformation VALUES (1, 212, ' 4.2 g Protein, 9.8 g fat, 27 g carbohydrate per 60 g ', ' Cholesterol 1.8 mg, Sodium 348 mg, Calcium 141 mg, Iron 1.7 mg, Potassium 72.6 mg per 60 g ');
INSERT INTO NutritionalInformation VALUES (2, 95, ' 5 g Protein, 3 g fat, 27 g Carbohydrates per 81 g ', ' 8.1 g fiber per 81 g ');
INSERT INTO NutritionalInformation VALUES (3, 80, ' 5.3 g Protein, 2.2 g Fat, 29.1 g Carbohydrates per 100 g ', ' Vitamin A 206.4 μg, Calcium 48 mg per 100 g ');
INSERT INTO NutritionalInformation VALUES (4, 513, ' 21 g Protein , 25 g Fat , 56 g Carbohydrates per 100 g ', ' vitamin C 12.3 mg per 100 g ');
INSERT INTO NutritionalInformation VALUES (5, 114, ' 0.2 g Protein, 0.3 g Fat, 28 g Carbohydrates per 200 mL ', ' vitamin C 13.3 mg , Potassium 142 mg, Iron 0.44 mg , Calcium 8 mg per 200 mL ');

INSERT INTO Recipe_has_NutritionalInformation VALUES (1, 1);
INSERT INTO Recipe_has_NutritionalInformation VALUES (2, 2);
INSERT INTO Recipe_has_NutritionalInformation VALUES (3, 3);
INSERT INTO Recipe_has_NutritionalInformation VALUES (4, 4);
INSERT INTO Recipe_has_NutritionalInformation VALUES (5, 5);

INSERT INTO requires VALUES (1, 3);
INSERT INTO requires VALUES (2, 2);
INSERT INTO requires VALUES (3, 1);
INSERT INTO requires VALUES (5, 4);
INSERT INTO requires VALUES (5, 5);
