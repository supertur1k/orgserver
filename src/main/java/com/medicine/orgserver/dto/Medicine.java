package com.medicine.orgserver.dto;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Medicine {
    @SerializedName("packing_id")
    private int packingId;
    @SerializedName("desc_id")
    private Integer descId;
    @SerializedName("prep_id")
    private int prepId;
    @SerializedName("trade_name_id")
    private int tradeNameId;
    @SerializedName("trade_name_rus")
    private String tradeNameRus;
    @SerializedName("trade_name_rus_html")
    private String tradeNameRusHtml;
    @SerializedName("lat_name_id")
    private Integer latNameId;
    @SerializedName("lat_name")
    private String latName;
    @SerializedName("dosage_form_id")
    private int dosageFormId;
    @SerializedName("dosage_form_full_name")
    private String dosageFormFullName;
    @SerializedName("dosage_form_short_name")
    private String dosageFormShortName;
    private String dose;
    @SerializedName("dose_amount")
    private Integer doseAmount;
    @SerializedName("pack_dosage")
    private String packDosage;
    @SerializedName("pack1_id")
    private Integer pack1Id;
    @SerializedName("pack1sn")
    private String pack1sn;
    @SerializedName("pack1n")
    private String pack1n;
    @SerializedName("amount1")
    private Integer amount1;
    @SerializedName("pack2_id")
    private Integer pack2Id;
    @SerializedName("pack2sn")
    private String pack2sn;
    @SerializedName("pack2n")
    private String pack2n;
    @SerializedName("amount2")
    private Integer amount2;
    @SerializedName("pack3_id")
    private Integer pack3Id;
    @SerializedName("pack3sn")
    private String pack3sn;
    @SerializedName("pack3n")
    private String pack3n;
    @SerializedName("amount3")
    private Integer amount3;
    @SerializedName("packing_short")
    private String packingShort;
    @SerializedName("packing_full")
    private String packingFull;
    @SerializedName("as_id")
    private int asId;
    @SerializedName("as_name_rus")
    private String asNameRus;
    @SerializedName("producer_id")
    private int producerId;
    @SerializedName("producer_tran")
    private String producerTran;
    @SerializedName("producer_orig")
    private String producerOrig;
    @SerializedName("producer_country_id")
    private int producerCountryId;
    @SerializedName("producer_country")
    private String producerCountry;
    @SerializedName("packer_id")
    private Integer packerId;
    @SerializedName("packer_tran")
    private String packerTran;
    @SerializedName("packer_orig")
    private String packerOrig;
    @SerializedName("packer_country_id")
    private Integer packerCountryId;
    @SerializedName("packer_country")
    private String packerCountry;
    private Integer amount;
    private String barcode;
    @SerializedName("dfc_id")
    private Integer dfcId;
    @SerializedName("dfc_full_name")
    private String dfcFullName;
    @SerializedName("dfc_short_name")
    private String dfcShortName;
    @SerializedName("completeness_id")
    private Integer completenessId;
    @SerializedName("completeness_name")
    private String completenessName;
    @SerializedName("completeness_short_name")
    private String completenessShortName;
    @SerializedName("reg_id")
    private int regId;
    @SerializedName("reg_number")
    private String regNumber;
    @SerializedName("reg_date")
    private Date regDate;
    @SerializedName("rereg_date")
    private Date reregDate;
    @SerializedName("reg_cancel_date")
    private Date regCancelDate;
    @SerializedName("reg_status_id")
    private int regStatusId;
    @SerializedName("reg_status")
    private String regStatus;
    @SerializedName("registrator_id")
    private int registratorId;
    @SerializedName("registrator_tran")
    private String registratorTran;
    @SerializedName("registrator_orig")
    private String registratorOrig;
    @SerializedName("registrator_country_id")
    private int registratorCountryId;
    @SerializedName("registrator_country")
    private String registratorCountry;
    @SerializedName("ntfr_id")
    private int ntfrId;
    @SerializedName("ntfr_name")
    private String ntfrName;
    @SerializedName("lt_id")
    private int ltId;
    @SerializedName("lt_name")
    private String ltName;
    @SerializedName("lt_month")
    private double ltMonth;
    @SerializedName("lte_name")
    private String lteName;
    @SerializedName("sc_id")
    private int scId;
    @SerializedName("sc_name")
    private String scName;
    @SerializedName("sc_short_name")
    private String scShortName;
    @SerializedName("sc_text")
    private String scText;
    @SerializedName("actdate")
    private Date actdate;
    private Object weight;
    private Object picname;
    @SerializedName("prep_short")
    private String prepShort;
    @SerializedName("prep_full")
    private String prepFull;
    private String registration;

    public Medicine(int packingId, Integer descId, int prepId, int tradeNameId, String tradeNameRus, String tradeNameRusHtml, Integer latNameId, String latName, int dosageFormId, String dosageFormFullName, String dosageFormShortName, String dose, Integer doseAmount, String packDosage, Integer pack1Id, String pack1sn, String pack1n, Integer amount1, Integer pack2Id, String pack2sn, String pack2n, Integer amount2, Integer pack3Id, String pack3sn, String pack3n, Integer amount3, String packingShort, String packingFull, int asId, String asNameRus, int producerId, String producerTran, String producerOrig, int producerCountryId, String producerCountry, Integer packerId, String packerTran, String packerOrig, Integer packerCountryId, String packerCountry, Integer amount, String barcode, Integer dfcId, String dfcFullName, String dfcShortName, Integer completenessId, String completenessName, String completenessShortName, int regId, String regNumber, Date regDate, Date reregDate, Date regCancelDate, int regStatusId, String regStatus, int registratorId, String registratorTran, String registratorOrig, int registratorCountryId, String registratorCountry, int ntfrId, String ntfrName, int ltId, String ltName, double ltMonth, String lteName, int scId, String scName, String scShortName, String scText, Date actdate, Object weight, Object picname, String prepShort, String prepFull, String registration, String firms) {
        this.packingId = packingId;
        this.descId = descId;
        this.prepId = prepId;
        this.tradeNameId = tradeNameId;
        this.tradeNameRus = tradeNameRus;
        this.tradeNameRusHtml = tradeNameRusHtml;
        this.latNameId = latNameId;
        this.latName = latName;
        this.dosageFormId = dosageFormId;
        this.dosageFormFullName = dosageFormFullName;
        this.dosageFormShortName = dosageFormShortName;
        this.dose = dose;
        this.doseAmount = doseAmount;
        this.packDosage = packDosage;
        this.pack1Id = pack1Id;
        this.pack1sn = pack1sn;
        this.pack1n = pack1n;
        this.amount1 = amount1;
        this.pack2Id = pack2Id;
        this.pack2sn = pack2sn;
        this.pack2n = pack2n;
        this.amount2 = amount2;
        this.pack3Id = pack3Id;
        this.pack3sn = pack3sn;
        this.pack3n = pack3n;
        this.amount3 = amount3;
        this.packingShort = packingShort;
        this.packingFull = packingFull;
        this.asId = asId;
        this.asNameRus = asNameRus;
        this.producerId = producerId;
        this.producerTran = producerTran;
        this.producerOrig = producerOrig;
        this.producerCountryId = producerCountryId;
        this.producerCountry = producerCountry;
        this.packerId = packerId;
        this.packerTran = packerTran;
        this.packerOrig = packerOrig;
        this.packerCountryId = packerCountryId;
        this.packerCountry = packerCountry;
        this.amount = amount;
        this.barcode = barcode;
        this.dfcId = dfcId;
        this.dfcFullName = dfcFullName;
        this.dfcShortName = dfcShortName;
        this.completenessId = completenessId;
        this.completenessName = completenessName;
        this.completenessShortName = completenessShortName;
        this.regId = regId;
        this.regNumber = regNumber;
        this.regDate = regDate;
        this.reregDate = reregDate;
        this.regCancelDate = regCancelDate;
        this.regStatusId = regStatusId;
        this.regStatus = regStatus;
        this.registratorId = registratorId;
        this.registratorTran = registratorTran;
        this.registratorOrig = registratorOrig;
        this.registratorCountryId = registratorCountryId;
        this.registratorCountry = registratorCountry;
        this.ntfrId = ntfrId;
        this.ntfrName = ntfrName;
        this.ltId = ltId;
        this.ltName = ltName;
        this.ltMonth = ltMonth;
        this.lteName = lteName;
        this.scId = scId;
        this.scName = scName;
        this.scShortName = scShortName;
        this.scText = scText;
        this.actdate = actdate;
        this.weight = weight;
        this.picname = picname;
        this.prepShort = prepShort;
        this.prepFull = prepFull;
        this.registration = registration;
        this.firms = firms;
    }

    public int getPackingId() {
        return packingId;
    }

    public void setPackingId(int packingId) {
        this.packingId = packingId;
    }

    public Integer getDescId() {
        return descId;
    }

    public void setDescId(Integer descId) {
        this.descId = descId;
    }

    public int getPrepId() {
        return prepId;
    }

    public void setPrepId(int prepId) {
        this.prepId = prepId;
    }

    public int getTradeNameId() {
        return tradeNameId;
    }

    public void setTradeNameId(int tradeNameId) {
        this.tradeNameId = tradeNameId;
    }

    public String getTradeNameRus() {
        return tradeNameRus;
    }

    public void setTradeNameRus(String tradeNameRus) {
        this.tradeNameRus = tradeNameRus;
    }

    public String getTradeNameRusHtml() {
        return tradeNameRusHtml;
    }

    public void setTradeNameRusHtml(String tradeNameRusHtml) {
        this.tradeNameRusHtml = tradeNameRusHtml;
    }

    public Integer getLatNameId() {
        return latNameId;
    }

    public void setLatNameId(Integer latNameId) {
        this.latNameId = latNameId;
    }

    public String getLatName() {
        return latName;
    }

    public void setLatName(String latName) {
        this.latName = latName;
    }

    public int getDosageFormId() {
        return dosageFormId;
    }

    public void setDosageFormId(int dosageFormId) {
        this.dosageFormId = dosageFormId;
    }

    public String getDosageFormFullName() {
        return dosageFormFullName;
    }

    public void setDosageFormFullName(String dosageFormFullName) {
        this.dosageFormFullName = dosageFormFullName;
    }

    public String getDosageFormShortName() {
        return dosageFormShortName;
    }

    public void setDosageFormShortName(String dosageFormShortName) {
        this.dosageFormShortName = dosageFormShortName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Integer getDoseAmount() {
        return doseAmount;
    }

    public void setDoseAmount(Integer doseAmount) {
        this.doseAmount = doseAmount;
    }

    public String getPackDosage() {
        return packDosage;
    }

    public void setPackDosage(String packDosage) {
        this.packDosage = packDosage;
    }

    public Integer getPack1Id() {
        return pack1Id;
    }

    public void setPack1Id(Integer pack1Id) {
        this.pack1Id = pack1Id;
    }

    public String getPack1sn() {
        return pack1sn;
    }

    public void setPack1sn(String pack1sn) {
        this.pack1sn = pack1sn;
    }

    public String getPack1n() {
        return pack1n;
    }

    public void setPack1n(String pack1n) {
        this.pack1n = pack1n;
    }

    public Integer getAmount1() {
        return amount1;
    }

    public void setAmount1(Integer amount1) {
        this.amount1 = amount1;
    }

    public Integer getPack2Id() {
        return pack2Id;
    }

    public void setPack2Id(Integer pack2Id) {
        this.pack2Id = pack2Id;
    }

    public String getPack2sn() {
        return pack2sn;
    }

    public void setPack2sn(String pack2sn) {
        this.pack2sn = pack2sn;
    }

    public String getPack2n() {
        return pack2n;
    }

    public void setPack2n(String pack2n) {
        this.pack2n = pack2n;
    }

    public Integer getAmount2() {
        return amount2;
    }

    public void setAmount2(Integer amount2) {
        this.amount2 = amount2;
    }

    public Integer getPack3Id() {
        return pack3Id;
    }

    public void setPack3Id(Integer pack3Id) {
        this.pack3Id = pack3Id;
    }

    public String getPack3sn() {
        return pack3sn;
    }

    public void setPack3sn(String pack3sn) {
        this.pack3sn = pack3sn;
    }

    public String getPack3n() {
        return pack3n;
    }

    public void setPack3n(String pack3n) {
        this.pack3n = pack3n;
    }

    public Integer getAmount3() {
        return amount3;
    }

    public void setAmount3(Integer amount3) {
        this.amount3 = amount3;
    }

    public String getPackingShort() {
        return packingShort;
    }

    public void setPackingShort(String packingShort) {
        this.packingShort = packingShort;
    }

    public String getPackingFull() {
        return packingFull;
    }

    public void setPackingFull(String packingFull) {
        this.packingFull = packingFull;
    }

    public int getAsId() {
        return asId;
    }

    public void setAsId(int asId) {
        this.asId = asId;
    }

    public String getAsNameRus() {
        return asNameRus;
    }

    public void setAsNameRus(String asNameRus) {
        this.asNameRus = asNameRus;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getProducerTran() {
        return producerTran;
    }

    public void setProducerTran(String producerTran) {
        this.producerTran = producerTran;
    }

    public String getProducerOrig() {
        return producerOrig;
    }

    public void setProducerOrig(String producerOrig) {
        this.producerOrig = producerOrig;
    }

    public int getProducerCountryId() {
        return producerCountryId;
    }

    public void setProducerCountryId(int producerCountryId) {
        this.producerCountryId = producerCountryId;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public Integer getPackerId() {
        return packerId;
    }

    public void setPackerId(Integer packerId) {
        this.packerId = packerId;
    }

    public String getPackerTran() {
        return packerTran;
    }

    public void setPackerTran(String packerTran) {
        this.packerTran = packerTran;
    }

    public String getPackerOrig() {
        return packerOrig;
    }

    public void setPackerOrig(String packerOrig) {
        this.packerOrig = packerOrig;
    }

    public Integer getPackerCountryId() {
        return packerCountryId;
    }

    public void setPackerCountryId(Integer packerCountryId) {
        this.packerCountryId = packerCountryId;
    }

    public String getPackerCountry() {
        return packerCountry;
    }

    public void setPackerCountry(String packerCountry) {
        this.packerCountry = packerCountry;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getDfcId() {
        return dfcId;
    }

    public void setDfcId(Integer dfcId) {
        this.dfcId = dfcId;
    }

    public String getDfcFullName() {
        return dfcFullName;
    }

    public void setDfcFullName(String dfcFullName) {
        this.dfcFullName = dfcFullName;
    }

    public String getDfcShortName() {
        return dfcShortName;
    }

    public void setDfcShortName(String dfcShortName) {
        this.dfcShortName = dfcShortName;
    }

    public Integer getCompletenessId() {
        return completenessId;
    }

    public void setCompletenessId(Integer completenessId) {
        this.completenessId = completenessId;
    }

    public String getCompletenessName() {
        return completenessName;
    }

    public void setCompletenessName(String completenessName) {
        this.completenessName = completenessName;
    }

    public String getCompletenessShortName() {
        return completenessShortName;
    }

    public void setCompletenessShortName(String completenessShortName) {
        this.completenessShortName = completenessShortName;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getReregDate() {
        return reregDate;
    }

    public void setReregDate(Date reregDate) {
        this.reregDate = reregDate;
    }

    public Date getRegCancelDate() {
        return regCancelDate;
    }

    public void setRegCancelDate(Date regCancelDate) {
        this.regCancelDate = regCancelDate;
    }

    public int getRegStatusId() {
        return regStatusId;
    }

    public void setRegStatusId(int regStatusId) {
        this.regStatusId = regStatusId;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public int getRegistratorId() {
        return registratorId;
    }

    public void setRegistratorId(int registratorId) {
        this.registratorId = registratorId;
    }

    public String getRegistratorTran() {
        return registratorTran;
    }

    public void setRegistratorTran(String registratorTran) {
        this.registratorTran = registratorTran;
    }

    public String getRegistratorOrig() {
        return registratorOrig;
    }

    public void setRegistratorOrig(String registratorOrig) {
        this.registratorOrig = registratorOrig;
    }

    public int getRegistratorCountryId() {
        return registratorCountryId;
    }

    public void setRegistratorCountryId(int registratorCountryId) {
        this.registratorCountryId = registratorCountryId;
    }

    public String getRegistratorCountry() {
        return registratorCountry;
    }

    public void setRegistratorCountry(String registratorCountry) {
        this.registratorCountry = registratorCountry;
    }

    public int getNtfrId() {
        return ntfrId;
    }

    public void setNtfrId(int ntfrId) {
        this.ntfrId = ntfrId;
    }

    public String getNtfrName() {
        return ntfrName;
    }

    public void setNtfrName(String ntfrName) {
        this.ntfrName = ntfrName;
    }

    public int getLtId() {
        return ltId;
    }

    public void setLtId(int ltId) {
        this.ltId = ltId;
    }

    public String getLtName() {
        return ltName;
    }

    public void setLtName(String ltName) {
        this.ltName = ltName;
    }

    public double getLtMonth() {
        return ltMonth;
    }

    public void setLtMonth(double ltMonth) {
        this.ltMonth = ltMonth;
    }

    public String getLteName() {
        return lteName;
    }

    public void setLteName(String lteName) {
        this.lteName = lteName;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public String getScShortName() {
        return scShortName;
    }

    public void setScShortName(String scShortName) {
        this.scShortName = scShortName;
    }

    public String getScText() {
        return scText;
    }

    public void setScText(String scText) {
        this.scText = scText;
    }

    public Date getActdate() {
        return actdate;
    }

    public void setActdate(Date actdate) {
        this.actdate = actdate;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    public Object getPicname() {
        return picname;
    }

    public void setPicname(Object picname) {
        this.picname = picname;
    }

    public String getPrepShort() {
        return prepShort;
    }

    public void setPrepShort(String prepShort) {
        this.prepShort = prepShort;
    }

    public String getPrepFull() {
        return prepFull;
    }

    public void setPrepFull(String prepFull) {
        this.prepFull = prepFull;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getFirms() {
        return firms;
    }

    public void setFirms(String firms) {
        this.firms = firms;
    }

    private String firms;

}