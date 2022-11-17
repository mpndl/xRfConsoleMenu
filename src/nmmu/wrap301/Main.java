package nmmu.wrap301;

import nmmu.wrap301.menu.Menu;
import nmmu.wrap301.menu.MenuBuilderUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        /*// create the object that has the logic of how the application is supposed to work,
        // and the methods that are triggered by each menu choice
        Controller controller = new Controller();

        // construct the menu
        Menu menu = new Menu("Main Menu");
        menu.add("What is your name?", controller::whatIsYourNameAction);
        menu.add("Say hello world", controller::helloWorldAction);
        menu.add("Display 5 x table", controller::fiveTimesTableAction);

        // construct a sub-menu
        Menu subMenu = new Menu("SubMenu");
        subMenu.add("Is prime?", controller::isPrimeAction);
        subMenu.add("Is odd?", controller::isOddAction);

        // attach the sub-menu to the menu
        menu.add("Other functions", subMenu);

        // start the console menu-based app to run
        menu.run();*/

        Menu menu = MenuBuilderUtil.build("C:\\Users\\user1\\IdeaProjects\\Assignment03\\Task2\\main.xml");
        menu.run();
    }
}
