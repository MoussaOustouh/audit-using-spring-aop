package mo.spring.auditusingspringaop.utils;

import mo.spring.auditusingspringaop.traceability.traces.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class UtilsGenericImpl implements IUtilsGeneric{
    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Trace> sendTraceUsingWebClient(URI uri, Trace trace) {
        return webClient.post().uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(trace), Trace.class)
                .retrieve()
                .bodyToMono(Trace.class);
    }
}
