package cn.piesat.realtimereceive.receive.tcp.netty.handler;

/**
 * 位置结果
 * @author zk
 * @date 2018/11/24 17:21
 */
public class PsrposEntity {
    /**
     *  BDS周计数
     */
    private String countOfWeek;
    /**
     *  BDS周内秒
     */
    private String sencondOfWeek;
    /**
     *  解算模式
     */
    private String model;
    /**
     *  保留
     */
    private String mark1;

    /**
     *  保留
     */
    private String mark2;
    /**
     *  纬度
     */
    private String lat;
    /**
     *  经度
     */
    private String lon;
    /**
     *  高程
     */
    private String height;
    /**
     *  保留
     */
    private String mark3;
    /**
     *  坐标系统标识
     */
    private String coorSysId;
    /**
     *  纬度标准偏差
     */
    private String latStd;
    /**
     *  经度标准偏差
     */
    private String lonStd;
    /**
     *  高度标准偏差
     */
    private String heightStd;

    /**
     *  跟踪卫星数
     */
    private String satNum;

    /**
     *  解算中使用的卫星数
     */
    private String usedSatNumm;

    public String getCountOfWeek() {
        return countOfWeek;
    }

    public void setCountOfWeek(String countOfWeek) {
        this.countOfWeek = countOfWeek;
    }

    public String getSencondOfWeek() {
        return sencondOfWeek;
    }

    public void setSencondOfWeek(String sencondOfWeek) {
        this.sencondOfWeek = sencondOfWeek;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMark3() {
        return mark3;
    }

    public void setMark3(String mark3) {
        this.mark3 = mark3;
    }

    public String getCoorSysId() {
        return coorSysId;
    }

    public void setCoorSysId(String coorSysId) {
        this.coorSysId = coorSysId;
    }

    public String getLatStd() {
        return latStd;
    }

    public void setLatStd(String latStd) {
        this.latStd = latStd;
    }

    public String getLonStd() {
        return lonStd;
    }

    public void setLonStd(String lonStd) {
        this.lonStd = lonStd;
    }

    public String getHeightStd() {
        return heightStd;
    }

    public void setHeightStd(String heightStd) {
        this.heightStd = heightStd;
    }

    public String getSatNum() {
        return satNum;
    }

    public void setSatNum(String satNum) {
        this.satNum = satNum;
    }

    public String getUsedSatNumm() {
        return usedSatNumm;
    }

    public void setUsedSatNumm(String usedSatNumm) {
        this.usedSatNumm = usedSatNumm;
    }

    @Override
    public String toString() {
        return "PsrposEntity{" +
                "BDS周计数 ='" + countOfWeek + '\'' +
                ", BDS周内秒 ='" + sencondOfWeek + '\'' +
                ", 解算模式='" + model + '\'' +
                ", 保留='" + mark1 + '\'' +
                ", 保留='" + mark2 + '\'' +
                ", 纬度='" + lat + '\'' +
                ", 经度='" + lon + '\'' +
                ", 高程='" + height + '\'' +
                ", 保留='" + mark3 + '\'' +
                ", 坐标系统标识='" + coorSysId + '\'' +
                ", 纬度标准偏差='" + latStd + '\'' +
                ", 经度标准偏差='" + lonStd + '\'' +
                ", 高度标准偏差='" + heightStd + '\'' +
                ", 跟踪卫星数='" + satNum + '\'' +
                ", 解算中使用的卫星数='" + usedSatNumm + '\'' +
                '}';
    }
}
