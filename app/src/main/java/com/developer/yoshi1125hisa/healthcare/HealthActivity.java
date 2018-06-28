package com.developer.yoshi1125hisa.healthcare;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HealthActivity extends AppCompatActivity implements SensorEventListener{
    protected BarChart chart;

    private SensorManager manager;
    private Sensor delectorSensor;
    private Sensor stepCntSensor;
    private int stepcount = 0;
    private int stepcount2 = 0;

    String walkCount = String.valueOf(stepcount);

    String gender = "Male";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg;
    Button mPostButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);




        DatabaseReference sendsRef = FirebaseDatabase.getInstance().getReference("users");
        sendsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dSnapshot : snapshot.getChildren()) {
                    // getApplication()でアプリケーションクラスのインスタンスを拾う
                    String key = dSnapshot.getKey();
                    walkCount = (String) dSnapshot.child("walkCount").getValue();
                    gender = (String) dSnapshot.child("gender").getValue();




                }
                // 保存した情報を用いた描画処理などを記載する。
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               // Log.d("error","error");
            }
        });






        refMsg = database.getReference("users");
        mPostButton = findViewById(R.id.post);


        mPostButton.findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();

                switch (id) {
                    case R.id.post:

                        final Post post = new Post(walkCount,gender);
                        refMsg.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //Log.d("ondatachange", dataSnapshot.getRef().push().getKey().toString());
                               // Intent intent = new Intent(PostActivity.this, SelectActivity.class);
                               // intent.putExtra("postData", dataSnapshot.getValue(Post.class));
                               // startActivity(intent);

                                //取得方法
                                //post.get**;
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        refMsg.push().setValue(post);
                        break;
                }

                Intent intent = new Intent(HealthActivity.this,BankActivity.class);
                startActivity(intent);
        }});




        chart = findViewById(R.id.bar_chart);

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



        //表示データ取得
        BarData data = new BarData(getBarData());
        chart.setData(data);

        //Y軸(左)
        YAxis left = chart.getAxisLeft();
        left.setAxisMinimum(0);
        left.setAxisMaximum(10000);
        left.setLabelCount(5);
        left.setDrawTopYLabelEntry(true);


        //整数表示に
        left.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "" + (int)value;
            }
        });




        //センサーマネージャを取得
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //センサマネージャから TYPE_STEP_DETECTOR についての情報を取得する
        delectorSensor = manager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        //センサマネージャから TYPE_STEP_COUNTER についての情報を取得する
        stepCntSensor = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // accuracy に変更があった時の処理
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;
        float[] values = event.values;
        long timestamp = event.timestamp;

        //TYPE_STEP_COUNTER
        if(sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            // 今までの歩数
            Log.d("type_step_counter", String.valueOf(values[0]));
            stepcount2++;
            //textview2.setText("STEP_COUNTER=" + stepcount2 + "歩");
        }
        if(sensor.getType() == Sensor.TYPE_STEP_DETECTOR){
            // ステップを検知した場合にアクセス
            Log.d("type_detector_counter", String.valueOf(values[0]));
            stepcount++;
            //textview.setText("STEP_DETECTOR=" + stepcount + "歩");

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // リスナー設定
        manager.registerListener (this,
                stepCntSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        manager.registerListener(this,
                delectorSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // リスナー解除
        manager.unregisterListener(this, stepCntSensor);
        manager.unregisterListener(this, delectorSensor);
    }




    //棒グラフのデータを取得
    private List<IBarDataSet> getBarData(){
//表示させるデータ
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 6352));
        entries.add(new BarEntry(2, 2637));
        entries.add(new BarEntry(3, 5000));
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




