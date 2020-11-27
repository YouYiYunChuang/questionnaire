package com.yyc.questionnaire.domain.metrics.appquality;

import com.yyc.questionnaire.domain.metrics.MainMetric;
import com.yyc.questionnaire.domain.metrics.MainMetricType;
import com.yyc.questionnaire.domain.metrics.devquality.BugMetric;
import com.yyc.questionnaire.domain.user.UserProfile;

public class AppQualityMetric extends MainMetric {

    private AppMetric appMetric;

    public AppQualityMetric(UserProfile metricOwner){
        this.metricOwner = metricOwner;
        metricOwner.setAppQualityMetric(this);
        this.metricMainType = MainMetricType.APP_QUALITY;
    }

    @Override
    public double getWeight() {
        return metricOwner.getWeight().getAppQualityWeight();
    }
}
