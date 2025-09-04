package com.sampleProject.FinancialTracker.Service.Statistics;

import com.sampleProject.FinancialTracker.Model.GraphModel;
import com.sampleProject.FinancialTracker.Model.StatsModel;


public interface StatService {
    GraphModel getChartData();
    StatsModel getStat();
}
