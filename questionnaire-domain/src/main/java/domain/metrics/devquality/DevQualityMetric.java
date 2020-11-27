package com.yyc.questionnaire.domain.metrics.devquality;

import com.yyc.questionnaire.domain.metrics.MainMetric;
import com.yyc.questionnaire.domain.metrics.MainMetricType;
import com.yyc.questionnaire.domain.user.UserProfile;
import lombok.Data;

@Data
public class DevQualityMetric extends MainMetric {

    private BugMetric bugMetric;

    public DevQualityMetric(UserProfile metricOwner){
        this.metricOwner = metricOwner;
        metricOwner.setDevQualityMetric(this);
        this.metricMainType = MainMetricType.DEV_QUALITY;
    }

    @Override
    public double getWeight() {
        return metricOwner.getWeight().getDevQualityWeight();
    }
}
