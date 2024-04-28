package com.loanely.application.views.lending;

import com.loanely.application.data.entities.LoanStatus;
import com.loanely.application.data.entities.Loans;
import com.loanely.application.data.enums.Qualification;
import com.loanely.application.services.LoanChannelService;
import com.loanely.application.services.LoanService;
import com.loanely.application.services.LoanStatusService;
import com.loanely.application.services.LoanTypeService;
import com.loanely.application.views.MainLayout;
import com.loanely.application.views.components.StatisticComponent;
import com.loanely.application.views.components.StatisticsGroup;
import com.storedobject.chart.*;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Lending")
@Route(value = "lending", layout = MainLayout.class)
public class LendingView extends VerticalLayout {

    private final LoanService loanService;
    private final LoanStatusService loanStatusService;
    private final LoanTypeService loanTypeService;
    private final LoanChannelService loanChannelService;

    public LendingView(
            LoanService loanService,
            LoanStatusService loanStatusService, LoanTypeService loanTypeService, LoanChannelService loanChannelService) {
        this.loanService = loanService;
        this.loanStatusService = loanStatusService;
        this.loanTypeService = loanTypeService;
        this.loanChannelService = loanChannelService;

        HorizontalLayout topStats = new HorizontalLayout();
        topStats.setWidthFull();
        topStats.setPadding(true);
        topStats.setSpacing(true);
        topStats.setAlignItems(FlexComponent.Alignment.STRETCH);
        topStats.setJustifyContentMode(JustifyContentMode.AROUND);

        StatisticComponent loanCount = new StatisticComponent(String.valueOf(loanService.count()), "Loan Count");
        StatisticComponent openloansCount = new StatisticComponent(String.valueOf(loanService.countByLoanStatus(loanStatusService.findStatusByStatusName("OPEN"))), "Open Loans Count");
        StatisticComponent closedloansCount = new StatisticComponent(String.valueOf(loanService.countByLoanStatus(loanStatusService.findStatusByStatusName("CLOSED"))), "Closed Loans Count");
        StatisticComponent pendingloansCount = new StatisticComponent(String.valueOf(loanService.countByLoanStatus(loanStatusService.findStatusByStatusName("PENDING"))), "Pending Loans Count");
        StatisticComponent failedloansCount = new StatisticComponent(String.valueOf(loanService.countByLoanStatus(loanStatusService.findStatusByStatusName("FAILED"))), "Failed Loans Count");
        StatisticsGroup group = new StatisticsGroup(loanCount, openloansCount,closedloansCount,pendingloansCount,failedloansCount);
        topStats.add(group);

        HorizontalLayout graphLayout= new HorizontalLayout();
        SOChart soChart = createBarChart(loanService, loanTypeService);
        SOChart channelChart = createChannelBarChart(loanService, loanChannelService);
        graphLayout.add(channelChart,soChart);


        GridCrud<Loans> loansGridCrud = new GridCrud<>(Loans.class, loanService);
        loansGridCrud.getCrudFormFactory().setUseBeanValidation(true);

        add(topStats, graphLayout,loansGridCrud);
    }

    private static @NotNull SOChart createBarChart(LoanService loanService, LoanTypeService loanTypeService) {
        SOChart soChart = new SOChart();
        soChart.setSize("800px", "500px");

        CategoryData loanTypeCategories = new CategoryData("Salary Advance","Utility Loan","Monthly loan","Asset Loan");
        Data loanTypeData = new Data((Number) loanService.countByLoanType(loanTypeService.findByLoanType("SALARY_ADVANCE")),
                (Number) loanService.countByLoanType(loanTypeService.findByLoanType("UTILITY_LOAN")),
                (Number) loanService.countByLoanType(loanTypeService.findByLoanType("MONTHLY_LOAN")),
                (Number) loanService.countByLoanType(loanTypeService.findByLoanType("ASSET_LOAN"))

                );
        BarChart bc = new BarChart(loanTypeCategories,loanTypeData);
        RectangularCoordinate rc = new RectangularCoordinate(new XAxis(DataType.CATEGORY), new YAxis(DataType.NUMBER));
        bc.plotOn(rc);

        // Just to demonstrate it, we are creating a "Download" and a "Zoom" toolbox button.
        Toolbox toolbox = new Toolbox();
        toolbox.addButton(new Toolbox.Download(), new Toolbox.Zoom());

// Let's add some titles.
        Title title = new Title("Loan Count by Type");
        title.setSubtext("Distribution of loans across by type");
        soChart.add(bc,toolbox,title);
        return soChart;
    }

    private static @NotNull SOChart createChannelBarChart(LoanService loanService, LoanChannelService loanChannelService) {
        SOChart soChart = new SOChart();
        soChart.setSize("800px", "500px");

        CategoryData loanTypeCategories = new CategoryData("USSD","Mobile App","Website","SMS");
        Data loanTypeData = new Data((Number) loanService.countByLoanChannel(loanChannelService.findByLoanChannel("USSD")),
                (Number) loanService.countByLoanChannel(loanChannelService.findByLoanChannel("MOBILE_APP")),
                (Number) loanService.countByLoanChannel(loanChannelService.findByLoanChannel("WEBSITE")),
                (Number) loanService.countByLoanChannel(loanChannelService.findByLoanChannel("SMS"))

                );
        BarChart bc = new BarChart(loanTypeCategories,loanTypeData);
        RectangularCoordinate rc = new RectangularCoordinate(new XAxis(DataType.CATEGORY), new YAxis(DataType.NUMBER));
        bc.plotOn(rc);

        // Just to demonstrate it, we are creating a "Download" and a "Zoom" toolbox button.
        Toolbox toolbox = new Toolbox();
        toolbox.addButton(new Toolbox.Download(), new Toolbox.Zoom());

// Let's add some titles.
        Title title = new Title("Loan Count by Channel");
        title.setSubtext("Distribution of loans by Channel");
        soChart.add(bc,toolbox,title);
        return soChart;
    }

}
