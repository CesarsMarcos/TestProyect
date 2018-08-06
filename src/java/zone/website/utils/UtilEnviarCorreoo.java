/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone.website.utils;

/**
 *
 * @author sisproem3007
 */
public class UtilEnviarCorreoo {

    private String SmFrom;
    private String SmTo;
    private String SmCc;
    private String SmSubject;
    private String SmMensaje;
    private String SmServidor;
    private boolean SmAuth;
    private boolean Starttls;
    private String SmUsuario;
    private String tSmPass;
    private String SocketFactoryPort;
    private String SmAdjuntos;
    private String SmPuerto;

    public String getSmFrom() {
        return SmFrom;
    }

    public void setSmFrom(String SmFrom) {
        this.SmFrom = SmFrom;
    }

    public String getSmTo() {
        return SmTo;
    }

    public void setSmTo(String SmTo) {
        this.SmTo = SmTo;
    }

    public String getSmCc() {
        return SmCc;
    }

    public void setSmCc(String SmCc) {
        this.SmCc = SmCc;
    }

    public String getSmSubject() {
        return SmSubject;
    }

    public void setSmSubject(String SmSubject) {
        this.SmSubject = SmSubject;
    }

    public String getSmMensaje() {
        return SmMensaje;
    }

    public void setSmMensaje(String SmMensaje) {
        this.SmMensaje = SmMensaje;
    }

    public String getSmServidor() {
        return SmServidor;
    }

    public void setSmServidor(String SmServidor) {
        this.SmServidor = SmServidor;
    }

    public boolean isSmAuth() {
        return SmAuth;
    }

    public void setSmAuth(boolean SmAuth) {
        this.SmAuth = SmAuth;
    }

    public boolean isStarttls() {
        return Starttls;
    }

    public void setStarttls(boolean Starttls) {
        this.Starttls = Starttls;
    }

    public String getSmUsuario() {
        return SmUsuario;
    }

    public void setSmUsuario(String SmUsuario) {
        this.SmUsuario = SmUsuario;
    }

    public String gettSmPass() {
        return tSmPass;
    }

    public void settSmPass(String tSmPass) {
        this.tSmPass = tSmPass;
    }

    public String getSocketFactoryPort() {
        return SocketFactoryPort;
    }

    public void setSocketFactoryPort(String SocketFactoryPort) {
        this.SocketFactoryPort = SocketFactoryPort;
    }

    public String getSmAdjuntos() {
        return SmAdjuntos;
    }

    public void setSmAdjuntos(String SmAdjuntos) {
        this.SmAdjuntos = SmAdjuntos;
    }

    public String getSmPuerto() {
        return SmPuerto;
    }

    public void setSmPuerto(String SmPuerto) {
        this.SmPuerto = SmPuerto;
    }

   
}
