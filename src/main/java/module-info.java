module com.pertemuan09.teori09 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;

    opens com.pertemuan09.teori09 to javafx.fxml;
    opens com.pertemuan09.teori09.entity to javafx.fxml, com.google.gson;
    exports com.pertemuan09.teori09;
    exports com.pertemuan09.teori09.entity;

}