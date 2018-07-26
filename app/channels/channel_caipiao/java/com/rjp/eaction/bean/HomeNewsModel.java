package com.rjp.eaction.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class HomeNewsModel implements Serializable {

    /**
     * id : 87500
     * mid : 1002237
     * tid : 0
     * preponderant : 0
     * title : 16日阿甲推荐：求胜若渴  飓风队三分有望
     * summary : 北京时间2017年06月17日06:00，阿甲联赛第28轮精彩继续，其中飓风队将坐镇主场迎战圣塔菲联队。双方近6次对阵中，飓风队1胜2平3负稍处下风。
     * img : http://img.iuliao.com/display/438df2a43fed9d2f4468f1620a4add62
     * created : 1497604563
     * tag : 阿甲,飓风,圣菲联合
     */

    private long id;
    private int mid;
    private int tid;
    private int preponderant;
    private String title;
    private String summary;
    private String img;
    private long created;
    private String tag;
    private String newsTime;
    private String domain;
    private BigDecimal _score;
    private int newsHits;
    private int sport;
    private int status;
    private boolean concern;
    private String content;

    private String url;
    private String desc;
    private String time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getPreponderant() {
        return preponderant;
    }

    public void setPreponderant(int preponderant) {
        this.preponderant = preponderant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public BigDecimal get_score() {
        return _score;
    }

    public void set_score(BigDecimal _score) {
        this._score = _score;
    }

    public int getNewsHits() {
        return newsHits;
    }

    public void setNewsHits(int newsHits) {
        this.newsHits = newsHits;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isConcern() {
        return concern;
    }

    public void setConcern(boolean concern) {
        this.concern = concern;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        setNewsTime("时间：" + time);
    }
}
