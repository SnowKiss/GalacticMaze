package projetandroid.galacticmaze.Vue;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by John on 23/01/2018.
 */

public class InputManager implements SensorEventListener {
    private float deltaX = 0;
    private float deltaY = 0;

    private float lastX, lastY;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private Activity currentActivity;

    public InputManager(Activity act) {
        this.currentActivity=act;
        sensorManager = (SensorManager) this.currentActivity.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            // fail! we dont have an accelerometer!
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // get the change of the x,y values of the accelerometer
        deltaX = lastX - event.values[0];
        deltaY = lastY - event.values[1];

        // if the change is below 0.2, it is just plain noise
        if (deltaX < 0.2 && deltaX > -0.2)
            deltaX = 0;
        if (deltaY < 0.2 && deltaY > -0.2)
            deltaY = 0;

        // reversed Y axis
        deltaY = -deltaY;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float getDeltaX() {
        return deltaX/50;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public float getDeltaY() {
        return deltaY/50;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    public float getLastX() {
        return lastX;
    }

    public void setLastX(float lastX) {
        this.lastX = lastX;
    }

    public float getLastY() {
        return lastY;
    }

    public void setLastY(float lastY) {
        this.lastY = lastY;
    }

    public SensorManager getSensorManager() {
        return sensorManager;
    }

    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    public Sensor getAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(Sensor accelerometer) {
        this.accelerometer = accelerometer;
    }
}

