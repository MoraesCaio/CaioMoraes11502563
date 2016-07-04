package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Hierarchy_descriptor extends Descriptor {
	public Hierarchy_descriptor(){
		setDescriptor_tag(4);
	}
	public Hierarchy_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public Hierarchy_descriptor read(FileInputStream fi){
		setHierarchy_type(br.zerarBits(br.lerBytes(fi, 1), 28));
		setHierarchy_layer_index(br.zerarBits(br.lerBytes(fi, 1), 26));
		setHierarchy_embedded_layer_index(br.zerarBits(br.lerBytes(fi, 1), 26));
		setHierarchy_channel(br.zerarBits(br.lerBytes(fi, 1), 26));
		return this;
	}
	private int hierarchy_type;
	public int getHierarchy_type(){
		return hierarchy_type;
	}
	public void setHierarchy_type(int hierarchy_type){
		this.hierarchy_type = hierarchy_type;
	}

	private int hierarchy_layer_index;
	public int getHierarchy_layer_index(){
		return hierarchy_layer_index;
	}
	public void setHierarchy_layer_index(int hierarchy_layer_index){
		this.hierarchy_layer_index = hierarchy_layer_index;
	}

	private int hierarchy_embedded_layer_index;
	public int getHierarchy_embedded_layer_index(){
		return hierarchy_embedded_layer_index;
	}
	public void setHierarchy_embedded_layer_index(int hierarchy_embedded_layer_index){
		this.hierarchy_embedded_layer_index = hierarchy_embedded_layer_index;
	}

	private int hierarchy_channel;
	public int getHierarchy_channel(){
		return hierarchy_channel;
	}
	public void setHierarchy_channel(int hierarchy_channel){
		this.hierarchy_channel = hierarchy_channel;
	}

	public String toString(){
		String s = super.toString();
		s += "Hierarchy_type: "+br.binaryString(hierarchy_type,28)+"\n";
		s += "Hierarchy_layer_index: "+br.binaryString(hierarchy_layer_index,26)+"\n";
		s += "Hierarchy_embedded_layer_index: "+br.binaryString(hierarchy_embedded_layer_index,26)+"\n";
		s += "Hierarchy_channel: "+br.binaryString(hierarchy_channel,26)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Hierarchy_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Hierarchy_descriptor other = (Hierarchy_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getHierarchy_type(), other.getHierarchy_type());
	        builder.append(getHierarchy_layer_index(), other.getHierarchy_layer_index());
	        builder.append(getHierarchy_embedded_layer_index(), other.getHierarchy_embedded_layer_index());
	        builder.append(getHierarchy_channel(), other.getHierarchy_channel());
    		return builder.isEquals();
        }
        return false;
    }
}
