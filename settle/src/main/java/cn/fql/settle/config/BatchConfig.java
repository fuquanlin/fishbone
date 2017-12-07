package cn.fql.settle.config;

import cn.fql.settle.batch.AcctCoreReader;
import cn.fql.settle.batch.AcctFlowSumProcessor;
import cn.fql.settle.batch.SettleMonthReportWriter;
import cn.fql.settle.service.SettleService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.DefaultExecutionContextSerializer;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Job repository
 * Job launcher
 * Job
 * Step
 * Tasklet
 * Item
 * Chunk
 * Item reader Item processor
 * Item writer
 * An infrastructure component that persists job execution metadata
 * An infrastructure component that starts job executions
 * An application component that represents a batch process
 * A phase in a job; a job is a sequence of steps
 * A transactional, potentially repeatable process occurring in a step A record read from or written to a data source
 * A list of items of a given size
 * A component responsible for reading items from a data source
 * A component responsible for processing (transforming, validating, or filtering) a read item before it’s written
 * A component responsible for writing a chunk to a data source
 * <p>
 * Created by fuquanlin on 30/11/2017.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private DataSource dataSource;


    @Bean
    public AcctCoreReader acctCoreReader() {
        AcctCoreReader acctCoreReader = new AcctCoreReader();
        acctCoreReader.setPageSize(2);
        return acctCoreReader;
    }

    @Bean
    public AcctFlowSumProcessor acctFlowSumProcessor() {
        return new AcctFlowSumProcessor();
    }

    @Bean
    public SettleMonthReportWriter settleMonthReportWriter() {
        return new SettleMonthReportWriter();
    }


    @Bean
    public Job generateBill(JobBuilderFactory jobBuilderFactory) {
        return jobBuilderFactory.get("generateBill")
                .incrementer(new RunIdIncrementer())
                .start(calculateSettle())
                .build();
    }

    @Bean
    public Step calculateSettle() {
        return stepBuilderFactory().get("importBillDetail")
                .chunk(100)
                .reader(acctCoreReader())
                .processor(acctFlowSumProcessor())
                .writer(settleMonthReportWriter())
                .taskExecutor(taskExecutor())
                .build();
    }


    /*
     * basic batch config below
     */
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }

    @Bean
    public JobBuilderFactory jobBuilderFactory() {
        return new JobBuilderFactory(jobRepository());
    }

    @Bean
    public StepBuilderFactory stepBuilderFactory() {
        StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(jobRepository(), resourcelessTransactionManager());
        return stepBuilderFactory;
    }


//    @Bean
//    public MapJobRepositoryFactoryBean mapJobRepositoryFactory()
//            throws Exception {
//        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(resourcelessTransactionManager());
//        factory.afterPropertiesSet();
//        return factory;
//    }

    @Bean
    public JobRepositoryFactoryBean jobRepositoryFactoryBean() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(resourcelessTransactionManager());
        factory.setSerializer(new DefaultExecutionContextSerializer());
        factory.setTablePrefix("bat_");
        factory.afterPropertiesSet();
        return factory;
    }


    @Bean
    public JobRegistry jobRegistry() {
        JobRegistry jobRegistry = new MapJobRegistry();
        return jobRegistry;
    }

    @Bean
    public JobExplorerFactoryBean jobExplorerFactoryBean() {
        JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
        jobExplorerFactoryBean.setDataSource(dataSource);
        jobExplorerFactoryBean.setSerializer(new DefaultExecutionContextSerializer());
        jobExplorerFactoryBean.setTablePrefix("bat_");
        return jobExplorerFactoryBean;
    }

    @Bean
    public JobExplorer jobExplorer() throws Exception {
        return jobExplorerFactoryBean().getObject();
    }

    public PlatformTransactionManager resourcelessTransactionManager() {
        return new ResourcelessTransactionManager();
    }

//    @Bean
//    public JobRepository jobRepository() {
//        try {
//            return mapJobRepositoryFactory().getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Bean
    public JobRepository jobRepository() {
        try {
            return jobRepositoryFactoryBean().getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        return jobLauncher;
    }
}
