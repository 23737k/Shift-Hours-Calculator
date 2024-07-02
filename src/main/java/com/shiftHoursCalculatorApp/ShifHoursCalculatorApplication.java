package com.shiftHoursCalculatorApp;

import com.shiftHoursCalculatorApp.cron.MyJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShifHoursCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShifHoursCalculatorApplication.class, args);

		try {
			// Creamos una instancia del scheduler
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// Comenzamos el scheduler
			scheduler.start();

			// Definimos el job
			JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("myJob", "group1").build();

			// Definimos el trigger que ejecutará el trabajo cada 14 minutos
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 */14 * * * ?")).build();

			// Programamos el job con el trigger
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException e) {
			e.printStackTrace();
    }

  }
	}


