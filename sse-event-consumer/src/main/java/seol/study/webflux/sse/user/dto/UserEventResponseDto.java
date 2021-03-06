package seol.study.webflux.sse.user.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import seol.study.webflux.sse.user.document.EventType;
import seol.study.webflux.sse.user.document.UserEvent;
import seol.study.webflux.sse.user.document.UserEventDetail;

@Data
@Builder
public class UserEventResponseDto {

	private String eventId; // event ID

	private String userId;

	private EventType eventType; // 푸시 타입

	private LocalDateTime createdAt; // 생성시간

	//	private Map<String, Object> detail;
	private UserEventDetail detail;

	public static UserEventResponseDto from(UserEvent userEvent) {
		return UserEventResponseDto.builder()
				.eventId(userEvent.getEventId())
				.userId(userEvent.getUserId())
				.eventType(userEvent.getEventType())
				.createdAt(userEvent.getCreatedAt())
				.detail(detail(userEvent.getEventType(), userEvent.getDetail()))
				.build();
	}

	private static UserEventDetail detail(final EventType eventType, final Map<String, Object> detail) {
		return new ObjectMapper().convertValue(detail, eventType.getClazz());
	}

}
