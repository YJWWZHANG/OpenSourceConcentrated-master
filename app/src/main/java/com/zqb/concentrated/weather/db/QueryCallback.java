package com.zqb.concentrated.weather.db;

import java.util.List;

/**
 * Created by zqb on 2016/12/19.
 */

public interface QueryCallback<T> {
    void QueryProvinceCompleted(List<T> daoBeanList);
}
