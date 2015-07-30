package cn.com.mars.allen.phrclient.Beans;

/**
 * Created by Allen on 2015/7/28.
 */
public class Hospital {
    private Integer hid = null;
    private String hName = null;
    private String hAddress = null;
    private String hProfile = null;

    public Hospital() {
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public void sethAddress(String hAddress) {
        this.hAddress = hAddress;
    }

    public void sethProfile(String hProfile) {
        this.hProfile = hProfile;
    }

    public Integer getHid() {
        return hid;
    }

    public String gethName() {
        return hName;
    }

    public String gethAddress() {
        return hAddress;
    }

    public String gethProfile() {
        return hProfile;
    }

    @Override
    public String toString() {
        return "Name = " + hName + ", Address = " + hAddress;
    }
}
