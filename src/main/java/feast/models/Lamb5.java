package feast.models;

import org.springframework.stereotype.Component;

@Component
public class Lamb5 {

    private final Goat6 goat6;

    public Lamb5(Goat6 goat6) {
        this.goat6 = goat6;
    }

    @Override
    public String toString() {
        return "which is stuffed inside a goat, " + goat6.toString();
    }
}
