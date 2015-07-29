package cn.com.mars.allen.phrclient.Beans;

/**
 * Created by Allen on 2015/7/29.
 */
public class Equipment {
    private Integer equip_id = null;
    private String equip_name = null;
    private String equip_price = null;

    public Equipment() {
    }

    public void setEquip_id(Integer equip_id) {
        this.equip_id = equip_id;
    }

    public void setEquip_name(String equip_name) {
        this.equip_name = equip_name;
    }

    public void setEquip_price(String equip_price) {
        this.equip_price = equip_price;
    }

    public Integer getEquip_id() {
        return equip_id;
    }

    public String getEquip_name() {
        return equip_name;
    }

    public String getEquip_price() {
        return equip_price;
    }
}
