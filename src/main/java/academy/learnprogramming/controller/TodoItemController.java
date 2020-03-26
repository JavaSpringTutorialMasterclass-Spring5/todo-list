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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping(Mappings.ADD_ITEM) //http://localhost:8080/todo-list/items   strona, która wyświetli formularz z możliwością utworzenianowego TodoItem
    public String addOrEditItem(Model model) {
        TodoItem emptyToDoItem = new TodoItem("", "", LocalDate.now());
        model.addAttribute(AttributeNames.TODO_ITEM, emptyToDoItem);
        return ViewNames.ADD_ITEM;
    }

    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) { //@ModelAttribute przerobi elementy formularza na stronie jsp do kompletnego obiektu TodoItem
        //nazwa przekazana tutaj wewnątrz adnotacji @ModelAttribute musi się licować z nazwą przekazaną w parametrze "modelAttribute" w formularzu POST z pliku jsp

        log.info("todoItem from jsp form = {}", todoItem);

        service.addItem(todoItem);

        return "redirect:/" + Mappings.ITEMS; //redirect robimy do innego mappingu (innego url-a), a nie do nazwy metody endpointu lub innego pliku jsp!
    }
}
