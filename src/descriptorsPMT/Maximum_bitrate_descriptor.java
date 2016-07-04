package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Maximum_bitrate_descriptor extends Descriptor {
	public Maximum_bitrate_descriptor(){
		setDescriptor_tag(14);
	}
	public Maximum_bitrate_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}

	public Maximum_bitrate_descriptor read(FileInputStream fi){
		setMaximum_bitrate(br.zerarBits(br.lerBytes(fi, 3), 10));
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
		s += "Maximum_bitrate: "+br.binaryString(maximum_bitrate,10)+"\n";
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
