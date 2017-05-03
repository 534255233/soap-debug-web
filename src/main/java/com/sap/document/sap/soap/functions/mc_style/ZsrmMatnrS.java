
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZsrmMatnrS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZsrmMatnrS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Matnr" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="Werks" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Maktx" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Bismt" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="Normt" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="Matkl" type="{urn:sap-com:document:sap:rfc:functions}char9"/>
 *         &lt;element name="Meins" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="Bstme" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="Lvorm" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Mtart" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Ladgr" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Ekgrp" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Ekwsl" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Kordb" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Webaz" type="{urn:sap-com:document:sap:rfc:functions}decimal3.0"/>
 *         &lt;element name="Beskz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Lgfsb" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Minpack" type="{urn:sap-com:document:sap:rfc:functions}decimal13.0"/>
 *         &lt;element name="Minord" type="{urn:sap-com:document:sap:rfc:functions}decimal13.0"/>
 *         &lt;element name="Ntgew" type="{urn:sap-com:document:sap:rfc:functions}decimal13.3"/>
 *         &lt;element name="Plifz" type="{urn:sap-com:document:sap:rfc:functions}decimal3.0"/>
 *         &lt;element name="Loggr" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Peinh" type="{urn:sap-com:document:sap:rfc:functions}decimal5.0"/>
 *         &lt;element name="Stprs" type="{urn:sap-com:document:sap:rfc:functions}decimal11.2"/>
 *         &lt;element name="Labor" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Sbdkz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Raube" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Qybs" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Eisbe" type="{urn:sap-com:document:sap:rfc:functions}decimal13.3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZsrmMatnrS", propOrder = {
    "matnr",
    "werks",
    "maktx",
    "bismt",
    "normt",
    "matkl",
    "meins",
    "bstme",
    "lvorm",
    "mtart",
    "ladgr",
    "ekgrp",
    "ekwsl",
    "kordb",
    "webaz",
    "beskz",
    "lgfsb",
    "minpack",
    "minord",
    "ntgew",
    "plifz",
    "loggr",
    "peinh",
    "stprs",
    "labor",
    "sbdkz",
    "raube",
    "qybs",
    "eisbe"
})
public class ZsrmMatnrS {

    @XmlElement(name = "Matnr", required = true)
    protected String matnr;
    @XmlElement(name = "Werks", required = true)
    protected String werks;
    @XmlElement(name = "Maktx", required = true)
    protected String maktx;
    @XmlElement(name = "Bismt", required = true)
    protected String bismt;
    @XmlElement(name = "Normt", required = true)
    protected String normt;
    @XmlElement(name = "Matkl", required = true)
    protected String matkl;
    @XmlElement(name = "Meins", required = true)
    protected String meins;
    @XmlElement(name = "Bstme", required = true)
    protected String bstme;
    @XmlElement(name = "Lvorm", required = true)
    protected String lvorm;
    @XmlElement(name = "Mtart", required = true)
    protected String mtart;
    @XmlElement(name = "Ladgr", required = true)
    protected String ladgr;
    @XmlElement(name = "Ekgrp", required = true)
    protected String ekgrp;
    @XmlElement(name = "Ekwsl", required = true)
    protected String ekwsl;
    @XmlElement(name = "Kordb", required = true)
    protected String kordb;
    @XmlElement(name = "Webaz", required = true)
    protected BigDecimal webaz;
    @XmlElement(name = "Beskz", required = true)
    protected String beskz;
    @XmlElement(name = "Lgfsb", required = true)
    protected String lgfsb;
    @XmlElement(name = "Minpack", required = true)
    protected BigDecimal minpack;
    @XmlElement(name = "Minord", required = true)
    protected BigDecimal minord;
    @XmlElement(name = "Ntgew", required = true)
    protected BigDecimal ntgew;
    @XmlElement(name = "Plifz", required = true)
    protected BigDecimal plifz;
    @XmlElement(name = "Loggr", required = true)
    protected String loggr;
    @XmlElement(name = "Peinh", required = true)
    protected BigDecimal peinh;
    @XmlElement(name = "Stprs", required = true)
    protected BigDecimal stprs;
    @XmlElement(name = "Labor", required = true)
    protected String labor;
    @XmlElement(name = "Sbdkz", required = true)
    protected String sbdkz;
    @XmlElement(name = "Raube", required = true)
    protected String raube;
    @XmlElement(name = "Qybs", required = true)
    protected String qybs;
    @XmlElement(name = "Eisbe", required = true)
    protected BigDecimal eisbe;

    /**
     * Gets the value of the matnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatnr() {
        return matnr;
    }

    /**
     * Sets the value of the matnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatnr(String value) {
        this.matnr = value;
    }

    /**
     * Gets the value of the werks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWerks() {
        return werks;
    }

    /**
     * Sets the value of the werks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWerks(String value) {
        this.werks = value;
    }

    /**
     * Gets the value of the maktx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaktx() {
        return maktx;
    }

    /**
     * Sets the value of the maktx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaktx(String value) {
        this.maktx = value;
    }

    /**
     * Gets the value of the bismt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBismt() {
        return bismt;
    }

    /**
     * Sets the value of the bismt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBismt(String value) {
        this.bismt = value;
    }

    /**
     * Gets the value of the normt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormt() {
        return normt;
    }

    /**
     * Sets the value of the normt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormt(String value) {
        this.normt = value;
    }

    /**
     * Gets the value of the matkl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatkl() {
        return matkl;
    }

    /**
     * Sets the value of the matkl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatkl(String value) {
        this.matkl = value;
    }

    /**
     * Gets the value of the meins property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeins() {
        return meins;
    }

    /**
     * Sets the value of the meins property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeins(String value) {
        this.meins = value;
    }

    /**
     * Gets the value of the bstme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBstme() {
        return bstme;
    }

    /**
     * Sets the value of the bstme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBstme(String value) {
        this.bstme = value;
    }

    /**
     * Gets the value of the lvorm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLvorm() {
        return lvorm;
    }

    /**
     * Sets the value of the lvorm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLvorm(String value) {
        this.lvorm = value;
    }

    /**
     * Gets the value of the mtart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtart() {
        return mtart;
    }

    /**
     * Sets the value of the mtart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtart(String value) {
        this.mtart = value;
    }

    /**
     * Gets the value of the ladgr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLadgr() {
        return ladgr;
    }

    /**
     * Sets the value of the ladgr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLadgr(String value) {
        this.ladgr = value;
    }

    /**
     * Gets the value of the ekgrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEkgrp() {
        return ekgrp;
    }

    /**
     * Sets the value of the ekgrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEkgrp(String value) {
        this.ekgrp = value;
    }

    /**
     * Gets the value of the ekwsl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEkwsl() {
        return ekwsl;
    }

    /**
     * Sets the value of the ekwsl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEkwsl(String value) {
        this.ekwsl = value;
    }

    /**
     * Gets the value of the kordb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKordb() {
        return kordb;
    }

    /**
     * Sets the value of the kordb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKordb(String value) {
        this.kordb = value;
    }

    /**
     * Gets the value of the webaz property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWebaz() {
        return webaz;
    }

    /**
     * Sets the value of the webaz property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWebaz(BigDecimal value) {
        this.webaz = value;
    }

    /**
     * Gets the value of the beskz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeskz() {
        return beskz;
    }

    /**
     * Sets the value of the beskz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeskz(String value) {
        this.beskz = value;
    }

    /**
     * Gets the value of the lgfsb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLgfsb() {
        return lgfsb;
    }

    /**
     * Sets the value of the lgfsb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLgfsb(String value) {
        this.lgfsb = value;
    }

    /**
     * Gets the value of the minpack property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinpack() {
        return minpack;
    }

    /**
     * Sets the value of the minpack property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinpack(BigDecimal value) {
        this.minpack = value;
    }

    /**
     * Gets the value of the minord property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinord() {
        return minord;
    }

    /**
     * Sets the value of the minord property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinord(BigDecimal value) {
        this.minord = value;
    }

    /**
     * Gets the value of the ntgew property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNtgew() {
        return ntgew;
    }

    /**
     * Sets the value of the ntgew property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNtgew(BigDecimal value) {
        this.ntgew = value;
    }

    /**
     * Gets the value of the plifz property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPlifz() {
        return plifz;
    }

    /**
     * Sets the value of the plifz property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPlifz(BigDecimal value) {
        this.plifz = value;
    }

    /**
     * Gets the value of the loggr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggr() {
        return loggr;
    }

    /**
     * Sets the value of the loggr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggr(String value) {
        this.loggr = value;
    }

    /**
     * Gets the value of the peinh property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeinh() {
        return peinh;
    }

    /**
     * Sets the value of the peinh property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeinh(BigDecimal value) {
        this.peinh = value;
    }

    /**
     * Gets the value of the stprs property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStprs() {
        return stprs;
    }

    /**
     * Sets the value of the stprs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStprs(BigDecimal value) {
        this.stprs = value;
    }

    /**
     * Gets the value of the labor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabor() {
        return labor;
    }

    /**
     * Sets the value of the labor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabor(String value) {
        this.labor = value;
    }

    /**
     * Gets the value of the sbdkz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSbdkz() {
        return sbdkz;
    }

    /**
     * Sets the value of the sbdkz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSbdkz(String value) {
        this.sbdkz = value;
    }

    /**
     * Gets the value of the raube property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaube() {
        return raube;
    }

    /**
     * Sets the value of the raube property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaube(String value) {
        this.raube = value;
    }

    /**
     * Gets the value of the qybs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQybs() {
        return qybs;
    }

    /**
     * Sets the value of the qybs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQybs(String value) {
        this.qybs = value;
    }

    /**
     * Gets the value of the eisbe property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEisbe() {
        return eisbe;
    }

    /**
     * Sets the value of the eisbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEisbe(BigDecimal value) {
        this.eisbe = value;
    }

}
