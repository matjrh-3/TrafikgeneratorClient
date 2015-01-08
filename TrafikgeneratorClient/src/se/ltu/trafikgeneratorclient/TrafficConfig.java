package se.ltu.trafikgeneratorclient;

import org.eclipse.californium.core.network.config.NetworkConfig;


public class TrafficConfig {

  static private String newline = "\n";//System.getProperty("line.separator");
  private String originalConfig;

  private String ipAddress;
  private int    udpPort   = 5683;
  private int    threads   = 2;
  private int    loadSize  = 512;
  private int    duration  = 10000;
  private int    timeout   = 2000;
  private int    retransmi = 4;
  private int    nPacket   = 1000;
  private float  random    = 1.5f;
  // Not setable by gui
  private int    nStart            = 1;
  private int    testPort          = 5683;  // 56830???
  private int    blockSize         = 512;
  private int    repeats           = 1;
  private int    paralleltr        = 1;
  private int    maxmessages       = 2500;
  private int    rate              = -1;
  private int    filesize          = 524288;
  private int    onoff_maxsendtime = 60000;
  private float  intermission      = 10000.0f;
  private float  burst_time        = 500.0f;
  private float  idle_time         = 500.0f;
  private String coap_messagetype  = "CON";
  private String traffic_type      = "CONSTANT_SOURCE";
  private String traffic_mode      = "THREAD";
//  private String traffic_mode      = "MESSAGES";

  TrafficConfig() {}

  public void setConfig(String ip, String port, String thread, String tim,
		        String timO, String retr, String rnd, String siz, String npkt) {
    this.ipAddress = ip;
    this.udpPort   = Integer.parseInt(port);
    this.threads   = Integer.parseInt(thread);
    this.loadSize  = Integer.parseInt(siz);
    this.duration  = Integer.parseInt(tim);
    this.timeout   = Integer.parseInt(timO);
    this.retransmi = Integer.parseInt(retr);
    this.random    = Float.valueOf(rnd);
    this.nPacket   = Integer.parseInt(npkt);
System.out.printf("ipAddress:  %s\n", ipAddress);
System.out.printf("port:       %d\n", udpPort);
System.out.printf("thread:     %d\n", threads);
System.out.printf("duration:   %d\n", duration);
System.out.printf("timeout:    %d\n", timeout);
System.out.printf("retransmit: %d\n", retransmi);
System.out.printf("random:     %f\n", random);
System.out.printf("load size:  %d\n", loadSize);
System.out.printf("num packet: %d\n", nPacket);
System.out.printf("Config written\n");
  }

  public NetworkConfig toNetworkConfig() {
	NetworkConfig config = new NetworkConfig();
	config.setInt("DEFAULT_COAP_PORT",   this.testPort);
	config.setInt("ACK_TIMEOUT",         this.timeout);
	config.setInt("NSTART",              this.nStart);
	config.setInt("MAX_RETRANSMIT",      this.retransmi);
	config.setInt("MAX_MESSAGE_SIZE",    this.loadSize);
	config.setFloat("ACK_RANDOM_FACTOR", this.random);
	config.setInt("DEFAULT_BLOCK_SIZE",  this.blockSize);
	return config;
  }

  public String getStringSetting(Settings setting) {
	switch (setting) {
	  case META_AUTHOR:                  return "";
	  case META_TITLE:                   return "";
	  case TEST_SERVER:                  return ipAddress;
	  case COAP_MESSAGETYPE:             return coap_messagetype;
	  case TRAFFIC_TYPE:                 return traffic_type;
	  case TRAFFIC_MODE:                 return traffic_mode;
	  default:                           return null;
    }
  }

  public Integer getIntegerSetting(Settings setting) {
	switch (setting) {
	  case TEST_SERVERPORT:              return udpPort;
	  case TEST_TESTPORT:                return testPort;
	  case TEST_REPEATS:                 return repeats;
	  case TEST_PARALLELTRANSFERS:       return paralleltr;
	  case TRAFFIC_MAXSENDTIME:          if (traffic_type.equals("ONOFF_SOURCE"))
                                           return onoff_maxsendtime;
                                         else
                                           return duration;
	  case TEST_NTPPORT:                 return 0;
	  case COAP_ACK_TIMEOUT:             return timeout;
	  case COAP_MAX_RETRANSMIT:          return retransmi;
	  case COAP_NSTART:                  return nStart;
	  case TRAFFIC_MAXMESSAGES:          return maxmessages;
	  case TRAFFIC_NRTHREADS:		     return threads;
	  case TRAFFIC_RATE:                 return rate;
	  case TRAFFIC_MESSAGESIZE:          return loadSize;
	  case TRAFFIC_FILESIZE:             return filesize;
	  case TRAFFIC_BLOCKSIZE:            return blockSize;
	  case TRAFFIC_NUMPACKETS:           return nPacket;
	  default:                           return null;
	}
  }

  public Float getDecimalSetting(Settings setting) {
	switch (setting) {
	  case TEST_INTERMISSION:            return intermission;
	  case COAP_ACK_RANDOM_FACTOR:       return random;
	  case TRAFFIC_BURST_TIME:           return burst_time;
	  case TRAFFIC_IDLE_TIME:            return idle_time;
	  default:                           return null;
	}
  }

  static public String configToString(TrafficConfig config) {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append(Settings.META_AUTHOR.toString()            + "=" + config.getStringSetting(Settings.META_AUTHOR)             + newline);
	stringBuilder.append(Settings.META_TITLE.toString()             + "=" + config.getStringSetting(Settings.META_TITLE)              + newline);
	stringBuilder.append(Settings.TEST_SERVER.toString()            + "=" + config.getStringSetting(Settings.TEST_SERVER)             + newline);
	stringBuilder.append(Settings.TEST_SERVERPORT.toString()        + "=" + config.getIntegerSetting(Settings.TEST_SERVERPORT)        + newline);
	stringBuilder.append(Settings.TEST_TESTPORT.toString()          + "=" + config.getIntegerSetting(Settings.TEST_TESTPORT)          + newline);
	stringBuilder.append(Settings.TEST_NTPPORT.toString()           + "=" + config.getIntegerSetting(Settings.TEST_NTPPORT)           + newline);
	stringBuilder.append(Settings.TEST_REPEATS.toString()           + "=" + config.getIntegerSetting(Settings.TEST_REPEATS)           + newline);
	stringBuilder.append(Settings.TEST_INTERMISSION.toString()      + "=" + config.getDecimalSetting(Settings.TEST_INTERMISSION)      + newline);
	stringBuilder.append(Settings.TEST_PARALLELTRANSFERS.toString() + "=" + config.getIntegerSetting(Settings.TEST_PARALLELTRANSFERS) + newline);
	stringBuilder.append(Settings.COAP_MESSAGETYPE.toString()       + "=" + config.getStringSetting(Settings.COAP_MESSAGETYPE)        + newline);
	stringBuilder.append(Settings.COAP_ACK_TIMEOUT.toString()       + "=" + config.getIntegerSetting(Settings.COAP_ACK_TIMEOUT)       + newline);
	stringBuilder.append(Settings.COAP_ACK_RANDOM_FACTOR.toString() + "=" + config.getDecimalSetting(Settings.COAP_ACK_RANDOM_FACTOR) + newline);
	stringBuilder.append(Settings.COAP_MAX_RETRANSMIT.toString()    + "=" + config.getIntegerSetting(Settings.COAP_MAX_RETRANSMIT)    + newline);
	stringBuilder.append(Settings.COAP_NSTART.toString()            + "=" + config.getIntegerSetting(Settings.COAP_NSTART)            + newline);
	stringBuilder.append(Settings.TRAFFIC_TYPE.toString()           + "=" + config.getStringSetting(Settings.TRAFFIC_TYPE)            + newline);
	stringBuilder.append(Settings.TRAFFIC_MODE.toString()           + "=" + config.getStringSetting(Settings.TRAFFIC_MODE)            + newline);
	stringBuilder.append(Settings.TRAFFIC_MAXSENDTIME.toString()    + "=" + config.getDecimalSetting(Settings.TRAFFIC_MAXSENDTIME)    + newline);
	stringBuilder.append(Settings.TRAFFIC_MAXMESSAGES.toString()    + "=" + config.getIntegerSetting(Settings.TRAFFIC_MAXMESSAGES)    + newline);
	stringBuilder.append(Settings.TRAFFIC_NRTHREADS.toString()      + "=" + config.getIntegerSetting(Settings.TRAFFIC_NRTHREADS)      + newline);
	stringBuilder.append(Settings.TRAFFIC_RATE.toString()           + "=" + config.getIntegerSetting(Settings.TRAFFIC_RATE)           + newline);
	stringBuilder.append(Settings.TRAFFIC_MESSAGESIZE.toString()    + "=" + config.getIntegerSetting(Settings.TRAFFIC_MESSAGESIZE)    + newline);
	stringBuilder.append(Settings.TRAFFIC_FILESIZE.toString()       + "=" + config.getIntegerSetting(Settings.TRAFFIC_FILESIZE)       + newline);
	stringBuilder.append(Settings.TRAFFIC_BLOCKSIZE.toString()      + "=" + config.getIntegerSetting(Settings.TRAFFIC_BLOCKSIZE)      + newline);
	stringBuilder.append(Settings.TRAFFIC_BURST_TIME.toString()     + "=" + config.getDecimalSetting(Settings.TRAFFIC_BURST_TIME)     + newline);
	stringBuilder.append(Settings.TRAFFIC_IDLE_TIME.toString()      + "=" + config.getDecimalSetting(Settings.TRAFFIC_IDLE_TIME)      + newline);
	stringBuilder.append(Settings.TRAFFIC_NUMPACKETS.toString()     + "=" + config.getDecimalSetting(Settings.TRAFFIC_NUMPACKETS)     + newline);
	return stringBuilder.toString();
  }

  static public NetworkConfig stringListToNetworkConfig (String list) {
	String[] array = list.split(",");
	NetworkConfig config = new NetworkConfig();
	for (int i = 0; i < array.length; i++) {
	  if (array[i].equals(""))
		continue;
	  String[] setting = array[i].split("=");
	  if (setting[0].equals("DEFAULT_COAP_PORT") || setting[0].equals("ACK_TIMEOUT") || setting[0].equals("ACK_TIMEOUT_SCALE") || setting[0].equals("NSTART") || setting[0].equals("DEFAULT_LEISURE") || setting[0].equals("MAX_RETRANSMIT") || setting[0].equals("MAX_MESSAGE_SIZE")) {
		config.setInt(setting[0], Integer.valueOf(setting[1]));
	  } else if (setting[0].equals("ACK_RANDOM_FACTOR"))
		config.setFloat(setting[0], Float.valueOf(setting[1]));
	  }
	return config;
  }

  static public String networkConfigToStringList (NetworkConfig config) {
	String list = "";
	list += "DEFAULT_COAP_PORT="  + Integer.toString(config.getInt("DEFAULT_COAP_PORT"));
	list += ",ACK_TIMEOUT="       + Integer.toString(config.getInt("ACK_TIMEOUT"));
	list += ",ACK_RANDOM_FACTOR=" + Float.toString(config.getFloat("ACK_RANDOM_FACTOR"));
	list += ",ACK_TIMEOUT_SCALE=" + Integer.toString(config.getInt("ACK_TIMEOUT_SCALE"));
	list += ",NSTART="            + Integer.toString(config.getInt("NSTART"));
	list += ",DEFAULT_LEISURE="   + Integer.toString(config.getInt("DEFAULT_LEISURE"));
	list += ",MAX_RETRANSMIT="    + Integer.toString(config.getInt("MAX_RETRANSMIT"));
	list += ",MAX_MESSAGE_SIZE="  + Integer.toString(config.getInt("MAX_MESSAGE_SIZE"));
	return list;
  }

  public String getOriginal() {
	//TODO: Should be remade so as to print out all values that have been overridden.
	return originalConfig;
  }
}
