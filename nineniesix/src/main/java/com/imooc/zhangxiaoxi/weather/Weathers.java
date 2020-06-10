/**
 * Copyright (C), 2020, XXX公司
 * FileName: Weathers
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.zhangxiaoxi.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-10 17:23
 */
public class Weathers {
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;

    private WeatherResponse doGetWeahter(String uri) {
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        String strBody = null;

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }
}

class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Weather data;
    private Integer status;
    private String desc;
    public Weather getData() {
        return data;
    }
    public void setData(Weather data) {
        this.data = data;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
    private String aqi;
    private String ganmao;
    private String wendu;
    private Yeaterday yesterday;
    private List<Forecast> forecast;
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAqi() {
        return aqi;
    }
    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
    public String getGanmao() {
        return ganmao;
    }
    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }
    public String getWendu() {
        return wendu;
    }
    public void setWendu(String wendu) {
        this.wendu = wendu;
    }
    public Yeaterday getYesterday() {
        return yesterday;
    }
    public void setYesterday(Yeaterday yesterday) {
        this.yesterday = yesterday;
    }
    public List<Forecast> getForecast() {
        return forecast;
    }
    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

}
class Yeaterday implements Serializable {
    private static final long serialVersionUID = 1L;
    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHigh() {
        return high;
    }
    public void setHigh(String high) {
        this.high = high;
    }
    public String getFx() {
        return fx;
    }
    public void setFx(String fx) {
        this.fx = fx;
    }
    public String getLow() {
        return low;
    }
    public void setLow(String low) {
        this.low = low;
    }
    public String getFl() {
        return fl;
    }
    public void setFl(String fl) {
        this.fl = fl;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
class Forecast implements Serializable {

    private static final long serialVersionUID = 1L;
    private String date;
    private String high;
    private String fengli;
    private String low;
    private String fengxiang;
    private String type;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHigh() {
        return high;
    }
    public void setHigh(String high) {
        this.high = high;
    }
    public String getFengli() {
        return fengli;
    }
    public void setFengli(String fengli) {
        this.fengli = fengli;
    }
    public String getLow() {
        return low;
    }
    public void setLow(String low) {
        this.low = low;
    }
    public String getFengxiang() {
        return fengxiang;
    }
    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}