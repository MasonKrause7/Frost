import org.frost.util.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

    ApplicationContainer.start(Main.class);


  ApplicationContainer applicationContainer = new ApplicationContainer();
  Car car = (Car)applicationContainer.getObject(Car.class);





    }

    public static void test(String[] strings) {

    }
}