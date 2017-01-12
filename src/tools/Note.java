package tools;

/**
 * Enumeration des notes des tweets
 * 
 * @author canda
 *
 */
public enum Note {

	NONTRAITE {
		public int getValue() {
			return -1;
		}
	},

	NEGATIF {
		public int getValue() {
			return 0;
		}
	},
	NEUTRE {
		public int getValue() {
			return 2;
		}
	},

	POSITIF {
		public int getValue() {
			return 4;
		}
	};

	// Donne la valeur de la note du sentiment associe au tweet
	public abstract int getValue();

	/**
	 * methode qui a partir de l'entier representant la note retourne la Note
	 * associee
	 * 
	 * @param value
	 * @return la Note
	 * @throws IllegalArgumentException
	 */
	public static Note getNoteByValue(int value) throws IllegalArgumentException {
		switch (value) {
		case -1:
			return NONTRAITE;
		case 0:
			return NEGATIF;
		case 2:
			return NEUTRE;
		case 4:
			return POSITIF;
		default:
			throw new IllegalArgumentException("La valeur doit etre egale a -1, 0, 2 ou 4.");
		}
	}

	/**
	 * methode qui retourne le String associee à une note
	 */
	public String toString() {
		if (this == NONTRAITE) {
			return "Non note";
		} else if (this == NEGATIF) {
			return "-";
		} else if (this == NEUTRE) {
			return "=";
		} else {
			return "+";
		}
	}
}
