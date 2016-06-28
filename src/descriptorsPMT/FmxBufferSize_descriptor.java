package descriptorsPMT;

import java.io.FileInputStream;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class FmxBufferSize_descriptor extends Descriptor {
	public FmxBufferSize_descriptor(){
		setDescriptor_tag(34);
	}
	public FmxBufferSize_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}

	public FmxBufferSize_descriptor read(FileInputStream fi){
		return this;
	}

	public String toString(){
		String s = super.toString();
		s += "Implementação não especificada.\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof FmxBufferSize_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        EqualsBuilder builder = new EqualsBuilder();
    		return builder.isEquals();
        }
        return false;
    }
}
