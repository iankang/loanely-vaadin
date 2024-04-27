package com.loanely.application.views.qualifiedbase;

import com.loanely.application.data.entities.SubscriberEntity;
import com.loanely.application.data.enums.Gender;
import com.loanely.application.data.enums.Qualification;
import com.loanely.application.services.SubscriberService;
import com.loanely.application.views.MainLayout;
import com.loanely.application.views.components.StatisticComponent;
import com.loanely.application.views.components.StatisticsGroup;
import com.storedobject.chart.CategoryData;
import com.storedobject.chart.Data;
import com.storedobject.chart.NightingaleRoseChart;
import com.storedobject.chart.SOChart;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Qualified Base")
@Route(value = "qualified-base", layout = MainLayout.class)
@Uses(Icon.class)
public class QualifiedBaseView extends VerticalLayout {

    private final SubscriberService subscriberService;

    public QualifiedBaseView(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
        setSizeFull();
        addClassNames("qualified-base-view");

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        horizontalLayout.setPadding(true);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.AROUND);


        StatisticComponent qualified = new StatisticComponent(String.valueOf(subscriberService.countByQualification(Qualification.QUALIFIED)), "Qualified");
        StatisticComponent unqualified = new StatisticComponent(String.valueOf(subscriberService.countByQualification(Qualification.UNQUALIFIED)), "Unqualified");

        StatisticComponent men = new StatisticComponent(String.valueOf(subscriberService.countByGender(Gender.MALE)), "Male");
        StatisticComponent women = new StatisticComponent(String.valueOf(subscriberService.countByGender(Gender.FEMALE)), "Female");
        StatisticComponent otherGender = new StatisticComponent(String.valueOf(subscriberService.countByGender(Gender.OTHER)), "Non binary");

        StatisticsGroup group = new StatisticsGroup(qualified, unqualified, men,women,otherGender);
        horizontalLayout.add(group);

        HorizontalLayout graphHorizontalLayout  = new HorizontalLayout();
        SOChart soChart = new SOChart();
        soChart.setSize("800px", "500px");


// Let us define some inline data.
        CategoryData labels = new CategoryData("Qualified", "Unqualified");
        Data data = new Data((Number) subscriberService.countByQualification(Qualification.QUALIFIED), (Number) subscriberService.countByQualification(Qualification.UNQUALIFIED));

// We are going to create a couple of charts. So, each chart should be positioned
// appropriately.
// Create a self-positioning chart.
        NightingaleRoseChart nc = new NightingaleRoseChart(labels, data);
//        Position p = new Position();
//        p.setTop(Size.percentage(50));
//        nc.setPosition(p);
        soChart.add(nc);

        SOChart genderChart = new SOChart();
        genderChart.setSize("800px","500px");

        CategoryData genderLabels = new CategoryData("Men", "Women", "Non binary");
        Data genderData = new Data((Number) subscriberService.countByGender(Gender.MALE),(Number) subscriberService.countByGender(Gender.FEMALE),(Number) subscriberService.countByGender(Gender.OTHER));

        NightingaleRoseChart genderNightingale = new NightingaleRoseChart(genderLabels,genderData);
        genderChart.add(genderNightingale);
        graphHorizontalLayout.add(soChart, genderChart);

        HorizontalLayout graphLayout = new HorizontalLayout();
        graphLayout.setWidthFull();
        graphLayout.setPadding(true);
        graphLayout.setSpacing(true);
        GridCrud<SubscriberEntity> subscriberEntityGridCrud = new GridCrud<>(SubscriberEntity.class,subscriberService);
        subscriberEntityGridCrud.getCrudFormFactory().setUseBeanValidation(true);

        graphLayout.add(subscriberEntityGridCrud);
        add(horizontalLayout, graphHorizontalLayout,graphLayout);
    }


}
