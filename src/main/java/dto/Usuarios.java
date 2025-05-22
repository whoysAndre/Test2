/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author yello
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByCodeUsua", query = "SELECT u FROM Usuarios u WHERE u.codeUsua = :codeUsua"),
    @NamedQuery(name = "Usuarios.findByLogiUsua", query = "SELECT u FROM Usuarios u WHERE u.logiUsua = :logiUsua"),
    @NamedQuery(name = "Usuarios.findByLogiPass", query = "SELECT u FROM Usuarios u WHERE u.logiPass = :logiPass"),
    @NamedQuery(name = "Usuarios.validar", query = "SELECT u FROM Usuarios u WHERE u.logiUsua = :logiUsua AND u.logiPass = :logiPass"),
})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codeUsua")
    private Integer codeUsua;
    @Size(max = 40)
    @Column(name = "logiUsua")
    private String logiUsua;
    @Size(max = 150)
    @Column(name = "logiPass")
    private String logiPass;

    public Usuarios() {
    }

    public Usuarios(String logiUsua, String logiPass) {
        this.logiUsua = logiUsua;
        this.logiPass = logiPass;
    }

    
    public Usuarios(Integer codeUsua) {
        this.codeUsua = codeUsua;
    }

    public Integer getCodeUsua() {
        return codeUsua;
    }

    public void setCodeUsua(Integer codeUsua) {
        this.codeUsua = codeUsua;
    }

    public String getLogiUsua() {
        return logiUsua;
    }

    public void setLogiUsua(String logiUsua) {
        this.logiUsua = logiUsua;
    }

    public String getLogiPass() {
        return logiPass;
    }

    public void setLogiPass(String logiPass) {
        this.logiPass = logiPass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeUsua != null ? codeUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.codeUsua == null && other.codeUsua != null) || (this.codeUsua != null && !this.codeUsua.equals(other.codeUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Usuarios[ codeUsua=" + codeUsua + " ]";
    }
    
}
