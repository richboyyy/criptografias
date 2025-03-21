package aula_03;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CriptografiaDES {
	public static void main(String[] args) {
		// Declarações de Variáveis
		BufferedReader leitor = new BufferedReader(
								new InputStreamReader(
								System.in));
		
		String texto = "";
		String chave = "";
		String criptograma = "";
		
		// Processamento
		try {
			System.out.print("Digite o texto: ");
			texto = leitor.readLine();
			
			System.out.print("Digite a chave: ");
			chave = leitor.readLine();
			
			criptograma = encriptar(texto, chave);
			
			System.out.println(criptograma);
			
			System.out.println(decriptar(criptograma, chave));
			
		} catch(Exception erro) {
			System.out.println("FODEU GRANDE !" + erro);
		
		}
	}
	
	public static String encriptar(String texto, String chave)
														throws Exception {
		String criptograma = "";
		
		Cipher cifra = Cipher.getInstance("DESede");
		cifra.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(
											chave.getBytes("UTF-8"), "DESede"));
		
		byte[] vetor = cifra.doFinal(texto.getBytes("UTF-8"));
		return Base64 .getEncoder().encodeToString(vetor);
	}
	public static String decriptar(String texto, String chave)
			throws Exception {
		String criptograma = "";

		Cipher cifra = Cipher.getInstance("DESede");
		cifra.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(
				chave.getBytes("UTF-8"), "DESede"));

		byte[] vetor = cifra.doFinal(
									Base64.getDecoder().decode(criptograma));
		return new String(vetor, "UTF-8");
	}
}
