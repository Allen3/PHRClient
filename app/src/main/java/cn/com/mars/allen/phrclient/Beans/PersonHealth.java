package cn.com.mars.allen.phrclient.Beans;

/**
 * Created by Allen on 2015/7/29.
 */
public class PersonHealth {
    private String person_id = null;
    private String diag_date = null;
    private String prompt_date = null;
    private String drug_name = null;
    private String drug_dose = null;

    public PersonHealth() {
    }

    public void setPrompt_date(String prompt_date) {
        this.prompt_date = prompt_date;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public void setDrug_dose(String drug_dose) {
        this.drug_dose = drug_dose;
    }

    public void setDiag_date(String diag_date) {
        this.diag_date = diag_date;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPrompt_date() {
        return prompt_date;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public String getDrug_dose() {
        return drug_dose;
    }

    public String getDiag_date() {
        return diag_date;
    }

    public String getPerson_id() {
        return person_id;
    }

    @Override
    public String toString() {
        return "Drug name: " + drug_name + '\n' + "Drug dose: " + drug_dose  + '\n' + "Diagose date: " + diag_date;
    }
}
