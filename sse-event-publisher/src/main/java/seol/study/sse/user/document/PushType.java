package seol.study.sse.user.document;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import seol.study.sse.user.document.detail.UserPushChangeAuthDetail;
import seol.study.sse.user.document.detail.UserPushDuplicateLoginDetail;
import seol.study.sse.user.document.detail.UserPushLogoutDetail;

@Getter
@RequiredArgsConstructor
public enum PushType {
	DUPLICATE_LOGIN(UserPushDuplicateLoginDetail.class), // 다른브라우저에서 로그인
	LOGOUT(UserPushLogoutDetail.class), // 정상 로그아웃
	CHANGE_AUTH(UserPushChangeAuthDetail.class),
	NULL_TEST(UserPushChangeAuthDetail.class),
	;

	private final Class<? extends UserPushDetail> clazz;

	public static PushType from(final String pushType) {
		if (StringUtils.isBlank(pushType)) {
			return null;
		}

		PushType[] values = values();
		for (final PushType value : values) {
			if (value.name().equals(pushType)) {
				return value;
			}
		}
		return null;
	}
}
