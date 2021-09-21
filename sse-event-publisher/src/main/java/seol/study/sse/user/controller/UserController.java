package seol.study.sse.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import seol.study.sse.user.document.EventType;
import seol.study.sse.user.document.UserEvent;
import seol.study.sse.user.document.UserEventDetail;
import seol.study.sse.user.dto.UserEventSaveRequestDto;
import seol.study.sse.user.repository.UserEventRepository;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserEventRepository userEventRepository;

	@PostMapping(value = "/save")
	public Mono<UserEvent> save(@RequestBody UserEventSaveRequestDto request) {
		log.info("request={}", request);
		UserEventDetail detail = detail(request.getEventType(), request.getDetail());

		UserEvent userEvent = UserEvent.builder(
				request.getUserId(),
				request.getAuthToken(),
				request.getEventType(),
				LocalDateTime.now(),
				detail
		)
				.build();
		return userEventRepository.save(userEvent);
	}

	private UserEventDetail detail(final EventType eventType, final Map<String, Object> detail) {
		return new ObjectMapper().convertValue(detail, eventType.getClazz());
	}

}
