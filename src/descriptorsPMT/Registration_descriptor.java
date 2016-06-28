package descriptorsPMT;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Registration_descriptor extends Descriptor {

	public Registration_descriptor(){
		setDescriptor_tag(5);
	}
	public Registration_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}

	public Registration_descriptor read(FileInputStream fi){
		try{
			int xbyte1 = fi.read();
			int xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			for(int i = 0; i < (descriptor_length - 4); i++){
				setAdditional_identification_info(fi.read());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int format_identifier;
	public int getFormat_identifier(){
		return format_identifier;
	}
	public void setFormat_identifier(int format_identifier){
		this.format_identifier = format_identifier;
	}
	private ArrayList<Integer> additional_identification_info = new ArrayList<Integer>();
	public ArrayList<Integer> getAdditional_identification_info(){
		return additional_identification_info;
	}
	public void setAdditional_identification_info(Integer additional_identification_info){
		this.additional_identification_info.add(additional_identification_info);
	}
	public String toString(){
		String s = super.toString();
		s += "Format_identifier: "+br.intBinaryString(format_identifier)+"\n";
		for(Integer i : additional_identification_info){
			s += "Additional_identification_info: "+br.intBinaryString(i,24)+"\n";
		}
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Registration_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Registration_descriptor other = (Registration_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getFormat_identifier(), other.getFormat_identifier());
	        builder.append(getAdditional_identification_info(), other.getAdditional_identification_info());
    		return builder.isEquals();
        }
        return false;
    }
}
