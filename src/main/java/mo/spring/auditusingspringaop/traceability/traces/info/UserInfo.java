package mo.spring.auditusingspringaop.traceability.traces.info;

public class UserInfo {
    private Long userId;
    private String ipAddress;

    public UserInfo() {
    }

    public UserInfo(Long userId, String ipAddress) {
        this.userId = userId;
        this.ipAddress = ipAddress;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
