package seol.study.sse.user.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import seol.study.sse.user.document.UserEvent;
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

		// TODO: 유효한 토큰이 아닐경우 에러처리
		String userId = "test";

		// 정상 토큰일때 응답
		Flux<UserEvent> userEventFlux = userEventRepository.findByAuthTokenAndGreaterThanCreatedAt(userId, authToken, LocalDateTime.now());
		return userEventFlux
				.map(UserEventResponseDto::from)
				.takeUntil(dto -> dto.getEventType().isLogout())
				.subscribeOn(Schedulers.boundedElastic())
				.log();
	}

	public Flux<UserEventResponseDto> connect(final String authToken, String userId) {
		// 정상 토큰일때 응답
		Flux<UserEvent> userEventFlux = userEventRepository.findByAuthTokenAndGreaterThanCreatedAt(userId, authToken, LocalDateTime.now());
		return userEventFlux
				.map(UserEventResponseDto::from)
				.takeUntil(dto -> dto.getEventType().isLogout())
				.subscribeOn(Schedulers.boundedElastic())
				.log();
	}

	public Flux<UserEventResponseDto> connectInterval(final String authToken, String userId) {
		final AtomicReference<LocalDateTime> now = new AtomicReference<>(LocalDateTime.now());
		return Flux.interval(Duration.ofSeconds(1L))
				.map(e -> userEventRepository.findByAuthTokenAndGreaterThanCreatedAtNotTailable(userId, authToken, now.get()))
				.flatMap(Function.identity())
				.map(UserEventResponseDto::from)
				.takeUntil(dto -> dto.getEventType().isLogout())
				.subscribeOn(Schedulers.boundedElastic())
				.doOnNext((e) -> now.set(LocalDateTime.now()))
				.log();
	}

//	public Predicate<UserEventResponseDto> redisTokenExpiredDistinct() {
//		AtomicBoolean atomicBoolean = new AtomicBoolean(false);
//		AtomicInteger atomicInteger = new AtomicInteger(0);
//		return dto -> {
//			System.out.println("atomicBoolean=" + atomicBoolean);
//			System.out.println("atomicInteger=" + atomicInteger);
//			if (atomicBoolean.get()) { // false일때 false리턴.
//				return true;
//			}
//			if (dto.getEventType() != REDIS_TOKEN_EXPIRED) { // 토큰만료가 아닌경우 그대로 리턴.
//				return false;
//			}
//			// 한번만 내보내고 그다음부터 false
//			log.info("야호!!!!! 세션만료다!!");
//			atomicInteger.incrementAndGet();
//			return atomicBoolean.getAndSet(true);
//		};
//	}

}


