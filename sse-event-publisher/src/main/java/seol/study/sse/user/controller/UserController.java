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
import seol.study.sse.user.document.PushType;
import seol.study.sse.user.document.UserPush;
import seol.study.sse.user.document.UserPushDetail;
import seol.study.sse.user.dto.UserPushSaveRequestDto;
import seol.study.sse.user.repository.UserPushRepository;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserPushRepository userPushRepository;

	@PostMapping(value = "/save")
	public Mono<UserPush> save(@RequestBody UserPushSaveRequestDto request) {
		log.info("request={}", request);
		UserPushDetail detail = detail(request.getPushType(), request.getDetail());

		UserPush userPush = UserPush.builder(
				request.getUserId(),
				request.getAuthToken(),
				request.getPushType(),
				LocalDateTime.now(),
				detail
		)
				.build();
		return userPushRepository.save(userPush);
	}

	private UserPushDetail detail(final PushType pushType, final Map<String, Object> detail) {
		return new ObjectMapper().convertValue(detail, pushType.getClazz());
	}

}
