package descriptorsPMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Video_stream_descriptor extends Descriptor {

	public Video_stream_descriptor(){
		setDescriptor_tag(2);
	}
	public Video_stream_descriptor(int descriptor_tag, int descriptor_length){
		setDescriptor_tag(descriptor_tag);
		setDescriptor_length(descriptor_length);
	}
	public Video_stream_descriptor read(FileInputStream fi){
		try{
			int xbyte1 = fi.read();
			setMultiple_frame_rate_flag(br.intExtrairBit(xbyte1,8));
			setMPEG1_only_flag(br.intExtrairBit(xbyte1,3));
			setConstrained_parameter_flag(br.intExtrairBit(xbyte1,2));
			setStill_picture_flag(br.intExtrairBit(xbyte1,1));
			xbyte1 = xbyte1 >> 3;
			xbyte1 = br.intZerarBits(xbyte1, 28, 32);
			if(MPEG1_only_flag == 0){
				int xbyte2 = 0;
				setProfile_and_level_indication(fi.read());
				xbyte1 = fi.read();
				setFrame_rate_extension_flag(br.intExtrairBit(xbyte1, 6));
				xbyte1 = br.intExtrairBit(xbyte1, 7);
				xbyte1 = br.intExtrairBit(xbyte1, 8);
				xbyte1 = br.concatBits(xbyte1, xbyte2);
				setChroma_format(xbyte1);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int multiple_frame_rate_flag;
	public int getMultiple_frame_rate_flag(){
		return multiple_frame_rate_flag;
	}
	public void setMultiple_frame_rate_flag(int multiple_frame_rate_flag){
		this.multiple_frame_rate_flag = multiple_frame_rate_flag;
	}

	private int frame_rate_code;
	public int getFrame_rate_code(){
		return frame_rate_code;
	}
	public void setFrame_rate_code(int frame_rate_code){
		this.frame_rate_code = frame_rate_code;
	}

	private int MPEG1_only_flag;
	public int getMPEG1_only_flag(){
		return MPEG1_only_flag;
	}
	public void setMPEG1_only_flag(int MPEG1_only_flag){
		this.MPEG1_only_flag = MPEG1_only_flag;
		if(MPEG1_only_flag != 0){
			setProfile_and_level_indication(0);
			setChroma_format(0);
			setFrame_rate_code(0);
		}
	}

	private int constrained_parameter_flag;
	public int getConstrained_parameter_flag(){
		return constrained_parameter_flag;
	}
	public void setConstrained_parameter_flag(int constrained_parameter_flag){
		this.constrained_parameter_flag = constrained_parameter_flag;
	}

	private int still_picture_flag;
	public int getStill_picture_flag(){
		return still_picture_flag;
	}
	public void setStill_picture_flag(int still_picture_flag){
		this.still_picture_flag = still_picture_flag;
	}

	private int profile_and_level_indication;
	public int getProfile_and_level_indication(){
		return profile_and_level_indication;
	}
	public void setProfile_and_level_indication(int profile_and_level_indication){
		this.profile_and_level_indication = profile_and_level_indication;
	}

	private int chroma_format;
	public int getChroma_format(){
		return chroma_format;
	}
	public void setChroma_format(int chroma_format){
		this.chroma_format = chroma_format;
	}

	private int frame_rate_extension_flag;
	public int getFrame_rate_extension_flag(){
		return frame_rate_extension_flag;
	}
	public void setFrame_rate_extension_flag(int frame_rate_extension_flag){
		this.frame_rate_extension_flag = frame_rate_extension_flag;
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
		s += "Multiple_frame_rate_flag: "+br.intBinaryString(multiple_frame_rate_flag,31)+"\n";
		s += "Frame_rate_code: "+br.intBinaryString(frame_rate_code,28)+"\n";
		s += "MPEG1_only_flag: "+br.intBinaryString(MPEG1_only_flag,31)+"\n";
		s += "Constrained_parameter_flag: "+br.intBinaryString(constrained_parameter_flag,31)+"\n";
		s += "Still_picture_flag: "+br.intBinaryString(still_picture_flag,31)+"\n";
		s += "Profile_and_level_indication: "+br.intBinaryString(profile_and_level_indication,24)+"\n";
		s += "Chroma_format: "+br.intBinaryString(chroma_format,30)+"\n";
		s += "Frame_rate_extension_flag: "+br.intBinaryString(frame_rate_extension_flag,33)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Video_stream_descriptor) {
	    	if(!super.equals(obj)){
		    		return false;
		    }
	        Video_stream_descriptor other = (Video_stream_descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getMultiple_frame_rate_flag(), other.getMultiple_frame_rate_flag());
	        builder.append(getFrame_rate_code(), other.getFrame_rate_code());
	        builder.append(getMPEG1_only_flag(), other.getMPEG1_only_flag());
	        builder.append(getConstrained_parameter_flag(), other.getConstrained_parameter_flag());
	        builder.append(getStill_picture_flag(), other.getStill_picture_flag());
	        builder.append(getProfile_and_level_indication(), other.getProfile_and_level_indication());
	        builder.append(getChroma_format(), other.getChroma_format());
	        builder.append(getFrame_rate_extension_flag(), other.getFrame_rate_extension_flag());
    		return builder.isEquals();
        }
        return false;
    }

}
