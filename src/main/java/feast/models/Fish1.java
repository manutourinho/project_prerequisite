package feast.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Fish1 {
    @Autowired
    private Duck2 duck2;

    @Override
    public String toString() {
        return "of a fish inside of a duck, " + duck2.toString();
    }
}
