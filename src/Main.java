import core.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import core.Helper;
import view.LoginUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        Helper.setTheme();
    }
}