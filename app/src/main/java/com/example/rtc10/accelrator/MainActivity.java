package com.example.rtc10.accelrator;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor mAccelerometer;
    TextView textView;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    float x = 0;
    float y = 0;
    float  max=0;
    @Override
    public void onSensorChanged(SensorEvent event) {



        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            if (Math.abs(event.values[0])>max){
                max = event.values[0];
            }
            Log.e("eventX",String.valueOf(event.values[0]));
            textView.setText(String.valueOf(event.values[0]));


        }




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e("accuracy",String.valueOf(accuracy));
    }

    int i=0;
    public void fn_start(View v){
        if (i==0){
            max=0;
            x = 0;
            i++;
            textView2.setText("0");
        }else {
            i=0;
            textView2.setText(String.valueOf(max));
        }

    }
}
