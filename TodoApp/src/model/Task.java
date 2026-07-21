package model;

import java.time.LocalDate;

public class Task {
	private int id;
	private String title;
	private LocalDate dueDate;
	private boolean isDone;

	public Task(int id, String title, LocalDate dueDate) {
		this.id = id;
		this.title = title;
		this.dueDate = dueDate;
		this.isDone = false;
	}

	public int getId() {
		return id;
	}

	public String gerTitle() {
		return title;
	}

	public LocalDate gerDueDate() {
		return dueDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isOverdue() {
		return !isDone && LocalDate.now().isAfter(dueDate);
	}

	@Override
	public String toString() {
		String status = isDone ? "✓" : (isOverdue() ? "期限切れ" : "未完了");
		return String.format("[%d]%s(期限:%s)%s", id, title, dueDate, status);
	}

}
