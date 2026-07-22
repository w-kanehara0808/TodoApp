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
			for (Task t : tasks) {
				//未完了のみ表示
				if (!t.isDone()) {
					System.out.println(t);
				}
			}
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
		for (Task t : tasks) {
			if (t.getId() == id) {
				t.setDone(true);
			}
		}
	}

	public void delete(int id) {
		tasks.removeIf(t -> t.getId() == id);
	}

	public void showCompelete() {
		for (Task t : tasks) {
			if (t.isDone()) {
				System.out.println(t);
			}

		}
	}

}
