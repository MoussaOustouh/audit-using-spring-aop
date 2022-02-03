package mo.spring.auditusingspringaop.traceability_services.impl;

import mo.spring.auditusingspringaop.dto.AddressDTO;
import mo.spring.auditusingspringaop.traceability.traces.Trace;
import mo.spring.auditusingspringaop.traceability.traces.info.UserInfo;
import mo.spring.auditusingspringaop.traceability_services.ITraceAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraceAddressServiceImpl implements ITraceAddressService {

    @Autowired
    private UserInfo userInfo;

    @Override
    public void traceAfterCreate(AddressDTO object, String action, String actionInfo) {
        Trace trace = this.buildTrace(object,
                object.getClass().getName(),
                object.getId(),
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());
    }

    @Override
    public void traceAfterUpdate(AddressDTO object, String action, String actionInfo) {
        Trace trace = this.buildTrace(object,
                object.getClass().getName(),
                object.getId(),
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());
    }

    @Override
    public void traceAfterDelete(AddressDTO object, String action, String actionInfo, Object[] args) {
        Trace trace = this.buildTrace(object,
                AddressDTO.class.getName(),
                (Long)args[0],
                action,
                actionInfo,
                userInfo.getUserId(),
                userInfo.getIpAddress());
    }
}
