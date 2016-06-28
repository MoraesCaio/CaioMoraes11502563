package estruturasPATePMT;

import org.apache.commons.lang3.builder.EqualsBuilder;
public class PAT {
	public PAT(){}
	public PAT(TS_Header header, AdaptationField adaptation_field, PAT_Body body){
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

	private PAT_Body body;
	public PAT_Body getBody(){
		return body;
	}
	public void setBody(PAT_Body body){
		this.body = body;
	}
	
	public String toString(){
		String s = "";
		s += "PAT Packet\n\n";
		s += "HEADER\n" + header+"\n";
		s += "\nADAPTATION FIELD\n" + adaptation_field+"\n";
		s += "\nBODY\n" + body+"\n";
		return s;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof PAT) {
	        PAT other = (PAT) obj;
	        EqualsBuilder builder = new EqualsBuilder();
	        builder.append(getHeader(), other.getHeader());
	        builder.append(getAdaptation_field(), other.getAdaptation_field());
	        builder.append(getBody(), other.getBody());
    		return builder.isEquals();
        }
        return false;
    }
}
