package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Target_background_grid_descriptor extends Descriptor {

	public Target_background_grid_descriptor(){
		setDescriptor_tag(7);
	}
	public Target_background_grid_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public Target_background_grid_descriptor read(FileInputStream fi){
		int xbyte1;
		int xbyte2;
		xbyte1 = br.lerBytes(fi, 2);
		xbyte2 = xbyte1 >> 2;
		setHorizontal_size(xbyte2);
		xbyte1 = br.zerarBits(xbyte1, 30);
		xbyte1 = br.shiftAndAddByte(xbyte1, br.lerBytes(fi, 2));
		xbyte2 = xbyte1 >> 4;
		setVertical_size(xbyte2);
		setAspect_ratio_information(br.zerarBits(xbyte1, 28));
		return this;
	}
	private int horizontal_size;
	public int getHorizontal_size(){
		return horizontal_size;
	}
	public void setHorizontal_size(int horizontal_size){
		this.horizontal_size = horizontal_size;
	}
	private int vertical_size;
	public int getVertical_size(){
		return vertical_size;
	}
	public void setVertical_size(int vertical_size){
		this.vertical_size = vertical_size;
	}
	private int aspect_ratio_information;
	public int getAspect_ratio_information(){
		return aspect_ratio_information;
	}
	public void setAspect_ratio_information(int aspect_ratio_information){
		this.aspect_ratio_information = aspect_ratio_information;
	}
		
	public String toString(){
		String s = super.toString();
		s += "Horizontal_size: "+br.binaryString(horizontal_size,18)+"\n";
		s += "Vertical_size: "+br.binaryString(vertical_size,18)+"\n";
		s += "Aspect_ratio_information: "+br.binaryString(aspect_ratio_information,28)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Target_background_grid_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Target_background_grid_descriptor other = (Target_background_grid_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getHorizontal_size(), other.getHorizontal_size());
	        builder.append(getVertical_size(), other.getVertical_size());
	        builder.append(getAspect_ratio_information(), other.getAspect_ratio_information());
    		return builder.isEquals();
        }
        return false;
    }
}
