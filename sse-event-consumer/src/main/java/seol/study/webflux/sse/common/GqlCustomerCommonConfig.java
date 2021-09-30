package seol.study.webflux.sse.common;

import graphql.kickstart.tools.SchemaParserDictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seol.study.webflux.sse.user.document.detail.UserEventChangeAuthDetail;
import seol.study.webflux.sse.user.document.detail.UserEventDuplicateLoginDetail;
import seol.study.webflux.sse.user.document.detail.UserEventLogoutDetail;

@Configuration
public class GqlCustomerCommonConfig {

	@Bean
	public SchemaParserDictionary schemaParserDictionary() {
		return new SchemaParserDictionary()
				.add(UserEventChangeAuthDetail.class)
				.add(UserEventDuplicateLoginDetail.class)
				.add(UserEventLogoutDetail.class)
//				.add(UserEventNullTestDetail.class)
				;
	}

}
