package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Private_data_indicator_descriptor extends Descriptor{
	public Private_data_indicator_descriptor(){
		setDescriptor_tag(15);
	}
	public Private_data_indicator_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public Private_data_indicator_descriptor read(FileInputStream fi){
		setPrivate_data_indicator(br.lerBytes(fi, 4));
		return this;
	}
	private int private_data_indicator;
	public int getPrivate_data_indicator(){
		return private_data_indicator;
	}
	public void setPrivate_data_indicator(int private_data_indicator){
		this.private_data_indicator = private_data_indicator;
	}
	public String toString(){
		String s = super.toString();
		s += "Private_data_indicator: "+br.binaryString(private_data_indicator)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Private_data_indicator_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Private_data_indicator_descriptor other = (Private_data_indicator_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getPrivate_data_indicator(), other.getPrivate_data_indicator());
    		return builder.isEquals();
        }
        return false;
    }
}
