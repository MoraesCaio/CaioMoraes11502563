package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Audio_stream_descriptor extends Descriptor {

	public Audio_stream_descriptor(){
		setDescriptor_tag(3);
	}
	public Audio_stream_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public Audio_stream_descriptor read(FileInputStream fi){
		int xbyte1 = br.lerBytes(fi, 1);
		setFree_format_flag(br.extrairBit(xbyte1, 8));
		setID(br.extrairBit(xbyte1, 7));
		setVariable_rate_audio_indicator(br.extrairBit(xbyte1, 4));
		xbyte1 = xbyte1 >> 4;
		setLayer(br.zerarBits(xbyte1, 30));
		return this;
	}
	private int free_format_flag;
	public int getFree_format_flag(){
		return free_format_flag;
	}
	public void setFree_format_flag(int free_format_flag){
		this.free_format_flag = free_format_flag;
	}
	
	private int ID;
	public int getID(){
		return ID;
	}
	public void setID(int ID){
		this.ID = ID;
	}
	
	private int layer;
	public int getLayer(){
		return layer;
	}
	public void setLayer(int layer){
		this.layer = layer;
	}
	
	private int variable_rate_audio_indicator;
	public int getVariable_rate_audio_indicator(){
		return variable_rate_audio_indicator;
	}
	public void setVariable_rate_audio_indicator(int variable_rate_audio_indicator){
		this.variable_rate_audio_indicator = variable_rate_audio_indicator;
	}
	
	private int reserved;
	public int getReserved(){
		return reserved;
	}
	public void setReserved(int reserved){
		this.reserved = reserved;
	}

	
	public String toString(){
		String s = super.toString();
		s += "Free_format_flag: "+br.binaryString(free_format_flag,31)+"\n";
		s += "ID: "+br.binaryString(ID,31)+"\n";
		s += "Layer: "+br.binaryString(layer,30)+"\n";
		s += "Variable_rate_audio_indicator: "+br.binaryString(variable_rate_audio_indicator,31)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Audio_stream_descriptor) {
	    	if(!super.equals(obj)){
	    		return false;
	    	}
	        Audio_stream_descriptor other = (Audio_stream_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getFree_format_flag(), other.getFree_format_flag());
	        builder.append(getID(), other.getID());
	        builder.append(getLayer(), other.getLayer());
	        builder.append(getVariable_rate_audio_indicator(), other.getVariable_rate_audio_indicator());
    		return builder.isEquals();
        }
        return false;
    }
}
