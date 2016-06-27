package estruturasPATePMT;

import java.io.*;
import java.util.ArrayList;
import manipulacaoBitByte.ByteReader;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class AdaptationField {
	ByteReader br = new ByteReader();
	public AdaptationField read(FileInputStream fi){
		try{
			int xbyte1 = 0;
			int xbyte2 = 0;
			xbyte1 = fi.read();
			if(xbyte1 == 0){
				setAdaptation_field_length(xbyte1);
			}else{
				xbyte1 = fi.read();
				setDiscontinuity_indicator(br.intExtrairBit(8));
				setRandom_access_indicator(br.intExtrairBit(7));
				setElementary_stream_priority_indicator(br.intExtrairBit(6));
				setPCR_flag(br.intExtrairBit(5));
				setOPCR_flag(br.intExtrairBit(4));
				setSplicing_point_flag(br.intExtrairBit(3));
				setTransport_private_data_flag(br.intExtrairBit(2));
				setAdaptation_field_extension_flag(br.intExtrairBit(1));
			}
			if(PCR_flag == 1){
				//vai somando os bytes
				xbyte1 = fi.read();
				xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				program_clock_reference_base = br.intBinaryStringRaw(xbyte1);
				//byte a seguir serve para dois campos
				xbyte2 = fi.read();
				xbyte1 = br.intExtrairBit(xbyte2, 8);
				program_clock_reference_base += Integer.toString(xbyte1);
				xbyte1 = br.intExtrairBit(xbyte2, 1);
				program_clock_reference_extension += Integer.toString(xbyte1);
				xbyte1 = fi.read();
				program_clock_reference_extension = br.intBinaryStringRaw(xbyte1);
			}
			if(OPCR_flag == 1){
				//vai somando os bytes
				xbyte1 = fi.read();
				xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				xbyte2 = fi.read();
				xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
				original_program_clock_reference_base = br.intBinaryStringRaw(xbyte1);
				//byte a seguir serve para dois campos
				xbyte2 = fi.read();
				xbyte1 = br.intExtrairBit(xbyte2, 8);
				original_program_clock_reference_base += Integer.toString(xbyte1);
				xbyte1 = br.intExtrairBit(xbyte2, 1);
				original_program_clock_reference_extension += Integer.toString(xbyte1);
				xbyte1 = fi.read();
				original_program_clock_reference_extension = br.intBinaryStringRaw(xbyte1);
			}
			if(splicing_point_flag == 1){
				setSplice_countdown(fi.read());
			}
			if(transport_private_data_flag == 1){
				setTransport_private_data_length(fi.read());
				for(int i = 0; i < transport_private_data_length; i++){
					setPrivate_data_byte(fi.read());
				}
			}
			if(adaptation_field_extension_length == 1){
				xbyte1 = fi.read();
				setAdaptation_field_extension_length(xbyte1);
				int bytesRestantes = adaptation_field_extension_length - 1;
				if(xbyte1 != 0 && xbyte1 != 1){
					xbyte1 = fi.read();
					setLtw_flag(br.intExtrairBit(xbyte1, 1));
					setPiecewise_rate_flag(br.intExtrairBit(xbyte1, 2));
					setSeamless_splice_flag(br.intExtrairBit(xbyte1, 3));
				}
				if(ltw_flag == 1){
					xbyte1 = fi.read();
					xbyte2 = fi.read();
					xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
					ltw_valid_flag = br.intExtrairBit(xbyte1, 1);
					ltw_offset = br.intZerarBits(xbyte1, 1, 16);
					bytesRestantes -= 2;
				}
				if(piecewise_rate_flag == 1){
					xbyte1 = fi.read();
					xbyte2 = fi.read();
					xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
					xbyte2 = fi.read();
					xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
					xbyte1 = br.intZerarBits(xbyte1, 10, 32);
					setPiecewise_rate(xbyte1);
					bytesRestantes -= 3;
				}
				if(seamless_splice_flag == 1){
					xbyte1 = fi.read();
					xbyte2 = xbyte1;
					setSplice_type(xbyte1 >> 4);
					xbyte2 = xbyte2 >> 1;
					xbyte2 = br.intZerarBits(xbyte2, 29, 32);
					setDTS_next_AU(xbyte2);
					xbyte1 = fi.read();
					xbyte2 = fi.read();
					xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
					xbyte1 = br.intZerarBits(xbyte1, 17, 32);
					setDTS_next_AU2(xbyte1);
					xbyte1 = fi.read();
					xbyte2 = fi.read();
					xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
					xbyte1 = br.intZerarBits(xbyte1, 17, 32);
					setDTS_next_AU2(xbyte1);
					bytesRestantes -= 5;
				}
				for(int i = 0; i < bytesRestantes; i++){
					reserved5.add(fi.read());
				}
			}

		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private int adaptation_field_length;
	public int getAdaptation_field_length(){
		return adaptation_field_length;
	}
	public void setAdaptation_field_length(int adaptation_field_length){
		this.adaptation_field_length = adaptation_field_length;
		if(adaptation_field_length == 0){
			//todos de 1 bit
			discontinuity_indicator = 0;
			random_access_indicator = 0;
			elementary_stream_priority_indicator = 0;
			//1-bit flags de extensão
			setPCR_flag(0);
			setOPCR_flag(0);
			setSplicing_point_flag(0);
			setTransport_private_data_flag(0);
			setAdaptation_field_extension_flag(0);
		}
	}

	private int discontinuity_indicator;
	public int getDiscontinuity_indicator(){
		return discontinuity_indicator;
	}
	public void setDiscontinuity_indicator(int discontinuity_indicator){
		this.discontinuity_indicator = discontinuity_indicator;
	}

	private int random_access_indicator;
	public int getRandom_access_indicator(){
		return random_access_indicator;
	}
	public void setRandom_access_indicator(int random_access_indicator){
		this.random_access_indicator = random_access_indicator;
	}

	private int elementary_stream_priority_indicator;
	public int getElementary_stream_priority_indicator(){
		return elementary_stream_priority_indicator;
	}
	public void setElementary_stream_priority_indicator(int elementary_stream_priority_indicator){
		this.elementary_stream_priority_indicator = elementary_stream_priority_indicator;
	}

	private int PCR_flag;
	public int getPCR_flag(){
		return PCR_flag;
	}
	public void setPCR_flag(int PCR_flag){
		this.PCR_flag = PCR_flag;
		if(this.PCR_flag != 1){
			program_clock_reference_base = "0";
			program_clock_reference_extension = "0";
		}
	}

	private int OPCR_flag;
	public int getOPCR_flag(){
		return OPCR_flag;
	}
	public void setOPCR_flag(int OPCR_flag){
		this.OPCR_flag = OPCR_flag;
		if(this.PCR_flag != 1){
			original_program_clock_reference_base = "0";
			original_program_clock_reference_extension = "0";
		}
	}

	private int splicing_point_flag;
	public int getSplicing_point_flag(){
		return splicing_point_flag;
	}
	public void setSplicing_point_flag(int splicing_point_flag){
		this.splicing_point_flag = splicing_point_flag;
		if(splicing_point_flag != 1){
			setSplice_countdown(0);
		}
	}

	private int transport_private_data_flag;
	public int getTransport_private_data_flag(){
		return transport_private_data_flag;
	}
	public void setTransport_private_data_flag(int transport_private_data_flag){
		this.transport_private_data_flag = transport_private_data_flag;
		if(transport_private_data_flag != 1){
			setTransport_private_data_length(0);
		}
	}

	private int adaptation_field_extension_flag;
	public int getAdaptation_field_extension_flag(){
		return adaptation_field_extension_flag;
	}
	public void setAdaptation_field_extension_flag(int adaptation_field_extension_flag){
		this.adaptation_field_extension_flag = adaptation_field_extension_flag;
		if(adaptation_field_extension_flag != 1){
			setAdaptation_field_extension_length(0);
		}
	}

	private String program_clock_reference_base;
	public String getProgram_clock_reference_base(){
		return program_clock_reference_base;
	}
	public void setProgram_clock_reference_base(String program_clock_reference_base){
		this.program_clock_reference_base = program_clock_reference_base;
	}


	private String program_clock_reference_extension;
	public String getProgram_clock_reference_extension(){
		return program_clock_reference_extension;
	}
	public void setProgram_clock_reference_extension(String program_clock_reference_extension){
		this.program_clock_reference_extension = program_clock_reference_extension;
	}

	private String original_program_clock_reference_base;
	public String getOriginal_program_clock_reference_base(){
		return original_program_clock_reference_base;
	}
	public void setOriginal_program_clock_reference_base(String original_program_clock_reference_base){
		this.original_program_clock_reference_base = original_program_clock_reference_base;
	}

	private String original_program_clock_reference_extension;
	public String getOriginal_program_clock_reference_extension(){
		return original_program_clock_reference_extension;
	}
	public void setOriginal_program_clock_reference_extension(String original_program_clock_reference_extension){
		this.original_program_clock_reference_extension = original_program_clock_reference_extension;
	}
	//splice countdown pode ser positivo ou negativo (1 byte)
	private byte splice_countdown;
	public byte getSplice_countdown(){
		return splice_countdown;
	}
	public void setSplice_countdown(int splice_countdown){
		//ByteReader br = new ByteReader();
		//br.intExtrairBit(splice_countdown, 8);
		this.splice_countdown = (byte) splice_countdown;
	}
	//não deve exceder o tamanho restante de adaptation field
	private int transport_private_data_length;
	public int getTransport_private_data_length(){
		return transport_private_data_length;
	}
	public void setTransport_private_data_length(int transport_private_data_length){
		this.transport_private_data_length = transport_private_data_length;
		if(transport_private_data_length == 0){
			private_data_byte.add(0);
		}
	}

	private ArrayList<Integer> private_data_byte = new ArrayList<Integer>();
	public ArrayList<Integer> getPrivate_data_byte(){
		return private_data_byte;
	}
	public void setPrivate_data_byte(int private_data_byte){
		this.private_data_byte.add(private_data_byte);
	}

	private int adaptation_field_extension_length;
	public int getAdaptation_field_extension_length(){
		return adaptation_field_extension_length;
	}
	public void setAdaptation_field_extension_length(int adaptation_field_extension_length){
		this.adaptation_field_extension_length = adaptation_field_extension_length;
		if(this.adaptation_field_extension_length == 0 ||
			this.adaptation_field_extension_length == 1){
			setLtw_flag(0);
			setPiecewise_rate(0);
			setSeamless_splice_flag(0);
		}
	}

	private int ltw_flag;
	public int getLtw_flag(){
		return ltw_flag;
	}
	public void setLtw_flag(int ltw_flag){
		this.ltw_flag = ltw_flag;
		if(this.ltw_flag != 1){
			ltw_valid_flag = 0;
			ltw_offset = 0;
		}
	}

	private int piecewise_rate_flag;
	public int getPiecewise_rate_flag(){
		return piecewise_rate_flag;
	}
	public void setPiecewise_rate_flag(int piecewise_rate_flag){
		this.piecewise_rate_flag = piecewise_rate_flag;
		if(this.piecewise_rate_flag != 1){
			piecewise_rate = 0;
		}
	}

	private int seamless_splice_flag;
	public int getSeamless_splice_flag(){
		return seamless_splice_flag;
	}
	public void setSeamless_splice_flag(int seamless_splice_flag){
		this.seamless_splice_flag = seamless_splice_flag;
		if(this.seamless_splice_flag != 1){
			splice_type = 0;
			DTS_next_AU = 0;
			marker_bit = 0;
			DTS_next_AU2 = 0;
			marker_bit2 = 0;
			DTS_next_AU3 = 0;
			marker_bit3 = 0;
		}
	}

	private int ltw_valid_flag;
	public int getLtw_valid_flag(){
		return ltw_valid_flag;
	}
	public void setLtw_valid_flag(int ltw_valid_flag){
		this.ltw_valid_flag = ltw_valid_flag;
	}

	private int ltw_offset;
	public int getLtw_offset(){
		return ltw_offset;
	}
	public void setLtw_offset(int ltw_offset){
		this.ltw_offset = ltw_offset;
	}

	private int piecewise_rate;
	public int getPiecewise_rate(){
		return piecewise_rate;
	}
	public void setPiecewise_rate(int piecewise_rate){
		this.piecewise_rate = piecewise_rate;
	}

	private int splice_type;
	public int getSplice_type(){
		return splice_type;
	}
	public void setSplice_type(int splice_type){
		this.splice_type = splice_type;
	}

	private int DTS_next_AU;
	public int getDTS_next_AU(){
		return DTS_next_AU;
	}
	public void setDTS_next_AU(int DTS_next_AU){
		this.DTS_next_AU = DTS_next_AU;
	}

	private int marker_bit;
	public int getMarker_bit(){
		return marker_bit;
	}
	public void setMarker_bit(int marker_bit){
		this.marker_bit = marker_bit;
	}

	private int DTS_next_AU2;
	public int getDTS_next_AU2(){
		return DTS_next_AU2;
	}
	public void setDTS_next_AU2(int DTS_next_AU2){
		this.DTS_next_AU2 = DTS_next_AU2;
	}

	private int marker_bit2;
	public int getMarker_bit2(){
		return marker_bit2;
	}
	public void setMarker_bit2(int marker_bit2){
		this.marker_bit2 = marker_bit2;
	}
	private int DTS_next_AU3;
	public int getDTS_next_AU3(){
		return DTS_next_AU3;
	}
	public void setDTS_next_AU3(int DTS_next_AU3){
		this.DTS_next_AU3 = DTS_next_AU3;
	}

	private int marker_bit3;
	public int getMarker_bit3(){
		return marker_bit3;
	}
	public void setMarker_bit3(int marker_bit3){
		this.marker_bit3 = marker_bit3;
	}
	private ArrayList<Integer> reserved5 = new ArrayList<Integer>();
	public ArrayList<Integer> getReserved5(){
		return reserved5;
	}
	public void setReserved5(int reserved5){
		int bytesRestantes = adaptation_field_extension_length - 1;
		if(ltw_flag == 1){
			bytesRestantes -= 2;
		}
		if(piecewise_rate_flag == 1){
			bytesRestantes -= 3;
		}
		if(seamless_splice_flag == 1){
			bytesRestantes -= 5;
		}
		bytesRestantes -= this.reserved5.size();
		if(bytesRestantes < 1){
			//garante que haja pelo menos 1 elemento. Do contrário, não adiciona
			if(this.reserved5.size() == 0){ 
				this.reserved5.add(0);
			}
		}else{
			this.reserved5.add(reserved5);
		}
	}

	public String toString(){
		ByteReader br = new ByteReader();
		int aux = 0;
		String s = "";
		s += "Adaptation_field_length: "+br.intBinaryString(adaptation_field_length,24)+"\n";
		s += "Discontinuity_indicator: "+br.intBinaryString(discontinuity_indicator,31)+"\n";
		s += "Random_access_indicator: "+br.intBinaryString(random_access_indicator,31)+"\n";
		s += "Elementary_stream_priority_indicator: "+br.intBinaryString(elementary_stream_priority_indicator,31)+"\n";
		s += "PCR_flag: "+br.intBinaryString(PCR_flag,31)+"\n";
		s += "OPCR_flag: "+br.intBinaryString(OPCR_flag,31)+"\n";
		s += "Splicing_point_flag: "+br.intBinaryString(splicing_point_flag,31)+"\n";
		s += "Transport_private_data_flag: "+br.intBinaryString(transport_private_data_flag,31)+"\n";
		s += "Adaptation_field_extension_flag: "+br.intBinaryString(adaptation_field_extension_flag,31)+"\n";
		s += "Program_clock_reference_base: "+program_clock_reference_base+"\n";
		s += "Program_clock_reference_extension: "+program_clock_reference_extension+"\n";
		s += "Original_program_clock_reference_base: "+original_program_clock_reference_base+"\n";
		s += "Original_program_clock_reference_extension: "+original_program_clock_reference_extension+"\n";
		s += "Splice_countdown: "+br.byteBinaryString(splice_countdown)+"\n";
		s += "Transport_private_data_length: "+br.intBinaryString(transport_private_data_length,24)+"\n";
		for(Integer i : private_data_byte){
			s += "Private_data_byte: "+br.intBinaryString(i,24)+"\n";
		}
		s += "Adaptation_field_extension_length: "+br.intBinaryString(adaptation_field_extension_length,24)+"\n";
		s += "Ltw_flag: "+br.intBinaryString(ltw_flag,31)+"\n";
		s += "Piecewise_rate_flag: "+br.intBinaryString(piecewise_rate_flag,31)+"\n";
		s += "Seamless_splice_flag: "+br.intBinaryString(seamless_splice_flag,31)+"\n";
		s += "Ltw_valid_flag: "+br.intBinaryString(ltw_valid_flag,31)+"\n";
		s += "Ltw_offset: "+br.intBinaryString(ltw_offset,17)+"\n";
		s += "Piecewise_rate: "+br.intBinaryString(piecewise_rate,10)+"\n";
		s += "Splice_type: "+br.intBinaryString(splice_type,28)+"\n";
		aux = br.intZerarBits(DTS_next_AU, 29, 32);
		aux = aux << 15;
		aux = aux | br.intZerarBits(DTS_next_AU2, 17, 32);
		aux = aux << 15;
		aux = aux | br.intZerarBits(DTS_next_AU3, 17, 32);
		s += "DTS_next_AU: "+br.intBinaryString(aux)+"\n";
		s += "Number of reserved bytes at the end of Adaptation Field Extension: "+reserved5.size()+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof AdaptationField) {
	        AdaptationField other = (AdaptationField) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getAdaptation_field_length(), other.getAdaptation_field_length());
	        builder.append(getDiscontinuity_indicator(), other.getDiscontinuity_indicator());
	        builder.append(getRandom_access_indicator(), other.getRandom_access_indicator());
	        builder.append(getElementary_stream_priority_indicator(), other.getElementary_stream_priority_indicator());
	        builder.append(getPCR_flag(), other.getPCR_flag());
	        builder.append(getOPCR_flag(), other.getOPCR_flag());
	        builder.append(getSplicing_point_flag(), other.getSplicing_point_flag());
	        builder.append(getTransport_private_data_flag(), other.getTransport_private_data_flag());
	        builder.append(getAdaptation_field_extension_flag(), other.getAdaptation_field_extension_flag());
	        builder.append(getProgram_clock_reference_base(), other.getProgram_clock_reference_base());
	        builder.append(getProgram_clock_reference_extension(), other.getProgram_clock_reference_extension());
	        builder.append(getOriginal_program_clock_reference_base(), other.getOriginal_program_clock_reference_base());
	        builder.append(getOriginal_program_clock_reference_extension(), other.getOriginal_program_clock_reference_extension());
	        builder.append(getSplice_countdown(), other.getSplice_countdown());
	        builder.append(getTransport_private_data_length(), other.getTransport_private_data_length());
	        builder.append(getPrivate_data_byte(), other.getPrivate_data_byte());
	        builder.append(getAdaptation_field_extension_length(), other.getAdaptation_field_extension_length());
	        builder.append(getLtw_flag(), other.getLtw_flag());
	        builder.append(getPiecewise_rate_flag(), other.getPiecewise_rate_flag());
	        builder.append(getSeamless_splice_flag(), other.getSeamless_splice_flag());
	        builder.append(getLtw_valid_flag(), other.getLtw_valid_flag());
	        builder.append(getLtw_offset(), other.getLtw_offset());
	        builder.append(getPiecewise_rate(), other.getPiecewise_rate());
	        builder.append(getSplice_type(), other.getSplice_type());
	        builder.append(getDTS_next_AU(), other.getDTS_next_AU());
	        builder.append(getDTS_next_AU2(), other.getDTS_next_AU2());
	        builder.append(getDTS_next_AU3(), other.getDTS_next_AU3());
	        builder.append(reserved5.size(), other.reserved5.size());
    		return builder.isEquals();
        }
        return false;
    }
}
