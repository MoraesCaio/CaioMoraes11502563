package descriptorsPMT;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class CA_descriptor extends Descriptor {

	public CA_descriptor(){
		setDescriptor_tag(9);
	}

	public CA_descriptor read(FileInputStream fi){
		try{
			super.read(fi);
			int xbyte1 = fi.read();
			int xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			setCA_system_ID(xbyte1);
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 19, 32);
			setCA_PID(xbyte1);
			for (int i = 0; i < (descriptor_length - 4); i++) {
				setPrivate_data_byte(fi.read());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int CA_system_ID;
	public int getCA_system_ID(){
		return CA_system_ID;
	}
	public void setCA_system_ID(int CA_system_ID){
		this.CA_system_ID = CA_system_ID;
	}
	private int CA_PID;
	public int getCA_PID(){
		return CA_PID;
	}
	public void setCA_PID(int CA_PID){
		this.CA_PID = CA_PID;
	}
	private ArrayList<Integer> private_data_byte = new ArrayList<Integer>();
	public ArrayList<Integer> getPrivate_data_byte(){
		return private_data_byte;
	}
	public void setPrivate_data_byte(Integer private_data_byte){
		this.private_data_byte.add(private_data_byte);
	}
	public String toString(){
		String s = super.toString();
		s += "CA_system_ID: "+br.intBinaryString(CA_system_ID,16)+"\n";
		s += "CA_PID: "+br.intBinaryString(CA_PID,19)+"\n";
		for(Integer i : private_data_byte){
			s += "Private_data_byte: "+br.intBinaryString(i,24)+"\n";
		}
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof CA_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        CA_descriptor other = (CA_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getCA_system_ID(), other.getCA_system_ID());
	        builder.append(getCA_PID(), other.getCA_PID());
    		return builder.isEquals();
        }
        return false;
    }
}
