package test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import manager.TodoManager;
import model.Task;

class TodoManagerTest {

	@Test
	void testAddTask() {
		TodoManager manager = new TodoManager();
		manager.addTask("テストタスク", LocalDate.now());

		//タスクが追加されたことを確認する方法を考える
		assertEquals(1, manager.getTaskCount());
	}

	@Test
	void testComplete() {
		TodoManager manager = new TodoManager();
		manager.addTask("テストタスク", LocalDate.now());
		manager.complete(1);

		Task task = manager.getTask(1);
		assertTrue(task.isDone());
	}
}
