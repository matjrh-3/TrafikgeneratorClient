package se.ltu.trafikgeneratorclient;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.network.CoAPEndpoint;

public class SendDataThread implements Runnable {

  private TrafficConfig config;
  private int ThreadNr = 0;
  private String token;
  private static Random random = new Random();
  private int nrPackets = 0;
  private long totalRTT = 0;
  private boolean execute;

  public SendDataThread(int ThreadNr, TrafficConfig config) {
	this.config = config;
	this.ThreadNr = ThreadNr;
  }

  public long getTotalRTT(){
	return totalRTT;
  }
	
  public int getNrOfPackets(){
	return nrPackets;
  }

  public void stop() {
	execute = false;
  }

  public void run() {
	CoAPEndpoint control = null;
  
	execute = true;
	try {
	  control = new CoAPEndpoint(config.toNetworkConfig());
	  control.start();
	} catch (IOException e) {
	  e.printStackTrace();
	}
	int i   = 0;
	int num = config.getIntegerSetting(Settings.TRAFFIC_NUMPACKETS);
    while ((execute) && (i++ < num)) {
	  SendPost(control);
    }
	
    File myFile = new File("./uploadThread-"+ThreadNr+".txt");
    try {
      myFile.createNewFile();
      FileOutputStream fOut = new FileOutputStream(myFile);
      OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
      myOutWriter.append("-------------------------------------------------\n");
      myOutWriter.append("Nr of packets sent: " + nrPackets+ "\n");
      myOutWriter.append("Total RTT: " + totalRTT + "\n");
      myOutWriter.append("Avg RTT: " + (totalRTT / nrPackets) + "\n");
      myOutWriter.append("-------------------------------------------------\n");
      myOutWriter.close();
      fOut.close();
	} catch (Exception e) {
	  e.printStackTrace();
	}
  }

  private void SendPost(CoAPEndpoint dataEndpoint) {
	try {
	  int payloadsize = config.getIntegerSetting(Settings.TRAFFIC_MESSAGESIZE);
	  Request test = Request.newPost();
	  String testURI =String.format(
			"coap://%1$s:%2$d/testing",
			config.getStringSetting(Settings.TEST_SERVER),
			config.getIntegerSetting(Settings.TEST_SERVERPORT));
	  CoAP.Type type = config.getStringSetting(Settings.COAP_MESSAGETYPE).equals("CON") ? CoAP.Type.CON : CoAP.Type.NON;
	  test.setURI(testURI);
	  test.setType(type);
	  test.setPayload(PayloadGenerator.generateRandomData(random.nextLong(), payloadsize));//TrafficConfig.networkConfigToStringList(config.toNetworkConfig()));
	  test.send(dataEndpoint);
	  nrPackets++;
	  Response response = test.waitForResponse();
	  totalRTT += response.getRTT();
	  token = response.getTokenString();
	} catch (Exception e) {
	  e.printStackTrace();
	}
  }
	
  private void SendDelete(CoAPEndpoint dataEndpoint) {
	try {
	  //Log.i("THREAD", this.getId() + " Sending DELETE with Token: " + token + " - CoAP: "+ dataEndpoint.getAddress().getPort());
	  Request controlRequest = Request.newDelete();
	  controlRequest.setURI(String.format("coap://%1$s/control?token=%2$s", config.getStringSetting(Settings.TEST_SERVER), token));
	  controlRequest.send(dataEndpoint);
	  Response response2 = controlRequest.waitForResponse();
	  //Log.i("THREAD",this.getId() + " Got response: " + response2.toString());
	} catch (Exception e) {
	  //Log.e("THREAD", e.getMessage());
	  e.printStackTrace();
	}
  }
}
