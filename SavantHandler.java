package rgbstrips;
import java.io.InputStream;
 
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

//class to handle SSH status from Savant host
 
public class SavantHandler {
		String volIn = ""; 
        String host="192.168.1.202";
        String user="RPM";
        String password="RPM";
        String command1="/Users/RPM/Applications/RacePointMedia/sclibridge readstate Kitchen.CurrentVolume";

	public String getSliderLevel(){
        try{
            
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            
            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);
             
            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
              while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                volIn = new String(tmp, 0, i);
              }
              if(channel.isClosed()){
                break;
             }
            }            
            return volIn;
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return volIn;
        }
        
}
