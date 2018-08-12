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
public class Hincha {
    
    private Integer idHincha;
    private String descripcion;
    private Integer idEquipo;

    public Hincha() {
    }

    public Hincha(Integer idHincha, String descripcion, Integer idEquipo) {
        this.idHincha = idHincha;
        this.descripcion = descripcion;
        this.idEquipo = idEquipo;
    }

    public Integer getIdHincha() {
        return idHincha;
    }

    public void setIdHincha(Integer idHincha) {
        this.idHincha = idHincha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }
    
    
    
    

}
