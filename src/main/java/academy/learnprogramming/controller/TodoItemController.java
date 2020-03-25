package academy.learnprogramming.controller;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.service.TodoItemService;
import academy.learnprogramming.util.Mappings;
import academy.learnprogramming.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
}
