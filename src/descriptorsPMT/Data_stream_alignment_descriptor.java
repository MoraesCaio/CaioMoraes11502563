package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Data_stream_alignment_descriptor extends Descriptor {

	public Data_stream_alignment_descriptor(){
		setDescriptor_tag(6);
	}
	public Data_stream_alignment_descriptor read(FileInputStream fi){
		try{
			super.read(fi);
			setAlignment_type(fi.read());
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int alignment_type;
	public int getAlignment_type(){
		return alignment_type;
	}
	public void setAlignment_type(int alignment_type){
		this.alignment_type = alignment_type;
	}

	public String toString(){
		String s = super.toString();
		s += "Alignment_type: "+br.intBinaryString(alignment_type,24)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Data_stream_alignment_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Data_stream_alignment_descriptor other = (Data_stream_alignment_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getAlignment_type(), other.getAlignment_type());
    		return builder.isEquals();
        }
        return false;
    }
}
