package descriptorsPMT;

import java.io.FileInputStream;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Muxcode_descriptor extends Descriptor {
	public Muxcode_descriptor(){
		setDescriptor_tag(33);
	}

	public Muxcode_descriptor read(FileInputStream fi){
		super.read(fi);
		return this;
	}

	public String toString(){
		String s = super.toString();
		s += "Implementação não especificada.\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Muxcode_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        EqualsBuilder builder = new EqualsBuilder();
    		return builder.isEquals();
        }
        return false;
    }
}
