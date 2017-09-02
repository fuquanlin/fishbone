package cn.fql.fishbone.config;

import cn.fql.fishbone.job.HelloJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by fuquanlin on 2016/10/27.
 */
//@Configuration
public class QuartzConfig {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzConfig.class);

    /**
     * 字段名                 允许的值                        允许的特殊字符
     * 秒                         0-59                               , - * /
     * 分                         0-59                               , - * /
     * 小时                     0-23                               , - * /
     * 日                         1-31                               , - * ? / L W C
     * 月                         1-12 or JAN-DEC         , - * /
     * 周几                     1-7 or SUN-SAT           , - * ? / L C #
     * 年 (可选字段)     empty, 1970-2099      , - * /
     * <p>
     * “?”字符：表示不确定的值
     * <p>
     * “,”字符：指定数个值
     * <p>
     * “-”字符：指定一个值的范围
     * <p>
     * “/”字符：指定一个值的增加幅度。n/m表示从n开始，每次增加m
     * <p>
     * “L”字符：用在日表示一个月中的最后一天，用在周表示该月最后一个星期X
     * <p>
     * “W”字符：指定离给定日期最近的工作日(周一到周五)
     * <p>
     * “#”字符：表示该月第几个周X。6#3表示该月第3个周五
     */

    private final static String CRON_EXPRESSION = "* */1 * * * ?";

    @Bean
    public JobDetailFactoryBean createJobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setName("HelloJob");
        jobDetailFactoryBean.setGroup("jobGroup");

        jobDetailFactoryBean.setJobClass(HelloJob.class);

        return jobDetailFactoryBean;
    }

    @Bean
    protected CronTriggerFactoryBean createTrigger(JobDetail jobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName("ten_seconds");
        cronTriggerFactoryBean.setGroup("triggerGroup");

        cronTriggerFactoryBean.setCronExpression(CRON_EXPRESSION);
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY);

        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(List<Trigger> triggers, DataSource dataSource) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        schedulerFactoryBean.setTriggers(triggers.toArray(new Trigger[0]));
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        schedulerFactoryBean.setDataSource(dataSource);

        return schedulerFactoryBean;
    }

    private Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        try {
            propertiesFactoryBean.afterPropertiesSet();
            return propertiesFactoryBean.getObject();
        } catch (IOException e) {
            LOG.error("read quartz.properties file error: {}", e.getMessage());
        }
        return null;
    }
}
