package manipulacaoBitByte;

public class ByteReader {
	private int xbyte;
	public void lerByte(int b){
		xbyte = intZerarBits(b, 24, 32);;
	}
	public byte byteExtrairBit(int ordem){
		byte result = 0b00000000;
		if(ordem > 8 || ordem < 1){
			return (byte) 0b01111111; // retorna 127 se requisitado valor inadequado
		}else{
			byte operando = (byte) Math.pow(2, ordem-1); //valores esperados 1,2,4,8,16,32,64,128
			result = (byte) (operando & xbyte);
			result = (byte) (result >> (ordem-1));
			return result;
		}
	}
	//caso não queira armazenar
	public byte byteExtrairBit(int b, int ordem){
		b = intZerarBits(b, 24, 32);
		byte result = 0b00000000;
		if(ordem > 8 || ordem < 1){
			return (byte) 0b01111111; // retorna 127 se requisitado valor inadequado
		}else{
			byte operando = (byte) Math.pow(2, ordem-1); //valores esperados 1,2,4,8,16,32,64,128
			result = (byte) (operando & b);
			result = (byte) (result >> (ordem-1));
			return result;
		}
	}
	public byte concatBits(int b1, int b2){
		b1 = intZerarBits(b1, 24, 32);
		b2 = intZerarBits(b2, 24, 32);
		byte destino = 0;
		destino = (byte) (destino | b1);
		destino = (byte) (destino << 1);
		destino = (byte) (destino | b2);
		return destino;
	}
	public int concatBytes(int b1, int b2){
		b1 = intZerarBits(b1, 24, 32);
		b2 = intZerarBits(b2, 24, 32);
		int destino = 0;
		destino = destino | b1;
		destino = destino << 8;
		destino = destino | b2;
		return destino;
	}
	public int shiftAndAddByte(int destino, int b){
		b = intZerarBits(b, 24, 32);
		destino = destino << 8;
		destino = destino | b;
		return destino;
	}
	//MÉTODOS DE STRING
	public String byteBinaryStringRaw(int b){
		b = intZerarBits(b, 24, 32);
		String x = String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
		return x;
	}
	public String byteBinaryStringRaw(int b, int n){
		b = intZerarBits(b, 24, 32);
		String x = String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
		x = x.substring(n);
		return x;
	}
	public String byteBinaryString(int b){
		b = intZerarBits(b, 24, 32);
		String x = byteBinaryStringRaw(b);
		String separadorLimite = "*";
		String separadorNibble = ".";

		x = x.substring(0, 4) + separadorNibble + x.substring(4, x.length());
		x = separadorLimite + x + separadorLimite;
		return x;
	}
	//exibe apenas o número desejado de bits
	public String byteBinaryString(int b, int n){
		b = intZerarBits(b, 24, 32);
		String x = byteBinaryString(b);

		String separadorLimiteInicial = x.substring(0, 1);
		x = x.substring(1);

		if(n < 4){
			x = x.substring(n  );
		}else if(n <= 8 && n >= 4){
			x = x.substring(n+1);
		}
		x = separadorLimiteInicial + x;
		return x;
	}
	public String intBinaryStringRaw(int i){
		String x = String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0');
		return x;
	}
	public String intBinaryStringRaw(int i, int n){
		String x = String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0');
		x = x.substring(n);
		return x;
	}
	public String intBinaryString(int i){
		String x = intBinaryStringRaw(i);
		String separadorNibble = ".";
		String separadorByte = "/";
		String separadorLimite = "*";

		x = x.substring(0, 28) + separadorNibble + x.substring(28, x.length());
		x = x.substring(0, 24) + separadorByte   + x.substring(24, x.length());
		x = x.substring(0, 20) + separadorNibble + x.substring(20, x.length());
		x = x.substring(0, 16) + separadorByte   + x.substring(16, x.length());
		x = x.substring(0, 12) + separadorNibble + x.substring(12, x.length());
		x = x.substring(0,  8) + separadorByte   + x.substring( 8, x.length());
		x = x.substring(0,  4) + separadorNibble + x.substring( 4, x.length());

		x = separadorLimite + x + separadorLimite;
		return x;
	}
	//exibe apenas o número desejado de bits
	public String intBinaryString(int i, int n){
		String x = intBinaryString(i);

		String separadorLimiteInicial = x.substring(0, 1);
		x = x.substring(1);

		if      (n <  4){
			x = x.substring(n  );
		}else if(n <  8 && n >=  4){
			x = x.substring(n+1);
		}else if(n < 12 && n >=  8){
			x = x.substring(n+2);
		}else if(n < 16 && n >= 12){
			x = x.substring(n+3);
		}else if(n < 20 && n >= 16){
			x = x.substring(n+4);
		}else if(n < 24 && n >= 20){
			x = x.substring(n+5);
		}else if(n < 28 && n >= 24){
			x = x.substring(n+6);
		}else if(n < 32 && n >= 28){
			x = x.substring(n+7);
		}
		x = separadorLimiteInicial + x;
		return x;
	}

	private int xint;
	public void lerInt(int y){
		xint = y;
	}
	public byte intExtrairBit(int ordem){
		byte result = 0b00000000;
		if(ordem > 32 || ordem < 1){
			return (byte) 0b01111111; // retorna 127 se requisitado valor inadequado
		}else{
			int operando = (int) Math.pow(2, ordem-1); //valores esperados 1,2,4,8,16,32,64,128
			int temp = operando & xint;
			result = (byte) (temp >> (ordem-1));
			return result;
		}
	}
	public byte intExtrairBit(int i, int ordem){
		byte result = 0b00000000;
		if(ordem > 32 || ordem < 1){
			return (byte) 0b01111111; // retorna 127 se requisitado valor inadequado
		}else{
			int operando = (int) Math.pow(2, ordem-1); //valores esperados 1,2,4,8,16,32,64,128
			int temp = operando & i;
			result = (byte) (temp >> (ordem-1));
			return result;
		}
	}
	public int intZerarBits(int inteiro, int n, int leftOffSet){
		int operando = 0;
		int result = 0;
		int flag32 = 0; //serve para ajustes de complemento de 2
		int operando2 = 0;

		//inverter o 32º bit não funciona da mesma maneira, tive que usar "<< 1"
		if(leftOffSet == 32){
			flag32 = 1;
			leftOffSet = 31;
			n--;
		}

		int aux = (int) Math.pow(2, leftOffSet-1);
		// vai adicionando 1's a esquerda do operando  0b0000 > 0b1000 > 0b1100
		for(int i = 0; i < n; i++){
			operando = operando >> 1;
			operando = operando | aux;
		}
		operando = ~operando; // 0b 0011 > 1100
		operando2 = inteiro;
		
		result = operando2 & operando;

		// seta o zero no 32 e inverte por causa do complemento de 2
		if(flag32==1){
			result=result << 1;
			result=result >> 1;
		}
		return result;
	}
}
