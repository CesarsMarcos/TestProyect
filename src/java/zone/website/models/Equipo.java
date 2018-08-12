/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone.website.models;

/**
 *
 * @author César
 */
public class Equipo {

    private Integer idEquipo;
    private String descripcion;

    public Equipo() {
    }

    public Equipo(Integer idEquipo, String descripcion) {
        this.idEquipo = idEquipo;
        this.descripcion = descripcion;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
    
}
