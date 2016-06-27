import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		boolean continua = true;
		do{
			continua = false;
			TS_Reader r = new TS_Reader();
			//(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue)
			String nomeArquivo = (String) JOptionPane.showInputDialog(null,"Insira o nome do arquivo \".ts\" a ser lido nesta pasta.\n"
															+"As informações sobre os pacotes PAT e PMT serão salvas em:\n"
															+"PastaAtual\\Resultados de leitura PAT e PMT\\nomeDoArquivo\\\n"
															+"Por favor, aguarde o término da leitura. Haverá um aviso."
															,"Leitura de pacotes PAT e PMT"
															,3 //plain message
															,null
															,null
															,"\'arquivo\', \'arquivo.\' ou \'arquivo.ts\'");
			try{
				r.extrairPATePMT(nomeArquivo);
				JOptionPane.showMessageDialog(null, "Leitura concluída com sucesso.");
			}catch(Exception e){
				if(nomeArquivo != null){
					JOptionPane.showMessageDialog(null,e.getMessage());
					continua = true;
				}
			}
		}while(continua);
	}
}