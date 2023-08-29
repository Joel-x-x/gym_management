package com.gym.model;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.gym.view.AgregarHuellaFrame;

public class ArduinoDataListener implements SerialPortDataListener {
	private static String mensaje = "";
	private static AgregarHuellaFrame agregarHuellaFrame;
	private boolean retirarHuella = true;
	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}
	
	public static void setClass(AgregarHuellaFrame agregarHuellaFrame) {
		ArduinoDataListener.agregarHuellaFrame = agregarHuellaFrame;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		
        if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
            byte[] newData = new byte[event.getSerialPort().bytesAvailable()];
            event.getSerialPort().readBytes(newData, newData.length);

            // Mensaje
            mensaje += new String(newData);
            System.out.println(mensaje);
            
            // Procesar respuesta
            if(mensaje.contains("f")) {
            	agregarHuellaFrame.modificarLabel("No se pudo registrar");
            	Arduino.close();
            	mensaje = "";
        		try {
        			Thread.sleep(500);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
            	agregarHuellaFrame.dispose();
            } else if(mensaje.contains("e") && mensaje.contains("o") && retirarHuella) {
            	agregarHuellaFrame.modificarLabel("Retira tu dedo");
            	retirarHuella = false;
            	mensaje = "";
            } else if(mensaje.contains("e") && mensaje.contains("o") && !retirarHuella) {
            	agregarHuellaFrame.modificarLabel("Huella Registrada");
        		try {
        			Thread.sleep(500);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
        		Arduino.close();
            	agregarHuellaFrame.dispose();
            	retirarHuella = false;
            	mensaje = "";
            } else if(mensaje.contains("e")) {
            	agregarHuellaFrame.modificarLabel("Coloca tu dedo");
            }
        }
	}

}
