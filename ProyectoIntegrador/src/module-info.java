module ProyectoIntegrador {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	opens view to javafx.fxml;
    opens controller to javafx.fxml;
    exports controller;
	
	opens application to javafx.graphics, javafx.fxml;
}
