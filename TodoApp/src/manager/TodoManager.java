package manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TodoManager {
	private List<Task> tasks = new ArrayList<>();
	private int nextId = 1;

	public void addTask(String title, LocalDate dueDate) {
		Task task = new Task(nextId++, title, dueDate);
		tasks.add(task);
	}

	public void showAll() {
		if (tasks.isEmpty()) {
			System.out.println("タスクはありません");
		} else {
			tasks.stream()
					.filter(t -> !t.isDone())
					.forEach(System.out::println);
		}
	}

	public void edit(int id, String title, LocalDate dueDate) {
		//idが一致するTaskを探して更新
		for (Task t : tasks) {
			if (t.getId() == id) {
				//titleが空でなければ変更
				if (title != null && !title.isEmpty()) {
					t.setTitle(title);
				}
				if (dueDate != null) {
					t.setDueDate(dueDate);
				}
			}
		}
	}

	public void complete(int id) {
		tasks.stream()
				.filter(t -> t.getId() == id)
				.forEachOrdered(t -> t.setDone(true));
	}

	public void delete(int id) {
		tasks.removeIf(t -> t.getId() == id);
	}

	public void showCompelete() {
		tasks.stream()
				.filter(t -> t.isDone())
				.forEach(System.out::println);

	}
}
