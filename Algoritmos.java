import java.util.*;

public class Algoritmos {
	
	public int posicao_inicial;
	public int n_setores;
	
	public Algoritmos(int p_ini, int n_set){
		this.posicao_inicial = p_ini; 
		this.n_setores = n_set;
	}
	
	public int ultima_posicao = posicao_inicial;
	public int passos = 0;
	public int diferenca = n_setores; 
	public int index = 0;
	public int lado = 0;
	public int talvez_ultima_posicao = 0;
	public int n_requisicoes;

	public void fcfs(ArrayList<Integer> requisicoes){ 
		n_requisicoes = requisicoes.size();
		int ultima_posicao = posicao_inicial;
		int passos = 0;

		Iterator<Integer> it = requisicoes.iterator();
		while (it.hasNext()) { 
			Integer proxima_posicao = it.next(); 
			if (proxima_posicao > ultima_posicao) {
				passos += proxima_posicao - ultima_posicao;
				ultima_posicao = proxima_posicao;
			} else if (proxima_posicao < ultima_posicao) {
				passos += ultima_posicao - proxima_posicao;
				ultima_posicao = proxima_posicao;
			} else {
				it.remove(); 
			}
		}
		System.out.println("Deslocamento do FCFS é de " + passos);
		System.out.println("Tempo médio de seek " + passos/n_requisicoes);
		
	}

	public void sstf(ArrayList<Integer> requisicoes){ 
		ultima_posicao = posicao_inicial;
		passos = 0;
		diferenca = n_setores;
		index = 0;
		n_requisicoes = requisicoes.size();
		
		while(!requisicoes.isEmpty()){ 

			menorDiferenca(requisicoes); 
			
			ultima_posicao = talvez_ultima_posicao;
			passos += diferenca;
			diferenca = n_setores;
			requisicoes.remove(index); 
		}

		System.out.println("Deslocamento do SSTF é de " + passos);
		System.out.println("Tempo médio de seek " + passos/n_requisicoes);
		
	}

	public void scan(ArrayList<Integer> requisicoes){
		ultima_posicao = posicao_inicial;
		passos = 0;
		diferenca = n_setores;
		lado = 0;
		n_requisicoes = requisicoes.size();

		menorDiferenca(requisicoes); 

		if (lado == 2) { 
			ultima_posicao = 0;
			passos = posicao_inicial;
			Collections.sort(requisicoes); 
			Iterator<Integer> proxima_posicao = requisicoes.iterator();
			while (proxima_posicao.hasNext()) {
				Integer proximo = proxima_posicao.next();
				diferenca = proximo - ultima_posicao;
				passos += diferenca;
				ultima_posicao = proximo;
			}
		} else if (lado == 1) { 
			ultima_posicao = n_setores;
			passos = n_setores - posicao_inicial;
			Collections.sort(requisicoes);
			Collections.reverse(requisicoes); 
			Iterator<Integer> proxima_posicao = requisicoes.iterator();
			while (proxima_posicao.hasNext()) {
				Integer proximo2 = proxima_posicao.next();
				diferenca = ultima_posicao - proximo2;
				passos += diferenca;
				ultima_posicao = proximo2;
			}
		}

		System.out.println("Deslocamento do SCAN é de " + passos);
		System.out.println("Tempo médio de seek " + passos/n_requisicoes);
		

	}
	
	public void cscan(ArrayList<Integer> requisicoes){ 
		ultima_posicao = posicao_inicial;
		passos = 0;
		diferenca = n_setores;
		index = 0;
		n_requisicoes = requisicoes.size();

		Collections.sort(requisicoes);
		
		ArrayList<Integer> delete = new ArrayList<Integer>();
		
		Iterator<Integer> proxima_req = requisicoes.iterator();
		while (proxima_req.hasNext()) {
			Integer proximo = proxima_req.next();
			if (proximo > ultima_posicao) {
				delete.add(proximo);
				diferenca = proximo - ultima_posicao;
				passos += diferenca;
				ultima_posicao = proximo;
			}
			if (proximo == ultima_posicao) {
				delete.add(proximo); 
			}
		}
		
		if (!requisicoes.isEmpty()){ 
			passos = passos + (n_setores - ultima_posicao);
		}
		
		Iterator<Integer> del = delete.iterator();
		while (del.hasNext()) { 
			Integer proximo2 = del.next();
			requisicoes.remove(proximo2); 
		}

		ultima_posicao = 0;
		Iterator<Integer> proxima_requisicao = requisicoes.iterator();
		while (proxima_requisicao.hasNext()) {
			Integer proximo2 = proxima_requisicao.next();
			if (proximo2 > ultima_posicao) {
				index = requisicoes.indexOf(proximo2);
				delete.add(proximo2);
				diferenca = proximo2 - ultima_posicao;
				passos += diferenca;
				ultima_posicao = proximo2;
			}
			if (proximo2 == ultima_posicao) {
				delete.add(proximo2); 
			}
		}
		Iterator<Integer> del2 = delete.iterator();
		while (del2.hasNext()) {
			Integer proximo2 = del2.next();
			requisicoes.remove(proximo2);
		}

		System.out.println("Deslocamento do C-SCAN é de " + passos);
		System.out.println("Tempo médio de seek " + passos/n_requisicoes);
		
	}
	
	public void clook(ArrayList<Integer> requisicoes){
		ultima_posicao = posicao_inicial;
		passos = 0;
		diferenca = n_setores;
		index = 0;
		n_requisicoes = requisicoes.size();
		boolean primeiro = true;

		Collections.sort(requisicoes);

		ArrayList<Integer> delete = new ArrayList<Integer>();
		Iterator<Integer> proxima_req = requisicoes.iterator();
		while (proxima_req.hasNext()) {
			Integer proximo = proxima_req.next();
			if (proximo > ultima_posicao) {
				delete.add(proximo);
				diferenca = proximo - ultima_posicao;
				passos += diferenca;
				ultima_posicao = proximo;
			}
		}
		
		Iterator<Integer> del = delete.iterator();
		while (del.hasNext()) {
			Integer proximo2 = del.next();
			requisicoes.remove(proximo2);
		}
		
		int ultima_posicao2 = 0;
		Iterator<Integer> go2 = requisicoes.iterator();
		while (go2.hasNext()) {
			Integer proximo2 = go2.next();
			index = requisicoes.indexOf(proximo2);
			delete.add(proximo2);
			diferenca = proximo2 - ultima_posicao2;
			if (!primeiro)
				passos += diferenca;
			else {
				passos += (ultima_posicao - proximo2);
				primeiro = false;
			}

			ultima_posicao2 = proximo2;
		}
		Iterator<Integer> del2 = delete.iterator();
		while (del2.hasNext()) {
			Integer proximo2 = del2.next();
			requisicoes.remove(proximo2);
		}

		System.out.println("Deslocamento do C-look é de " + passos);
		System.out.println("Tempo médio de seek " + passos/n_requisicoes);
		
	}
	
	public void menorDiferenca(ArrayList<Integer> requests){
		Iterator<Integer> it_dif = requests.iterator();
		while (it_dif.hasNext()) {
			Integer proximo = it_dif.next();
			if (proximo > ultima_posicao) {
				if (proximo - ultima_posicao < diferenca) { 
					diferenca = proximo - ultima_posicao; 
					talvez_ultima_posicao = proximo; 
					index = requests.indexOf(proximo); 
					lado = 1;
				}
			} else if (proximo < ultima_posicao) {
				if (ultima_posicao - proximo < diferenca) { 
					diferenca = ultima_posicao - proximo; 
					talvez_ultima_posicao = proximo; 
					index = requests.indexOf(proximo); 
					lado = 2; 
				}
			}
		}
		
	}
	
	

}
