package com.gym.model;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class ArduinoDataListener implements SerialPortDataListener {
	private static String mensaje = "";
	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
        if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
            byte[] newData = new byte[event.getSerialPort().bytesAvailable()];
            event.getSerialPort().readBytes(newData, newData.length);
//            if(new String(newData).contains("\n")) {
//            	System.out.println(mensaje);
//            	mensaje = "";
//            }
            
            mensaje += new String(newData);
            System.out.println(mensaje);
            
        }
	}

}
