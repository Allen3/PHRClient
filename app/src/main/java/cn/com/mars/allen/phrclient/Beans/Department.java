package cn.com.mars.allen.phrclient.Beans;

/**
 * Created by Allen on 2015/7/28.
 */
public class Department {
    private Integer dep_id = null;
    private String dep_name = null;
    private Integer hid = null;

    public Department() {
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public Integer getHid() {
        return hid;
    }

    @Override
    public String toString() {
        return dep_name;
    }
}
