/**
 * <ul>
 * <li>SensorsListTuto</li>
 * <li>com.android2ee.android.tuto.sensorlist</li>
 * <li>17 Novembre 2011</li>
 * 
 * <li>======================================================</li>
 * 
 * <li>Projet : Mathias Seguy (Android2EE)</li>
 * <li>Produit par MSE.</li>
 *
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br> 
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * 
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br> 
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */
package com.android2ee.android.tuto.sensorlist;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SensorsListTutoActivity extends Activity {
	/**
	 * The Tag for the Log
	 */
	private static final String LOG_TAG = "SensorsList";
	/** * The sensor manager */
	SensorManager sensorManager;
	/**
	 * The hello message
	 */
	private TextView txvHello;
	/**
	 * The Layout Parameter used to add Name in LilContent
	 */
	LinearLayout.LayoutParams lParamsName;

	/******************************************************************************************/
	/** Manage life cycle **************************************************************************/
	/******************************************************************************************/
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// instanciate the components of the main.xml layout declaration
		txvHello = (TextView) findViewById(R.id.txvHello);
		txvHello.setBackgroundColor(0xFFCFDBEC);
		txvHello.setTextColor(0xFF000000);
		// list the sensor:
		Log.e(LOG_TAG, "");
		// Instanciate the SensorManager
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// The find out which sensors are installed
		listSensor();
	}

	/************************************************************************************/
	/** List existing Sensors And build GUI **********************************************/
	/************************************************************************************/
	/**
	 * * Find the list of all the sensors
	 */
	private void listSensor() {
		// Then you can find all the sensors installed:
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		// The associated string description
		StringBuffer sensorDesc = new StringBuffer();
		// the current unit
		String unit;
		// Browse the sensor and build the associated GUI
		for (Sensor sensor : sensors) {
			// find the unit
			unit = getUnit(sensor.getType());
			// Build the string description
			sensorDesc.append("Type: " + getType(sensor.getType()) + "\r\n");
			sensorDesc.append("Unit: " + getUnitName(sensor.getType()) + "\r\n");
			sensorDesc.append("Resolution: " + sensor.getResolution() + " " + unit + "\r\n");
			sensorDesc.append("Power used: " + sensor.getPower() + "mA\r\n");
			sensorDesc.append("Max. range of the sensor: " + sensor.getMaximumRange() + " " + unit + "\r\n");
			sensorDesc.append("Min. delay between two events: " + sensor.getMinDelay() + " mS\r\n"
					+ "(zero if this sensor only returns a value when the data changes)" + "\r\n");
			sensorDesc.append("Vendor: " + sensor.getVendor() + "\r\n");
			sensorDesc.append("Version: " + sensor.getVersion() + "\r\n\r\n");
		}
		// Log the result
		Log.i(LOG_TAG, sensorDesc.toString());
		// change the Hello string
		txvHello.setText(txvHello.getText() + "\r\n" + sensorDesc.toString());
	}

	/******************************************************************************************/
	/** Information on sensor **************************************************************************/
	/******************************************************************************************/
	/**
	 * @param type
	 *            the integer type
	 * @return the type as a string
	 */
	private String getType(int type) {
		String strType;
		switch (type) {
		case Sensor.TYPE_ACCELEROMETER:
			strType = "TYPE_ACCELEROMETER";
			break;
		case Sensor.TYPE_GRAVITY:
			strType = "TYPE_GRAVITY";
			break;
		case Sensor.TYPE_GYROSCOPE:
			strType = "TYPE_GYROSCOPE";
			break;
		case Sensor.TYPE_LIGHT:
			strType = "TYPE_LIGHT";
			break;
		case Sensor.TYPE_LINEAR_ACCELERATION:
			strType = "TYPE_LINEAR_ACCELERATION";
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			strType = "TYPE_MAGNETIC_FIELD";
			break;
		case Sensor.TYPE_ORIENTATION:
			strType = "TYPE_ORIENTATION";
			break;
		case Sensor.TYPE_PRESSURE:
			strType = "TYPE_PRESSURE";
			break;
		case Sensor.TYPE_PROXIMITY:
			strType = "TYPE_PROXIMITY";
			break;
		case Sensor.TYPE_ROTATION_VECTOR:
			strType = "TYPE_ROTATION_VECTOR";
			break;
		case Sensor.TYPE_TEMPERATURE:
			strType = "TYPE_TEMPERATURE";
			break;
		default:
			strType = "TYPE_UNKNOW";
			break;
		}
		return strType;
	}

	/**
	 * @param type
	 *            the integer type
	 * @return the unit of the sensor as a string
	 */
	private String getUnitName(int type) {
		String strType;
		switch (type) {
		case Sensor.TYPE_ACCELEROMETER:
		case Sensor.TYPE_GRAVITY:
		case Sensor.TYPE_LINEAR_ACCELERATION:
			strType = "Meter/Second^2";
			break;
		case Sensor.TYPE_GYROSCOPE:
			strType = "Radian/Second";
			break;
		case Sensor.TYPE_LIGHT:
			strType = "Lux";
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			strType = "µTesla";
			break;
		case Sensor.TYPE_ORIENTATION:
			strType = "Degrees";
			break;
		case Sensor.TYPE_PRESSURE:
			strType = "KPascal";
			break;
		case Sensor.TYPE_PROXIMITY:
			strType = "Meter";
			break;
		case Sensor.TYPE_ROTATION_VECTOR:
			strType = "Unitless";
			break;
		case Sensor.TYPE_TEMPERATURE:
			strType = "Celsius";
			break;
		default:
			strType = "TYPE_UNKNOW";
			break;
		}
		return strType;
	}

	/**
	 * @param type
	 *            the integer type
	 * @return the unit of the sensor as a string
	 */
	private String getUnit(int type) {
		String strType;
		switch (type) {
		case Sensor.TYPE_ACCELEROMETER:
		case Sensor.TYPE_GRAVITY:
		case Sensor.TYPE_LINEAR_ACCELERATION:
			strType = "m/s^2";
			break;
		case Sensor.TYPE_GYROSCOPE:
			strType = "R/s";
			break;
		case Sensor.TYPE_LIGHT:
			strType = "L";
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			strType = "µT";
			break;
		case Sensor.TYPE_ORIENTATION:
			strType = "°";
			break;
		case Sensor.TYPE_PRESSURE:
			strType = "KP";
			break;
		case Sensor.TYPE_PROXIMITY:
			strType = "m";
			break;
		case Sensor.TYPE_ROTATION_VECTOR:
			strType = "";
			break;
		case Sensor.TYPE_TEMPERATURE:
			strType = "C";
			break;
		default:
			strType = "?";
			break;
		}
		return strType;
	}
}