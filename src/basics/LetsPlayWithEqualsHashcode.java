package basics;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Comment :
 * <ul>
 * <li>"perdre" un objet ajouté dans un {@link Set} ;</li>
 * <li>se retrouver avec un {@link Set} contenant 2 objets égaux.</li>
 * </ul>
 */
public class LetsPlayWithEqualsHashcode {

    private static Obj a = new Obj("A");
    private static Obj b = new Obj("B");
    private static Obj c = new Obj("C");
    private static Obj d = new Obj("C");

    public static void main(String[] args) {
        Set<Obj> set = new HashSet<>();

        System.out.println("Soit un HashSet :");
        System.out.println(set);

        set.add(a);
        set.add(b);
        set.add(c);
        set.add(d);

        // observation: d (ou c) a disparu
        System.out.println("\nJ'y insère 4 objets (A, B, C, C) :");
        System.out.println(set);

        b.value = a.value;

        // observation : a et b coexistent dans le Set
        System.out.println("\nJe rends B égal à A (j'obtiens un doublon !) :");
        System.out.println(set);
        System.out.println("parce que les hashcodes sont encore differentes!");
        
        // remove the object from the set, then modify and re-insert it.
//        set.remove(b);
//        b.value = a.value;
//        set.add(b);
//        System.out.println(set);

        // So, do not implement a hashCode() method that depends on any mutable fields.

        System.out.println("\nUn autre HashSet avec les mêmes éléments (pas de doublon) :");
        System.out.println(new HashSet<>(set));
    }

    static class Obj {
        String value;

        Obj(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || o instanceof Obj && Objects.equals(value, ((Obj) o).value);

        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Obj{" + value + '}';
        }
    }
}
