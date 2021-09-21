package seol.study.sse.user.gql;

import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.RestController;
import seol.study.sse.user.gql.userpush.UserPush;
import seol.study.sse.user.service.UserPushGqlService;

@RestController
@RequiredArgsConstructor
class UserPushSubscriptionResolver implements GraphQLSubscriptionResolver {

	private final UserPushGqlService userPushGqlService;

	public Publisher<UserPush> userEvent(String authToken) {
		return userPushGqlService.userEvent(authToken);
	}
}
