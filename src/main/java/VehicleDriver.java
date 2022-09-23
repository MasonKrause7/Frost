import org.frost.util.Component;
import org.frost.util.Inject;

@Component
public class VehicleDriver extends Person {



    //should we make an @injectable annotation? That would make it easier to handle nested dependencies and multiple dependencies in the same constructor
    //or it could be @dependency
    @Inject
    public VehicleDriver(){

    }


}
