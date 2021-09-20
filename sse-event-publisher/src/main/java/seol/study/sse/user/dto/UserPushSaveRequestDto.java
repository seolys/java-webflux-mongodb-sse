package seol.study.sse.user.dto;

import java.util.Map;
import lombok.Data;
import seol.study.sse.user.document.PushType;

@Data
public class UserPushSaveRequestDto {

	private String userId; // 사용자ID

	private String authToken; // 인증토큰

	private PushType pushType; // 푸시 타입

	private Map<String, Object> detail;

}
