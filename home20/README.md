###**Задание к уроку N20 (Patterns)**

1 Разработать класс(классы) по конвертации произвольных объектов классов:   
Integer, Long, Float, Double, BigDecimal, String  
в любой требуемый объект:  
Integer, Long, Float, Double, BigDecimal, String  
Реализация должна быть написана таким образом, чтобы была возможность легко   
добавить новые правила конвертации любого произвольного объекта в любой другой   
произвольный объект.  

***Реализация***

Модуль home20 входит в состав родительского проекта  
В src/main/java/home20/base содержиться:  
Converter - интерфейс для реализации конвертации  
ConverterImpl - реализация интрефейса Converter  
ConverterValue - интрефейс для реализации конвертации определенного объекта  
ConverterValueAbstract - абстрактный класс для реализации интрефейса ConverterValue  
В src/main/java/home20/realization содержиться:  
tobigdecimal/ToBigDecimal - класс для конвертации в BigDecimal  
tobigdecimal/fromnumber/NumberToBigDecimal - класс для конкретной конвертации Number в BigDecimal  
tobigdecimal/fromstring/StringToBigDecimal - класс для конкретной конвертации String в BigDecimal  
todouble/ToDouble.. все по аналогии  
В src/test/java/home20 содержиться:  
ConverterTest - тесты для Конвертера  

***Итог***

1 Реализовал конвертер для конвертации произвольных объектов классов.  
При необходимости можно легко добавить новый конвертер, по аналогии с существующими.  
Разобрался с поведенческими паттренам.

