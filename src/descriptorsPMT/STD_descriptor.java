package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class STD_descriptor extends Descriptor {
	public STD_descriptor(){
		setDescriptor_tag(17);
	}
	public STD_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public STD_descriptor read(FileInputStream fi){
		setLeak_valid_flag(br.extrairBit(br.lerBytes(fi, 1),1));
		return this;
	}
	private int leak_valid_flag;
	public int getLeak_valid_flag(){
		return leak_valid_flag;
	}
	public void setLeak_valid_flag(int leak_valid_flag){
		this.leak_valid_flag = leak_valid_flag;
	}
	public String toString(){
		String s = super.toString();
		s += "Leak_valid_flag: "+br.binaryString(leak_valid_flag,31)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof STD_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        STD_descriptor other = (STD_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getLeak_valid_flag(), other.getLeak_valid_flag());
    		return builder.isEquals();
        }
        return false;
    }
}
