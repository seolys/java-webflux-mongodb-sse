package seol.study.webflux.sse.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import seol.study.webflux.sse.user.dto.UserEventResponseDto;
import seol.study.webflux.sse.user.service.UserEventService;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

	private final ApplicationContext context;
	private final UserEventService userEventService;

	@GetMapping(value = "/connect/{authToken}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<UserEventResponseDto> connect(@PathVariable String authToken) {
		log.info("authToken = {}", authToken);
		return userEventService.connect(authToken);
	}


	@GetMapping(value = "/test_traffic/{authToken}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<UserEventResponseDto> test1(@PathVariable String authToken) {
		log.info("authToken = {}", authToken);

		Flux<UserEventResponseDto> flux = Flux.empty();
		int loopSize = 48;
		for (int i = 1; i <= loopSize; i++) {
			final int finalI = i;
			log.info("{}번째", i);
			flux = userEventService.connect(authToken, "test" + i)
//					.doOnNext((e) -> {
//						if (finalI == loopSize) {
//							log.info(finalI + ". next={}", e);
//						}
//					})
					.mergeWith(flux);
		}
		return flux;
	}

	@GetMapping(value = "/test_interval/{authToken}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<UserEventResponseDto> test2(@PathVariable String authToken) {
		log.info("authToken = {}", authToken);

		Flux<UserEventResponseDto> flux = Flux.empty();
		int loopSize = 500;
		for (int i = 1; i <= loopSize; i++) {
			final int finalI = i;
			log.info("{}번째", i);
			flux = userEventService.connectInterval(authToken, "test" + i)
					.doOnNext((e) -> {
						if (finalI == loopSize) {
							log.info(finalI + ". next={}", e);
						}
					})
					.mergeWith(flux);
		}
		return flux;
	}

}
