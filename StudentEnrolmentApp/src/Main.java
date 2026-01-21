import ui.DashboardFrame;
import db.DatabaseSetup;

public class Main {
    public static void main(String[] args) {
        DatabaseSetup.createTables();
        new DashboardFrame();
    }
}
