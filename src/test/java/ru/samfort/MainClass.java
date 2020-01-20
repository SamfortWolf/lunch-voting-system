package ru.samfort;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.samfort.model.Restaurant;
import ru.samfort.model.Vote;
import ru.samfort.util.SecurityUtil;
import ru.samfort.web.UserController;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext()){
            applicationContext.load("spring/spring_app.xml", "spring/spring_mvc.xml");
            applicationContext.refresh();

            UserController userController = applicationContext.getBean(UserController.class);
            TestUtils.mockAuthorize(TestData.USER);

            userController.vote(100004);
            try {
                Thread.sleep(1000);
                userController.vote(100003);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Vote> votes = userController.getAllVotes();
//            votes.forEach(System.out::println);
            System.out.println(votes.get(0).getRestaurant().getName());
        }
    }
}
