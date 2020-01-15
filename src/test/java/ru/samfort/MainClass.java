package ru.samfort;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.samfort.model.Vote;
import ru.samfort.util.SecurityUtil;
import ru.samfort.web.UserController;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext()){
            applicationContext.load("spring/spring_app.xml", "spring/inmemory.xml");
            applicationContext.refresh();

            //System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));

            UserController userController = applicationContext.getBean(UserController.class);
//            SecurityUtil.setAuthUserId(0);

            userController.vote(3);
            try {
                Thread.sleep(1000);
                userController.vote(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Vote> votes = userController.getAllVotes();
            System.out.println(votes.get(0).getRestaurant().getName());
        }
    }
}
