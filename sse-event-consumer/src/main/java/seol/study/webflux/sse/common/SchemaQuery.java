package seol.study.webflux.sse.common;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchemaQuery implements GraphQLQueryResolver {

	public String health() {
		return "OK";
	}

}
