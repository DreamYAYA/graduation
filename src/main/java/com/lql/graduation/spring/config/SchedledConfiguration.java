package com.lql.graduation.spring.config;


import com.lql.graduation.common.ScheduleQuartz;
import com.lql.graduation.pojo.Scheduler.MyJob;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedledConfiguration {


    /**
     * attention:
     * Details：配置定时任务
     */
    @Bean
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(MyJob myJob) {// ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

        jobDetail.setTargetObject(myJob);
        jobDetail.setTargetMethod("doSomething");
        //设置是否并发
        jobDetail.setConcurrent(false);
        return jobDetail;
    }

    /**
     * attention:
     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
      tigger.setJobDetail(methodInvokingJobDetailFactoryBean.getObject());
        tigger.setCronExpression("0/1 * * * * ?");// 初始时的cron表达式  ，没5分钟执行一次
        return tigger;
    }


    /**
     * attention:
     * Details：定义quartz调度工厂
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger jobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
//        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
//        bean.setOverwriteExistingJobs(true);
//        // 延时启动，应用启动1秒后
//        bean.setStartupDelay(1);
//        // 注册触发器
        bean.setTriggers(jobTrigger);
        return bean;
    }

    @Bean
    public ScheduleQuartz scheduleQuartz(SchedulerFactoryBean schedulerFactoryBean){

        ScheduleQuartz  scheduleQuartz = new ScheduleQuartz();
        scheduleQuartz.setScheduler(schedulerFactoryBean.getScheduler());

        return  scheduleQuartz;
    }

}
