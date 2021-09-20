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
@Document(collection = "userPush")
public class UserPush {

	@Id
	private String id; // PUSH ID

	private String userId; // 사용자ID

	private String authToken; // 인증토큰

	private PushType pushType; // 푸시 타입

	private LocalDateTime createdAt; // 생성시간

	private Map<String, Object> detail;

	public static UserPushBuilder builder(
			String userId,
			String authToken,
			PushType pushType,
			LocalDateTime createdAt,
			UserPushDetail userPushDetail
	) {
		return UserPush.documentBuilder()
				.userId(userId)
				.authToken(authToken)
				.pushType(pushType)
				.createdAt(createdAt)
				.detail(new ObjectMapper().convertValue(userPushDetail, Map.class));
	}

}
