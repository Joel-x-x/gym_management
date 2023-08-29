package com.gym.model;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fazecast.jSerialComm.SerialPort;


public class Arduino{
    public static SerialPort serialPort;
    private static OutputStream outputStream;
    private static InputStream inputStream;

//    public Arduino() {
//        initializeSerialPort();
//    }

    public static void initializeSerialPort() {
        SerialPort[] ports = SerialPort.getCommPorts();
        serialPort = null;

        for (SerialPort port : ports) {
        	System.out.println(".");
            if (port.getDescriptivePortName().contains("Arduino")) {
            	serialPort = port;
                break;
            }
        }
		
		if (serialPort != null && serialPort.openPort()) {
            System.out.println("Conexión exitosa con Arduino en el puerto: " + serialPort.getSystemPortName());
            
            serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 5000, 5000); // Configura los tiempos de espera
            
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
        } else {
            System.out.println("No se pudo conectar con Arduino.");
        }
		
		ArduinoDataListener listener = new ArduinoDataListener();
		serialPort.addDataListener(listener);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    public static void sendCommand(String command) {
        try {
            outputStream.write(command.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveResult() {
        try {
            StringBuilder messageBuilder = new StringBuilder(); // Usaremos un StringBuilder para construir el mensaje completo
            while (true) {
                if (serialPort.bytesAvailable() > 0) {
                    byte[] readBuffer = new byte[serialPort.bytesAvailable()];
                    int numBytes = serialPort.readBytes(readBuffer, readBuffer.length);
                    String receivedMessage = new String(readBuffer, 0, numBytes); // Convertir bytes a String
                    messageBuilder.append(receivedMessage); // Agregar el mensaje al StringBuilder
                    System.out.println(messageBuilder);
                    if (receivedMessage.contains("stored")) {
                        return messageBuilder.toString(); // Devolver el mensaje completo
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close() {
        try {
            outputStream.close();
            inputStream.close();
            serialPort.closePort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
