import org.frost.util.Component;
import org.frost.util.Inject;


@Component
public class Car extends Vehicle {

    //private VehicleDriver drivee; do we need this field since were injecting the dependency? when would it be better to inject the field?
    private String licensePlate;

    public Car(){

    }


    @Inject //injection for a constructor
    public Car(VehicleDriver drivee){
        System.out.printf("\n Driver ", drivee.getName());//inject the driver object here!!!!!!!!

    }

    @Inject //injection for a method --> Handling of this is not yet implemented in Injector class
    public void drive(VehicleDriver drivee){
        System.out.println("Vrooooommm");
    }

}
