package home4;

import java.util.*;

/**
 * Класс получения объектов грузовиков по Id и по Type
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Application {
    /**
     * Карта для сортировки по Id
     */
    private Map<Long, Truck> truckRegistryById = new HashMap<>();

    /**
     * Карата для сортировки по Type
     */
    private Map<TruckType, List<Truck>> truckRegistryByType = new HashMap<>();

    /**
     * Конструктор инициализирующий truckRegistryById и truckRegistryByType
     *
     * @param truckDao объект класса имплиментирующего интерфейс TruckDao
     */
    public Application(TruckDao truckDao) {
        List<Truck> list = truckDao.list();
        for (Truck truck : list) {
            Truck previous = truckRegistryById.put(truck.getId(), truck);
            if (null != previous) {
                throw new IllegalStateException("two trucks with one ID");
            }

            TruckType type = truck.getType();

            if (null == type) {
                throw new IllegalStateException("truck without type!!");
            }
            if (truckRegistryByType.containsKey(type)) {
                truckRegistryByType.get(type).add(truck);
            } else {
                truckRegistryByType.put(truck.getType(), new ArrayList<>(Arrays.asList(truck)));
            }
        }
    }

    /**
     * Метод возвращает объектн truck по заданному Id
     *
     * @param truckId Id объекта для поиска
     * @return объект truck с искомым Id
     * @throws IllegalArgumentException исключение если truck не найден по Id
     */
    public Truck getTruckById(long truckId) {
        Truck truck = truckRegistryById.get(truckId);
        if (truck == null) {
            throw new IllegalArgumentException("not found truck by id");
        }
        return truck;
    }

    /**
     * Метод возвращает объектны truck по заданному type
     *
     * @param type type объекта для поиска
     * @return список объектов trucks с искомым type
     * @throws IllegalArgumentException исключение если truck не найден по type
     */
    public List<Truck> getTruckByType(TruckType type) {
        List<Truck> trucks = truckRegistryByType.get(type);
        if (trucks == null) {
            throw new IllegalArgumentException("not found trucks by type");
        }
        return trucks;
    }

    /**
     * Метод выводит в консоль все объекты truck
     */
    void viewTruckRegistryById() {
        System.out.println(truckRegistryById);
    }

    /**
     * Точка входа в класс
     *
     * @param args Массив строковых аргументов
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            printHelp();
            System.exit(1);
        }

        TruckDao truckDao = (TruckDao) new TruckDaoInMemoryImpl();
        Application application = new Application(truckDao);

        long truckId = Long.parseLong(args[0]);
        TruckType type = TruckType.valueOf(args[1]);

        application.viewTruckRegistryById();

        System.out.println(application.getTruckById(truckId));
        System.out.println(application.getTruckByType(type));
    }

    /**
     * Метод выводит в консоль сообщение об ошибке входных данных
     */
    private static void printHelp() {
        System.out.println("Use first argument as truck ID, second as truck TYPE");
    }

}
