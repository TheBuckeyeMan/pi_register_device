package pi.device.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pi.device.register.service.RegisterDevice;

@SpringBootApplication
public class RegisterApplication {
	private static final Logger log = LoggerFactory.getLogger(RegisterApplication.class);
	public static void main(String[] args) {
		int exitCode = 0;
        ConfigurableApplicationContext context = null;

		log.info("Starting the Register Pi Device Application...");
		try{
			context = SpringApplication.run(RegisterApplication.class, args);

			//Run the Application
			RegisterDevice registerDevice = context.getBean(RegisterDevice.class);
			registerDevice.register();
			log.info("Device Registeration Completed Successfully!");


		} catch (RuntimeException e){
			log.error("Fatal Error occured while attempting to boot the application", e.getMessage(), e);
			exitCode = 1;
		} finally {
			if (context != null){
				SpringApplication.exit(context);
			}
			log.info("Exiting the Service with exit code: " + exitCode);
			System.exit(exitCode);
		}
	}

}
