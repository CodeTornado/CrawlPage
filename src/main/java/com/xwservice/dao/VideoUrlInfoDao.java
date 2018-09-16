package com.xwservice.dao;

import com.xwservice.bean.VideoUrlInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface VideoUrlInfoDao {

    @Insert("INSERT INTO `videoinfo`(" +
            "`FromUrl`," +
            "`ImgUrl`," +
            "`VideoContent`," +
            "`VideoHref`," +
            "`Title`," +
            "`PlayNum`," +
            "`DMNum`," +
            "`FavNum`," +
            "`UpName`," +
            "`UpIndexUrl`," +
            "`VideoDate`," +
            "`Remark1`," +
            "`Remark2`," +
            "`label`," +
            "`classify`" +
            ")" +
            "VALUES" +
            "(" +
            "#{FromUrl}," +
            "#{ImgUrl}," +
            "#{VideoContent}," +
            "#{VideoHref}," +
            "#{Title}," +
            "#{PlayNum}," +
            "#{DMNum}," +
            "#{FavNum}," +
            "#{UpName}," +
            "#{UpIndexUrl}," +
            "#{VideoDate}," +
            "#{Remark1}," +
            "#{Remark2}," +
            "#{label}," +
            "#{classify}" +
            ")")
    public int insertVideoInfo(VideoUrlInfo videoInfo);

}
