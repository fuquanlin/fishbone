package cn.fql.fishbone.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fuquanlin on 2016/10/28.
 */
public class HelloJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("Do the job!");
        try {
            LOG.info("job scheduler = {}.", context.getScheduler().getSchedulerName());
        } catch (SchedulerException e) {
            LOG.error("SchedulerException", e);
        }
        LOG.info("job trigger = {}.", context.getTrigger());
    }
}
