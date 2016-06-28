package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Multiplex_buffer_utilization_descriptor extends Descriptor {

	public Multiplex_buffer_utilization_descriptor(){
		setDescriptor_tag(12);
	}
	public Multiplex_buffer_utilization_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public Multiplex_buffer_utilization_descriptor read(FileInputStream fi){
		try{
			int xbyte1 = fi.read();
			int xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = br.intExtrairBit(xbyte1,16);
			setBound_valid_flag(xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 17, 32);
			setLTW_offset_lower_bound(xbyte1);
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 17, 32);
			setLTW_offset_upper_bound(xbyte1);
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}

	private int bound_valid_flag;
	public int getBound_valid_flag(){
		return bound_valid_flag;
	}
	public void setBound_valid_flag(int bound_valid_flag){
		this.bound_valid_flag = bound_valid_flag;
	}
	private int LTW_offset_lower_bound;
	public int getLTW_offset_lower_bound(){
		return LTW_offset_lower_bound;
	}
	public void setLTW_offset_lower_bound(int LTW_offset_lower_bound){
		this.LTW_offset_lower_bound = LTW_offset_lower_bound;
	}
	private int LTW_offset_upper_bound;
	public int getLTW_offset_upper_bound(){
		return LTW_offset_upper_bound;
	}
	public void setLTW_offset_upper_bound(int LTW_offset_upper_bound){
		this.LTW_offset_upper_bound = LTW_offset_upper_bound;
	}
	public String toString(){
		String s = super.toString();
		s += "Bound_valid_flag: "+br.intBinaryString(bound_valid_flag,31)+"\n";
		s += "LTW_offset_lower_bound: "+br.intBinaryString(LTW_offset_lower_bound,17)+"\n";
		s += "LTW_offset_upper_bound: "+br.intBinaryString(LTW_offset_upper_bound,17)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Multiplex_buffer_utilization_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Multiplex_buffer_utilization_descriptor other = (Multiplex_buffer_utilization_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getBound_valid_flag(), other.getBound_valid_flag());
	        builder.append(getLTW_offset_lower_bound(), other.getLTW_offset_lower_bound());
	        builder.append(getLTW_offset_upper_bound(), other.getLTW_offset_upper_bound());
    		return builder.isEquals();
        }
        return false;
    }

}
