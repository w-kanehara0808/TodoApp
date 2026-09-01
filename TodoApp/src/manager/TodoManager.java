package manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	public void saveToCsv(String filePath) throws IOException {
		List<String> lines = new ArrayList<>();

		for (Task t : tasks) {
			String line = t.getId() + "," + t.getTitle() + "," + t.getDueDate() + "," + t.isDone();
			lines.add(line);
		}

		Files.write(Paths.get(filePath), lines);
	}

	public void loadFromCsv(String filePath) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filePath));

		for (String line : lines) {

			//カンマで分解して。id,title,dueDate,isDoneに分ける
			String[] parts = line.split(",");

			int id = Integer.parseInt(parts[0]);
			String title = parts[1];
			LocalDate dueDate = LocalDate.parse(parts[2]);
			boolean isDone = Boolean.parseBoolean(parts[3]);

			//それらの値からTaskオブジェクトを作る
			Task task = new Task(id, title, dueDate);

			//tasksリストに追加する
			if (isDone) {
				task.setDone(true);
			}
			tasks.add(task);
		}

	}

	public int getTaskCount() {
		return tasks.size();
	}

	public Task getTask(int id) {
		for (Task t : tasks) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}
}
