package mo.spring.auditusingspringaop.traceability_services.impl;

import mo.spring.auditusingspringaop.dto.MemberDTO;
import mo.spring.auditusingspringaop.traceability.traces.Trace;
import mo.spring.auditusingspringaop.traceability.traces.info.UserInfo;
import mo.spring.auditusingspringaop.traceability_services.ITraceMemberService;
import mo.spring.auditusingspringaop.utils.IUtilsGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class TraceMemberServiceImpl implements ITraceMemberService {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private IUtilsGeneric utilsGeneric;

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
//
        utilsGeneric.sendTraceUsingWebClient(URI.create(tracerServiceURI + "/traces"), trace).subscribe();
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

        utilsGeneric.sendTraceUsingWebClient(URI.create(tracerServiceURI + "/traces"), trace).subscribe();
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

        utilsGeneric.sendTraceUsingWebClient(URI.create(tracerServiceURI + "/traces"), trace).subscribe();
    }
}
