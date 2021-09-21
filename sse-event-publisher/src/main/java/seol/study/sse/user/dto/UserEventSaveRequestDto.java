package seol.study.sse.user.dto;

import java.util.Map;
import lombok.Data;
import seol.study.sse.user.document.EventType;

@Data
public class UserEventSaveRequestDto {

	private String userId; // 사용자ID

	private String authToken; // 인증토큰

	private EventType eventType; // 푸시 타입

	private Map<String, Object> detail;

}
