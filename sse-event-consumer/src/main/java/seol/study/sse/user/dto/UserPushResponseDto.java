package seol.study.sse.user.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import seol.study.sse.user.document.PushType;
import seol.study.sse.user.document.UserPush;
import seol.study.sse.user.document.UserPushDetail;

@Data
@Builder
public class UserPushResponseDto {

	private String id; // PUSH ID

	private PushType pushType; // 푸시 타입

	private LocalDateTime createdAt; // 생성시간

	//	private Map<String, Object> detail;
	private UserPushDetail detail;

	public static UserPushResponseDto from(UserPush userPush) {
		return UserPushResponseDto.builder()
				.id(userPush.getId())
				.pushType(userPush.getPushType())
				.createdAt(userPush.getCreatedAt())
				.detail(detail(userPush.getPushType(), userPush.getDetail()))
				.build();
	}

	private static UserPushDetail detail(final PushType pushType, final Map<String, Object> detail) {
		return new ObjectMapper().convertValue(detail, pushType.getClazz());
	}

}
