package seol.study.sse.user.repository;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import seol.study.sse.user.document.UserEvent;

public interface UserEventRepository extends ReactiveMongoRepository<UserEvent, String> {

	@Tailable
	@Query("{ authToken: ?0, createdAt: {$gt: ?1} }")
	Flux<UserEvent> findByAuthTokenAndGreaterThanCreatedAt(String authToken, LocalDateTime createdAt);

}
