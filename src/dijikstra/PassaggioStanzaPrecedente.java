package dijikstra;

public class PassaggioStanzaPrecedente extends Passaggio{
	private boolean passaAlProssimo = false;

	public boolean portaAvanti() {
		return passaAlProssimo;
	}
}
