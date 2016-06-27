package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class System_clock_descriptor extends Descriptor {

	public System_clock_descriptor(){
		setDescriptor_tag(11);
	}

	public System_clock_descriptor read(FileInputStream fi){
		try{
			super.read(fi);
			int xbyte1 = fi.read();
			setExternal_clock_reference_indicator(br.intExtrairBit(xbyte1, 8));
			xbyte1 = br.intZerarBits(xbyte1, 26, 32);
			setClock_accuracy_integer(xbyte1);
			xbyte1 = fi.read();
			xbyte1 = xbyte1 >> 5;
			xbyte1 = br.intZerarBits(xbyte1, 29, 32);
			setClock_accuracy_exponent(xbyte1);
		}catch(IOException e){
			e.printStackTrace();
		}
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
		s += "External_clock_reference_indicator: "+br.intBinaryString(external_clock_reference_indicator,31)+"\n";
		s += "Clock_accuracy_integer: "+br.intBinaryString(clock_accuracy_integer,26)+"\n";
		s += "Clock_accuracy_exponent: "+br.intBinaryString(clock_accuracy_exponent,29)+"\n";
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
