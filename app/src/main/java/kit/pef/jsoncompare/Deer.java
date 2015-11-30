package kit.pef.jsoncompare;

import java.util.HashMap;
import java.util.List;

	public class Deer {
		public final String id;
		public final List<HashMap<String, String>> positions;

		Deer(String id, List<HashMap<String, String>> positions) {
			this.id = id;
			this.positions = positions;
		}
	}
