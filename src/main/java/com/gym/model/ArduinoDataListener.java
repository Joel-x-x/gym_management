package com.gym.model;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.gym.utilidades.Utilidades;
import com.gym.view.AgregarHuellaFrame;
import com.gym.view.RegistrosDiariosPanel;

public class ArduinoDataListener implements SerialPortDataListener {
	private static String mensaje = "";
	private static AgregarHuellaFrame agregarHuellaFrame;
	private static RegistrosDiariosPanel registrosDiariosPanel;
	private boolean retirarHuella = true;
	private static boolean agregarHuella = true;
	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}
	
	public static void setClass(AgregarHuellaFrame agregarHuellaFrame) {
		ArduinoDataListener.agregarHuellaFrame = agregarHuellaFrame;
		ArduinoDataListener.agregarHuella = true;
	}
	
	public static void setClassVerficar(RegistrosDiariosPanel registrosDiariosPanel) {
		ArduinoDataListener.registrosDiariosPanel = registrosDiariosPanel;
		ArduinoDataListener.agregarHuella = false;
	}
	
	public static void limpiarMensaje() {
		ArduinoDataListener.mensaje = "";
	}
	
	public void agregarHuella() {
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

	public void verificarHuella() {
		// Procesar respuesta
        if(mensaje.contains("f")) {
//        	registrosDiariosPanel.modificarLabel("Ocurrio un error");
        	mensaje = "";
        	registrosDiariosPanel.verificarHuella();
        	System.out.println("verificar f");
        } else if(mensaje.contains("n")) {
        	registrosDiariosPanel.modificarLabel("Usuario no encontrado");
        	mensaje = "";
        	registrosDiariosPanel.verificarHuella();
        	System.out.println("verificar n");
        } else if(Utilidades.isNumber(mensaje.replaceAll("e", ""))) {
        	registrosDiariosPanel.modificarLabel("Usuario encontrado");
        	registrosDiariosPanel.usuarioEncontrado(Integer.parseInt(mensaje.replaceAll("e", "")));
        	mensaje = "";
        	registrosDiariosPanel.verificarHuella();
        	System.out.println("verificar bien");
        } else if(mensaje.contains("e")) {
        	registrosDiariosPanel.modificarLabel("Coloca tu dedo");
        }
        
	}
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		
        if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
            byte[] newData = new byte[event.getSerialPort().bytesAvailable()];
            event.getSerialPort().readBytes(newData, newData.length);

            // Mensaje
            mensaje += new String(newData);
            System.out.println(mensaje);
            if(ArduinoDataListener.agregarHuella) {
            	agregarHuella();
            } else {
            	verificarHuella();
            }

        }
	}

}
