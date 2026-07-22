package main;

import java.time.LocalDate;
import java.util.Scanner;

import manager.TodoManager;

public class Main {

	public static void main(String[] args) {
		// 1.TodoManagerのインスタンスを作成する
		TodoManager manager = new TodoManager();

		//2.Scannerを作る
		Scanner scanner = new Scanner(System.in);

		//3.whileループでメニューを繰り返す
		while (true) {
			//タスク一覧表示
			manager.showAll();
			//4.メニューを表示する
			System.out.println("""
					=====TodoApp=====
					1.タスク追加
					2.タスクをDoneにする
					3.タスク編集
					4.完了済み一覧
					0.終了
					番号を入力してください >""");
			//5.番号を入力させる
			int choice = scanner.nextInt();
			scanner.nextLine();
			if (choice == 0) {
				break;
			}
			//6.switchで処理を分岐する
			switch (choice) {
			case 1 -> {
				//①タイトルを入力させる
				System.out.println("タイトルを入力してください");
				String title = scanner.nextLine();
				//②期限を入力させる
				System.out.println("期限を入力してください(例：2026-08-25) >");
				LocalDate dueDate = LocalDate.parse(scanner.nextLine());
				manager.addTask(title, dueDate);
			}
			case 2 -> {
				//IDを入力させる
				System.out.println("DoenにするタスクのIDを入力してください >");
				int id = scanner.nextInt();
				manager.complete(id);
			}
			case 3 -> {
				System.out.println("編集するタスクのIDを入力してください >");
				//タスクを呼び出して編集する
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.println("新しいタイトルを入力してください(変更しない場合はEnter) >");
				String title = scanner.nextLine();
				System.out.println("新しい期限を入力してください(例：2026-08-25) >");
				LocalDate dueDate = LocalDate.parse(scanner.nextLine());
				manager.edit(id, title, dueDate);
			}
			case 4 -> {
				//完了したタスクを一覧に表示
				manager.showCompelete();
			}
			}
		}

	}

}
