package academy.learnprogramming.service;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import org.springframework.stereotype.Service;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    // == fields ==
    private final TodoData mockTodoData = new TodoData();

    // == public methods ==

    @Override
    public void addItem(TodoItem item) {
        mockTodoData.addItem(item);
    }

    @Override
    public void removeItem(int id) {
        mockTodoData.removeItem(id);
    }

    @Override
    public TodoItem getItem(int id) {
        return mockTodoData.getItem(id);
    }

    @Override
    public void updateItem(TodoItem item) {
        mockTodoData.updateItem(item);
    }

    @Override
    public TodoData getData() {
        return mockTodoData;
    }
}
