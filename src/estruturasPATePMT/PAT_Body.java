package estruturasPATePMT;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import manipulacaoBitByte.ByteReader;

public class PAT_Body {
	ByteReader br = new ByteReader();
	private static PAT_SecaoN currentTempN;// = new PAT_SecaoN();
	private static PAT_SecaoN previousTempN;// = new PAT_SecaoN();
	public PAT_Body read(FileInputStream fi){
		try{
			int xbyte1 = fi.read();
			int xbyte2;
			setTable_id(xbyte1);
			xbyte1 = fi.read();
			setSection_syntax_indicator(br.intExtrairBit(xbyte1, 8));
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte1 = br.intZerarBits(xbyte1, 20, 32);
			setSection_length(xbyte1);
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			setTransport_stream_id(xbyte1);
			xbyte1 = fi.read();
			setCurrent_next_indicator(br.intExtrairBit(xbyte1, 1));
			xbyte1 = xbyte1 >> 1;
			xbyte1 = br.intZerarBits(xbyte1, 27, 32);
			setVersion_number(xbyte1);
			setSection_number(fi.read());
			setLast_section_number(fi.read());
			for(int i = 0; i < (section_length-12); i += 4){
				if(i == 0){
					previousTempN = new PAT_SecaoN();
					previousTempN.read(fi);
					setElementosSecaoN(previousTempN);
				}else{
					currentTempN = new PAT_SecaoN();
					currentTempN.read(fi);
					if(!previousTempN.equals(currentTempN)){
						previousTempN = currentTempN;
						setElementosSecaoN(currentTempN);
					}
				}
			}
			for(PAT_SecaoN n : elementosSecaoN){
				if(n.getProgram_number() == 0){
					listaNetwork_PID.add(n.getNetwork_PID());
				}else{
					listaPMT_PID.add(n.getProgram_map_PID());
				}
			}
			xbyte1 = fi.read();
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			xbyte2 = fi.read();
			xbyte1 = br.shiftAndAddByte(xbyte1, xbyte2);
			setSection_CRC(xbyte1);
		}catch(IOException e){
			e.printStackTrace();
		}
		return this;
	}
	private ArrayList<Integer> listaPMT_PID = new ArrayList<Integer>();
	public ArrayList<Integer> getListaPMT_PID(){
		return listaPMT_PID;
	}
	public void setListaPMT_PID(int PMT_PID){
		this.listaPMT_PID.add(PMT_PID);
	}

	private ArrayList<Integer> listaNetwork_PID = new ArrayList<Integer>();
	public ArrayList<Integer> getListaNetwork_PID(){
		return listaNetwork_PID;
	}
	public void setListaNetwork_PID(int network_PID){
		listaNetwork_PID.add(network_PID);
	}

	private ArrayList<PAT_SecaoN> elementosSecaoN = new ArrayList<PAT_SecaoN>();
	public ArrayList<PAT_SecaoN> getElementosSecaoN(){
		return elementosSecaoN;
	}
	public void setElementosSecaoN(PAT_SecaoN elementosSecaoN){
		this.elementosSecaoN.add(elementosSecaoN);
	}

	private int table_id;
	public int getTable_id(){
		return table_id;
	}
	public void setTable_id(int table_id){
		this.table_id = table_id;
	}

	private int section_syntax_indicator;
	public int getSection_syntax_indicator(){
		return section_syntax_indicator;
	}
	public void setSection_syntax_indicator(int section_syntax_indicator){
		this.section_syntax_indicator = section_syntax_indicator;
	}

	private int zero;
	public int getZero(){
		return zero;
	}
	public void setZero(int zero){
		this.zero = zero;
	}

	private int reserved1;
	public int getReserved1(){
		return reserved1;
	}
	public void setReserved1(int reserved1){
		this.reserved1 = reserved1;
	}

	private int section_length;
	public int getSection_length(){
		return section_length;
	}
	public void setSection_length(int section_length){
		if(section_length <= 0x3fd){
			this.section_length = section_length;
		}else{
			this.section_length = -1;
		}
	}

	private int transport_stream_id;
	public int getTransport_stream_id(){
		return transport_stream_id;
	}
	public void setTransport_stream_id(int transport_stream_id){
		this.transport_stream_id = transport_stream_id;
	}

	private int reserved2;
	public int getReserved2(){
		return reserved2;
	}
	public void setReserved2(int reserved2){
		this.reserved2 = reserved2;
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

	private int section_number;
	public int getSection_number(){
		return section_number;
	}
	public void setSection_number(int section_number){
		this.section_number = section_number;
	}

	private int last_section_number;
	public int getLast_section_number(){
		return last_section_number;
	}
	public void setLast_section_number(int last_section_number){
		this.last_section_number = last_section_number;
	}

	private int section_CRC;
	public int getSection_CRC(){
		return section_CRC;
	}
	public void setSection_CRC(int section_CRC){
		this.section_CRC = section_CRC;
	}

	public String toString(){
		String s = "";
		s += "Table_id: "+br.intBinaryString(table_id,24)+"\n";
		s += "Section_syntax_indicator: "+br.intBinaryString(section_syntax_indicator,31)+"\n";
		s += "Section_length: "+br.intBinaryString(section_length,20)+"\n";
		s += "Transport_stream_id: "+br.intBinaryString(transport_stream_id,16)+"\n";
		s += "Version_number: "+br.intBinaryString(version_number,27)+"\n";
		s += "Current_next_indicator: "+br.intBinaryString(current_next_indicator,31)+"\n";
		s += "Section_number: "+br.intBinaryString(section_number,24)+"\n";
		s += "Last_section_number: "+br.intBinaryString(last_section_number,24)+"\n";
		s += "PID\n";
		for(PAT_SecaoN n : elementosSecaoN){
			s += n;
		}
		s += "Section_CRC: "+br.intBinaryString(section_CRC)+" = "+	String.format("%x", section_CRC) + "\n";
		s += "-----------------------------------------------------------------";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof PAT_Body) {
	        PAT_Body other = (PAT_Body) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getTable_id(), other.getTable_id());
	        builder.append(getSection_syntax_indicator(), other.getSection_syntax_indicator());
	        builder.append(getSection_length(), other.getSection_length());
	        builder.append(getTransport_stream_id(), other.getTransport_stream_id());
	        builder.append(getVersion_number(), other.getVersion_number());
	        builder.append(getCurrent_next_indicator(), other.getCurrent_next_indicator());
	        builder.append(getSection_number(), other.getSection_number());
	        builder.append(getElementosSecaoN(), other.getElementosSecaoN());
	        builder.append(getSection_CRC(), other.getSection_CRC());
	        builder.append(getListaNetwork_PID(), other.getListaNetwork_PID());
	        builder.append(getListaPMT_PID(), other.getListaPMT_PID());
    		return builder.isEquals();
        }
        return false;
    }
}
