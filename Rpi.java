package rgbstrips;
import rgbstrips.RGBStrip;
import rgbstrips.SavantHandler;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Rpi {


	public static void main(String[] args) {
				
		//creates position for strip
		int position = 0;
		int curcolornum = 0;
		
		@SuppressWarnings("unused")
		String testStringPreset = "";
		
		Boolean setupFlag = true;
		
		//creates new strip object
		int[] strip = {0, 0, 0};
		int[] color = {0, 0, 0};
		
		int[][] presetArray = {{0, 0, 0} , {0, 0, 0} , {0, 0, 0} , {0, 0, 0} , {0, 0, 0} , {0, 0, 0} , {0, 0, 0} , {0, 0, 0} , {0, 0, 0} , {0, 0, 0}};
		//create RGB Strip objects for each wall and one for all walls
		RGBStrip strip1 = new RGBStrip(strip);
		RGBStrip strip2 = new RGBStrip(strip);
		RGBStrip strip3 = new RGBStrip(strip);
		RGBStrip strip4 = new RGBStrip(strip);
		RGBStrip allStrips = new RGBStrip(strip);
		//create new SavantHandler object for Savant commands
		SavantHandler prohost = new SavantHandler();
		
		
		
		RGBStrip[] lightStrips = {strip1, strip2, strip3, strip4, allStrips};
		//lightStrips[position].setStrip(5, 7, 7, position, 34);


		//Beginning of TCP Server - 2/16/16
		//Returns char:typeChar -- char that specifies # or @ to be handled
		//Returns String:stringOutput -- String that specifies the command sent
		
		System.out.println("going into server try");
		try{
			 ServerSocket input = new ServerSocket(60128);
			 Socket connectionSocket = input.accept();
			 System.out.println("Client Connected!");
			 InputStream stream = connectionSocket.getInputStream();
			 
			  while(true)
			  {				
				  try
				  	{
					//handling input stream and formatting data
					  while(true)
					  	{
						  char typeChar; 
						  String stringOutput = "";
						  String stringType;
						  typeChar = (char)stream.read();
				          while(stream.available() !=0)
				          	{
				        	  stringOutput = stringOutput + (char)stream.read();
				        	}

				          stringType = typeChar + "";
					//data received with # in beginning indicates volume change
				          switch(stringType)
				  		{
				  			case "#":
				  			{
				  			try
				  			{
				  				int testInt = 0;
				  				testInt = Integer.parseInt(stringOutput.substring(0,2));
				  				color = lightStrips[position].color(testInt);
				  				lightStrips[position].setStrip(color[0], color[1], color[2], position, testInt);
				  								  				
				  			//catch might need something here to handle the ssh or something like that
				  			}catch(NumberFormatException e)
				  			{
								//Volume level check in Savant to verify correct levels 
				  				stringOutput = prohost.getSliderLevel();
				  				int testInt = 0;
				  				testInt = Integer.parseInt(stringOutput.substring(0,2));
				  				color = lightStrips[position].color(testInt);
				  				lightStrips[position].setStrip(color[0], color[1], color[2], position, testInt);
				  				break;
				  			}
				  			stringType = "";
				  			}
				  			//data received with @ indicate interface command
				  			case "@":
				  			{
				  			switch(stringOutput)
				  			{
				  				case "PAUSE":
				  					break;
				  				case "PLAY":
				  					break;
				  				case "ZERO":
				  					if (setupFlag == true)
				  					{
				  						presetArray[0] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[0][0], presetArray[0][1], presetArray[0][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "ONE":
				  					if (setupFlag == true)
				  					{
				  						presetArray[1] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[1][0], presetArray[1][1], presetArray[1][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "TWO":
				  					if (setupFlag == true)
				  					{
				  						presetArray[2] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[2][0], presetArray[2][1], presetArray[2][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "THREE":
				  					if (setupFlag == true)
				  					{
				  						presetArray[3] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[3][0], presetArray[3][1], presetArray[3][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "FOUR":
				  					if (setupFlag == true)
				  					{
				  						presetArray[4] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[4][0], presetArray[4][1], presetArray[4][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "FIVE":
				  					if (setupFlag == true)
				  					{
				  						presetArray[5] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[5][0], presetArray[5][1], presetArray[5][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "SIX":
				  					if (setupFlag == true)
				  					{
				  						presetArray[6] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[6][0], presetArray[6][1], presetArray[6][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "SEVEN":
				  					if (setupFlag == true)
				  					{
				  						presetArray[7] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[7][0], presetArray[7][1], presetArray[7][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "EIGHT":
				  					if (setupFlag == true)
				  					{
				  						presetArray[8] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[8][0], presetArray[8][1], presetArray[8][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;
				  				case "NINE":
				  					if (setupFlag == true)
				  					{
				  						presetArray[9] = (lightStrips[position].getStrip());
				  						testStringPreset = (lightStrips[position].getStripString());
				  					}
				  					else{
				  						lightStrips[position].setStrip(presetArray[9][0], presetArray[9][1], presetArray[9][2], position, lightStrips[position].getCurrColorNum());
				  						}
				  					break;

				  				case "UP":
				  					if(lightStrips[position].curcolornum < 65)
				  					{
				  						curcolornum = 1 + lightStrips[position].curcolornum;
				  					}
				  					else {
				  						curcolornum = 0;
				  					}
				  					color = lightStrips[position].color(curcolornum);
				  					lightStrips[position].setStrip(color[0], color[1], color[2], position, curcolornum);
				  					break;
				  					
				  				case "DOWN":
				  					if(lightStrips[position].curcolornum > 0)
				  					{
				  						curcolornum = lightStrips[position].curcolornum - 1;
				  					}
				  					else {
				  						curcolornum = 65;
				  					}
				  					color = lightStrips[position].color(curcolornum);
				  					lightStrips[position].setStrip(color[0], color[1], color[2], position, curcolornum);
				  					break;
				  					
				  					
				  				case "LEFT":
				  					if(position > 0)
				  					{
				  						position = position -1;
				  					}
				  					else{
				  						position = 4;
				  					}
				  					lightStrips[position].setPosition(position);
				  					break;
				  					
				  				case "RIGHT":
				  					if(position < 4)
				  					{
				  						position = position +1;
				  					}
				  					else{
				  						position = 0;
				  					}
				  					lightStrips[position].setPosition(position);
				  					break;
				  					
				  				case "SELECT":
				  					break;
				  					
				  				case "RANDOM":
				  					Random ranNum = new Random();
				  					int randNum = ranNum.nextInt(65) + 1;
				  					color = lightStrips[position].color(randNum);
				  					lightStrips[position].setStrip(color[0], color[1], color[2]);
				  					break;
				  					
				  				case "SETUP":
				  					if(setupFlag == true)
				  						setupFlag = false;
				  					else
				  						setupFlag = true;
				  					break;
				  					
				  				case "PWROFF":
				  					
				  			}
				  			}
				  			

				  			break;
				  		}		
				  		}
				  					  
				  			}catch(Exception e)
				  	  {
				  		input.close();
				  		  e.getStackTrace();
				  	  }
				  }
				  }catch(Exception e)
				  	{
				  	e.getStackTrace();
				  	}
		
	}
	
}

				          
				        
		
		
		
		
		
