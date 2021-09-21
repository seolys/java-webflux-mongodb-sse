package seol.study.sse.user.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import seol.study.sse.user.dto.UserEventResponseDto;
import seol.study.sse.user.repository.UserEventRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserEventService {

	private final UserEventRepository userEventRepository;

	public Flux<UserEventResponseDto> connect(final String authToken) {
		// TODO: Redis에 토큰 검증.

		return userEventRepository.findByAuthTokenAndGreaterThanCreatedAt(authToken, LocalDateTime.now())
				.map(UserEventResponseDto::from)
				.subscribeOn(Schedulers.boundedElastic())
				.log();
	}


}
