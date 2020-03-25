package academy.learnprogramming.controller;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.util.Mappings;
import academy.learnprogramming.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TodoItemController {

    // == model attributes ==
    @ModelAttribute
    public TodoData todoData() {
        return new TodoData();
    }

    // == handler methods ==

    @GetMapping(Mappings.ITEMS) //http://localhost:8080/todo-list/items
    public String items() {
        return ViewNames.ITEMS_LIST;
    }
}
