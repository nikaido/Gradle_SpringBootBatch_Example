package org.example.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class SimpleTasklet implements Tasklet {
  private static final Logger logger = LoggerFactory.getLogger(SimpleTasklet.class);
  
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    System.out.println("✅ バッチ処理が実行されました！");
    logger.info("✅ バッチ処理が実行されました！");
    return RepeatStatus.FINISHED;
  }
}
