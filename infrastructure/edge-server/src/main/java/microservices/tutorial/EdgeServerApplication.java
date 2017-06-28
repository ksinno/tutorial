package microservices.tutorial;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.Filter;

@SpringBootApplication
@EnableZuulProxy
@Controller
public class EdgeServerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EdgeServerApplication.class).web(true).run(args);
	}

	@Bean
	public Filter logFilter() {
		// TODO: Extract bean-def to Util-component!
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(5120);
		return filter;
	}

	@Bean
	public LoggingFilter simpleFilter() {
		return new LoggingFilter();
	}

}
