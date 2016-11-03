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
ApplicationConfiguration - конфигурационный файл  

В src/main/java/ru/sbt/home/core содержиться:  
CookbookSheet - класс для создания записи в книгу рецептов  
CookbookSheetSimple - класс для создания упрощенной записи в книгу рецептов  
Ingredient - класс для создания инредиента  
Recipe - класс для создания рецепта  

В src/main/java/ru/sbt/home/creation содержиться:  
DatabaseUpdater - класс для создания баз данных  

В src/main/java/ru/sbt/home/dao содержиться:  
Dao - интрефейс Dao  
DaoImpl - реализация интрефейса Dao  

В src/main/java/ru/sbt/home/view содержиться:  
Message - окно для вывода сообщения  
MainController - главное окно  
createbranch/CreateController - окно для создания записи в книгу рецептов  
searchbranch/SearchController - окно для поиска рецептов  
searchbranch/SearchControllerRedactor - окно для редактирования рецептов  
   
В src/main/java/sql содержиться:  
cookbook.sql - базы данных для реализации книги рецептов  
В проекте использовалось три таблицы Recipes, Ingredients и для их связи  
Cookbook

В src/main/java/resources содержиться:   
Файлы *.fxml соответсвующих окон и Log4j.properties  
      
***Итог***

Реализовал простую "книгу рецептов" на JavaFX, Spring JDBC и H2 со  
всеми возможными операциями CrUD    

