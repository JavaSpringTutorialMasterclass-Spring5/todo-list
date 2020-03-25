package academy.learnprogramming.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class TodoData {

    //zrobimy tę klasę jako immutable
    //dlatego lista jest final: będzie można do niej dodawać, ale nie będzie można zmieniać wartości (reassign) poszczególnych elementów
    //nie będziemy też dopuszczac zadnej zewnetrznej metody do operowania na naszej liście
    //stworzymy specjalną metodę, która zwróci niezmienialną listę na podstawie naszej wewnętrznej listy

    // == fields ==
    private static int idValue = 1;
    private final List<TodoItem> items = new ArrayList<>();

    // == constructor ==
    public TodoData() {

        //dodajemy mockowe dane za pomocą metody addItem
        addItem(new TodoItem("first", "first details", LocalDate.now()));
        addItem(new TodoItem("second", "second details", LocalDate.now()));
        addItem(new TodoItem("third", "third details", LocalDate.now()));
    }

    // == public ethods ==
    public List<TodoItem> getItems() {
        return Collections.unmodifiableList(items); //specjalnie zwracamy niemodyfikowalną listę
    }

    public void addItem(@NonNull TodoItem toAdd) { //lombokowa adnotacja sprawdzająca za nas czy przekazany parametr nie jest nullem!!!

        toAdd.setId(idValue);
        items.add(toAdd);
        idValue++;
    }

    public void removeItem(int id) { //lombokowa adnotacja sprawdzająca za nas czy przekazany parametr nie jest nullem!!!

        ListIterator<TodoItem> itemIterator = items.listIterator();

        //przechodzimy w iteratorze przez listę, jeśli znajdziemy element o tym samym id, to go usuwamy i przeryamy pętlę

        while (itemIterator.hasNext()) {

            TodoItem item = itemIterator.next();
            if (item.getId() == id) {
                itemIterator.remove();
                break;
            }
        }
    }

    public TodoItem getItem(int id) {

        for (TodoItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public void updateItem(@NonNull TodoItem toUpdate) {

        ListIterator<TodoItem> itemIterator = items.listIterator();

        //przechodzimy w iteratorze przez listę, jeśli znajdziemy element o tym samym id, to go usuwamy i przeryamy pętlę

        while (itemIterator.hasNext()) {

            TodoItem item = itemIterator.next();

            if (item.equals(toUpdate)) {
                itemIterator.set(toUpdate); //powoduje podmianę starego elementu na liście na nowy
                break;
            }
        }
    }
}
