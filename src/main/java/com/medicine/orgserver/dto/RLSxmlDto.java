package com.medicine.orgserver.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RLSxmlDto {

    int packing_id;
    int prep_id;
    String prep_short;
    String prep_full;
    String as_name_rus;
    String lifetime_text;
    String dosage_form_full_name;

    public int getPacking_id() {
        return packing_id;
    }

    @XmlElement
    public void setPacking_id(int packing_id) {
        this.packing_id = packing_id;
    }

    public int getPrep_id() {
        return prep_id;
    }

    @XmlElement
    public void setPrep_id(int prep_id) {
        this.prep_id = prep_id;
    }

    public String getPrep_short() {
        return prep_short;
    }

    @XmlElement
    public void setPrep_short(String prep_short) {
        this.prep_short = prep_short;
    }

    public String getPrep_full() {
        return prep_full;
    }

    @XmlElement
    public void setPrep_full(String prep_full) {
        this.prep_full = prep_full;
    }

    public String getAs_name_rus() {
        return as_name_rus;
    }

    @XmlElement
    public void setAs_name_rus(String as_name_rus) {
        this.as_name_rus = as_name_rus;
    }

    public String getLifetime_text() {
        return lifetime_text;
    }

    @XmlElement
    public void setLifetime_text(String lifetime_text) {
        this.lifetime_text = lifetime_text;
    }

    public String getDosage_form_full_name() {
        return dosage_form_full_name;
    }

    @XmlElement
    public void setDosage_form_full_name(String dosage_form_full_name) {
        this.dosage_form_full_name = dosage_form_full_name;
    }
}
