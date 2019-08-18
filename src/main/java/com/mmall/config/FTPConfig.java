package com.mmall.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.groups.ConvertGroup;

@PropertySource(value = "classpath:mmall.properties")
@ConfigurationProperties(prefix = "ftp")
@Getter
@Setter
@Component
public class FTPConfig {

    private String serverIp;
    private String user;
    private String pass;
    private String serverHttpPrefix;

}
