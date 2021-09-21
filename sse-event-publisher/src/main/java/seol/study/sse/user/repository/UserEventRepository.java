package seol.study.sse.user.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import seol.study.sse.user.document.UserEvent;

public interface UserEventRepository extends ReactiveMongoRepository<UserEvent, String> {
	
}
