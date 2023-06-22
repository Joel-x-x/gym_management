package com.gym.model;

public class Plan_has_clase {
    int id;
    int plan_id;
    int clase_id;
    Float precio_total;
    Float valor_extra;
    public Plan_has_clase(int id, int plan_id, int clase_id, Float precio_total, Float valor_extra) {
        this.id = id;
        this.plan_id = plan_id;
        this.clase_id = clase_id;
        this.precio_total = precio_total;
        this.valor_extra = valor_extra;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPlan_id() {
        return plan_id;
    }
    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }
    public int getClase_id() {
        return clase_id;
    }
    public void setClase_id(int clase_id) {
        this.clase_id = clase_id;
    }
    public Float getPrecio_total() {
        return precio_total;
    }
    public void setPrecio_total(Float precio_total) {
        this.precio_total = precio_total;
    }
    public Float getValor_extra() {
        return valor_extra;
    }
    public void setValor_extra(Float valor_extra) {
        this.valor_extra = valor_extra;
    }
    
}
