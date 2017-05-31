package com.zqb.concentrated.weather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zqb on 2016/12/14.
 */

@Entity
public class ProvinceDaoBean {

    @Id
    private Long id;

    @NotNull
    private String provinceName;
    @NotNull
    private String provincePyName;
    @Generated(hash = 67460963)
    public ProvinceDaoBean(Long id, @NotNull String provinceName,
            @NotNull String provincePyName) {
        this.id = id;
        this.provinceName = provinceName;
        this.provincePyName = provincePyName;
    }
    @Generated(hash = 1180633319)
    public ProvinceDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getProvincePyName() {
        return this.provincePyName;
    }
    public void setProvincePyName(String provincePyName) {
        this.provincePyName = provincePyName;
    }

    @Override
    public String toString() {
        return "ProvinceDaoBean{" +
                "id=" + id +
                ", provinceName='" + provinceName + '\'' +
                ", provincePyName='" + provincePyName + '\'' +
                '}';
    }
}
