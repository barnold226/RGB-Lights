package rgbstrips;
import com.pi4j.wiringpi.SoftPwm;
//Class to handle modulation of GPIO pins to set RGB light colors
public class RpiGpio {
	
		public int holder;
		//Pins wired on RPI
		public int[][] gpioPins = {{12, 23, 25}, {18, 19, 26}, {21, 26, 20}, {17, 24, 27}}; 

		RpiGpio(){
			com.pi4j.wiringpi.Gpio.wiringPiSetup();
			for(int i = 0; i<=3; i++){
				for(int j = 0; j<=2; j++){
					SoftPwm.softPwmCreate(gpioPins[i][j], 0, 100);
				}
			}
		}
		//function to set pins when called
		public void SetGpio(int[] color, int currPos){
			int top = 0;
			boolean fourFlag = false;
			if(currPos==4){
				top=3;
				fourFlag = true;
			}
			for(int i=0; i<=top;i++)
			{
				if(fourFlag)
					currPos = i;
				for(int j=0; j<=2; j++){
					color[j] = (int) (100.00*(color[j]/255.00));
					SoftPwm.softPwmWrite(gpioPins[currPos][j], color[j]);
					//System.out.printf("SoftPwm.softPwmWrite(" + gpioPins[currPos][j]+","+ color[j]+");\n");

				}
			}												
		}
}
