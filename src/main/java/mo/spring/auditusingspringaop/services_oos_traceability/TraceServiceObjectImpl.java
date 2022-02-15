package mo.spring.auditusingspringaop.services_oos_traceability;

import mo.spring.auditusingspringaop.traceability.traces.Trace;
import mo.spring.auditusingspringaop.traceability.traces.info.UserInfo;
import mo.spring.auditusingspringaop.utils.IUtilsGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class TraceServiceObjectImpl implements ITraceServiceObjectLong{
    @Autowired
    private UserInfo userInfo;

    @Autowired
    private IUtilsGeneric utilsGeneric;

    @Value("${tracer-service-url}")
    private String tracerServiceURI;

    @Override
    public void traceAfterCreate(Class<?> tracingObejectClass, Object object, Long objectId, String action, String actionInfo) {
        Trace trace = this.buildTrace(object,
                tracingObejectClass.getName(),
                objectId,
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());

        utilsGeneric.sendTraceUsingWebClient(URI.create(tracerServiceURI + "/traces"), trace).subscribe();
    }

    @Override
    public void traceAfterUpdate(Class<?> tracingObejectClass, Object object, Long objectId, String action, String actionInfo) {
        Trace trace = this.buildTrace(object,
                tracingObejectClass.getName(),
                objectId,
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());
//
        utilsGeneric.sendTraceUsingWebClient(URI.create(tracerServiceURI + "/traces"), trace).subscribe();
    }

    @Override
    public void traceAfterDelete(Class<?> tracingObjectClass, Object object, String action, String actionInfo, Object[] args) {
        Trace trace = this.buildTrace(object,
                tracingObjectClass.getName(),
                (Long)args[0],
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());
// //
        utilsGeneric.sendTraceUsingWebClient(URI.create(tracerServiceURI + "/traces"), trace).subscribe();
    }
}
