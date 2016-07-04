package estruturasPATePMT;

import java.io.*;
import java.util.ArrayList;
import manipulacaoBitByte.ByteReader;
import descriptorsPMT.Descriptor;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class PMT_SecaoN1 {
	private Descriptor tempDescriptor;
	ByteReader br = new ByteReader();
	public PMT_SecaoN1 read(FileInputStream fi){
		int xbyte1;
		setStream_type(br.lerBytes(fi, 1));
		xbyte1 = br.zerarBits(br.lerBytes(fi, 2), 19);
		setElementary_PID(xbyte1);
		xbyte1 = br.zerarBits(br.lerBytes(fi, 2), 20);
		setES_info_length(xbyte1);
		for(int i = 0; i < ES_info_length;){
			tempDescriptor = new Descriptor();
			setListaDescriptor(tempDescriptor.read(fi));
			i += 2 + tempDescriptor.getDescriptor_length();
		}
		return this;
	}
	private int stream_type;
	public int getStream_type(){
		return stream_type;
	}
	public void setStream_type(int stream_type){
		this.stream_type = stream_type;
	}
	private int elementary_PID;
	public int getElementary_PID(){
		return elementary_PID;
	}
	public void setElementary_PID(int elementary_PID){
		this.elementary_PID = elementary_PID;
	}
	private int ES_info_length;
	public int getES_info_length(){
		return ES_info_length;
	}
	public void setES_info_length(int ES_info_length){
		this.ES_info_length = ES_info_length;
	}
	private ArrayList<Descriptor> listaDescriptor = new ArrayList<Descriptor>();
	public ArrayList<Descriptor> getListaDescriptor(){
		return listaDescriptor;
	}
	public void setListaDescriptor(Descriptor listaDescriptor){
		this.listaDescriptor.add(listaDescriptor);
	}
	public String toString(){
		String s = "\n";
		s += "Stream_type: "+br.binaryString(stream_type,24)+"\n";
		s += "Elementary_PID: "+br.binaryString(elementary_PID,19)+"\n";
		s += "ES_info_length: "+br.binaryString(ES_info_length,20)+"\n";
		s += "Lista de Descriptors:\n\n";
		for(Descriptor d : listaDescriptor){
			s += d+"\n";
		}
		return s+"Fim da lista de Descriptors.\n";
	}
	public boolean equals(Object obj) {
	    if (obj instanceof PMT_SecaoN1) {
	        PMT_SecaoN1 other = (PMT_SecaoN1) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getStream_type(), other.getStream_type());
	        builder.append(getElementary_PID(), other.getElementary_PID());
	        builder.append(getES_info_length(), other.getES_info_length());
	        builder.append(getListaDescriptor(), other.getListaDescriptor());
    		return builder.isEquals();
        }
        return false;
    }
}
