package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class IBP_descriptor extends Descriptor{
	public IBP_descriptor(){
		setDescriptor_tag(18);
	}

	public IBP_descriptor read(FileInputStream fi){
		try{
			super.read(fi);
			int xbyte1;
			int xbyte2;
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = br.intExtrairBit(xbyte1,16);
			setClosed_gop_flag(xbyte2);
			xbyte2 = br.intExtrairBit(xbyte1,15);
			setIdentical_gop_flag(xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 18, 32);
			setMax_gop_length(xbyte1);
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int closed_gop_flag;
	public int getClosed_gop_flag(){
		return closed_gop_flag;
	}
	public void setClosed_gop_flag(int closed_gop_flag){
		this.closed_gop_flag = closed_gop_flag;
	}
	private int identical_gop_flag;
	public int getIdentical_gop_flag(){
		return identical_gop_flag;
	}
	public void setIdentical_gop_flag(int identical_gop_flag){
		this.identical_gop_flag = identical_gop_flag;
	}
	private int max_gop_length;
	public int getMax_gop_length(){
		return max_gop_length;
	}
	public void setMax_gop_length(int max_gop_length){
		this.max_gop_length = max_gop_length;
	}
	public String toString(){
		String s = super.toString();
		s += "Closed_gop_flag: "+br.intBinaryString(closed_gop_flag,31)+"\n";
		s += "Identical_gop_flag: "+br.intBinaryString(identical_gop_flag,31)+"\n";
		s += "Max_gop_length: "+br.intBinaryString(max_gop_length,18)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof IBP_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        IBP_descriptor other = (IBP_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getClosed_gop_flag(), other.getClosed_gop_flag());
	        builder.append(getIdentical_gop_flag(), other.getIdentical_gop_flag());
	        builder.append(getMax_gop_length(), other.getMax_gop_length());
    		return builder.isEquals();
        }
        return false;
    }
}
