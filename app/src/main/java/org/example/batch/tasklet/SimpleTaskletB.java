package org.example.batch.tasklet;

import java.util.List;

import org.example.batch.entity.User;
import org.example.batch.mapper.UsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class SimpleTaskletB implements Tasklet {
  private static final Logger logger = LoggerFactory.getLogger(SimpleTaskletB.class);
  
  UsersMapper mapper;
  
  public SimpleTaskletB(UsersMapper mapper) {
    this.mapper = mapper;
  }
  
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    System.out.println("✅ バッチ処理が実行されました！B");
    logger.info("✅ バッチ処理が実行されました！B");

    User userA = new User();
    User userB = new User();
    userA.setUsername("USER_A");
    userB.setUsername("USER_B");
    
    mapper.insertUser(userA);
    mapper.insertUser(userB);
    
    List<User> users = mapper.findAll();
    
    System.out.println("The number of users: " + users.size());
    
    for(User user : users) {
      System.out.println("Username = " + user.getUsername());
    }
    
    return RepeatStatus.FINISHED;
  }
}
