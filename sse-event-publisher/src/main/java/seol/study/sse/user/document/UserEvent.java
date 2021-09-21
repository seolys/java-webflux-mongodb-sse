package seol.study.sse.user.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(builderMethodName = "documentBuilder", toBuilder = true)
@Document(collection = "userEvent")
public class UserEvent {

	@Id
	private String eventId; // 이벤트 ID

	private String userId; // 사용자ID

	private String authToken; // 인증토큰

	private EventType eventType; // 푸시 타입

	private LocalDateTime createdAt; // 생성시간

	private Map<String, Object> detail;

	public static UserEventBuilder builder(
			String userId,
			String authToken,
			EventType eventType,
			LocalDateTime createdAt,
			UserEventDetail userEventDetail
	) {
		return UserEvent.documentBuilder()
				.userId(userId)
				.authToken(authToken)
				.eventType(eventType)
				.createdAt(createdAt)
				.detail(new ObjectMapper().convertValue(userEventDetail, Map.class));
	}

}
