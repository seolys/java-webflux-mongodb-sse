package seol.study.sse.user.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import seol.study.sse.user.dto.UserPushResponseDto;
import seol.study.sse.user.repository.UserPushRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserPushService {

	private final UserPushRepository userPushRepository;

	public Flux<UserPushResponseDto> connect(final String authToken) {
		// TODO: Redis에 토큰 검증.

		return userPushRepository.findByAuthTokenAndGreaterThanCreatedAt(authToken, LocalDateTime.now())
				.map(UserPushResponseDto::from)
				.subscribeOn(Schedulers.boundedElastic())
				.log();
	}

}
