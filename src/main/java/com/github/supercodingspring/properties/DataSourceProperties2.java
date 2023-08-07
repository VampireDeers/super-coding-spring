package com.github.supercodingspring.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "datasource2")
public class DataSourceProperties2 {
    private String username;
    private String password;
    private String url;
    private String driverClassName;
}
