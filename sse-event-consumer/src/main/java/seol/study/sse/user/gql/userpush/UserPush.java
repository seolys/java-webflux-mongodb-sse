package seol.study.sse.user.gql.userpush;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import seol.study.sse.user.document.PushType;
import seol.study.sse.user.document.UserPushDetail;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPush {

	private String id;

	private PushType pushType;

	private LocalDateTime createdAt;

	private UserPushDetail detail;


	public static UserPush from(seol.study.sse.user.document.UserPush userPush) {
		return UserPush.builder()
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
