package descriptorsPMT;

import java.io.*;
import manipulacaoBitByte.ByteReader;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Descriptor {
	ByteReader br = new ByteReader();
	public Descriptor read(FileInputStream fi){
		try{
			setDescriptor_tag(fi.read());
			setDescriptor_length(fi.read());
			switch(this.descriptor_tag){
				case 2:
					Video_stream_descriptor d2 = new Video_stream_descriptor();
					d2.read(fi);
					return d2;
				case 3:
					Audio_stream_descriptor d3 = new Audio_stream_descriptor();
					d3.read(fi);
					return d3;
				case 4:
					Hierarchy_descriptor d4 = new Hierarchy_descriptor();
					d4.read(fi);
					return d4;
				case 5:
					Registration_descriptor d5 = new Registration_descriptor();
					d5.read(fi);
					return d5;
				case 6:
					Data_stream_alignment_descriptor d6 = new Data_stream_alignment_descriptor();
					d6.read(fi);
					return d6;
				case 7:
					Target_background_grid_descriptor d7 = new Target_background_grid_descriptor();
					d7.read(fi);
					return d7;
				case 8:
					Video_window_descriptor d8 = new Video_window_descriptor();
					d8.read(fi);
					return d8;
				case 9:
					CA_descriptor d9 = new CA_descriptor();
					d9.read(fi);
					return d9;
				case 10:
					ISO_639_language_descriptor d10 = new ISO_639_language_descriptor();
					d10.read(fi);
					return d10;
				case 11:
					System_clock_descriptor d11 = new System_clock_descriptor();
					d11.read(fi);
					return d11;
				case 12:
					Multiplex_buffer_utilization_descriptor d12 = new Multiplex_buffer_utilization_descriptor();
					d12.read(fi);
					return d12;
				case 13:
					Copyright_descriptor d13 = new Copyright_descriptor();
					d13.read(fi);
					return d13;
				case 14:
					Maximum_bitrate_descriptor d14 = new Maximum_bitrate_descriptor();
					d14.read(fi);
					return d14;
				case 15:
					Private_data_indicator_descriptor d15 = new Private_data_indicator_descriptor();
					d15.read(fi);
					return d15;
				case 16:
					Smoothing_buffer_descriptor d16 = new Smoothing_buffer_descriptor();
					d16.read(fi);
					return d16;
				case 17:
					STD_descriptor d17 = new STD_descriptor();
					d17.read(fi);
					return d17;
				case 18:
					IBP_descriptor d18 = new IBP_descriptor();
					d18.read(fi);
					return d18;
				case 27:
					MPEG4_video_descriptor d27 = new MPEG4_video_descriptor();
					d27.read(fi);
					return d27;
				case 28:
					MPEG4_audio_descriptor d28 = new MPEG4_audio_descriptor();
					d28.read(fi);
					return d28;
				case 29:
					IOD_descriptor d29 = new IOD_descriptor();
					d29.read(fi);
					return d29;
				case 30:
					SL_descriptor d30 = new SL_descriptor();
					d30.read(fi);
					return d30;
				case 31:
					FMC_descriptor d31 = new FMC_descriptor();
					d31.read(fi);
					return d31;
				case 32:
					External_ES_ID_descriptor d32 = new External_ES_ID_descriptor();
					d32.read(fi);
					return d32;
				case 33:
					Muxcode_descriptor d33 = new Muxcode_descriptor();
					d33.read(fi);
					return d33;
				case 34:
					FmxBufferSize_descriptor d34 = new FmxBufferSize_descriptor();
					d34.read(fi);
					return d34;
				case 35:
					MultiplexBuffer_descriptor d35 = new MultiplexBuffer_descriptor();
					d35.read(fi);
					return d35;
				default:
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	protected String nome_descriptor;
	public String getNome_descriptor(){
		return nome_descriptor;
	}
	public void setNome_descriptor(String nome_descriptor){
		this.nome_descriptor = nome_descriptor;
	}
	protected int descriptor_tag;
	public int getDescriptor_tag(){
		return descriptor_tag;
	}
	public void setDescriptor_tag(int descriptor_tag){
		this.descriptor_tag = descriptor_tag;
		switch(descriptor_tag){
			case 2:
				setNome_descriptor("Video Stream");
				break;
			case 3:
				setNome_descriptor("Audio Stream");
				break;
			case 4:
				setNome_descriptor("Hierarchy");
				break;
			case 5:
				setNome_descriptor("Registration");
				break;
			case 6:
				setNome_descriptor("Data Stream Alignment");
				break;
			case 7:
				setNome_descriptor("Target Background Grid");
				break;
			case 8:
				setNome_descriptor("Video Window");
				break;
			case 9:
				setNome_descriptor("CA");
				break;
			case 10:
				setNome_descriptor("ISO 639 Language");
				break;
			case 11:
				setNome_descriptor("System Clock");
				break;
			case 12:
				setNome_descriptor("Multiplex Buffer Utilization");
				break;
			case 13:
				setNome_descriptor("Copyright");
				break;
			case 14:
				setNome_descriptor("Maximum Bitrate");
				break;
			case 15:
				setNome_descriptor("Private Data Indicator");
				break;
			case 16:
				setNome_descriptor("Smoothing Buffer");
				break;
			case 17:
				setNome_descriptor("STD");
				break;
			case 18:
				setNome_descriptor("IBP");
				break;
			case 27:
				setNome_descriptor("MPEG-4 Video");
				break;
			case 28:
				setNome_descriptor("MPEG-4 Audio");
				break;
			case 29:
				setNome_descriptor("IOD");
				break;
			case 30:
				setNome_descriptor("SL");
				break;
			case 31:
				setNome_descriptor("FMC");
				break;
			case 32:
				setNome_descriptor("External ES ID");
				break;
			case 33:
				setNome_descriptor("MuxCode");
				break;
			case 34:
				setNome_descriptor("FMXBufferSize");
				break;
			case 35:
				setNome_descriptor("MultiplexBuffer");
				break;
			default:
				setNome_descriptor("Tipo indefinido");
		}
	}

	protected int descriptor_length;
	public int getDescriptor_length(){
		return descriptor_length;
	}
	public void setDescriptor_length(int descriptor_length){
		this.descriptor_length = descriptor_length;
	}

	public String toString(){
		String s = "\n";
		s += "Descriptor do tipo:\t"+nome_descriptor+"\n";
		s += "Descriptor_tag: "+br.intBinaryString(descriptor_tag,24)+"\n";
		s += "Descriptor_length: "+br.intBinaryString(descriptor_length,24)+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Descriptor) {
	        Descriptor other = (Descriptor) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getDescriptor_tag(), other.getDescriptor_tag());
	        builder.append(getDescriptor_length(), other.getDescriptor_length());
    		return builder.isEquals();
        }
        return false;
    }
}

/*
//detalhes importantes sobre algumas classes descriptor
regis arraylist
ca arraylist
iso639lang pacote
copy arraylist
fmc pacote
muxcode arraylist ?? pg 97
FMX ?? e arraylist ?? pg 97


import java.io.*;

	public XXXX(){
		setDescriptor_tag(W);
	}

	public XXXX read(FileInputStream fi){
		try{
			super.read(fi);
			int xbyte1;
			int xbyte2;

		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}


quando não tem implementação

import java.io.*;

	public XXXX(){
		setDescriptor_tag(W);
	}

	public XXXX read(FileInputStream fi){
		super.read(fi);
		return this;
	}

	public String toString(){
		String s = super.toString();
		s += "Implementação não especificada.\n";
		return s;
	}
*/
