package com.developer.yoshi1125hisa.healthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class SleepActivity extends AppCompatActivity {
    protected BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

    chart = findViewById(R.id.bar_chart);



    //表示データ取得
    BarData data = new BarData(getBarData());
        chart.setData(data);

    //Y軸(左)
    YAxis left = chart.getAxisLeft();
        left.setAxisMinimum(0);
        left.setAxisMaximum(10);
        left.setLabelCount(5);
        left.setDrawTopYLabelEntry(true);

    //整数表示に
        left.setValueFormatter(new IAxisValueFormatter() {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return "" + (int)value;
        }
    });

    //Y軸(右)
    YAxis right = chart.getAxisRight();
        right.setDrawLabels(false);
        right.setDrawGridLines(false);
        right.setDrawZeroLine(true);
        right.setDrawTopYLabelEntry(true);

    //X軸
    XAxis xAxis = chart.getXAxis();
    //X軸に表示するLabelのリスト(最初の""は原点の位置)
    final String[] labels = {"","6/15", "6/16", "6/17"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
    XAxis bottomAxis = chart.getXAxis();
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottomAxis.setDrawLabels(true);
        bottomAxis.setDrawGridLines(false);
        bottomAxis.setDrawAxisLine(true);

    //グラフ上の表示
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setClickable(false);

    //凡例
        chart.getLegend().setEnabled(false);

        chart.setScaleEnabled(false);
    //アニメーション
        chart.animateY(1200, Easing.EasingOption.Linear);
}

    //棒グラフのデータを取得
    private List<IBarDataSet> getBarData(){
//表示させるデータ
        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1, 6));
        entries.add(new BarEntry(2, 3));
        entries.add(new BarEntry(3, 8));
        List<IBarDataSet> bars = new ArrayList<>();
        BarDataSet dataSet = new BarDataSet(entries, "bar");


        //整数で表示
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "" + (int) value;
            }
        });
        //ハイライトさせない
        dataSet.setHighlightEnabled(false);

        //Barの色をセット
        dataSet.setColors(new int[]{R.color.BLACK, R.color.BLACK, R.color.BLACK}, this);
        bars.add(dataSet);

        return bars;
    }

}
