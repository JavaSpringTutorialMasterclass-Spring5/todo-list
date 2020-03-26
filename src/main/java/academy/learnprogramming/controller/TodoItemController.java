package academy.learnprogramming.controller;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import academy.learnprogramming.service.TodoItemService;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.Mappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@Controller
public class TodoItemController {

    // == fields ==
    private final TodoItemService service;

    // == constructor ==
    @Autowired
    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    // == model attributes ==
    @ModelAttribute
    public TodoData todoData() {
        return service.getData();
    }

    // == handler methods ==

    @GetMapping(Mappings.ITEMS) //http://localhost:8080/todo-list/items
    public String items() {
        return ViewNames.ITEMS_LIST;
    }

    @GetMapping(Mappings.ADD_ITEM) //http://localhost:8080/todo-list/items   strona, która wyświetli formularz z możliwością utworzenia nowego TodoItem (jeśli nie przesłaliśmy parametru id) lub edycji istniejącego (jeśli przekazano id)
    public String addOrEditItem(Model model, @RequestParam(required = false, defaultValue = "-1") int id) {

        log.info("editing id = {}", id);

        //próba pobrania
        TodoItem item = service.getItem(id); // jeśli id nei będzie podane, to wyszukiwanie obiektu po id = -1 nie powiedzie się, metoda zwróci null

        if (item == null) {
            item = new TodoItem("", "", LocalDate.now()); //jeśli nie odnaleźliśmy w bazie danych, to znaczy, że trzeba dodać nowy
        }

        model.addAttribute(AttributeNames.TODO_ITEM, item);
        return ViewNames.ADD_ITEM;
    }

    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) { //@ModelAttribute przerobi elementy formularza na stronie jsp do kompletnego obiektu TodoItem
        //nazwa przekazana tutaj wewnątrz adnotacji @ModelAttribute musi się licować z nazwą przekazaną w parametrze "modelAttribute" w formularzu POST z pliku jsp

        log.info("todoItem from jsp form = {}", todoItem);

        if (todoItem.getId() == 0) {
            service.addItem(todoItem); //jeśli id obiktu, który przyszedł w requeście jest równe zero, to znaczy, że dodajemy nowy obiekt
        } else {
            service.updateItem(todoItem); //w przeciwnym razie updatujemy istniejący
        }

        return "redirect:/" + Mappings.ITEMS; //redirect robimy do innego mappingu (innego url-a), a nie do nazwy metody endpointu lub innego pliku jsp!
    }

    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id) {

        log.info("Deleting item with id = {}", id);

        service.removeItem(id);

        return "redirect:/" + Mappings.ITEMS;
    }
}
