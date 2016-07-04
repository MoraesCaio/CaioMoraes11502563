package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
public class External_ES_ID_descriptor extends Descriptor {

	public External_ES_ID_descriptor(){
		setDescriptor_tag(32);
	}
	public External_ES_ID_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public External_ES_ID_descriptor read(FileInputStream fi){
		setExternal_ES_ID(br.lerBytes(fi, 2));
		return this;
	}

	private int external_ES_ID;
	public int getExternal_ES_ID(){
		return external_ES_ID;
	}
	public void setExternal_ES_ID(int external_ES_ID){
		this.external_ES_ID = external_ES_ID;
	}
	public String toString(){
		String s = super.toString();
		s += "External_ES_ID: "+br.binaryString(external_ES_ID,16)+"\n";
		return s;
	}

	public boolean equals(Object obj) {
	    if (obj instanceof External_ES_ID_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        External_ES_ID_descriptor other = (External_ES_ID_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getExternal_ES_ID(), other.getExternal_ES_ID());
    		return builder.isEquals();
        }
        return false;
    }
}
