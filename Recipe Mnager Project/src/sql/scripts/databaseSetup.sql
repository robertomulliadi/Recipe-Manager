CREATE TABLE Equipment (
    EquipmentID INTEGER PRIMARY KEY,
    Category VARCHAR(20),
    Name VARCHAR NOT NULL
);

CREATE TABLE Recipe (
    RecipeID INTEGER PRIMARY KEY,
    Title VARCHAR,
    Instructions VARCHAR NOT NULL,
    CookingTime INTEGER,
    ServingSize INTEGER
);

CREATE TABLE MealType (
    Type VARCHAR PRIMARY KEY
);

CREATE TABLE DifficultyLevel (
    Level INTEGER PRIMARY KEY;
);

CREATE TABLE Ingredient (
    IngredientID INTEGER PRIMARY KEY,
    Name VARCHAR NOT NULL,
    MeasurementUnit VARCHAR NOT NULL
);

CREATE TABLE Non_Vegan (
    IngredientID INTEGER PRIMARY KEY,
    Name VARCHAR NOT NULL,
    MeasurementUnit VARCHAR NOT NULL,
    FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID));

CREATE TABLE Vegan (
    IngredientID INTEGER PRIMARY KEY,
    Name VARCHAR NOT NULL,
    MeasurementUnit VARCHAR NOT NULL,
    FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID)
);

CREATE TABLE IngredientQuantity (
    Quantity INTEGER,
    IngredientID INTEGER,
    PRIMARY KEY (QuantityID, IngredientID)
);

CREATE TABLE AllergenInformation (
    AllergenID INTEGER PRIMARY KEY,
    AllergenType VARCHAR NOT NULL
);

CREATE TABLE User (
    UserID INTEGER PRIMARY KEY,
    Username VARCHAR NOT NULL,
    Password VARCHAR NOT NULL,
    Preferences VARCHAR,
    Email VARCHAR NOT NULL,
    UNIQUE (Username),
    UNIQUE (Email)
);

CREATE TABLE Review (
    ReviewID INTEGER PRIMARY KEY,
    ReviewText VARCHAR,
    Date DATE
);

CREATE TABLE NutritionalInformation (
    NutritionID INTEGER PRIMARY KEY,
    Calories INTEGER,
    Macronutrients VARCHAR,
    VitaminsMinerals VARCHAR
);

CREATE TABLE Recipe_has_DifficultyLevel (
    RecipeID INTEGER,
    Level INTEGER,
    PRIMARY KEY (RecipeID, Level),
    FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID),
    FOREIGN KEY (Level) REFERENCES DifficultyLevel(Level)
);

CREATE TABLE includes (
    RecipeID INTEGER,
    IngredientID INTEGER,
    PRIMARY KEY (RecipeID, IngredientID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID),
    FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID)
);

CREATE TABLE Ingredient_has_AllergenInformation (
    IngredientID INTEGER,
    AllergenID INTEGER,
    PRIMARY KEY (IngredientID, AllergenID),
    FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID),
    FOREIGN KEY (AllergenID) REFERENCES AllergenInformation(AllergenID)
);

CREATE TABLE mayContain (
    RecipeID INTEGER,
    AllergenID INTEGER,
    PRIMARY KEY (RecipeID, AllergenID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID),
    FOREIGN KEY (AllergenID) REFERENCES AllergenInformation(AllergenID)
);

CREATE TABLE createdBy (
    RecipeID INTEGER,
    UserID INTEGER,
    PRIMARY KEY (RecipeID, UserID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

CREATE TABLE leaves (
    UserID INTEGER,
    ReviewID INTEGER,
    PRIMARY KEY (UserID, ReviewID),
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (ReviewID) REFERENCES Review(ReviewID)
);

CREATE TABLE Review_has_Recipe (
    ReviewID INTEGER,
    RecipeID INTEGER,
    PRIMARY KEY (ReviewID, RecipeID),
    FOREIGN KEY (ReviewID) REFERENCES Review(ReviewID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID)
);

CREATE TABLE Recipe_has_NutritionalInformation (
    RecipeID INTEGER,
    NutritionID INTEGER,
    PRIMARY KEY (RecipeID, NutritionID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID),
    FOREIGN KEY (NutritionID) REFERENCES NutritionalInformation(NutritionID)
);

CREATE TABLE requires(
    equipmentID INTEGER,
    RecipeID INTEGER,
    PRIMARY KEY(equipmentID, RecipeID),
    FOREIGN KEY (equipmentID) REFERENCES Equipment(EquipmentID),
    FOREIGN KEY (RecipeID) REFERENCES Recipe(RecipeID)
);


INSERT INTO Equipment VALUES
(1, ‘Cutting’, ‘Paring Knife’),
(2, ‘Cooking’, ‘Microwave’),
(3, ‘Cooking’, “Oven’),
(4, ‘Pots and Pans’, ‘Frying Pan’),
(5, ‘Measuring’, ‘Liquid Measure’);

INSERT INTO Recipe(RecipeID, Title, Instructions, CookingTime, Serving Size) VALUES
(1, ‘Best Buttermilk Biscuits’, ‘insert instruction’, 50, 4),
(2, ‘Oatmeal’, ‘boil oats’, 30, 1),
(3, ‘Good soup’, ‘boil things’, 30, 5),
(4, ‘Smoothie’, ‘blend fruits’, 10, 4),
(5, ‘Apple Juice’, ‘juice apples’, 10, 2);

INSERT INTO MealType (Type) VALUES
(‘Breakfast’),
(‘Lunch’),
(‘Dinner’),
(‘Dessert’),
(‘Beverage’);

INSERT INTO DifficultyLevel (Level) VALUES
(1),
(2),
(3),
(4),
(5);

INSERT INTO User (UserID, Username, Password, Preferences, Email) VALUES
(1, ‘John Smith’, ‘supersecretpassword123’, ‘Italian cuisine’, ‘smith_john@gmail.com’),
(2, ‘Ben Simmons’, ‘iamtall4019’, ‘Healthy eating’, ‘bensimmons@gmail.com’),
(3, ‘Alexander Bell’, ‘imakeTelephonesringring3303’, ‘Desserts’, bellringer_alex@yahoo.com),
(4, ‘Lionel Messi’, ‘iamthegoat’, ‘Asian dishes’, greatestofall_time@gmail.com),
(5, ‘Billy Joel’, ‘\\pianoman\\!’, ‘Vegetarian’, ‘billythejoelreal@gmail.com’);

INSERT INTO Ingredient (IngredientID, Name, MeasurementUnit) VALUES
(1, ‘Eggs’, ‘unit’),
(2, ‘Rice’, ‘gram’),
(3, ‘Chicken Breast’, ‘gram’),
(4, ‘Potato’, ‘unit’),
(5, ‘Cow Milk’, ‘mL’),
(6, ‘Steak’, ‘gram’),
(7, ‘Salmon’, ‘gram’),
(8, ‘Apple’, ‘unit’),
(9, ‘Orange’ ‘unit’),
(10, ‘Flour’, ‘mL’),
(11, ‘Peanut’, ‘unit’),
(12, ‘Almond’, ‘unit’);

INSERT INTO Non_Vegan (IngredientID, Name, MeasurementUnit) VALUES
(1, ‘Eggs’, ‘unit’),
(3, ‘Chicken Breast’, ‘gram’),
(5, ‘Cow Milk’, ‘mL’),
(6, ‘Steak’, ‘gram’),
(7, ‘Salmon’, ‘gram’);

INSERT INTO Vegan (IngredientID, Name, MeasurementUnit) VALUES
(2, ‘Rice’, ‘gram’),
(4, ‘Potato’, ‘unit’),
(8, ‘apple’, ‘unit’),
(9, ‘Orange’ ‘unit’),
(10, ‘Flour’, ‘ml’),
(11, ‘Peanut’, ‘unit’),
(12, ‘Almond’, ‘unit’);

INSERT INTO IngredientQuantity (IngredientID, Quantity) VALUES
(2, 300),
(2, 400),
(1, 1),
(1, 2),
(1, 3);

INSERT INTO AllergenInformation (AllergenID, AllergenType) VALUES
(1, ‘Gluten’),
(2, ‘Dairy’),
(3, ‘Nuts’),
(4, ‘Eggs’),
(5, ‘Soy’);

INSERT INTO Recipe_has_DifficultyLevel (RecipeID, Level) VALUES
(1, 4),
(2, 2),
(3, 3),
(4, 2),
(5, 1);

INSERT INTO includes (RecipeID, IngredientID) VALUES
(1, 1),
(1, 10),
(4, 8),
(4, 9),
(5, 8);

INSERT INTO Ingredient_has_AllergenInformation (IngredientID, AllergenID) VALUES
(10, 1),
(1, 4),
(11, 3),
(5, 2),
(12, 3);

INSERT INTO mayContain (RecipeID, AllergenID) VALUES
(1,1),
(1,2),
(1,4),
(1,3),
(2,3);

INSERT INTO createdBy (RecipeID, UserID) VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5);






