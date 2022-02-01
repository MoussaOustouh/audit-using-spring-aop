package mo.spring.auditusingspringaop.traceability.services;

import mo.spring.auditusingspringaop.traceability.traces.Trace;

public interface ITraceService<T> {
    void traceAfterCreate(T object, String action, String actionInfo);

    void traceAfterUpdate(T object, String action, String actionInfo);

    void traceAfterDelete(T object, String action, String actionInfo, Object[] args);

    default Trace buildTrace(T entityState, String entityClassName, Long entityId, String action, String actionInfo, Long userId, String ipAddress){
        return new Trace.Builder(entityState, entityClassName, (Long)entityId)
                .action(action)
                .actionInfo(actionInfo)
                .userId(userId)
                .ipAddress(ipAddress)
                .build();
    }
}
