package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Video_window_descriptor extends Descriptor {

	public Video_window_descriptor(){
		setDescriptor_tag(8);
	}

	public Video_window_descriptor read(FileInputStream fi){
		try{
			super.read(fi);
			int xbyte1 = fi.read();
			int xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = xbyte1 >> 2;
			setHorizontal_offset(xbyte1);
			xbyte2 = br.intZerarBits(xbyte2, 30, 32);
			xbyte1 = xbyte2;
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = xbyte1 >> 4;
			setVertical_offset(xbyte1);
			xbyte2 = br.intZerarBits(xbyte2, 28, 32);
			setWindow_priority(xbyte2);
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int horizontal_offset;
	public int getHorizontal_offset(){
		return horizontal_offset;
	}
	public void setHorizontal_offset(int horizontal_offset){
		this.horizontal_offset = horizontal_offset;
	}
	private int vertical_offset;
	public int getVertical_offset(){
		return vertical_offset;
	}
	public void setVertical_offset(int vertical_offset){
		this.vertical_offset = vertical_offset;
	}
	private int window_priority;
	public int getWindow_priority(){
		return window_priority;
	}
	public void setWindow_priority(int window_priority){
		this.window_priority = window_priority;
	}
	public String toString(){
		String s = super.toString();
		s += "Horizontal_offset: "+br.intBinaryString(horizontal_offset,18)+"\n";
		s += "Vertical_offset: "+br.intBinaryString(vertical_offset,18)+"\n";
		s += "Window_priority: "+br.intBinaryString(window_priority,28)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Video_window_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Video_window_descriptor other = (Video_window_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getHorizontal_offset(), other.getHorizontal_offset());
	        builder.append(getVertical_offset(), other.getVertical_offset());
	        builder.append(getWindow_priority(), other.getWindow_priority());
    		return builder.isEquals();
        }
        return false;
    }
}
