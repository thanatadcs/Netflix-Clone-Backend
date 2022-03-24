package muzoo.io.sscsw.webappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NetflixCloneAndChillWebappBackendApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NetflixCloneAndChillWebappBackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(NetflixCloneAndChillWebappBackendApplication.class, args);
	}

}
