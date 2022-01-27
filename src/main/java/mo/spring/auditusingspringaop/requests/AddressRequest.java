package mo.spring.auditusingspringaop.requests;

import java.io.Serializable;

public class AddressRequest implements Serializable {
    private static final long serialVersionUID = -663854596866598561L;

    private Long id;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private MemberRequest member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public MemberRequest getMember() {
        return member;
    }

    public void setMember(MemberRequest member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "AddressRequest{" +
                "id=" + id +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", member=" + member +
                '}';
    }
}
