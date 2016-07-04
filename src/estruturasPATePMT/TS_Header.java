package estruturasPATePMT;

import java.io.*;
import manipulacaoBitByte.ByteReader;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class TS_Header {
	ByteReader br = new ByteReader();
	public TS_Header read(FileInputStream fi){
		int xbyte1 = 0;
		int xbyte2 = 0;
		int xbyte3 = 0;
		setSync_byte(br.lerBytes(fi, 1));
		xbyte1 = br.lerBytes(fi, 1);
		setTransport_error_indicator(br.extrairBit(xbyte1,8));
		setPayload_unit_start_indicator(br.extrairBit(xbyte1,7));
		setTransport_priority(br.extrairBit(xbyte1,6));
		//manipulação de PID
		xbyte1 = br.zerarBits(xbyte1, 27);//retira os indicadores
		xbyte1 = br.shiftAndAddByte(xbyte1, br.lerBytes(fi, 1));
		setPID(xbyte1);
		//4º byte do Header
		xbyte1 = br.lerBytes(fi, 1);
		//manipulação de Scrambling Control
		xbyte2 = br.extrairBit(xbyte1,8);
		xbyte3 = br.extrairBit(xbyte1,7);
		xbyte2 = br.shiftAndAddBit(xbyte2, xbyte3);
		setTransport_scrambling_control(xbyte2);
		//manipulação de Adaptation Field Control
		xbyte2 = br.extrairBit(xbyte1,6);
		xbyte3 = br.extrairBit(xbyte1,5);
		xbyte2 = br.shiftAndAddBit(xbyte2, xbyte3);
		setAdaptation_field_control(xbyte2);
		//manipulação de Continuity counter
		setContinuity_counter(br.zerarBits(xbyte1, 28)); //zera o Scontrol e o AFcontrol
		return this;
	}
	private int sync_byte;
	public int getSync_byte(){
		return sync_byte;
	}
	public void setSync_byte(int sync_byte){
		this.sync_byte = sync_byte;
	}

	private int transport_error_indicator;
	public int getTransport_error_indicator(){
		return transport_error_indicator;
	}
	public void setTransport_error_indicator(int transport_error_indicator){
		this.transport_error_indicator = transport_error_indicator;
	}

	private int payload_unit_start_indicator;
	public int getPayload_unit_start_indicator(){
		return payload_unit_start_indicator;
	}
	public void setPayload_unit_start_indicator(int payload_unit_start_indicator){
		this.payload_unit_start_indicator = payload_unit_start_indicator;
	}

	private int transport_priority;
	public int getTransport_priority(){
		return transport_priority;
	}
	public void setTransport_priority(int transport_priority){
		this.transport_priority = transport_priority;
	}

	private int PID;
	public int getPID(){
		return PID;
	}
	public void setPID(int PID){
		this.PID = PID;
	}

	private int transport_scrambling_control;
	public int getTransport_scrambling_control(){
		return transport_scrambling_control;
	}
	public void setTransport_scrambling_control(int transport_scrambling_control){
		this.transport_scrambling_control = transport_scrambling_control;
	}

	private int adaptation_field_control;
	public int getAdaptation_field_control(){
		return adaptation_field_control;
	}
	public void setAdaptation_field_control(int adaptation_field_control){
		this.adaptation_field_control = adaptation_field_control;
	}

	private int continuity_counter;
	public int getContinuity_counter(){
		return continuity_counter;
	}
	public void setContinuity_counter(int continuity_counter){
		this.continuity_counter = continuity_counter;
	}

	public TS_Header(){}
	public TS_Header(int sync_byte,int transport_error_indicator,int payload_unit_start_indicator,int transport_priority,int PID,int transport_scrambling_control,int adaptation_field_control,int continuity_counter){
		this.sync_byte = sync_byte;
		this.transport_error_indicator = transport_error_indicator;
		this.payload_unit_start_indicator = payload_unit_start_indicator;
		this.transport_priority = transport_priority;
		this.PID = PID;
		this.transport_scrambling_control = transport_scrambling_control;
		this.adaptation_field_control = adaptation_field_control;
		this.continuity_counter	= continuity_counter;
	}

	public String toString(){
		String s = "";
		//s += "\nHeader:";
		s += "sync_byte: " + br.binaryString(sync_byte,24)+" = "+sync_byte;
		s += "\ntransport_error_indicator: " + br.binaryString(transport_error_indicator,31);
		s += "\npayload_unit_start_indicator: " + br.binaryString(payload_unit_start_indicator,31);
		s += "\ntransport_priority: " + br.binaryString(transport_priority,31);
		s += "\nPID: " + br.binaryString(PID,19)+" = "+PID;
		s += "\ntransport_scrambling_control: " + br.binaryString(transport_scrambling_control,30);
		s += "\nadaptation_field_control: " + br.binaryString(adaptation_field_control,30);
		s += "\ncontinuity_counter: " + br.binaryString(continuity_counter,28);
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof TS_Header) {
	        TS_Header other = (TS_Header) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getSync_byte(), other.getSync_byte());
	        builder.append(getTransport_error_indicator(), other.getTransport_error_indicator());
	        builder.append(getPayload_unit_start_indicator(), other.getPayload_unit_start_indicator());
	        builder.append(getTransport_priority(), other.getTransport_priority());
	        builder.append(getPID(), other.getPID());
	        builder.append(getTransport_scrambling_control(), other.getTransport_scrambling_control());
	        builder.append(getAdaptation_field_control(), other.getAdaptation_field_control());
    		return builder.isEquals();
        }
        return false;
    }
}
