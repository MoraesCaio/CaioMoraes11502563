package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class System_clock_descriptor extends Descriptor {

	public System_clock_descriptor(){
		setDescriptor_tag(11);
	}
	public System_clock_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}

	public System_clock_descriptor read(FileInputStream fi){
		int xbyte1 = br.lerBytes(fi, 1);
		setExternal_clock_reference_indicator(br.extrairBit(xbyte1, 8));
		setClock_accuracy_integer(br.zerarBits(xbyte1, 26));
		xbyte1 = br.lerBytes(fi, 1);
		xbyte1 = xbyte1 >> 5;
		xbyte1 = br.zerarBits(xbyte1, 29);
		setClock_accuracy_exponent(xbyte1);
		return this;
	}
	private int external_clock_reference_indicator;
	public int getExternal_clock_reference_indicator(){
		return external_clock_reference_indicator;
	}
	public void setExternal_clock_reference_indicator(int external_clock_reference_indicator){
		this.external_clock_reference_indicator = external_clock_reference_indicator;
	}
	private int clock_accuracy_integer;
	public int getClock_accuracy_integer(){
		return clock_accuracy_integer;
	}
	public void setClock_accuracy_integer(int clock_accuracy_integer){
		this.clock_accuracy_integer = clock_accuracy_integer;
	}
	private int clock_accuracy_exponent;
	public int getClock_accuracy_exponent(){
		return clock_accuracy_exponent;
	}
	public void setClock_accuracy_exponent(int clock_accuracy_exponent){
		this.clock_accuracy_exponent = clock_accuracy_exponent;
	}
	public String toString(){
		String s = super.toString();
		s += "External_clock_reference_indicator: "+br.binaryString(external_clock_reference_indicator,31)+"\n";
		s += "Clock_accuracy_integer: "+br.binaryString(clock_accuracy_integer,26)+"\n";
		s += "Clock_accuracy_exponent: "+br.binaryString(clock_accuracy_exponent,29)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof System_clock_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        System_clock_descriptor other = (System_clock_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getExternal_clock_reference_indicator(), other.getExternal_clock_reference_indicator());
	        builder.append(getClock_accuracy_integer(), other.getClock_accuracy_integer());
	        builder.append(getClock_accuracy_exponent(), other.getClock_accuracy_exponent());
    		return builder.isEquals();
        }
        return false;
    }
}
