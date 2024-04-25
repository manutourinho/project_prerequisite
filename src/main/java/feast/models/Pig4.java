package feast.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pig4 {

    @Autowired
    private Lamb5 lamb5;

    @Override
    public String toString() {
        return "which is stuffed inside a lamb, " + lamb5.toString();
    }
}
