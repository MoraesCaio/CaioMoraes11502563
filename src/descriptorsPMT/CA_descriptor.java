package descriptorsPMT;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class CA_descriptor extends Descriptor {

	public CA_descriptor(){
		setDescriptor_tag(9);
	}
	public CA_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}

	public CA_descriptor read(FileInputStream fi){
		setCA_system_ID(br.lerBytes(fi, 2));
		setCA_PID(br.zerarBits(br.lerBytes(fi, 2), 19));
		for (int i = 0; i < (descriptor_length - 4); i++) {
			setPrivate_data_byte(br.lerBytes(fi, 1));
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
		s += "CA_system_ID: "+br.binaryString(CA_system_ID,16)+"\n";
		s += "CA_PID: "+br.binaryString(CA_PID,19)+"\n";
		for(Integer i : private_data_byte){
			s += "Private_data_byte: "+br.binaryString(i,24)+"\n";
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
