package com.zlp.soap.ftp;

public interface GetFtpFileRecordMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(GetFtpFileRecord record);

    int insertSelective(GetFtpFileRecord record);

    GetFtpFileRecord selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(GetFtpFileRecord record);

    int updateByPrimaryKey(GetFtpFileRecord record);
}