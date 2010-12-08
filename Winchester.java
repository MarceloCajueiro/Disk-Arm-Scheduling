import java.util.*;

public class Winchester {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int count = 0;
		boolean existe = false;
		
		System.out.println("Monte o seu HD");
		System.out.println("");
		System.out.print("Digite o número de setores: ");
		int setores = input.nextInt();
		System.out.print("Digite o tamanho do setor: ");
		int tamanho_setor = input.nextInt();
		System.out.print("Digite o número de cabeças: ");
		int cabecas = input.nextInt();
		System.out.print("Digite o número de cilindros: ");
		int cilindros = input.nextInt();
		
		Caracteristicas cat = new Caracteristicas(setores, cabecas, cilindros, tamanho_setor);
		
		System.out.println("Capacidade do seu HD é " + cat.getCapacidade());
		
		System.out.println("");

		System.out.print("Digite a posição inicial do braço: ");
		int posicao_inicial = input.nextInt();
		
		Algoritmos disk = new Algoritmos(posicao_inicial, setores);
		
		ArrayList<Integer> requisicoes1 = new ArrayList<Integer>();
		ArrayList<Integer> requisicoes2 = new ArrayList<Integer>();
		ArrayList<Integer> requisicoes3 = new ArrayList<Integer>();
		ArrayList<Integer> requisicoes4 = new ArrayList<Integer>();
		ArrayList<Integer> requisicoes5 = new ArrayList<Integer>();
		
		int mov = posicao_inicial;
		
		while (mov >= 0) {
			System.out.print("Requisite um setor ou digite um número negativo para sair: ");
			mov = input.nextInt();
			Iterator<Integer> it_dif = requisicoes1.iterator();
			while (it_dif.hasNext()) {
				Integer proximo = it_dif.next();
				if (mov == proximo){
					existe = true;
					System.out.println("Digite outro setor, esse já foi requisitador.");
					break;
				}
			}
			if(mov > setores){
				System.out.println("Digite um setor que não seja superior ao número de setores.");
			} else if(mov >= 0 && existe == false){
				requisicoes1.add(mov);
				requisicoes2.add(mov);
				requisicoes3.add(mov);
				requisicoes4.add(mov);
				requisicoes5.add(mov);
				count++;
			}
		}

		
		disk.fcfs(requisicoes1);
		disk.sstf(requisicoes2);
	    disk.scan(requisicoes3);
	    disk.cscan(requisicoes4);
	    disk.clook(requisicoes5);
	    
	}

}
