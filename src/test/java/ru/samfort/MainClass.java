package ru.samfort;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.samfort.model.Vote;
import ru.samfort.util.SecurityUtil;
import ru.samfort.web.VotingController;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext()){
            applicationContext.load("spring/spring_app.xml", /*"spring/spring_db.xml", */"spring/inmemory.xml");
            applicationContext.refresh();

            //System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));

            VotingController votingController = applicationContext.getBean(VotingController.class);
            SecurityUtil.setAuthUserId(0);

            votingController.vote(3);
            try {
                Thread.sleep(1000);
                votingController.vote(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Vote> votes = votingController.getAll();
            System.out.println(votes.get(0).getRestaurant().getName());
        }
    }
}
