package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class MultiplexBuffer_descriptor extends Descriptor {

	public MultiplexBuffer_descriptor(){
		setDescriptor_tag(35);
	}
	public MultiplexBuffer_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}

	public MultiplexBuffer_descriptor read(FileInputStream fi){
		setMB_buffer_size(br.lerBytes(fi, 3));
		setTB_leak_rate(br.lerBytes(fi, 3));
		return this;
	}
	private int MB_buffer_size;
	public int getMB_buffer_size(){
		return MB_buffer_size;
	}
	public void setMB_buffer_size(int MB_buffer_size){
		this.MB_buffer_size = MB_buffer_size;
	}
	private int TB_leak_rate;
	public int getTB_leak_rate(){
		return TB_leak_rate;
	}
	public void setTB_leak_rate(int TB_leak_rate){
		this.TB_leak_rate = TB_leak_rate;
	}
	public String toString(){
		String s = super.toString();
		s += "MB_buffer_size: "+br.binaryString(MB_buffer_size,8)+"\n";
		s += "TB_leak_rate: "+br.binaryString(TB_leak_rate,8)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof MultiplexBuffer_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        MultiplexBuffer_descriptor other = (MultiplexBuffer_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getMB_buffer_size(), other.getMB_buffer_size());
	        builder.append(getTB_leak_rate(), other.getTB_leak_rate());
    		return builder.isEquals();
        }
        return false;
    }
}
