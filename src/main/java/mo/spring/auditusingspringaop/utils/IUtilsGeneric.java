package mo.spring.auditusingspringaop.utils;

import mo.spring.auditusingspringaop.traceability.traces.Trace;
import reactor.core.publisher.Mono;

import java.net.URI;

public interface IUtilsGeneric {
    Mono<Trace> sendTraceUsingWebClient(URI uri, Trace trace);
}
