package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Smoothing_buffer_descriptor extends Descriptor {
	public Smoothing_buffer_descriptor(){
		setDescriptor_tag(16);
	}
	public Smoothing_buffer_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public Smoothing_buffer_descriptor read(FileInputStream fi){
		try{
			int xbyte1;
			int xbyte2;
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 10, 32);
			setSb_leak_rate(xbyte1);
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 10, 32);
			setSb_size(xbyte1);

		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int sb_leak_rate;
	public int getSb_leak_rate(){
		return sb_leak_rate;
	}
	public void setSb_leak_rate(int sb_leak_rate){
		this.sb_leak_rate = sb_leak_rate;
	}
	private int sb_size;
	public int getSb_size(){
		return sb_size;
	}
	public void setSb_size(int sb_size){
		this.sb_size = sb_size;
	}
	public String toString(){
		String s = super.toString();
		s += "Sb_leak_rate: "+br.intBinaryString(sb_leak_rate,10)+"\n";
		s += "Sb_size: "+br.intBinaryString(sb_size,10)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Smoothing_buffer_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Smoothing_buffer_descriptor other = (Smoothing_buffer_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getSb_leak_rate(), other.getSb_leak_rate());
	        builder.append(getSb_size(), other.getSb_size());
    		return builder.isEquals();
        }
        return false;
    }
}
