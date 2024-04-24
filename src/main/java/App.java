import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());
        HelloWorld helloWorldBeanTwo =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(helloWorldBeanTwo.getMessage());

        Cat catBeanOne = (Cat) applicationContext.getBean("cat");
        System.out.println(catBeanOne.getMiau());
        Cat catBeanTwo = (Cat) applicationContext.getBean("cat");
        System.out.println(catBeanTwo.getMiau());

        System.out.println("should return true, and is: \n" + compareBeans(bean, helloWorldBeanTwo));
        System.out.println("should return false, and is: \n" + compareBeans(catBeanOne, catBeanTwo));

    }

    public static boolean compareBeans(Object objOne, Object objTwo) {
        return objOne == objTwo;

    }
}