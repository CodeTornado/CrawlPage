package com.xwservice.bean;

import java.sql.Timestamp;

/**
 * 视频信息bean
 */
public class VideoUrlInfo {

    private Integer Id;
    private String FromUrl;
    private String ImgUrl;
    private String VideoContent;
    private String VideoHref;
    private String Title;
    private String PlayNum;
    private String DMNum;
    private String FavNum;
    private String UpName;
    private String UpIndexUrl;
    private String VideoDate;
    private Timestamp UpdateTime;
    private Timestamp CreateTime;
    private String Remark1;
    private String Remark2;
    private Integer IsDelete;
    private Integer Status;
    private String label;
    private Integer classify;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFromUrl() {
        return FromUrl;
    }

    public void setFromUrl(String fromUrl) {
        FromUrl = fromUrl;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getVideoContent() {
        return VideoContent;
    }

    public void setVideoContent(String videoContent) {
        VideoContent = videoContent;
    }

    public String getVideoHref() {
        return VideoHref;
    }

    public void setVideoHref(String videoHref) {
        VideoHref = videoHref;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPlayNum() {
        return PlayNum;
    }

    public void setPlayNum(String playNum) {
        PlayNum = playNum;
    }

    public String getDMNum() {
        return DMNum;
    }

    public void setDMNum(String DMNum) {
        this.DMNum = DMNum;
    }

    public String getFavNum() {
        return FavNum;
    }

    public void setFavNum(String favNum) {
        FavNum = favNum;
    }

    public String getUpName() {
        return UpName;
    }

    public void setUpName(String upName) {
        UpName = upName;
    }

    public String getUpIndexUrl() {
        return UpIndexUrl;
    }

    public void setUpIndexUrl(String upIndexUrl) {
        UpIndexUrl = upIndexUrl;
    }

    public String getVideoDate() {
        return VideoDate;
    }

    public void setVideoDate(String videoDate) {
        VideoDate = videoDate;
    }

    public Timestamp getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        UpdateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Timestamp createTime) {
        CreateTime = createTime;
    }

    public String getRemark1() {
        return Remark1;
    }

    public void setRemark1(String remark1) {
        Remark1 = remark1;
    }

    public String getRemark2() {
        return Remark2;
    }

    public void setRemark2(String remark2) {
        Remark2 = remark2;
    }

    public Integer getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(Integer isDelete) {
        IsDelete = isDelete;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "VideoUrlInfo{" +
                "Id=" + Id +
                ", FromUrl='" + FromUrl + '\'' +
                ", ImgUrl='" + ImgUrl + '\'' +
                ", VideoContent='" + VideoContent + '\'' +
                ", VideoHref='" + VideoHref + '\'' +
                ", Title='" + Title + '\'' +
                ", PlayNum='" + PlayNum + '\'' +
                ", DMNum='" + DMNum + '\'' +
                ", FavNum='" + FavNum + '\'' +
                ", UpName='" + UpName + '\'' +
                ", UpIndexUrl='" + UpIndexUrl + '\'' +
                ", VideoDate='" + VideoDate + '\'' +
                ", UpdateTime=" + UpdateTime +
                ", CreateTime=" + CreateTime +
                ", Remark1='" + Remark1 + '\'' +
                ", Remark2='" + Remark2 + '\'' +
                ", IsDelete=" + IsDelete +
                ", Status=" + Status +
                ", label='" + label + '\'' +
                ", classify=" + classify +
                '}';
    }
}
