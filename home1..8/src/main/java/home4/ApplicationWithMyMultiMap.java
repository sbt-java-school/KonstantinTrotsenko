package home4;

import home4_1.MyMultiMap;

import java.util.*;


/**
 * Класс получения объектов грузовиков по Type с использованием MyMultiMap
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 * @see MyMultiMap
 */
public class ApplicationWithMyMultiMap {
    /**
     * Карата для сортировки по Type
     */
    private MyMultiMap<TruckType, Truck> truckRegistryByType = new MyMultiMap<>();

    /**
     * Конструктор инициализирующий ruckRegistryByType
     *
     * @param truckDao объект класса имплиментирующего интерфейс TruckDao
     */
    public ApplicationWithMyMultiMap(TruckDao truckDao) {
        List<Truck> list = truckDao.list();
        for (Truck truck : list) {
            TruckType type = truck.getType();
            truckRegistryByType.put(type,truck);
        }
    }

    /**
     * Метод выводит в консоль все объекты типа truck
     */
    public void viewTrucksByType(TruckType type){
        System.out.println(truckRegistryByType.get(type));
    }

    /**
     * Точка входа в класс
     *
     * @param args Массив строковых аргументов
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            printHelp();
            System.exit(1);
        }

        TruckDao truckDao = (TruckDao) new TruckDaoInMemoryImpl();
        ApplicationWithMyMultiMap application = new ApplicationWithMyMultiMap(truckDao);

        TruckType type = TruckType.valueOf(args[0]);

        application.viewTrucksByType(type);
    }

    /**
     * Метод выводит в консоль сообщение об ошибке входных данных
     */
    private static void printHelp() {
        System.out.println("Use first argument as truck TYPE");
    }

}
