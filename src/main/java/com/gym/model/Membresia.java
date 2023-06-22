package com.gym.model;

public class Membresia {
    int id;
    String fecha_inicio;
    String fecha_fin;
    int plan_has_clase_id;
    int usuario_id;
    int usuario_cuenta_id;
    public Membresia(int id, String fecha_inicio, String fecha_fin, int plan_has_clase_id, int usuario_id,
            int usuario_cuenta_id) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.plan_has_clase_id = plan_has_clase_id;
        this.usuario_id = usuario_id;
        this.usuario_cuenta_id = usuario_cuenta_id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public String getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public int getPlan_has_clase_id() {
        return plan_has_clase_id;
    }
    public void setPlan_has_clase_id(int plan_has_clase_id) {
        this.plan_has_clase_id = plan_has_clase_id;
    }
    public int getUsuario_id() {
        return usuario_id;
    }
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    public int getUsuario_cuenta_id() {
        return usuario_cuenta_id;
    }
    public void setUsuario_cuenta_id(int usuario_cuenta_id) {
        this.usuario_cuenta_id = usuario_cuenta_id;
    }
    
    
}
