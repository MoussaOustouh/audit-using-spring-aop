package mo.spring.auditusingspringaop.traceability_services.impl;

import mo.spring.auditusingspringaop.dto.MemberDTO;
import mo.spring.auditusingspringaop.traceability.traces.Trace;
import mo.spring.auditusingspringaop.traceability.traces.info.UserInfo;
import mo.spring.auditusingspringaop.traceability_services.ITraceMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraceMemberServiceImpl implements ITraceMemberService {

    @Autowired
    private UserInfo userInfo;

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
