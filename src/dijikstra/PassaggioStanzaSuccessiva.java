package dijikstra;

public class PassaggioStanzaSuccessiva extends Passaggio{
	private boolean passaAlProssimo = true;

	public boolean portaAvanti() {
		return passaAlProssimo;
	}
}
