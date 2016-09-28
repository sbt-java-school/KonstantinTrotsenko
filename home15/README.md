###**Задание к уроку N15 (MultiThreading)**

1 Написать реализацию класса Task имеющего один метод get()  
2 Написать реализацию интерфейса ExecutionManager  

***Реализация***

Модуль home15 входит в состав родительского проекта  
В src/main/java/home15_1 содержиться:  
Task - класс реализация задания  
MyException - обертка для RuntimeException  
В src/main/java/home15_2 содержиться:  
Context - интерфейс для реализации  
ExecutionManager - интерфейс для реализации  
ExecutionManagerImpl - реализация задания  
В src/test/java/home15_1 содержиться:  
TaskTest - класс для тестирования класса Task  
В src/test/java/home15_2 содержиться:  
ExecutionManagerImplTest - класс для тестирования класса ExecutionManagerImpl  

***Итог***

Реализовал класс Task с помощью библиотеки Сoncurrent, для записи
результата метода и исключения использовал ConcurrentHashMap<>. 
Применил паттерн Double checked locking.
Реализовал интрефейс ExecutionManager с помощью ExecutorService и ThreadPoolExecutor. 
Но возникли проблемы с получением тасков, в которых возникает Exception! Решение вижу 
либо использование вместо Runnable Callable, либо не испольовать ExecutorService и ThreadPoolExecutor.
 

