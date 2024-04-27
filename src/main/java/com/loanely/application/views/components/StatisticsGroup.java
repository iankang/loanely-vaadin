package com.loanely.application.views.components;

import com.vaadin.flow.component.html.Div;

public class StatisticsGroup extends Div {

    public StatisticsGroup(StatisticComponent... statisticComponents) {
        addClassName("ui");
        addClassName("statistics");

        add(statisticComponents);
    }
}
