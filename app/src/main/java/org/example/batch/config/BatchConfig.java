package org.example.batch.config;

import org.example.batch.tasklet.SimpleTasklet;
import org.example.batch.tasklet.SimpleTaskletB;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

  /**
   * ステップの定義
   * 
   * @param jobRepository
   * @param transactionManager
   * @param tasklet
   * @return
   */
  @Bean
  public Step simpleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
      SimpleTasklet tasklet) {
    return new StepBuilder("simpleStep", jobRepository).tasklet(tasklet, transactionManager).build();
  }

  @Bean
  public Step stepB(JobRepository jobRepository, PlatformTransactionManager transactionManager,
      SimpleTaskletB taskletB) {
    return new StepBuilder("stepB", jobRepository).tasklet(taskletB, transactionManager).build();
  }

  /**
   * ジョブの定義
   * 
   * @param jobRepository
   * @param simpleStep
   * @return
   */
  @Bean
  public Job multiStepJob(
      JobRepository jobRepository,
      @Qualifier("simpleStep") Step stepA,
      @Qualifier("stepB") Step stepB) {

    return new JobBuilder("multiStepJob", jobRepository)
        .start(stepA)
        .next(stepB)
        .build();
  }
}