package seol.study.webflux.sse.user.document;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "userEvent")
public class UserEvent {

	@Id
	private String eventId; // 이벤트ID

	private String userId; // 사용자ID

	private String authToken; // 인증토큰

	private EventType eventType; // 푸시 타입

	private LocalDateTime createdAt; // 생성시간

	//	private BsonDocument detail;
	private Map<String, Object> detail;

}
