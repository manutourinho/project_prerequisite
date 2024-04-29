package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


      User userOne = new User("Sandra", "Tourinho", "sandra@mail.com");
      userOne.setCar(new Car("Peugeot 405", 181818));
      userService.add(userOne);

      User userTwo = new User("Maria Luisa", "Tourinho", "malu@mail.com");
      userTwo.setCar(new Car("Volkswagen Fusca", 787878));
      userService.add(userTwo);

      User userThree = new User("José", "Junior", "josejr@mail.com");
      userThree.setCar(new Car("Ferrari 296", 464646));
      userService.add(userThree);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car model= " + user.getCar().getModel() + " / "
                    + "Car series = " + user.getCar().getSeries());
         }
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Ferrari 296", 464646));

      context.close();
   }
}
