package seol.study.sse.user.repository;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import seol.study.sse.user.document.UserPush;

public interface UserPushRepository extends ReactiveMongoRepository<UserPush, String> {

	@Tailable
	@Query("{ authToken: ?0, createdAt: {$gt: ?1} }")
	Flux<UserPush> findByAuthTokenAndGreaterThanCreatedAt(String authToken, LocalDateTime createdAt);

}
