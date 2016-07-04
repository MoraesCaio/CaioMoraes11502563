import java.io.*;
import java.util.ArrayList;

import estruturasPATePMT.*;

public class TS_Reader {
	private File entrada;
	private File log;
	private File listaPAT;
	private File listaPMT;
	private File semRep_PAT;
	private File semRep_PMT;
	private FileInputStream fi;
	private BufferedWriter logWriter;
	private BufferedWriter listaPATWriter;
	private BufferedWriter listaPMTWriter;
	private BufferedWriter semRep_PATWriter;
	private BufferedWriter semRep_PMTWriter;
	//PAT e PMT sem repetições
	private ArrayList<PAT> semRep_listaPAT = new ArrayList<PAT>();
	private ArrayList<PMT> semRep_listaPMT = new ArrayList<PMT>();
	//auxiliares para evitar repetições (deixar sem iniciar!)
	private PAT previousPAT;
	private PMT previousPMT;
	//temporários de partes de um pacote TS
	private TS_Header tempHeader = new TS_Header();
	private AdaptationField tempAF = new AdaptationField();
	private PAT_Body tempPATBody = new PAT_Body();
	private PAT tempPAT = new PAT();
	private PMT tempPMT = new PMT();
	private PMT_Body tempPMTBody = new PMT_Body();
	//lista de PID's
	private static ArrayList<Integer> PMT_PID = new ArrayList<Integer>();
	//contadores
	private static int number_packetRead = 0;
	private static int number_PAT = 0;
	private static int number_PMT = 0;

	public String ajustaExtensao(String nomeEntrada) throws Exception{
		String extensao = "";
		int c = nomeEntrada.lastIndexOf('.');
		if (c > 0) {
			extensao = nomeEntrada.substring(c);
		}
		if(extensao.length() <= 1){
			if(extensao.length() == 0){
				return nomeEntrada += ".ts";
			}else if(extensao.length() == 1){
				return nomeEntrada += "ts";
			}
		}else if(extensao.equals(".ts")){
			return nomeEntrada;
		}else{
			throw new Exception("Extensão de arquivo inválida.");
		}
		return nomeEntrada;
	}

	public void openFile(String nomeEntrada) throws Exception{
		nomeEntrada = ajustaExtensao(nomeEntrada);
		entrada = new File(nomeEntrada);
		fi = new FileInputStream(entrada);
		//criação do diretório de saída
		File pasta = new File("Resultados de leitura de PAT e PMT\\"+nomeEntrada+"\\");
		pasta.mkdirs();
		log = new File(pasta,"log.txt");
		listaPAT = new File(pasta,"listaPAT.txt");
		listaPMT = new File(pasta,"listaPMT.txt");
		semRep_PAT = new File(pasta,"semRep_PAT.txt");
		semRep_PMT = new File(pasta,"semRep_PMT.txt");
		//Limpa os arquivos de saída, se existirem
		logWriter = new BufferedWriter(new FileWriter(log));
		logWriter.write("");
		logWriter.flush();
		listaPATWriter = new BufferedWriter(new FileWriter(listaPAT));
		listaPATWriter.write("");
		listaPATWriter.flush();
		semRep_PATWriter = new BufferedWriter(new FileWriter(semRep_PAT));
		semRep_PATWriter.write("");
		semRep_PATWriter.flush();
		listaPMTWriter = new BufferedWriter(new FileWriter(listaPMT));
		listaPMTWriter.write("");
		listaPMTWriter.flush();
		semRep_PMTWriter = new BufferedWriter(new FileWriter(semRep_PMT));
		semRep_PMTWriter.write("");
		semRep_PMTWriter.flush();
		//seta os buffers de saída
		logWriter = new BufferedWriter(new FileWriter(log,true));
		listaPATWriter = new BufferedWriter(new FileWriter(listaPAT,true));
		listaPMTWriter = new BufferedWriter(new FileWriter(listaPMT,true));
		semRep_PATWriter = new BufferedWriter(new FileWriter(semRep_PAT,true));
		semRep_PMTWriter = new BufferedWriter(new FileWriter(semRep_PMT,true));
	}
	public void readFile() throws IOException, NullPointerException{
			//É necessário nova instância para que o arrayList resete
			//	isso diminui a quantidade de texto escrita no arquivo.
			while(fi.available() > 0){
			//for(int i = 0; i < 50000; i++){
				tempHeader = new TS_Header();
				tempHeader.read(fi);
				if(tempHeader.getPID() == 0){
					number_PAT++;
					listaPATWriter.write("\n"+number_PAT+"\n");
					//leitura
					tempAF = new AdaptationField();
					tempAF = tempAF.read(fi);
					tempPATBody = new PAT_Body();
					tempPATBody = tempPATBody.read(fi);
					tempPAT = new PAT(tempHeader, tempAF, tempPATBody);
					//salvando a primeira iteração sem repetição
					if(previousPAT == null){
						previousPAT = tempPAT;
						semRep_listaPAT.add(previousPAT);
					}
					//atualização de PID dos PMT
					for(Integer PID : tempPAT.getBody().getListaPMT_PID()){
						if(!PMT_PID.contains(PID)){
							PMT_PID.add(PID);
						}
					}
					//escrita
					listaPATWriter.write(""+tempPAT);
					//salvando se for diferente
					if(!previousPAT.equals(tempPAT)){
						previousPAT = tempPAT;
						semRep_listaPAT.add(tempPAT);
					}
					//quantos bytes faltam: tamanhoTS -header-adapField-3-section
					pularBytes(188-(4+(1+tempAF.getAdaptation_field_length())+(3+tempPATBody.getSection_length())));
				}else if(PMT_PID.contains(tempHeader.getPID())){
					number_PMT++;
					listaPMTWriter.write("\n"+number_PMT+"\n");
					//leitura
					tempAF = new AdaptationField();
					tempAF = tempAF.read(fi);
					tempPMTBody = new PMT_Body();
					tempPMTBody = tempPMTBody.read(fi);
					tempPMT = new PMT(tempHeader, tempAF, tempPMTBody);
					//salvando a primeira iteração sem repetição
					if(previousPMT == null){
						previousPMT = tempPMT;
						semRep_listaPMT.add(tempPMT);
					}
					//escrita
					listaPMTWriter.write(""+tempPMT);
					//salvando se for diferente
					if(!previousPMT.equals(tempPMT)){
						previousPMT = tempPMT;
						semRep_listaPMT.add(tempPMT);
					}
					//quantos bytes faltam: tamanhoTS -header-adapField-3-section
					pularBytes(188-(4+(1+tempAF.getAdaptation_field_length())+(3+tempPMTBody.getSection_length())));
				}else{
					//Pacote que não é PAT, nem PMT
					pularBytes(184);
				}
				number_packetRead++;
				System.out.println("Pacotes lidos até o momento:\t"+number_packetRead);
			}
			logWriter.write("Arquivo lido:\t"+entrada
						+"\nTamanho em bytes:\t"+String.format("%,3d",number_packetRead*188)+" bytes"
						+"\nTotal de pacotes lidos:\t"+String.format("%,3d",number_packetRead)
						+"\nTotal de PAT encontrados:\t"+String.format("%,3d",number_PAT)
						+"\nTotal de PMT encontrados:\t"+String.format("%,3d",number_PMT)
						+"\nQuantidade de PID's diferentes para pacotes PMT neste arquivo:"+String.format("%,3d",PMT_PID.size())
						+"\nLista dos PID dos pacotes PMT:");
			for(Integer j : PMT_PID){
				logWriter.write("\n"+j);
			}
			fi.close();
			logWriter.close();
			listaPATWriter.close();
			listaPMTWriter.close();
			//salva as versões diferentes de PAT
			semRep_PATWriter.write("Abaixo consta a lista de todos os PAT que se diferenciam dos demais\n"
									+"desconsiderando os incrementos do Continuity_Counter.\n"
									+"Sendo assim, deve-se desconsiderar os valores desse counter neste arquivo.\n");
			semRep_PATWriter.write("Quantidade encontrada de pacotes PAT não repetidos:\t"+semRep_listaPAT.size()+"\n\n");
			for(PAT pat : semRep_listaPAT){
				semRep_PATWriter.write(""+pat);
			}
			semRep_PATWriter.close();
			//salva as versões diferentes de PMT
			semRep_PMTWriter.write("Abaixo consta a lista de todos os PMT que se diferenciam dos demais\n"
									+"desconsiderando os incrementos do Continuity_Counter.\n"
									+"Sendo assim, deve-se desconsiderar os valores desse counter neste arquivo.\n");
			semRep_PMTWriter.write("Quantidade encontrada de pacotes PMT não repetidos:\t"+semRep_listaPMT.size()+"\n\n");
			for(PMT pmt : semRep_listaPMT){
				semRep_PMTWriter.write(""+pmt);
			}
			semRep_PMTWriter.close();

	}
	public void extrairPATePMT(String nomeArquivo) throws Exception{
		try{
			openFile(nomeArquivo);
			readFile();
			closeFile();
		}catch(IOException e){
			throw new IOException("Arquivo não encontrado.");
		}catch(NullPointerException e1){
			throw new NullPointerException("Arquivo não encontrado.");
		}catch(Exception e2){
			throw new Exception(e2.getMessage());
		}
	}
	//método skip dá muito problema
	public void pularBytes(int n){
		for(int j = 0; j < n; j++){
			try {
				fi.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void closeFile() throws IOException{
			fi.close();
			logWriter.close();
			listaPATWriter.close();
			listaPMTWriter.close();
			semRep_PATWriter.close();
			semRep_PMTWriter.close();
	}
}
