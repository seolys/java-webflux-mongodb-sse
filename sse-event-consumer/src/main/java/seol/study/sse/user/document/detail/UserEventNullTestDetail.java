package seol.study.sse.user.document.detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import seol.study.sse.user.document.UserEventDetail;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEventNullTestDetail implements UserEventDetail {

//	private String dummy;
}
