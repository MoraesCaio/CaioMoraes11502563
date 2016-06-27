package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Maximum_bitrate_descriptor extends Descriptor {
	public Maximum_bitrate_descriptor(){
		setDescriptor_tag(14);
	}

	public Maximum_bitrate_descriptor read(FileInputStream fi){
		try{
			super.read(fi);
			int xbyte1 = fi.read();
			int xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 10, 32);
			setMaximum_bitrate(xbyte1);
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int maximum_bitrate;
	public int getMaximum_bitrate(){
		return maximum_bitrate;
	}
	public void setMaximum_bitrate(int maximum_bitrate){
		this.maximum_bitrate = maximum_bitrate;
	}
	public String toString(){
		String s = super.toString();
		s += "Maximum_bitrate: "+br.intBinaryString(maximum_bitrate,10)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Maximum_bitrate_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Maximum_bitrate_descriptor other = (Maximum_bitrate_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getMaximum_bitrate(), other.getMaximum_bitrate());
    		return builder.isEquals();
        }
        return false;
    }
}
