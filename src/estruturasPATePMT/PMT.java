package estruturasPATePMT;

import org.apache.commons.lang3.builder.EqualsBuilder;
public class PMT {
	public PMT(){}
	public PMT(TS_Header header, AdaptationField adaptation_field, PMT_Body body){
		this.header = header;
		this.adaptation_field = adaptation_field;
		this.body = body;
	}
	private TS_Header header;
	public TS_Header getHeader(){
		return header;
	}
	public void setHeader(TS_Header header){
		this.header = header;
	}

	private AdaptationField adaptation_field;
	public AdaptationField getAdaptation_field(){
		return adaptation_field;
	}
	public void setAdaptation_field(AdaptationField adaptation_field){
		this.adaptation_field = adaptation_field;
	}

	private PMT_Body body;
	public PMT_Body getBody(){
		return body;
	}
	public void setBody(PMT_Body body){
		this.body = body;
	}

	public String toString(){
		String s = "";
		s += "PMT Packet\n\n";
		s += "Header:\n" + header+"\n";
		s += "\nAdaptation Field:\n" + adaptation_field+"\n";
		s += "\nBody:\n" + body+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof PMT) {
	        PMT other = (PMT) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getHeader(), other.getHeader());
	        builder.append(getAdaptation_field(), other.getAdaptation_field());
	        builder.append(getBody(), other.getBody());
    		return builder.isEquals();
        }
        return false;
    }
}
