package seol.study.webflux.sse.user.gql.userevent;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import seol.study.webflux.sse.user.document.EventType;
import seol.study.webflux.sse.user.document.UserEventDetail;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEvent {

	private String eventId;

	private EventType eventType;

	private LocalDateTime createdAt;

	private UserEventDetail detail;


	public static UserEvent from(seol.study.webflux.sse.user.document.UserEvent userEvent) {
		return UserEvent.builder()
				.eventId(userEvent.getEventId())
				.eventType(userEvent.getEventType())
				.createdAt(userEvent.getCreatedAt())
				.detail(detail(userEvent.getEventType(), userEvent.getDetail()))
				.build();
	}

	private static UserEventDetail detail(final EventType eventType, final Map<String, Object> detail) {
		return new ObjectMapper().convertValue(detail, eventType.getClazz());
	}

}
