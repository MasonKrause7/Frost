package csci2010.program1;

import org.frost.util.ApplicationContainer;

public class Main {
    public static void main(String[] args) {
        ApplicationContainer.start(Main.class);
        ApplicationContainer applicationContainer = new ApplicationContainer();
        AguilarTorresProgram1 aguilarTorresProgram1 = (AguilarTorresProgram1) applicationContainer.getObject(AguilarTorresProgram1.class);
        aguilarTorresProgram1.start();


    }
}
