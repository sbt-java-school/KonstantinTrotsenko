###**Задание к уроку N13-14 (MultiThreading)**

1 Написать загрузчик ThreadPool 

***Реализация***

Модуль home13..14 входит в состав родительского проекта  
В src/main/java/home13 содержиться:  
WorkerThread - класс для создания потока и выполнения какой либо работы  
SimpleThreadPool - реализация ThreadPool с использованием Executors.class   
В src/main/java/home14 содержиться:  
BlockingQueue - класс реализующий блокирующую очередь  
SomethingTask - класс симулирующий какую либо работу  
