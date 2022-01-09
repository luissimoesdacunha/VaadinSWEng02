package pt.ismt.exemplo.ui.views.dashboard;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import pt.ismt.exemplo.backend.service.AlunoService;
import pt.ismt.exemplo.ui.MainLayout;

@PageTitle("Dashboard | Vaadin CRM")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    private final AlunoService alunoService;

    public DashboardView(AlunoService alunoService) {
        this.alunoService = alunoService;

        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(
            getContactStats()
            // getCompaniesChart()
        );
    }

    private Span getContactStats() {
        Span stats = new Span(alunoService.count() + " alunos");
        stats.addClassName("alunos-stats");

        return stats;
    }

    /*
    private Component getCompaniesChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        Map<String, Integer> stats = companyService.getStats();
        stats.forEach((name, number) ->
            dataSeries.add(new DataSeriesItem(name, number)));

        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
     */
}
