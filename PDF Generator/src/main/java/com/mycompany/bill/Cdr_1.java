/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bill;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author M Gamal
 */
@Entity
@Table(name = "cdr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cdr_1.findAll", query = "SELECT c FROM Cdr_1 c")
    , @NamedQuery(name = "Cdr_1.findByOrigin", query = "SELECT c FROM Cdr_1 c WHERE c.origin = :origin")
    , @NamedQuery(name = "Cdr_1.findByDestination", query = "SELECT c FROM Cdr_1 c WHERE c.destination = :destination")
    , @NamedQuery(name = "Cdr_1.findByServiceid", query = "SELECT c FROM Cdr_1 c WHERE c.serviceid = :serviceid")
    , @NamedQuery(name = "Cdr_1.findByDurationMessageVolume", query = "SELECT c FROM Cdr_1 c WHERE c.durationMessageVolume = :durationMessageVolume")
    , @NamedQuery(name = "Cdr_1.findByStartdate", query = "SELECT c FROM Cdr_1 c WHERE c.startdate = :startdate")
    , @NamedQuery(name = "Cdr_1.findByStarttime", query = "SELECT c FROM Cdr_1 c WHERE c.starttime = :starttime")
    , @NamedQuery(name = "Cdr_1.findByExternalrating", query = "SELECT c FROM Cdr_1 c WHERE c.externalrating = :externalrating")
    , @NamedQuery(name = "Cdr_1.findByInternalrating", query = "SELECT c FROM Cdr_1 c WHERE c.internalrating = :internalrating")
    , @NamedQuery(name = "Cdr_1.findByIsrated", query = "SELECT c FROM Cdr_1 c WHERE c.israted = :israted")
    , @NamedQuery(name = "Cdr_1.findByCdrId", query = "SELECT c FROM Cdr_1 c WHERE c.cdrId = :cdrId")})
public class Cdr_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "origin")
    private BigInteger origin;
    @Column(name = "destination")
    private String destination;
    @Column(name = "serviceid")
    private Integer serviceid;
    @Column(name = "duration_message_volume")
    private Integer durationMessageVolume;
    @Column(name = "startdate")
    private String startdate;
    @Column(name = "starttime")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "externalrating")
    private Double externalrating;
    @Column(name = "internalrating")
    private Double internalrating;
    @Column(name = "israted")
    private Boolean israted;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cdr_id")
    private Integer cdrId;

    public Cdr_1() {
    }

    public Cdr_1(Integer cdrId) {
        this.cdrId = cdrId;
    }

    public BigInteger getOrigin() {
        return origin;
    }

    public void setOrigin(BigInteger origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public Integer getDurationMessageVolume() {
        return durationMessageVolume;
    }

    public void setDurationMessageVolume(Integer durationMessageVolume) {
        this.durationMessageVolume = durationMessageVolume;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Double getExternalrating() {
        return externalrating;
    }

    public void setExternalrating(Double externalrating) {
        this.externalrating = externalrating;
    }

    public Double getInternalrating() {
        return internalrating;
    }

    public void setInternalrating(Double internalrating) {
        this.internalrating = internalrating;
    }

    public Boolean getIsrated() {
        return israted;
    }

    public void setIsrated(Boolean israted) {
        this.israted = israted;
    }

    public Integer getCdrId() {
        return cdrId;
    }

    public void setCdrId(Integer cdrId) {
        this.cdrId = cdrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdrId != null ? cdrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cdr_1)) {
            return false;
        }
        Cdr_1 other = (Cdr_1) object;
        if ((this.cdrId == null && other.cdrId != null) || (this.cdrId != null && !this.cdrId.equals(other.cdrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bill.Cdr_1[ cdrId=" + cdrId + " ]";
    }
    
}
