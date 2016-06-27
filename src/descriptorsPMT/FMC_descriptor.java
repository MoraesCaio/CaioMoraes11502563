package descriptorsPMT;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class FMC_descriptor extends Descriptor {
	private class Unidade{

		private Unidade read(FileInputStream fi){
			try{
				int xbyte1 = fi.read();
				int xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				setES_ID(xbyte1);
				setFlex_mux_channel(fi.read());
			}catch(IOException e){
				e.printStackTrace();
			}
			return this;
		}
		private int ES_ID;
		private int getES_ID(){
			return ES_ID;
		}
		private void setES_ID(int ES_ID){
			this.ES_ID = ES_ID;
		}
		private int flex_mux_channel;
		private int getFlex_mux_channel(){
			return flex_mux_channel;
		}
		private void setFlex_mux_channel(int flex_mux_channel){
			this.flex_mux_channel = flex_mux_channel;
		}
		public String toString(){
			String s = "";
			s += "ES_ID: "+br.intBinaryString(ES_ID,16)+"\n";
			s += "Flex_mux_channel: "+br.intBinaryString(flex_mux_channel,24)+"\n";
			return s;
		}
		public boolean equals(Object obj) {
		    if (obj instanceof Unidade) {
		        Unidade other = (Unidade) obj;
		        EqualsBuilder builder = new EqualsBuilder();
		        builder.append(getES_ID(), other.getES_ID());
		        builder.append(getFlex_mux_channel(), other.getFlex_mux_channel());
        		return builder.isEquals();
            }
            return false;
        }
	}

	public FMC_descriptor(){
		setDescriptor_tag(31);
	}

	public FMC_descriptor read(FileInputStream fi){
		super.read(fi);
		for(int i = 0; i < descriptor_length; i += 3){
			Unidade u = new Unidade();
			setUnidadesDeInfo(u.read(fi));
		}
		return this;
	}

	private ArrayList<Unidade> unidadesDeInfo = new ArrayList<Unidade>();
	public ArrayList<Unidade> getUnidadesDeInfo(){
		return unidadesDeInfo;
	}
	public void setUnidadesDeInfo(Unidade unidadesDeInfo){
		this.unidadesDeInfo.add(unidadesDeInfo);
	}
	public String toString(){
		String s = super.toString();
		for(Unidade u : unidadesDeInfo){
			s += u.toString();
		}
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof FMC_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        FMC_descriptor other = (FMC_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getUnidadesDeInfo(), other.getUnidadesDeInfo());
    		return builder.isEquals();
        }
        return false;
    }
}
