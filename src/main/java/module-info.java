module com.unstuck.supply_chain_management {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.unstuck.supply_chain_management to javafx.fxml;
    exports com.unstuck.supply_chain_management;
}