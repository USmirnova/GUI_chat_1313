package com.example.gui_chat_1313;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HelloController {
    DataOutputStream out; // Поток вывода выносим в эту область видимости, чтобы он был доступен из всех вложенных методов.
    @FXML // аннотация
    Button connectBtn; // кнопка "Подключиться"
    @FXML
    Button sendBtn; // кнопка "Отправить"
    @FXML
    TextField textField; // поле ввода данных
    @FXML
    TextArea textArea; // область текстовая
    @FXML
    public void handlerSend() { // отправка сообщений (кнопка "отправить")
        String text = textField.getText(); // берем текст из поля ввода
        textArea.setWrapText(true); // Перенос текста по строкам (чтобы гориз. скролл не появлялся)
        textArea.appendText(text+"\n"); // добавляем текст в textArea
        textField.clear(); // очищаем поле ввода
        //textField.requestFocus(); // устанавливаем курсор в поле ввода
        try {
            out.writeUTF(text); // вывод текста
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void connect() { // Кнопка "Подключиться"
        try {
            Socket socket = new Socket("127.0.0.1", 8179); // Проверить порт в консольной (серверной части). Должен совпадать
            System.out.println("Успешно подключен");
            out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Thread thread = new Thread(new Runnable() { // отдельный поток для вывода серверных сообщений в textArea
                @Override // например сообщение о присоединении новых пользователей и рассылки сообщений
                public void run() {
                    try { // соединение в порядке
                        while (true){
                            String response = in.readUTF(); // считываем данне из объекта ввода (сообщение с сервера)
                            textArea.appendText(response+"\n"); // дописываем в textArea
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("что-то с соединением");
                    }
                }
            });
            thread.start();
            textField.setDisable(false); // делаем активным поле ввода
            sendBtn.setDisable(false); // делаем активной кнопку отправки сообщений
            connectBtn.setDisable(true); // делаем неактивной кнопку "Подключиться"
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Проблемы с подключением");
        }
    }
}