package seol.study.webflux.sse.user.document;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import seol.study.webflux.sse.user.document.detail.UserEventChangeAuthDetail;
import seol.study.webflux.sse.user.document.detail.UserEventDuplicateLoginDetail;
import seol.study.webflux.sse.user.document.detail.UserEventLogoutDetail;
import seol.study.webflux.sse.user.document.detail.UserEventRedisTokenExpiredDetail;

@Getter
@RequiredArgsConstructor
public enum EventType {
	DUPLICATE_LOGIN(UserEventDuplicateLoginDetail.class), // 다른브라우저에서 로그인
	LOGOUT(UserEventLogoutDetail.class), // 정상 로그아웃
	CHANGE_AUTH(UserEventChangeAuthDetail.class),
	REDIS_TOKEN_EXPIRED(UserEventRedisTokenExpiredDetail.class);

	private final Class<? extends UserEventDetail> clazz;

	public static EventType from(final String eventType) {
		if (StringUtils.isBlank(eventType)) {
			return null;
		}

		EventType[] values = values();
		for (final EventType value : values) {
			if (value.name().equals(eventType)) {
				return value;
			}
		}
		return null;
	}

	public boolean isLogout() {
		return this == REDIS_TOKEN_EXPIRED;
	}
}
