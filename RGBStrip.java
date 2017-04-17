package rgbstrips;
//Class to handle RGB strip functions; setting and getting light colors
public class RGBStrip extends RpiGpio{
    public int curcolornum;
    public int[] strip;
    public int curposition;
    public Boolean setupFlag;
	//recursion to enumerate strip object
	RGBStrip(int[] strip)
	{
		this.strip = strip;
	}

    //returns the strip value
    public int[] getStrip()
	{
    	int[] stripInt = {0,0,0};
    	stripInt[0] = this.strip[0];
    	stripInt[1] = this.strip[1];
    	stripInt[2] = this.strip[2];
        return stripInt;
	}
    //get call to return strip object
    public RGBStrip getStripObj()
    {
    	return this;
    }
    //get call to return current strip selected
    public int getPosition()
    {
    	return this.curposition;
    }
    //get call to return curent color value
    public int getCurrColorNum()
    {
    	int colorNum = this.curcolornum;
    	return colorNum;
    }
    //get call to return current strip values
    public String getStripString()
    {
    	
    	String stringReturn = " ";
    	stringReturn = this.strip[0] + "," + this.strip[1] + "," + this.strip[2];
    	return stringReturn;
    }

    //appends a color of a strip/location to a new one
    public int[] appendstrip(int[] strip, int loc, int val)
	{
        this.strip[loc] = val;
        return this.strip;
	}
    //set call to set color values of strip
    public void setStrip(int r, int g, int b)
    {
    	this.strip[0] = r;
    	this.strip[1] = g;
    	this.strip[2] = b;
    	this.SetGpio(this.strip, this.curposition);
    }
    
    //sets the strip and position to a new color
    //all strips if pos = 4, individual strip if < 4
    public void setStrip(int r, int g, int b, int curposition)
	{
    	int curP = curposition;
        this.curposition = curP;
        this.strip[0] = r;
        this.strip[1] = g;
        this.strip[2] = b;
    	this.SetGpio(this.strip, this.curposition);
	}
    
    //sets the strip, position, and colornumber
    //all strips if pos = 4, individual strip if < 4
    public void setStrip(int r, int g, int b, int curposition, int curcolornum)
    {
    	int curP = curposition;
        this.curposition = curP;
        this.curcolornum = curcolornum;
        this.strip[0] = r;
        this.strip[1] = g;
        this.strip[2] = b;    	
    	this.SetGpio(this.strip, this.curposition);
    }
    //set value to set received color number to strip
    public void setCurColorNum(int curcolornum)
    {
    	this.curcolornum = curcolornum;
    }
    //set value to set receieved strip position
    public void setPosition(int curposition){
    	int position = curposition;
    	this.curposition = position;
    }
    //set value to set flag if in preset edit mode
    public void setSetupFlag(Boolean setupFlag)
    {
    	this.setupFlag = setupFlag;
    }
    	//method to set RGB color from percentage of 100
	public int[] color(int value)
	{
		Double minimum, maximum, red, green, blue, ratio;
		minimum = 0.00;
		int r, g, b;
		maximum = 65.00;
		ratio = 2 * (value-minimum) / (maximum - minimum); 
		blue = Math.max(0, 255*(1 - ratio));
		red = Math.max(0, 255*(ratio - 1));
		green = 255.00 - blue - red; 
		r = red.intValue();
		b = blue.intValue();
		g = green.intValue();
		int[] color = {r, g, b};
	    return color;
	}

}
