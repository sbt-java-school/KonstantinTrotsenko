package home4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс служит для заполнения коллеции грузовиков
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class TruckDaoInMemoryImpl implements TruckDao {
    private final List<Truck> trucks = new ArrayList<>(
            Arrays.asList(
                    new Truck(1, TruckType.KAMAZ, 10),
                    new Truck(21, TruckType.VOLVO, 20),
                    new Truck(3, TruckType.FREIGHTLINER, 30),
                    new Truck(4, TruckType.VOLVO, 40),
                    new Truck(5, TruckType.FREIGHTLINER, 50),
                    new Truck(6, TruckType.VOLVO, 60),
                    new Truck(7, TruckType.KAMAZ, 60),
                    new Truck(8, TruckType.SCANIA, 60),
                    new Truck(9, TruckType.HOWO, 70)
            ));

    @Override
    public List<Truck> list() {
        return trucks;
    }
}
