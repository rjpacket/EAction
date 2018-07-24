package com.rjp.eaction.sort;

import com.rjp.eaction.bean.MatchDetailGameEventsModel;

import java.util.Comparator;

/**
 * Created by small on 2018/7/24.
 */

public class MatchDetailGameComparator implements Comparator<MatchDetailGameEventsModel> {
    @Override
    public int compare(MatchDetailGameEventsModel o1, MatchDetailGameEventsModel o2) {
        try {
            return Integer.parseInt(o1.getEventTime()) - Integer.parseInt(o2.getEventTime());
        }catch (Exception e){

        }
        return 0;
    }
}
