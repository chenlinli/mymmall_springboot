package com.mmall.utils;

import com.mmall.config.FTPConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class FTPUtil {

    @Autowired
    private FTPConfig ftpConfig;

    private static String ftpIP;
    private static String ftpUser;
    private static String ftpPass;

    @PostConstruct
    public void init(){
        ftpIP=ftpConfig.getServerIp();
        ftpUser=ftpConfig.getUser();
        ftpPass = ftpConfig.getPass();
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIP,21,ftpUser,ftpPass);
        log.info("开始连接Ftp服务器");
        boolean result = ftpUtil.uploadFile("img",fileList);
        log.info("开始链接ftp服务器，结束上传，上传结果:{}",result);
        return result;

    }

    //多个文件上传到ftp服务器的文件夹/文件夹
    private  boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //链接服务器
        if(connectServer(getIp(),getPort(),getUser(),getPwd())){
            try {
                //切换文件夹：//这里ftp服务器上没有img文件夹，切换目录不成功返回false
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //打开本地被动模式
                ftpClient.enterLocalPassiveMode();
                for(File file : fileList){
                    fis = new FileInputStream(file);
                    ftpClient.storeFile(file.getName(),fis);
                }

            } catch (IOException e) {
                uploaded=false;
                log.error("上传文件异常");
            }finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;

    }

    private boolean connectServer(String ip, int port, String user, String pwd){
        boolean loginSuccess=false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            loginSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            log.error("连接ftp服务器异常");
        }
        return loginSuccess;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
