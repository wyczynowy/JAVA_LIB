package app;

import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

public class jSerialCommExample {

	public static void main(String[] args) {
		
		SerialPort[] ports = SerialPort.getCommPorts();
		System.out.println("Select a port:");
		int i = 1;
		for(SerialPort port : ports)
			System.out.println(i++ +  ": " + port.getSystemPortName());
		Scanner s = new Scanner(System.in);
		int chosenPort = s.nextInt();

		SerialPort serialPort = ports[chosenPort - 1];
		if(serialPort.openPort())
			System.out.println("Port opened successfully.");
		else {
			System.out.println("Unable to open the port.");
			return;
		}

		
		serialPort.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);

		byte[] buf = {'D', 'A', 'W', 'I', 'D', '\n', '\r'};
		for(i = 0; i < 10; i++)
			serialPort.writeBytes(buf, buf.length);
		serialPort.closePort();
		
	}

}
