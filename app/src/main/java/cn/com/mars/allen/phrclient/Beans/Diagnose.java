package cn.com.mars.allen.phrclient.Beans;

/**
 * Created by Allen on 2015/7/29.
 */
public class Diagnose {
    private String person_id = null;
    private Integer doctor_id = null;
    private String diagnose_date = null;

    public Diagnose() {
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDiagnose_date(String diagnose_date) {
        this.diagnose_date = diagnose_date;
    }

    public String getPerson_id() {
        return person_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public String getDiagnose_date() {
        return diagnose_date;
    }
}
