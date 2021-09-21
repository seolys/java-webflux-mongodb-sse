package seol.study.sse.common;

import graphql.kickstart.tools.SchemaParserDictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seol.study.sse.user.document.detail.UserPushChangeAuthDetail;
import seol.study.sse.user.document.detail.UserPushDuplicateLoginDetail;
import seol.study.sse.user.document.detail.UserPushLogoutDetail;
import seol.study.sse.user.document.detail.UserPushNullTestDetail;

@Configuration
public class GqlCustomerCommonConfig {

	@Bean
	public SchemaParserDictionary schemaParserDictionary() {
		return new SchemaParserDictionary()
				.add(UserPushChangeAuthDetail.class)
				.add(UserPushDuplicateLoginDetail.class)
				.add(UserPushLogoutDetail.class)
				.add(UserPushNullTestDetail.class)
				;
	}

}
