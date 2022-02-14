package mo.spring.auditusingspringaop.traceability.strategies.oos.services;

import mo.spring.auditusingspringaop.traceability.traces.Trace;

public interface ITraceServiceObject<IdType>{
    void traceAfterCreate(Class<?> tracingObejectClass, Object object,IdType objectId, String action, String actionInfo);

    void traceAfterUpdate(Class<?> tracingObejectClass, Object object,IdType objectId, String action, String actionInfo);

    void traceAfterDelete(Class<?> tracingObjectClass, Object object, String action, String actionInfo, Object[] args);

    default Trace buildTrace(Object entityState, String entityClassName, Long entityId, String action, String actionInfo, Long userId, String ipAddress){
        return new Trace.Builder(entityState, entityClassName, (Long)entityId)
                .action(action)
                .actionInfo(actionInfo)
                .userId(userId)
                .ipAddress(ipAddress)
                .build();
    }
}
