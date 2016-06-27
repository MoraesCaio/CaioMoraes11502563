package descriptorsPMT;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class ISO_639_language_descriptor extends Descriptor {
	
	private class Unidade{
		
		private Unidade read(FileInputStream fi){
			try{
				int xbyte1 = fi.read();
				int xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				setISO_639_language_code(xbyte1);
				setAudio_type(fi.read());
			}catch(IOException e){
				e.printStackTrace();
			}
			return this;
		}

		private int ISO_639_language_code;
		private int getISO_639_language_code(){
			return ISO_639_language_code;
		}
		private void setISO_639_language_code(int ISO_639_language_code){
			this.ISO_639_language_code = ISO_639_language_code;
		}
		private int audio_type;
		private int getAudio_type(){
			return audio_type;
		}
		private void setAudio_type(int audio_type){
			this.audio_type = audio_type;
		}
		public String toString(){
			String s = super.toString();
			s += "ISO_639_language_code: "+br.intBinaryString(ISO_639_language_code,8)+"\n";
			s += "Audio_type: "+br.intBinaryString(audio_type,24)+"\n";
			return s;
		}

		public boolean equals(Object obj) {
		    if (obj instanceof Unidade) {
		        Unidade other = (Unidade) obj;
		        EqualsBuilder builder = new EqualsBuilder();
		        builder.append(getISO_639_language_code(), other.getISO_639_language_code());
		        builder.append(getAudio_type(), other.getAudio_type());
        		return builder.isEquals();
            }
            return false;
        }
	}

	public ISO_639_language_descriptor(){
		setDescriptor_tag(10);
	}

	public ISO_639_language_descriptor read(FileInputStream fi){
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
	    if (obj instanceof ISO_639_language_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        ISO_639_language_descriptor other = (ISO_639_language_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getUnidadesDeInfo(), other.getUnidadesDeInfo());
    		return builder.isEquals();
        }
        return false;
    }
}
