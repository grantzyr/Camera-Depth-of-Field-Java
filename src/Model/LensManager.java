package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LensManager implements Iterable<Lens> {
    private List<Lens> lenses = new ArrayList<>();

    public int size() {
        return lenses.size();
    }

    public Lens getLens(int index) {
        return lenses.get(index);
    }

    public void add(Lens lens) {
        lenses.add(lens);
    }


    @Override
    public Iterator<Lens> iterator() {
        return lenses.iterator();
    }
}
