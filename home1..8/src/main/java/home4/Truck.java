package home4;

/**
 * Класс сущности
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Truck {

    private TruckType type;
    private long id;
    private int capacity;

    public Truck(long id, TruckType type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getId() {
        return id;
    }

    public TruckType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Truck: id=" + id + ", type=" + type + ", capacity=" + capacity+". ";
    }


}
