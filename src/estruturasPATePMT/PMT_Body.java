package estruturasPATePMT;

import java.io.*;
import java.util.ArrayList;
import manipulacaoBitByte.ByteReader;
import descriptorsPMT.Descriptor;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class PMT_Body {
	ByteReader br = new ByteReader();
	private static PMT_SecaoN1 tempPMTSecaoN1;
	private static Descriptor tempDescriptor;
	public PMT_Body read(FileInputStream fi){
		try{
			setTable_id(fi.read());
			int xbyte1 = fi.read();
			int xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = br.intExtrairBit(xbyte1,16);
			setSection_syntax_indicator(xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 20, 32);
			setSection_length(xbyte1);
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			setProgram_number(xbyte1);
			xbyte1 = fi.read();
			xbyte2 = br.intExtrairBit(xbyte1,1);
			setCurrent_next_indicator(xbyte2);
			xbyte1 = xbyte1 >> 1;
			xbyte1 = br.intZerarBits(xbyte1, 27, 32);
			setVersion_number(xbyte1);
			setSection_number(fi.read());
			setLast_section_number(fi.read());
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			setPCR_PID(xbyte1);
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 20, 32);
			setProgram_info_length(xbyte1);
			//tamanho da seção n1 é section - 9 (das outras variáveis) - program_info_length - 4(do CRC)
			int tamanhoSecaoN1 = section_length - 9 - program_info_length - 4;
			//desN
			for(int i = 0; i < program_info_length;){
				tempDescriptor = new Descriptor();
				setDescriptorsN(tempDescriptor.read(fi));
				i += 1 + tempDescriptor.getDescriptor_length();
			}
			//desN1
			for(int i = 0; i < tamanhoSecaoN1;){
				tempPMTSecaoN1 = new PMT_SecaoN1();
				setSecao_descriptorsN1(tempPMTSecaoN1.read(fi));
				i += 5 + tempPMTSecaoN1.getES_info_length();
			}
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			setCRC_32(xbyte1);
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	//tem que ser 0x02
	private int table_id;
	public int getTable_id(){
		return table_id;
	}
	public void setTable_id(int table_id){
		this.table_id = table_id;
	}
	//tem que ser 1
	private int section_syntax_indicator;
	public int getSection_syntax_indicator(){
		return section_syntax_indicator;
	}
	public void setSection_syntax_indicator(int section_syntax_indicator){
		this.section_syntax_indicator = section_syntax_indicator;
	}
	//12 bits, considerar apenas os últimos 10
	//valor <= 0x3FD
	//número de bytes até o fim do PMT (inclui o CRC)
	private int section_length;
	public int getSection_length(){
		return section_length;
	}
	public void setSection_length(int section_length){
		this.section_length = section_length;
	}
	private int program_number;
	public int getProgram_number(){
		return program_number;
	}
	public void setProgram_number(int program_number){
		this.program_number = program_number;
	}
	private int version_number;
	public int getVersion_number(){
		return version_number;
	}
	public void setVersion_number(int version_number){
		this.version_number = version_number;
	}
	private int current_next_indicator;
	public int getCurrent_next_indicator(){
		return current_next_indicator;
	}
	public void setCurrent_next_indicator(int current_next_indicator){
		this.current_next_indicator = current_next_indicator;
	}
	// tem que ser 0x00
	private int section_number;
	public int getSection_number(){
		return section_number;
	}
	public void setSection_number(int section_number){
		this.section_number = section_number;
	}
	//tem que ser 0x00
	private int last_section_number;
	public int getLast_section_number(){
		return last_section_number;
	}
	public void setLast_section_number(int last_section_number){
		this.last_section_number = last_section_number;
	}
	private int PCR_PID;
	public int getPCR_PID(){
		return PCR_PID;
	}
	public void setPCR_PID(int PCR_PID){
		this.PCR_PID = PCR_PID;
	}
	//número de descriptors em N
	private int program_info_length;
	public int getProgram_info_length(){
		return program_info_length;
	}
	public void setProgram_info_length(int program_info_length){
		this.program_info_length = program_info_length;
	}
	private ArrayList<Descriptor> descriptorsN = new ArrayList<Descriptor>();
	public ArrayList<Descriptor> getDescriptorsN(){
		return descriptorsN;
	}
	public void setDescriptorsN(Descriptor descriptorsN){
		this.descriptorsN.add(descriptorsN);
	}
	//size() deve ser [section_length - (13 - program_info_length)]
	private ArrayList<PMT_SecaoN1> secao_descriptorsN1 = new ArrayList<PMT_SecaoN1>();
	public ArrayList<PMT_SecaoN1> getSecao_descriptorsN1(){
		return secao_descriptorsN1;
	}
	public void setSecao_descriptorsN1(PMT_SecaoN1 secao_descriptorsN1){
		this.secao_descriptorsN1.add(secao_descriptorsN1);
	}

	private int CRC_32;
	public int getCRC_32(){
		return CRC_32;
	}
	public void setCRC_32(int CRC_32){
		this.CRC_32 = CRC_32;
	}
	public String toString(){
		String s = "";
		s += "Table_id: "+br.intBinaryString(table_id,24)+" = "+table_id+"\n";
		s += "Section_syntax_indicator: "+br.intBinaryString(section_syntax_indicator,31)+"\n";
		s += "Section_length: "+br.intBinaryString(section_length,20)+" = "+section_length+"\n";
		s += "Program_number: "+br.intBinaryString(program_number,16)+"\n";
		s += "Version_number: "+br.intBinaryString(version_number,27)+"\n";
		s += "Current_next_indicator: "+br.intBinaryString(current_next_indicator,31)+"\n";
		s += "Section_number: "+br.intBinaryString(section_number,24)+"\n";
		s += "Last_section_number: "+br.intBinaryString(last_section_number,24)+"\n";
		s += "PCR_PID: "+br.intBinaryString(PCR_PID,19)+" = "+PCR_PID+"\n";
		s += "Program_info_length: "+br.intBinaryString(program_info_length,20)+" = "+program_info_length+"\n";
		s += "\nPROGRAM INFO\n\n";
		for(Descriptor d : descriptorsN){
			s += d+"\n";
		}
		s += "Fim de Program info\n";
		s += "EXTENSÃO DE INFO\n";
		for(PMT_SecaoN1 n1 : secao_descriptorsN1){
			s += n1;
		}
		s += "CRC_32: "+br.intBinaryString(CRC_32)+" = "+String.format("%x", CRC_32)+"\n";
		s += "------------------------------------------------------------";
		return s;
	}

	public boolean equals(Object obj) {
	    if (obj instanceof PMT_Body) {
	        PMT_Body other = (PMT_Body) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getTable_id(), other.getTable_id());
	        builder.append(getSection_syntax_indicator(), other.getSection_syntax_indicator());
	        builder.append(getSection_length(), other.getSection_length());
	        builder.append(getProgram_number(), other.getProgram_number());
	        builder.append(getVersion_number(), other.getVersion_number());
	        builder.append(getCurrent_next_indicator(), other.getCurrent_next_indicator());
	        builder.append(getSection_number(), other.getSection_number());
	        builder.append(getLast_section_number(), other.getLast_section_number());
	        builder.append(getPCR_PID(), other.getPCR_PID());
	        builder.append(getProgram_info_length(), other.getProgram_info_length());
	        builder.append(getDescriptorsN(), other.getDescriptorsN());
	        builder.append(getSecao_descriptorsN1(), other.getSecao_descriptorsN1());
	        builder.append(getCRC_32(), other.getCRC_32());
    		return builder.isEquals();
        }
        return false;
    }
}
