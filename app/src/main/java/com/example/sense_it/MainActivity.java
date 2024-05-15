package com.example.sense_it;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager != null){
            Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if(lightSensor != null){
                sensorManager.registerListener(this,lightSensor ,SensorManager.SENSOR_DELAY_NORMAL);
            }
            else{
                Toast.makeText(this, "Light Sensor not Found", Toast.LENGTH_SHORT).show();
            }

            Sensor accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(accSensor != null) {
                sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            else{
                Toast.makeText(this, "Accelerometer Sensor not Found", Toast.LENGTH_SHORT).show();
            }

            Sensor gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            if(gyroSensor != null) {
                sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            else{
                Toast.makeText(this, "Gyroscope Sensor not Found", Toast.LENGTH_SHORT).show();
            }

            Sensor proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if(proxSensor != null) {
                sensorManager.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            else{
                Toast.makeText(this, "Proximity Sensor not Found", Toast.LENGTH_SHORT).show();
            }

            Sensor gameRotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
            if(gameRotationSensor != null) {
                sensorManager.registerListener(this, gameRotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
            else{
                Toast.makeText(this, "Game Rotation Sensor not Found", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){

            ((TextView)(findViewById(R.id.valueLight))).setText(String.format("%s" , event.values[0]));
        }
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            ((TextView)(findViewById(R.id.valueAccelerometer_x))).setText(String.format( "x = %s" , event.values[0]));
            ((TextView)(findViewById(R.id.valueAccelerometer_y))).setText(String.format( "y = %s" , event.values[1]));
            ((TextView)(findViewById(R.id.valueAccelerometer_z))).setText(String.format( "z = %s" , event.values[2]));
        }
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            ((TextView)(findViewById(R.id.valueGyroscope_x))).setText(String.format( "x = %s" , event.values[0]));
            ((TextView)(findViewById(R.id.valueGyroscope_y))).setText(String.format( "y = %s" , event.values[1]));
            ((TextView)(findViewById(R.id.valueGyroscope_z))).setText(String.format( "z = %s" , event.values[2]));
        }

        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            ((TextView)(findViewById(R.id.valueProximity))).setText(String.format("%s" , event.values[0]));
        }

        if(event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR){
            ((TextView)(findViewById(R.id.value_game_rotation_vector_w))).setText(String.format("w = %s" , event.values[0]));
            ((TextView)(findViewById(R.id.value_game_rotation_vector_x))).setText(String.format("x = %s" , event.values[1]));
            ((TextView)(findViewById(R.id.value_game_rotation_vector_y))).setText(String.format("y = %s" , event.values[2]));
            ((TextView)(findViewById(R.id.value_game_rotation_vector_z))).setText(String.format("z = %s" , event.values[3]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}