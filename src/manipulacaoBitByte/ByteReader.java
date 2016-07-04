//LEMBRETE:
// ESTA É UMA VERSÃO >>POSTERIOR<< A SELEÇÃO DO LAVID!!

package manipulacaoBitByte;

import java.io.*;

public class ByteReader {
	//lê o inteiro e retorna o inteiro apagando n bits
	public int zerarBits(int entrada, int n){
		//int tamanhoInt = 32; //tamanho do int em bits
		//int n = tamanhoInt - bitsDeRetorno;
		entrada = entrada << n;
		entrada = entrada >>> n;
		if(n == 32){
			entrada = 0;
		}
		return entrada;
	}
	//retorna um int, que vai conter em seus últimos qtdBytes bytes, os bytes lidos
	// devido a int ter 32 bits, para valores acima de 4, apenas os últimos quatro bytes retornarão
	public int lerBytes(FileInputStream fi, int qtdBytes){
		int x = 0;
		int aux;
		for(int i = 0; i < qtdBytes; i++){
			try {
				aux = fi.read();
				x = shiftAndAddByte(x, aux);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return x;
	}
	public int extrairBit(int i, int ordem){
		i = i >>> (ordem - 1);
		i = zerarBits(i, 31);
		return i;
	}
	public byte byteExtrairBit(int b, int ordem){
		b = b >>> (ordem - 1);
		b = zerarBits(b, 31);
		return (byte) b;
	}
	public byte shiftAndAddBit(int b1, int b2){
		b1 = zerarBits(b1, 33);
		b2 = zerarBits(b2, 33);
		byte destino = 0;
		destino = (byte) (destino | b1);
		destino = (byte) (destino << 1);
		destino = (byte) (destino | b2);
		return destino;
	}
	public int shiftAndAddByte(int destino, int b){
		b = zerarBits(b, 24);
		destino = destino << 8;
		destino = destino | b;
		return destino;
	}
	//Meio inútil
	public int concatBytes(int b1, int b2){
		b1 = zerarBits(b1, 24);
		b2 = zerarBits(b2, 24);
		int destino = 0;
		destino = destino | b1;
		destino = destino << 8;
		destino = destino | b2;
		return destino;
	}
	//MÉTODOS DE STRING
	/*STRING PARA TIPO BYTE*/
	public String byteBinaryStringRaw(int b){
		b = zerarBits(b, 24);
		String x = String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
		return x;
	}
	public String byteBinaryStringRaw(int b, int n){
		b = zerarBits(b, 24);
		String x = String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
		x = x.substring(n);
		return x;
	}
	public String byteBinaryString(int b){
		b = zerarBits(b, 24);
		String x = byteBinaryStringRaw(b);
		String separadorLimite = "*";
		String separadorNibble = ".";

		x = x.substring(0, 4) + separadorNibble + x.substring(4, x.length());
		x = separadorLimite + x + separadorLimite;
		return x;
	}
	//exibe apenas o número desejado de bits
	public String byteBinaryString(int b, int n){
		b = zerarBits(b, 24);
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
	/*STRING PARA TIPO INT*/
	public String binaryStringRaw(int i){
		String x = String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0');
		return x;
	}
	public String binaryStringRaw(int i, int n){
		String x = String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0');
		x = x.substring(n);
		return x;
	}
	public String binaryString(int i){
		String x = binaryStringRaw(i);
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
	public String binaryString(int i, int n){
		String x = binaryString(i);
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
		}else if(n <= 32 && n >= 28){
			x = x.substring(n+7);
		}
		x = separadorLimiteInicial + x;
		return x;
	}
}
