import java.util.ArrayList;

public class Sort {
	public ArrayList<People> QuickSortString(ArrayList<People> a, int p, int r,
			String y) {/* This function sorting an arraylist for string */
		int q;
		if (p < r) {
			q = PartitionString(a, p, r, y);
			QuickSortString(a, p, q - 1, y);
			QuickSortString(a, q + 1, r, y);
		}
		return a;
	}

	public int PartitionString(ArrayList<People> a, int p, int r, String y) {

		String x = a.get(r).get(y);
		int i = p - 1;
		for (int j = p; j <= r - 1; j++) {
			if (a.get(j).get(y).compareTo(x) <= 0) {
				i = i + 1;

				Swap(a.get(i), a.get(j));
			}
		}
		Swap(a.get(i + 1), a.get(r));
		return i + 1;
	}

	public static void QuickSort(String[] A, int p, int r) {
		if (p < r) {
			int q = Partition(A, p, r);
			QuickSort(A, p, q - 1);
			QuickSort(A, q + 1, r);
		}
	}

	public static int Partition(String[] A, int p, int r) {
		String x = A[r];
		int i = p - 1;

		for (int j = p; j <= r - 1; j++) {
			if (A[j].compareTo(x) <= 0) {
				i = i + 1;
				String temp1 = A[i];
				A[i] = A[j];
				A[j] = temp1;
			}
		}

		String temp2 = A[i + 1];
		A[i + 1] = A[r];
		A[r] = temp2;
		return i + 1;
	}

	public void Swap(People a, People b) {/* This is my swap function. */
		People tmp = new People("a", "b", "c", "d", "e", "f");
		tmp.CID = a.CID;
		tmp.City = a.City;
		tmp.FirstName = a.FirstName;
		tmp.LastName = a.LastName;
		tmp.AddressLine1 = a.AddressLine1;
		tmp.SocialSecurityNumber = a.SocialSecurityNumber;
		a.CID = b.CID;
		a.FirstName = b.FirstName;
		a.LastName = b.LastName;
		a.City = b.City;
		a.AddressLine1 = b.AddressLine1;
		a.SocialSecurityNumber = b.SocialSecurityNumber;
		b.CID = tmp.CID;
		b.FirstName = tmp.FirstName;
		b.LastName = tmp.LastName;
		b.City = tmp.City;
		b.AddressLine1 = tmp.AddressLine1;
		b.SocialSecurityNumber = tmp.SocialSecurityNumber;
	}
}
