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
TaskExecutor - класс выолняющий задачу из очереди
ThreadPool - реализация ThreadPool без использования Executors.class
В src/test/java/home14 содержиться:  
ThreadPoolTest - класс для тестирования работоспособности ThreadPool  

***Итог***

Реализовал простой ThreadPool двумя способами, с использованием Executors.class
и без него
 

