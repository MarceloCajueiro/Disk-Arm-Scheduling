public class Caracteristicas {
	private long capacidade;
	private int cabecas, cilindros, setores, tamanho_setor;
	public Caracteristicas(int setores, int cabecas, int cilindros, int tamanho_setor) {
		this.setores = setores;
		this.cabecas = cabecas;
		this.cilindros = cilindros;
		this.tamanho_setor = tamanho_setor;
	}
	
	public long getCapacidade() {
		this.capacidade = (((long)getCabecas() * (long)getCilindros() * (long)getSetores() * (long)getTamanho_setor())/1024)/1024;
		return this.capacidade;
	}

	public int getCabecas() {
		return this.cabecas;
	}

	public int getCilindros() {
		return this.cilindros;
	}

	public int getSetores() {
		return this.setores;
	}

	public int getTamanho_setor() {
		return this.tamanho_setor;
	}
	
}
