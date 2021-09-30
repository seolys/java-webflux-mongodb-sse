package seol.study.webflux.websocket;

import java.time.Duration;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		// 1)
//		return session.receive() // receive: 인바운드 메세지 스트림에 접근하고 커넥션을 닫으면 완료한다.
//				.map(it -> {
//					log.info("[receive] message={}", it.getPayloadAsText());
//					return it.getPayloadAsText() + "응답";
//				})
//				.then();

		// 2)
//		Flux<WebSocketMessage> output = session.receive() // receive: 인바운드 메세지 스트림에 접근하고 커넥션을 닫으면 완료한다.
//				.map(it -> {
//					log.info("[receive] message={}", it.getPayloadAsText());
//					return it.getPayloadAsText() + "응답";
//				})
//				.map(message -> session.textMessage("Echo: " + message));

		// 3)
		Flux<WebSocketMessage> output = session.receive()
				.map(it -> {
					log.info("[receive] message={}", it.getPayloadAsText());
					return Flux.interval(Duration.ofSeconds(3L))
							.map(s -> session.textMessage("Echo: " + s));
				})
				.flatMap(Function.identity());
//		Flux<WebSocketMessage> output = Flux.interval(Duration.ofSeconds(3L))
//				.map(s -> session.textMessage("Echo: " + s));
		return session.send(output);
	}
}
