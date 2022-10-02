package csci2010.program1;

import org.frost.util.ApplicationContainer;

public class Main {
    public static void main(String[] args) {

        ApplicationContainer applicationContainer = new ApplicationContainer();
        applicationContainer.start(Main.class);
        AguilarTorresProgram1 aguilarTorresProgram1 = (AguilarTorresProgram1) applicationContainer.getObject(AguilarTorresProgram1.class);
        aguilarTorresProgram1.start();


    }
}
