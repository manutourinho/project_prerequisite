import feast.TraditionalFeast;
import feast.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class MainTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void mainTest() {
        String text = "Traditional Bedouin Wedding Feast consists of stuffing of a fish inside of a duck, which is stuffed inside a chicken, which is stuffed inside a pig, which is stuffed inside a lamb, which is stuffed inside a goat, which is stuffed inside a horse, which is stuffed inside a camel, and cooked over a charcoal fire.";

        TraditionalFeast traditionalFeast =
                applicationContext.getBean(TraditionalFeast.class);
        System.out.println(traditionalFeast.getTraditionalFeast());
        String testText = traditionalFeast.getTraditionalFeast();

        if (!testText.contains(text) && testText.length() <= text.length()) {
            Assert.fail("The test failed due to incorrect wiring of the beans");
        }
    }

}
