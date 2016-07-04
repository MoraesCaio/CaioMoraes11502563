package estruturasPATePMT;

import java.io.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import manipulacaoBitByte.ByteReader;

public class PAT_SecaoN {
	ByteReader br = new ByteReader();
	public PAT_SecaoN read(FileInputStream fi){
		setProgram_number(br.lerBytes(fi, 2));
		if(program_number == 0){
			setNetwork_PID(br.zerarBits(br.lerBytes(fi, 2), 19));
		}else{
			setProgram_map_PID(br.zerarBits(br.lerBytes(fi, 2), 19));
		}
		return this;
	}
	private int program_number;
	public int getProgram_number(){
		return program_number;
	}
	public void setProgram_number(int program_number){
		this.program_number = program_number;
	}

	private int reserved;
	public int getReserved(){
		return reserved;
	}
	public void setReserved(int reserved){
		this.reserved = reserved;
	}

	private int network_PID;
	public int getNetwork_PID(){
		return network_PID;
	}
	public void setNetwork_PID(int network_PID){
		this.network_PID = network_PID;
	}

	private int program_map_PID;
	public int getProgram_map_PID(){
		return program_map_PID;
	}
	public void setProgram_map_PID(int program_map_PID){
		this.program_map_PID = program_map_PID;
	}
	
	public boolean equals(Object obj) {
	    if (obj instanceof PAT_SecaoN) {
	    	PAT_SecaoN other = (PAT_SecaoN) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getProgram_number(), other.getProgram_number());
	        builder.append(getNetwork_PID(), other.getNetwork_PID());
	        builder.append(getProgram_map_PID(), other.getProgram_map_PID());
	        return builder.isEquals();
	    }
	    return false;
	}
	
	public String toString(){
		String s = "";
		s += "\nProgram_number: "+br.binaryString(program_number,16)+"\n";
		s += "Network_PID: "+br.binaryString(network_PID,19)+" = "+network_PID+"\n";
		s += "Program_map_PID: "+br.binaryString(program_map_PID,19)+" = "+program_map_PID+"\n\n";
		return s;
	}

}
