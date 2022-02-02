package mo.spring.auditusingspringaop.traceability_services.impl;

import mo.spring.auditusingspringaop.dto.MemberDTO;
import mo.spring.auditusingspringaop.traceability.traces.Trace;
import mo.spring.auditusingspringaop.traceability.traces.info.UserInfo;
import mo.spring.auditusingspringaop.traceability_services.ITraceMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TraceMemberServiceImpl implements ITraceMemberService {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private WebClient webClient;

    @Value("${tracer-service-url}")
    private String tracerServiceURI;

    @Override
    public void traceAfterCreate(MemberDTO object, String action, String actionInfo) {
        Trace trace = this.buildTrace(object,
                object.getClass().getName(),
                object.getId(),
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());

        System.out.println("=== Trace ===========================================");
        System.out.println(trace);
        System.out.println("-----------------------------------------------------");

        Mono<Trace> traceMono = webClient.post().uri(tracerServiceURI + "/traces")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(trace), Trace.class)
                .retrieve()
                .bodyToMono(Trace.class);

        traceMono.subscribe();
    }

    @Override
    public void traceAfterUpdate(MemberDTO object, String action, String actionInfo) {
        Trace trace = this.buildTrace(object,
                object.getClass().getName(),
                object.getId(),
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());

        System.out.println("=== Trace ===========================================");
        System.out.println(trace);
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void traceAfterDelete(MemberDTO object, String action, String actionInfo, Object[] args) {
        Trace trace = this.buildTrace(object,
                MemberDTO.class.getName(),
                (Long)args[0],
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());

        System.out.println("=== Trace ===========================================");
        System.out.println(trace);
        System.out.println("-----------------------------------------------------");
    }
}
