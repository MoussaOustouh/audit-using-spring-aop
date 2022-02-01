package mo.spring.auditusingspringaop.traceability.traces;

import java.io.Serializable;

public class Trace  implements Serializable {
    private static final long serialVersionUID = 4790844501835793234L;

    private final Long userId;
    private final String ipAddress;

    private final Object entityState;
    private final String entityClassName;
    private final Long entityId;

    private final String action;
    private final String actionInfo;

    public Trace(Builder builder){
        this.userId = builder.userId;
        this.ipAddress = builder.ipAddress;
        this.entityState = builder.entityState;
        this.entityClassName  = builder.entityClassName;
        this.entityId = builder.entityId;
        this.action = builder.action;
        this.actionInfo = builder.actionInfo;
    }

    public Long getUserId() {
        return userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Object getEntityState() {
        return entityState;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public String getAction() {
        return action;
    }

    public String getActionInfo() {
        return actionInfo;
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


        public Builder(Object entityState, String entityClassName, Long entityId) {
            this.entityState = entityState;
            this.entityClassName = entityClassName;
            this.entityId = entityId;
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

        public Trace build(){
            return new Trace(this);
        }
    }
}
