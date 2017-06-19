package servletListner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import schedule.TriggersThread;

@WebListener
public class MyAppServletContextListener implements ServletContextListener {

	TriggersThread triggerModule;

	public MyAppServletContextListener() {
		triggerModule = new TriggersThread();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("MyAppServletContextListener.contextInitialized()");
		triggerModule.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		triggerModule.stop();
	}

}
