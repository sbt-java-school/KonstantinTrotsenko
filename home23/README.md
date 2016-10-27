###**Задание к уроку N23 (JDBC)**

1 Выполнить рефакторинг кода, который делали на занятии  
2 Реализовать кэширующий прокси с помощью JDBC   

***Реализация***

Модуль home23 входит в состав родительского проекта  
  
В src/main/java/ru/sbt/home/refactoring содержиться:  
core/User - класс для сохранения в базу данных  
creation/DatabaseUpdater - класс для создания базы данных  
dao/DaoDemo - класс Dao для работы с базой данных  
dao/JdbcAction - интрефейс для возможности испольовать лямбда выражение  
dao/JdbcTemplate - класс для создания соединения с базой данных  
dao/UserDao - класс для рабоыт с базой данных  
view/MainController - класс для визуальной работы с базой данных  
view/Message - класс для трансялции сообщений при работе с MainController  
   
В src/main/java/ru/sbt/home/cache содержиться:  
core/Cache - аннотоция для кэширования результата методов  
core/Calculator - интрефейс калькулятор  
core/CalculatorImpl - реализация интрейса Calculator  
creation/DatabaseUpdater - класс для создания базы данных  
dao/Dao - класс для рабоы с базой данных  
dao/JdbcAction - интрефейс для возможности испольовать лямбда выражение  
dao/JdbcTemplate - класс для создания соединения с базой данных  
proxy/ProxyUtils - класс прокси для CalculatorImpl  

В src/main/java/sql содержиться:  
method.sql - база данных для реализации киширующего прокси  
user.sql - база данных с занятия  
 
В src/main/test/java/ru/sbt/home содержиться:  
cache/ProxyTest - класс для тестирования кэширующего прокси  
refactoring/DaoDemoTest - класс для тестирования CrUD
      
***Итог***

1 Сделал рефакторинг кода - на основе кода с занятия сделал CrUD на Java FX.  
2 Реализовал кэширующий прокси с помощью JDBC и H2.  

