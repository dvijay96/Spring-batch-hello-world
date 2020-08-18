package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldConfig {

	@Autowired
	public JobBuilderFactory jobBuilder;

	@Autowired
	public StepBuilderFactory stepBuilder;

	@Bean
	public Step helloWorldStep() {
		return stepBuilder.get("helloWorldStep").tasklet(new Tasklet() {

			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("Hello World Step");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Job helloWorldJob() {
		return jobBuilder.get("helloWorldJob").start(helloWorldStep()).build();

	}

}
