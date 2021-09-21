package seol.study.sse.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
//		corsRegistry.addMapping("/**")
////				.allowCredentials(true)
////				.allowedOrigins("*")
//				.allowedOriginPatterns("/**")
//				.allowedHeaders("*")
//				.allowedMethods("*");
		corsRegistry.addMapping("/**").allowedOrigins("*");
	}
}
