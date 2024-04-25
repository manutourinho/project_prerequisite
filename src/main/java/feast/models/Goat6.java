package feast.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Goat6 {

    @Autowired
    private Horse7 horse7;

    @Override
    public String toString() {
        return "which is stuffed inside a horse, " + horse7.toString();
    }
}
