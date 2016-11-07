###**Задание к уроку N24 (Spring JDBC)**

Разработать консольное приложение для хранения рецептов.  
Функциональность:  
• Поиск рецепта по имени или части имени блюда   
• Добавление рецепта - рецепт состоит из множества ингредиентов и их  
количественного состава  
• Удаление блюда     

***Реализация***

Модуль home24 входит в состав родительского проекта  

В src/main/java/ru/sbt/home содержиться:  
configuration/ApplicationConfiguration - конфигурационный файл  
creationdb/DatabaseUpdater - класс для создания баз данных  
dao/ingredientdao/IngredientDao - интрефейс Dao для Ingredient  
dao/ingredientdao/IngredientDaoImpl - реализация IngredientDao  
dao/recipedao/RecipeDao - интрефейс Dao для Recipe  
dao/recipedao/RecipeDaoImpl - реализация RecipeDao  
dto/Ingredient - класс инредиета  
dto/Recipe - класс рецепта  
service/IngredientService - класс сервисов для Ingredient, где реализована  
вся бизнесс логика и транзакциональность  
service/RecipeService - класс сервисов для Recipe, где реализована  
вся бизнесс логика и транзакциональность  
view/MainController - главное окно (Поиск, Создание и Выход)  
view/base/BaseController - класс наследник Application с общими методами  
создания нового окна и вывода сообщения  
view/base/SpringFxmlLoader - класс для загрузки Fxml окон работающих на Spring  
view/create/CreateController - окно для создания рецепта  
view/search/SearchController - окно для поиска рецепта  
view/search/SearchControllerRedactor - окно для редактирования созданного рецепта  
view/MainController - гланвное окно  
В src/main/java/sql содержиться:  
cookbook.sql - базы данных для реализации книги рецептов  
В проекте использовалось три таблицы Recipes, Ingredients и для их связи  
Cookbook
В src/main/java/resources содержиться:   
Файлы *.fxml соответсвующих окон и Log4j.properties  
      
***Итог***

Реализовал простую "книгу рецептов" JavaFX, Spring JDBC и H2. Создать всю  
необходимую структуру spring приложения:  
dao - слой для доступа к базу данных  
sevice - слой для реализации бизнесс логики по операциям с dao  
view - графический интерфейс  

***Предустановка***

Необходимо выполнить создание базы данных - запустить  
src/main/java/ru/sbt/home/creationdb/DatabaseUpdater  

***Графическая часть***

Главное окно содержит 3 кнопки  
![Главное окно](https://github.com/Airoo/Pictures/blob/master/images/SbtHome/home24/main.jpg)  
Оно создания рецепта содержит поля: название рецепта, описание рецепта и возможность  
добавления/обновления/удаления ингредиентов. Предусмотрены все возможные ошибки  
при создании нового рецепта, при наступлении которых появляеться всплывающее окно  
При создании ингредиентам присваиваеться временный id, при сохренении  
происходит проверка наличия идентичных ингредиентов в DB и перезапись id  
![Оно создания рецепта](https://github.com/Airoo/Pictures/blob/master/images/SbtHome/home24/create.jpg)  
Заполнение формы перед сохранением рецепта  
![Заполнение формы](https://github.com/Airoo/Pictures/blob/master/images/SbtHome/home24/createWithParam.jpg)  
Сохранение рецепта возвразает в главному окну  
![Сохранение рецепта](https://github.com/Airoo/Pictures/blob/master/images/SbtHome/home24/createSave.jpg)  
Окно поиска содержит: поле поиска, поле выбора id, кнопку поиска,  
кнопку удаления, кнопку выбора и возврата к главному окну.  
![Окно поиска](https://github.com/Airoo/Pictures/blob/master/images/SbtHome/home24/search.jpg)  
Окно редактирования содержит все возможные поля для редактирования рецепта.  
Предусмотрены все возможные ошибки при редактировании рецепта, при  
наступлении которых появляеться всплывающее окно  
![Окно редактирования](https://github.com/Airoo/Pictures/blob/master/images/SbtHome/home24/searchRedactor.jpg)  
Выполнение редактирования  
![Окно редактирования после редактирования](https://github.com/Airoo/Pictures/blob/master/images/SbtHome/home24/searchRedactorAfterRadaction.jpg)  
 




