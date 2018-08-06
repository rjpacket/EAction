package com.rjp.eaction.bean;

import com.rjp.baselistview.pinned.PinnedModel;

import java.util.List;

/**
 * author : Gimpo create on 2018/4/13 12:51
 * email  : jimbo922@163.com
 */

public class JCZQBiFenGroupModel extends PinnedModel<JCZQBiFenModel> {

    private String period;
    private List<JCZQBiFenModel> matchInfo;

    public JCZQBiFenGroupModel(){
        super(null);
    }

    public JCZQBiFenGroupModel(List<JCZQBiFenModel> children) {
        super(children);
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<JCZQBiFenModel> getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(List<JCZQBiFenModel> matchInfo) {
        this.matchInfo = matchInfo;
        children = matchInfo;
    }
}
