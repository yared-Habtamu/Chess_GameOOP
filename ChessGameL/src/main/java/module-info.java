module com.example.chessgamel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    opens com.example.chessgamel to javafx.fxml;
    exports com.example.chessgamel.project.mainChessApp;
    opens com.example.chessgamel.project.mainChessApp to javafx.fxml;
    exports com.example.chessgamel.project.pieces;
    opens com.example.chessgamel.project.pieces to javafx.fxml;
}