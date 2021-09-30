package seol.study.webflux.sse.user.repository;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import seol.study.webflux.sse.user.document.UserEvent;

public interface UserEventRepository extends ReactiveMongoRepository<UserEvent, String> {

	@Tailable
	@Query("{ $and: [ { $or: [ { userId: ?0 }, { authToken: ?1 }] }, { createdAt: { $gt: ?2 } } ]}")
	Flux<UserEvent> findByAuthTokenAndGreaterThanCreatedAt(String userId, String authToken, LocalDateTime createdAt);

	@Query("{ $and: [ { $or: [ { userId: ?0 }, { authToken: ?1 }] }, { createdAt: { $gt: ?2 } } ]}")
	Flux<UserEvent> findByAuthTokenAndGreaterThanCreatedAtNotTailable(String userId, String authToken, LocalDateTime createdAt);
}
