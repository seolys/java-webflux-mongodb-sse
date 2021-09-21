package seol.study.sse.user.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.scheduler.Schedulers;
import seol.study.sse.user.gql.userevent.UserEvent;
import seol.study.sse.user.repository.UserEventRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserEventGqlService {

	private final UserEventRepository userEventRepository;

	public Publisher<UserEvent> userEvent(final String authToken) {
		return userEventRepository.findByAuthTokenAndGreaterThanCreatedAt(authToken, LocalDateTime.now())
				.map(UserEvent::from)
				.subscribeOn(Schedulers.boundedElastic())
				.log();
	}
}
