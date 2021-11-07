package com.example.gui_chat_1313;
// группа 1313, урок от 03.11.2021 д.з., графический чат финал, лабораторная работа №10.1
// добавляем коллекцию онлайн пользователей в поле слева
// принимаем и обрабатываем объеты (строку и/или коллекцию)
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // создание объекта сцены с заданными параметрами ширины и высоты
        stage.setTitle("Клиент чата"); // заголовок окна
        stage.setScene(scene); // установить сцену?
        stage.show(); // показать сцену?
        // Действие при закрытии окна
        stage.setOnCloseRequest(e->{ // выполняем полное закрытие приложения при закрытие окна "Клиент чата"
            Platform.exit(); // выход из программы, но с кодом 130, значит какие-то проблемы.
            System.exit(0); // чтобы программа завершалась кодом 0
        });
    }

    public static void main(String[] args) {
        launch();
    }
}