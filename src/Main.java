import business.UserController;
import core.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import core.Helper;
import entity.User;
import view.DashboardUI;
import view.LoginUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        Helper.setTheme();
        /*UserController userController = new UserController();
        User user = userController.findByLogin("mustafa@patika.dev","123123");
        DashboardUI dashboardUI = new DashboardUI(user);
*/
    }
}