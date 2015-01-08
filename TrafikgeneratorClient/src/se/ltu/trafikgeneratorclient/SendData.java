package se.ltu.trafikgeneratorclient;

import java.util.ArrayList;
import java.util.List;

public class SendData {

  private static float totRTT = 0f;
  private static int   totPkt = 0;
  private TrafficConfig config;

  public SendData(TrafficConfig config) {
	this.config = config;
  }

  public float getRTT() {
	return totRTT;
  }

  public int getPkg() {
	return totPkt;
  }

  public static void runThreads(TrafficConfig config) {
	int timeout;

	List<SendDataThread> threadList = new ArrayList<SendDataThread>();

	for (int i=0; i < config.getIntegerSetting(Settings.TRAFFIC_NRTHREADS); i++) {
	  SendDataThread sdt = new SendDataThread(i+1, config);
	  Thread t = new Thread(sdt);
	  t.start();
      threadList.add(sdt);
	}
	timeout = config.getIntegerSetting(Settings.TRAFFIC_MAXSENDTIME);
    try {
	  Thread.sleep(timeout);
    } catch (InterruptedException e) {
    }
	for (SendDataThread thread : threadList) {
	  totRTT += thread.getTotalRTT();
	  totPkt += thread.getNrOfPackets();
	  thread.stop();
	}
	System.out.printf("Tot rtt: %f\n", totRTT);
	System.out.printf("Tot num pkt: %d\n", totPkt);
	System.out.printf("Avg rtt %f\n",(float)(totRTT / totPkt));
    System.out.printf("All threads closed\n");
  }
}
