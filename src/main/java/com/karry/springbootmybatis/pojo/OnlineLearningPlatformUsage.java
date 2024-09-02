package com.karry.springbootmybatis.pojo;

public class OnlineLearningPlatformUsage {
    private String school;
    private String plat;
    private Integer rsnum;
    private Integer rtnum;
    private Integer source;
    private Integer sdownload;

    // 默认构造函数
    public OnlineLearningPlatformUsage() {
    }

    // Getter 和 Setter 方法
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public Integer getRsnum() {
        return rsnum;
    }

    public void setRsnum(Integer rsnum) {
        this.rsnum = rsnum;
    }

    public Integer getRtnum() {
        return rtnum;
    }

    public void setRtnum(Integer rtnum) {
        this.rtnum = rtnum;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getSdownload() {
        return sdownload;
    }

    public void setSdownload(Integer sdownload) {
        this.sdownload = sdownload;
    }
}
