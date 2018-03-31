import java.util.ArrayList;

public class BinarySearch {
	public int binarySearch(ArrayList<People> a, String x, String type) {
		return binarySearch(a, x, type, 0, a.size() - 1);
	}

	private int binarySearch(ArrayList<People> a, String x, String type, int low, int high) {
		if (low > high)
			return high + 1;
		int mid = (low + high) / 2;
		if (a.get(mid).get(type).equals(x))
			return mid;
		else if (a.get(mid).get(type).compareTo(x) < 0)
			return binarySearch(a, x, type, mid + 1, high);
		else
			return binarySearch(a, x, type, low, mid - 1);
	}
}
