
package com.lickhunter.spotbot.models.sentiments;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "s",
    "n",
    "p",
    "p_btc",
    "v",
    "vt",
    "pc",
    "pch",
    "mc",
    "gs",
    "ss",
    "as",
    "bl",
    "br",
    "sp",
    "na",
    "md",
    "t",
    "r",
    "yt",
    "sv",
    "u",
    "c",
    "sd",
    "d",
    "cr",
    "acr",
    "tc",
    "categories",
    "df",
    "e2"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("s")
    private String s;
    @JsonProperty("n")
    private String n;
    @JsonProperty("p")
    private Double p;
    @JsonProperty("p_btc")
    private Double pBtc;
    @JsonProperty("v")
    private Double v;
    @JsonProperty("vt")
    private Double vt;
    @JsonProperty("pc")
    private Double pc;
    @JsonProperty("pch")
    private Double pch;
    @JsonProperty("mc")
    private Long mc;
    @JsonProperty("gs")
    private Integer gs;
    @JsonProperty("ss")
    private Integer ss;
    @JsonProperty("as")
    private Double as;
    @JsonProperty("bl")
    private Integer bl;
    @JsonProperty("br")
    private Integer br;
    @JsonProperty("sp")
    private Integer sp;
    @JsonProperty("na")
    private Integer na;
    @JsonProperty("md")
    private Integer md;
    @JsonProperty("t")
    private Integer t;
    @JsonProperty("r")
    private Integer r;
    @JsonProperty("yt")
    private Integer yt;
    @JsonProperty("sv")
    private Integer sv;
    @JsonProperty("u")
    private Integer u;
    @JsonProperty("c")
    private Integer c;
    @JsonProperty("sd")
    private Double sd;
    @JsonProperty("d")
    private Double d;
    @JsonProperty("cr")
    private Double cr;
    @JsonProperty("acr")
    private Integer acr;
    @JsonProperty("tc")
    private Integer tc;
    @JsonProperty("categories")
    private String categories;
    @JsonProperty("df")
    private Integer df;
    @JsonProperty("e2")
    private Integer e2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("s")
    public String getS() {
        return s;
    }

    @JsonProperty("s")
    public void setS(String s) {
        this.s = s;
    }

    @JsonProperty("n")
    public String getN() {
        return n;
    }

    @JsonProperty("n")
    public void setN(String n) {
        this.n = n;
    }

    @JsonProperty("p")
    public Double getP() {
        return p;
    }

    @JsonProperty("p")
    public void setP(Double p) {
        this.p = p;
    }

    @JsonProperty("p_btc")
    public Double getpBtc() {
        return pBtc;
    }

    @JsonProperty("p_btc")
    public void setpBtc(Double pBtc) {
        this.pBtc = pBtc;
    }

    @JsonProperty("v")
    public Double getV() {
        return v;
    }

    @JsonProperty("v")
    public void setV(Double v) {
        this.v = v;
    }

    @JsonProperty("vt")
    public Double getVt() {
        return vt;
    }

    @JsonProperty("vt")
    public void setVt(Double vt) {
        this.vt = vt;
    }

    @JsonProperty("pc")
    public Double getPc() {
        return pc;
    }

    @JsonProperty("pc")
    public void setPc(Double pc) {
        this.pc = pc;
    }

    @JsonProperty("pch")
    public Double getPch() {
        return pch;
    }

    @JsonProperty("pch")
    public void setPch(Double pch) {
        this.pch = pch;
    }

    @JsonProperty("mc")
    public Long getMc() {
        return mc;
    }

    @JsonProperty("mc")
    public void setMc(Long mc) {
        this.mc = mc;
    }

    @JsonProperty("gs")
    public Integer getGs() {
        return gs;
    }

    @JsonProperty("gs")
    public void setGs(Integer gs) {
        this.gs = gs;
    }

    @JsonProperty("ss")
    public Integer getSs() {
        return ss;
    }

    @JsonProperty("ss")
    public void setSs(Integer ss) {
        this.ss = ss;
    }

    @JsonProperty("as")
    public Double getAs() {
        return as;
    }

    @JsonProperty("as")
    public void setAs(Double as) {
        this.as = as;
    }

    @JsonProperty("bl")
    public Integer getBl() {
        return bl;
    }

    @JsonProperty("bl")
    public void setBl(Integer bl) {
        this.bl = bl;
    }

    @JsonProperty("br")
    public Integer getBr() {
        return br;
    }

    @JsonProperty("br")
    public void setBr(Integer br) {
        this.br = br;
    }

    @JsonProperty("sp")
    public Integer getSp() {
        return sp;
    }

    @JsonProperty("sp")
    public void setSp(Integer sp) {
        this.sp = sp;
    }

    @JsonProperty("na")
    public Integer getNa() {
        return na;
    }

    @JsonProperty("na")
    public void setNa(Integer na) {
        this.na = na;
    }

    @JsonProperty("md")
    public Integer getMd() {
        return md;
    }

    @JsonProperty("md")
    public void setMd(Integer md) {
        this.md = md;
    }

    @JsonProperty("t")
    public Integer getT() {
        return t;
    }

    @JsonProperty("t")
    public void setT(Integer t) {
        this.t = t;
    }

    @JsonProperty("r")
    public Integer getR() {
        return r;
    }

    @JsonProperty("r")
    public void setR(Integer r) {
        this.r = r;
    }

    @JsonProperty("yt")
    public Integer getYt() {
        return yt;
    }

    @JsonProperty("yt")
    public void setYt(Integer yt) {
        this.yt = yt;
    }

    @JsonProperty("sv")
    public Integer getSv() {
        return sv;
    }

    @JsonProperty("sv")
    public void setSv(Integer sv) {
        this.sv = sv;
    }

    @JsonProperty("u")
    public Integer getU() {
        return u;
    }

    @JsonProperty("u")
    public void setU(Integer u) {
        this.u = u;
    }

    @JsonProperty("c")
    public Integer getC() {
        return c;
    }

    @JsonProperty("c")
    public void setC(Integer c) {
        this.c = c;
    }

    @JsonProperty("sd")
    public Double getSd() {
        return sd;
    }

    @JsonProperty("sd")
    public void setSd(Double sd) {
        this.sd = sd;
    }

    @JsonProperty("d")
    public Double getD() {
        return d;
    }

    @JsonProperty("d")
    public void setD(Double d) {
        this.d = d;
    }

    @JsonProperty("cr")
    public Double getCr() {
        return cr;
    }

    @JsonProperty("cr")
    public void setCr(Double cr) {
        this.cr = cr;
    }

    @JsonProperty("acr")
    public Integer getAcr() {
        return acr;
    }

    @JsonProperty("acr")
    public void setAcr(Integer acr) {
        this.acr = acr;
    }

    @JsonProperty("tc")
    public Integer getTc() {
        return tc;
    }

    @JsonProperty("tc")
    public void setTc(Integer tc) {
        this.tc = tc;
    }

    @JsonProperty("categories")
    public String getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(String categories) {
        this.categories = categories;
    }

    @JsonProperty("df")
    public Integer getDf() {
        return df;
    }

    @JsonProperty("df")
    public void setDf(Integer df) {
        this.df = df;
    }

    @JsonProperty("e2")
    public Integer getE2() {
        return e2;
    }

    @JsonProperty("e2")
    public void setE2(Integer e2) {
        this.e2 = e2;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
