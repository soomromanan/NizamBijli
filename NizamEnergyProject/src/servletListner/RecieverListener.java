package servletListner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import schedule.MessageRecieverThread;

@WebListener
public class RecieverListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("RecieverListener.contextInitialized()");
		ScheduledExecutorService as = Executors
				.newSingleThreadScheduledExecutor();
		as.scheduleAtFixedRate(new MessageRecieverThread(), 0, 1,
				TimeUnit.MINUTES);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// scheduler.shutdownNow();
	}

}