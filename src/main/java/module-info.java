module com.example.gui_chat_1313 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gui_chat_1313 to javafx.fxml;
    exports com.example.gui_chat_1313;
}