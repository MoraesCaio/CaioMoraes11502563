package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class SL_descriptor extends Descriptor {

	public SL_descriptor(){
		setDescriptor_tag(30);
	}

	public SL_descriptor read(FileInputStream fi){
		try{
			super.read(fi);
			int xbyte1 = fi.read();
			int xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			setES_ID(xbyte1);

		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int ES_ID;
	public int getES_ID(){
		return ES_ID;
	}
	public void setES_ID(int ES_ID){
		this.ES_ID = ES_ID;
	}
	public String toString(){
		String s = super.toString();
		s += "ES_ID: "+br.intBinaryString(ES_ID,16)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof SL_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        SL_descriptor other = (SL_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getES_ID(), other.getES_ID());
    		return builder.isEquals();
        }
        return false;
    }
}
