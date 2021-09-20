package seol.study.sse.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import seol.study.sse.user.dto.UserPushResponseDto;
import seol.study.sse.user.service.UserPushService;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserPushService userPushService;

	@GetMapping(value = "/connect/{authToken}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<UserPushResponseDto> connect(@PathVariable String authToken) {
		return userPushService.connect(authToken);
	}
	
}
