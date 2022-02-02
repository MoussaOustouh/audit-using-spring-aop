package mo.spring.auditusingspringaop.traceability.traces;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Trace  implements Serializable {
    private static final long serialVersionUID = 4790844501835793234L;

    private Long userId;
    private String ipAddress;

    private Object entityState;
    private String entityClassName;
    private Long entityId;

    private String action;
    private String actionInfo;

    private LocalDateTime tracedAt;

    public Trace() {

    }

    public Trace(Builder builder){
        this.userId = builder.userId;
        this.ipAddress = builder.ipAddress;
        this.entityState = builder.entityState;
        this.entityClassName  = builder.entityClassName;
        this.entityId = builder.entityId;
        this.action = builder.action;
        this.actionInfo = builder.actionInfo;
        this.tracedAt = builder.tracedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Object getEntityState() {
        return entityState;
    }

    public void setEntityState(Object entityState) {
        this.entityState = entityState;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(String actionInfo) {
        this.actionInfo = actionInfo;
    }

    public LocalDateTime getTracedAt() {
        return tracedAt;
    }

    public void setTracedAt(LocalDateTime tracedAt) {
        this.tracedAt = tracedAt;
    }

    @Override
    public String toString() {
        return "Trace{" +
                "userId=" + userId +
                ", ipAddress='" + ipAddress + '\'' +
                ", entityState=" + entityState +
                ", entityClassName='" + entityClassName + '\'' +
                ", entityId=" + entityId +
                ", action='" + action + '\'' +
                ", actionInfo='" + actionInfo + '\'' +
                ", tracedAt='" + tracedAt + '\'' +
                '}';
    }

    public static class Builder {
        private Long userId;
        private String ipAddress;

        private final Object entityState;
        private final String entityClassName;
        private final Long entityId;

        private String action;
        private String actionInfo;
        private LocalDateTime tracedAt;


        public Builder(Object entityState, String entityClassName, Long entityId) {
            this.entityState = entityState;
            this.entityClassName = entityClassName;
            this.entityId = entityId;
            this.tracedAt = LocalDateTime.now();
        }

        public Builder userId(Long userId){
            this.userId = userId;
            return this;
        }

        public Builder ipAddress(String ipAddress){
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder action(String action){
            this.action = action;
            return this;
        }

        public Builder actionInfo(String actionInfo){
            this.actionInfo = actionInfo;
            return this;
        }

        public Builder tracedAt(LocalDateTime tracedAt){
            this.tracedAt = tracedAt;
            return this;
        }

        public Trace build(){
            return new Trace(this);
        }
    }
}
