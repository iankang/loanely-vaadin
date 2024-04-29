package com.loanely.application.views.stats;


import com.loanely.application.services.LoanService;
import com.loanely.application.views.MainLayout;
import com.loanely.application.views.stats.ServiceHealth.Status;
import com.storedobject.chart.*;
import com.storedobject.chart.XAxis;
import com.storedobject.chart.YAxis;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

@PageTitle("stats")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class StatsView extends VerticalLayout {

    private final LoanService loanService;
    public StatsView(LoanService loanService) {

        this.loanService = loanService;

        HorizontalLayout mainGraph = new HorizontalLayout();

        // Creating a chart display area
        SOChart soChart = new SOChart();
        soChart.setSize("1200px", "500px");

        // Data (common to all charts)
        CategoryData x = new CategoryData("Banana", "Mango", "Apple", "Juice", "Orange");
        Data y1 = new Data((Number) 5, (Number)9, (Number)6, (Number)7,(Number) 8);
        Data y2 = new Data((Number)4, (Number)8, (Number)7,(Number) 8,(Number) 9);

        // Chart I - Independent bars

        BarChart barChart1 = new BarChart(x, y1);
        barChart1.setName("Capacity");

        BarChart barChart2 = new BarChart(x, y2);
        barChart2.setName("Used Capacity");
        barChart2.setColors(new Color("#25B15F"));

        XAxis xAxis1 = new XAxis(DataType.CATEGORY);
        YAxis yAxis = new YAxis(DataType.NUMBER);

        RectangularCoordinate rc = new RectangularCoordinate(xAxis1, yAxis);
        rc.getPosition(true).setRight(Size.percentage(70));

        barChart1.plotOn(rc);
        barChart2.plotOn(rc);

        // Add to the chart display
        soChart.add(rc);
        Text text = new Text(loanService.getDateAndSum().toString());
        mainGraph.add(soChart, text);
        add(mainGraph);
    }


}
