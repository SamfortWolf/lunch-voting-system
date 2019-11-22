package ru.samfort.voting;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.samfort.voting.model.Vote;
import ru.samfort.voting.util.SecurityUtil;
import ru.samfort.voting.web.VotingController;

import java.util.Arrays;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext()){
            applicationContext.load("spring/spring_app.xml", "spring/spring_db.xml");
            applicationContext.refresh();

            //System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));

            VotingController votingController = applicationContext.getBean(VotingController.class);
            SecurityUtil.setAuthUserId(0);

            votingController.vote(3);
            List<Vote> votes = votingController.getAll().getBody();
            votes.forEach(System.out::println);
        }
    }
}
