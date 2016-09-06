package br.com.nozinho.ejb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.nozinho.dao.DashboardDAO;
import br.com.nozinho.ejb.service.DashboardService;
import br.com.nozinho.model.Dashboard;
import br.com.nozinho.model.charts.Balloon;
import br.com.nozinho.model.charts.CategoryAxis;
import br.com.nozinho.model.charts.ChartCursor;
import br.com.nozinho.model.charts.Export;
import br.com.nozinho.model.charts.Graphs;
import br.com.nozinho.model.charts.PieChart;
import br.com.nozinho.model.charts.SerialChart;
import br.com.nozinho.model.charts.ValueAxes;
import br.com.nozinho.util.date.DateUtils;

@Stateless
public class DashboardServiceImpl implements DashboardService {

	@EJB
	private DashboardDAO dao;
	
	@Override
	public Dashboard getDashBoard() {
		Dashboard board = new Dashboard();
		board.setNumDemandas15(dao.getDemandasMenor15Dias());
		board.setNumDemandas30(dao.getDemandasEntre15E30Dias());
		board.setNumDemandas60(dao.getDemandasEntre30E60Dias());
		board.setNumDemandasMaior60(dao.getDemandasMais60Dias());
		board.setTotalDemandas(dao.getTotalDemandasPrazo());
		board.setSerialChart(createSerialChart(dao.getNumDemandaMesCurrentYear()));
		board.setPieChart(createPieChart(dao.getNumDemandasTipo()));
		return board;
	}

	
	private SerialChart createSerialChart(List<Object[]> data) {
		SerialChart chart = new SerialChart();
		chart.setType("serial");
		chart.setTheme("light");
		chart.setGridAboveGraphs(true);
		chart.setStartDuration(1);
		chart.setCategoryField("mes");
		chart.setExport(new Export(false));
		
		ValueAxes valueAxes = new ValueAxes();
		valueAxes.setGridColor("#FFFFFF");
		valueAxes.setGridAlpha(new BigDecimal("0.2"));
		valueAxes.setDashLength(0);
		chart.getValueAxes().add(valueAxes);
		
		Graphs graphs = new Graphs();
		graphs.setBalloonText("[[category]]: <b>[[value]]</b>");
		graphs.setFillAlphas(new BigDecimal("0.8"));
		graphs.setLineAlpha(new BigDecimal("0.2"));
		graphs.setType("column");
		graphs.setValueField("numDemandas");
		chart.getGraphs().add(graphs);
		
		ChartCursor cursor = new ChartCursor();
		cursor.setCategoryBalloonEnabled(false);
		cursor.setCursorAlpha(0);
		cursor.setZoomable(false);
		chart.setChartCursor(cursor);
		
		CategoryAxis category = new CategoryAxis();
		category.setGridPosition("start");
		category.setGridAlpha(0);
		category.setTickPosition("start");
		category.setTickLength(20);
		chart.setCategoryAxis(category);
		
		//Seta os dados
		List<Map<String, String>> dataProvider = new ArrayList<Map<String,String>>();
		for(int i = 1; i <= 12; i++){			
			Map<String, String> value = new HashMap<String, String>();
			for(Object[] obj : data){
				if(new Integer(obj[1].toString()) == i){
					value.put("numDemandas", obj[0].toString());
				} else {
					value.put("numDemandas", "0");
				}
			}
			value.put("mes", DateUtils.getMes(i));
			dataProvider.add(value);
		}
		chart.setDataProvider(dataProvider);
		
		return chart;
	}

	
	private PieChart createPieChart(List<Object[]> data){
		PieChart chart = new PieChart();
		chart.setType("pie");
		chart.setTheme("light");
		chart.setValueField("numDemandas");
		chart.setTitleField("tipoDemanda");
		chart.setBalloon(new Balloon(true));
		chart.setExport(new Export(false));
		List<Map<String, String>> dataProvider = new ArrayList<Map<String,String>>();
		for(Object[] obj : data){
			Map<String, String> value = new HashMap<String, String>();
			value.put("numDemandas", obj[0].toString());
			value.put("tipoDemanda", obj[1].toString());
			dataProvider.add(value);
		}
		chart.setDataProvider(dataProvider);
		return chart;
	}
	
	
	
}
