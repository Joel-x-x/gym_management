package com.gym.model;

public class Cuenta {
    int id;
    String nombre_empresa;
    String logo_empresa;
    String imagen_perfil;
    int administrador_id;

public Cuenta(){
this.nombre_empresa="";
this.id=0;
this.logo_empresa="";
this.imagen_perfil="";
this.administrador_id=0;

}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getNombre_empresa() {
    return nombre_empresa;
}

public void setNombre_empresa(String nombre_empresa) {
    this.nombre_empresa = nombre_empresa;
}

public String getLogo_empresa() {
    return logo_empresa;
}

public void setLogo_empresa(String logo_empresa) {
    this.logo_empresa = logo_empresa;
}

public String getImagen_perfil() {
    return imagen_perfil;
}

public void setImagen_perfil(String imagen_perfil) {
    this.imagen_perfil = imagen_perfil;
}

public int getAdministrador_id() {
    return administrador_id;
}

public void setAdministrador_id(int administrador_id) {
    this.administrador_id = administrador_id;
}

}