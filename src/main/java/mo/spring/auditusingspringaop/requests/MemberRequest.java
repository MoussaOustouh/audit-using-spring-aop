package mo.spring.auditusingspringaop.requests;

import java.io.Serializable;
import java.util.Set;

public class MemberRequest implements Serializable {
    private static final long serialVersionUID = -2646008113624234225L;

    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Set<AddressRequest> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<AddressRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressRequest> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "MemberRequest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
