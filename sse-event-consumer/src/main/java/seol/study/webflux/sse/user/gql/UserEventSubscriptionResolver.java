package seol.study.webflux.sse.user.gql;

import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.RestController;
import seol.study.webflux.sse.user.gql.userevent.UserEvent;
import seol.study.webflux.sse.user.service.UserEventGqlService;

@Slf4j
@RestController
@RequiredArgsConstructor
class UserEventSubscriptionResolver implements GraphQLSubscriptionResolver {

	private final UserEventGqlService userEventGqlService;

	public Publisher<UserEvent> userEvent(String authToken) {
		log.info("userEvent start");
		return userEventGqlService.userEvent(authToken);
	}
}
