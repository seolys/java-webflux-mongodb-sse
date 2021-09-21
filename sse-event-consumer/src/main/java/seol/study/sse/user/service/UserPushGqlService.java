package seol.study.sse.user.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.scheduler.Schedulers;
import seol.study.sse.user.gql.userpush.UserPush;
import seol.study.sse.user.repository.UserPushRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserPushGqlService {

	private final UserPushRepository userPushRepository;

	public Publisher<UserPush> userEvent(final String authToken) {
		return userPushRepository.findByAuthTokenAndGreaterThanCreatedAt(authToken, LocalDateTime.now())
				.map(UserPush::from)
				.subscribeOn(Schedulers.boundedElastic())
				.log();
	}
}
