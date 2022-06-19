import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.Set;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * Cada tema té el mateix pes, i l'avaluació consistirà en:
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - La neteja del codi (pensau-ho com faltes d'ortografia). L'estàndar que heu de seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . No és
 *   necessari seguir-la estrictament, però ens basarem en ella per jutjar si qualcuna se'n desvia
 *   molt.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1:Marcos Ferrer Pacheco
 * - Nom 2:Fernando Pons Taltavull
 * - Nom 3:
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital abans de la data que se us hagui
 * comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més fàcilment
 * les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat, assegurau-vos
 * que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
  /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * Els mètodes reben de paràmetre l'univers (representat com un array) i els predicats adients
   * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
   * l'univers, podeu fer-ho com `p.test(x)`, té com resultat un booleà. Els predicats de dues
   * variables són de tipus `BiPredicate<Integer, Integer>` i similarment s'avaluen com
   * `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats retorneu `true`
   * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
   * petit com per utilitzar la força bruta)
   */
  static class Tema1 {
    /*
     * És cert que ∀x,y. P(x,y) -> Q(x) ^ R(y) ?
     */
    static boolean exercici1(
        int[] universe,
        BiPredicate<Integer, Integer> p,
        Predicate<Integer> q,
        Predicate<Integer> r) {
      int x;
      int y;
      for(int i=0;i<universe.length;i++){
        for(int z=0;z<universe.length;z++){
          x=universe[i];
          y=universe[z];
          if(p.test(x,y)){
            if(!(q.test(x)&&(r.test(y)))){
              return false;
            }
          }
        }
      }
      return true; // DONE
    }

    /*
     * És cert que ∃!x. ∀y. Q(y) -> P(x) ?
     */
    static boolean exercici2(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
      int x;
      int y;
      int contadorx=0;
      for(int i=0;i<universe.length;i++){
        x=universe[i];
        boolean b=true;
        for(int z=0;z<universe.length;z++){
          y=universe[z];
          if(q.test(y)){
            if((p.test(x))&&(b==true)){
              if(contadorx>1){
                return false;
              }else{
                contadorx++;
                b=false;
              }
            }
          }
        }
      }
      if(contadorx==0){
        return false;
      }
      return true; // DONE
    }

    /*
     * És cert que ¬(∃x. ∀y. y ⊆ x) ?
     *
     * Observau que els membres de l'univers són arrays, tractau-los com conjunts i podeu suposar
     * que cada un d'ells està ordenat de menor a major.
     */
    static boolean exercici3(int[][] universe) {
      static boolean exercici3(int[][] universe) {
        for(int i=0;i<universe.length-1;i++){
            int[]y=universe[i];
            boolean b=true;
            for(int z=0;z<universe.length&&b==true;z++){
                if(z!=i){
                    int[]x=universe[z];
                    if(yincluidox(y,x)){
                        b=false;
                    }
                }
            }
            if(b==true){
                return true;//no cambiar
            }
        }
        return false; // DONE
    }
      //Mètode que retorna true si y está inclós en x
    static boolean yincluidox(int[]y,int[]x){
        if(y.length==0){
            return true;
        }
        if(iguales(x,y)){
            return true;
        }
        for(int i=0;i<y.length;i++){
            if(!numincluidox(y[i],x)){
                return false;
            }
        }
        return true;
    }
      //Mètode que retorna true si un nombre y está inclós en x
    static boolean numincluidox(int y,int[]x){
        for(int i=0;i<x.length;i++){
            if(y==x[i]){
                return true;
            }
        }
        return false;
    }
      //Mètode que retorna true si y és igual a x
    static boolean iguales(int[]x,int[]y){
        if(x.length!=y.length){
            return false;
        }
        for(int i=0;i<x.length;i++){
            if(x[i]!=y[i]){
                return false;
            }
        }
        return true;
    }
    }

    /*
     * És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?
     */
    static boolean exercici4(int[] universe, int n) {
      static boolean exercici4(int[] universe, int n) {
        int x;
        int y;
        for(int i=0;i<universe.length;i++){
            x=universe[i];
            int contadorx=0;
            for(int z=0;z<universe.length;z++){
                y=universe[z];
                if(congr(x*y,n)){
                    contadorx++;
                }
                if(contadorx>1){
                    return false;
                }
            }
            if(contadorx==0){
                return false;
            }
        }
        return true; // DONE
    }
    static boolean congr(int i,int n){
        while(i>=11){
            i=i-11;
        }
        return i==1;
    }
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // ∀x,y. P(x,y) -> Q(x) ^ R(y)

      assertThat(
          exercici1(
              new int[] { 2, 3, 5, 6 },
              (x, y) -> x * y <= 4,
              x -> x <= 3,
              x -> x <= 3
          )
      );

      assertThat(
          !exercici1(
              new int[] { -2, -1, 0, 1, 2, 3 },
              (x, y) -> x * y >= 0,
              x -> x >= 0,
              x -> x >= 0
          )
      );

      // Exercici 2
      // ∃!x. ∀y. Q(y) -> P(x) ?

      assertThat(
          exercici2(
              new int[] { -1, 1, 2, 3, 4 },
              x -> x < 0,
              x -> true
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3, 4, 5, 6 },
              x -> x % 2 == 0, // x és múltiple de 2
              x -> x % 4 == 0  // x és múltiple de 4
          )
      );

      // Exercici 3
      // ¬(∃x. ∀y. y ⊆ x) ?

      assertThat(
          exercici3(new int[][] { {1, 2}, {0, 3}, {1, 2, 3}, {} })
      );

      assertThat(
          !exercici3(new int[][] { {1, 2}, {0, 3}, {1, 2, 3}, {}, {0, 1, 2, 3} })
      );

      // Exercici 4
      // És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?

      assertThat(
          exercici4(
              new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
              11
          )
      );

      assertThat(
          !exercici4(
              new int[] { 0, 5, 7 },
              13
          )
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * De la mateixa manera que al Tema 1, per senzillesa tractarem els conjunts com arrays (sense
   * elements repetits). Per tant, un conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam donant int[] a,
   * int[] b, i un objecte de tipus Function<Integer, Integer> que podeu avaluar com f.apply(x) (on
   * x és un enter d'a i el resultat f.apply(x) és un enter de b).
   */
  static class Tema2 {
    /*
     * És `p` una partició d'`a`?
     *
     * `p` és un array de conjunts, haureu de comprovar que siguin elements d'`a`. Podeu suposar que
     * tant `a` com cada un dels elements de `p` està ordenat de menor a major.
     */
    static boolean exercici1(int[] a, int[][] p) {
      return false; // TO DO
    }
 
    /*
     * Comprovau si la relació `rel` definida sobre `a` és un ordre parcial i que `x` n'és el mínim.
     *
     * Podeu soposar que `x` pertany a `a` i que `a` està ordenat de menor a major.
     */
    static boolean exercici2(int[] a, int[][] rel, int x) {
      if(a[0]!=x){
            return false;
        }
        if(!reflexiva(a,rel)){
            return false;
        }
        if(!antisimetrica(a,rel)){
            return false;
        }
        if(!transitiva(a,rel)){
            return false;
        }
        return true; // DONE
    }
    static boolean reflexiva(int [] a, int[][]rel){
        for(int i=0;i<a.length;i++){
            int x=a[i];
            boolean z=false;
            for(int j=0;j<rel.length;j++){
                int [] k=rel[j];
                if(k[0]==x&&k[1]==x){
                    z=true;
                }
            }
            if(z==false){
                return false;
            }
        }
        return true;
    }
    static boolean antisimetrica(int [] a, int[][]rel){
        for(int i=0;i<a.length;i++){
            int x=a[i];
            for(int j=0;j<rel.length;j++){
                int[]u=rel[j];
                if(x==u[0]){
                    int t=u[1];
                    for(int c=0;c<rel.length;c++){
                        int[]r=rel[c];
                        if(t==r[0]&&x==r[1]){
                            if(t!=x){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    static boolean transitiva(int [] a, int[][]rel){
        for(int j=0;j<rel.length;j++){
            int q=0;
            boolean v=false;
            int []u=rel[j];
            if(u[0]==u[1]){
                v=true;
            }
            int x=u[0];
            int t=u[1];
            for(int i=0;i<rel.length&&v==false;i++){
                int []p=rel[i];
                if(t==p[0]){
                    int w=p[1];
                    if(seRelaciona(t,rel)){
                        for(int y=0;y<rel.length&&v==false;y++){
                            int[]l=rel[y];
                            if(!iguales(u,l)){
                                if(x==l[0]&&w==l[1]){
                                    v=true;
                                }
                            }
                        }
                    }else{
                        q++;
                    }
                }
            }
            if(v==false&&q==0){
                return false;
            }
        }
        return true;
    }
    static boolean seRelaciona(int x,int[][]rel){
        for(int i=0;i<rel.length;i++){
            int[]y=rel[i];
            if(x==y[0]){
                if(y[0]!=y[1]){
                    return true;
                }
            }
        }
        return false;
    }
    static boolean iguales(int[]x,int[]y){
        if(x.length!=y.length){
            return false;
        }
        for(int i=0;i<x.length;i++){
            if(x[i]!=y[i]){
                return false;
            }
        }
        return true;
    }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Trobau l'antiimatge de
     * `y` (ordenau el resultat de menor a major, podeu utilitzar `Arrays.sort()`). Podeu suposar
     * que `y` pertany a `codom` i que tant `dom` com `codom` també estàn ordenats de menor a major.
     */
    static int[] exercici3(int[] dom, int[] codom, Function<Integer, Integer> f, int y) {
        int[]t=new int[dom.length];
        int numeroFinal=0;
        for(int i=0;i<dom.length;i++){
            if(f.apply(dom[i])==0){
                t[numeroFinal]=dom[i];
                numeroFinal++;
            }
        }
        int[]Final=new int[numeroFinal];
        for(int i=0;i<numeroFinal;i++){
            Final[i]=t[i];
        }
        return Final; // DONE
    }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Retornau:
     * - 3 si `f` és bijectiva
     * - 2 si `f` només és exhaustiva
     * - 1 si `f` només és injectiva
     * - 0 en qualsevol altre cas
     *
     * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major. Per comoditat, podeu
     * utilitzar les constants definides a continuació:
     */
    static final int NOTHING_SPECIAL = 0;
    static final int INJECTIVE = 1;
    static final int SURJECTIVE = 2;
    static final int BIJECTIVE = INJECTIVE + SURJECTIVE;

    static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
      return injectiva(dom,codom,f)+exhaustiva(dom,codom,f); // DONE
    }
    static int injectiva(int[] dom, int[] codom, Function<Integer, Integer> f){
        int[]t=new int[codom.length];
        for(int i=0;i<t.length;i++){
            t[i]=0;
        }
        for(int i=0;i<dom.length;i++){
            for(int j=0;j<codom.length;j++){
                if(f.apply(dom[i])==codom[j]){
                    t[j]++;
                }
            }
        }
        for(int i=0;i<t.length;i++){
            if(t[i]>1){
                return 0;
            }
        }
        return 1;
    }
    static int exhaustiva(int[] dom, int[] codom, Function<Integer, Integer> f){
        int[]t=new int[codom.length];
        for(int i=0;i<t.length;i++){
            t[i]=0;
        }
        for(int i=0;i<dom.length;i++){
            for(int j=0;j<codom.length;j++){
                if(f.apply(dom[i])==codom[j]){
                    t[j]++;
                }
            }
        }
        for(int i=0;i<t.length;i++){
            if(t[i]==0){
                return 0;
            }
        }
        return 2;
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `p` és una partició d'`a`?
 
      assertThat(
          exercici1(
              new int[] { 1, 2, 3, 4, 5 },
              new int[][] { {1, 2}, {3, 5}, {4} }
          )
      );

      assertThat(
          !exercici1(
              new int[] { 1, 2, 3, 4, 5 },
              new int[][] { {1, 2}, {5}, {1, 4} }
          )
      );

      // Exercici 2
      // és `rel` definida sobre `a` d'ordre parcial i `x` n'és el mínim?

      ArrayList<int[]> divisibility = new ArrayList<int[]>();

      for (int i = 1; i < 8; i++) {
        for (int j = 1; j <= i; j++) {
          if (i % j == 0) {
            // i és múltiple de j, és a dir, j|i
            divisibility.add(new int[] { j, i });
          }
        }
      }

      assertThat(
          exercici2(
              new int[] { 1, 2, 3, 4, 5, 6, 7 },
              divisibility.toArray(new int[][] {}),
              1
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3 },
              new int[][] { {1, 1}, {2, 2}, {3, 3}, {1, 2}, {2, 3} },
              1
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3, 4, 5, 6, 7 },
              divisibility.toArray(new int[][] {}),
              2
          )
      );

      // Exercici 3
      // calcular l'antiimatge de `y`

      assertThat(
          Arrays.equals(
              new int[] { 0, 2 },
              exercici3(
                  new int[] { 0, 1, 2, 3 },
                  new int[] { 0, 1 },
                  x -> x % 2, // residu de dividir entre 2
                  0
              )
          )
      );

      assertThat(
          Arrays.equals(
              new int[] { },
              exercici3(
                  new int[] { 0, 1, 2, 3 },
                  new int[] { 0, 1, 2, 3, 4 },
                  x -> x + 1,
                  0
              )
          )
      );

      // Exercici 4
      // classificar la funció en res/injectiva/exhaustiva/bijectiva

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3 },
              x -> (x + 1) % 4
          )
          == BIJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3, 4 },
              x -> x + 1
          )
          == INJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1 },
              x -> x / 2
          )
          == SURJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3 },
              x -> x <= 1 ? x+1 : x-1
          )
          == NOTHING_SPECIAL
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 3 (Aritmètica).
   *
   */
  static class Tema3 {
    /*
     * Donat `a`, `b` retornau el màxim comú divisor entre `a` i `b`.
     *
     * Podeu suposar que `a` i `b` són positius.
     */
    static int exercici1(int a, int b) {
    //Obtiene el mcd
    int temp;
    while (b != 0) {
        temp = b;
        b = a % b;
        a = temp;
    }
    return a;// DONE
    }

    /*
     * Es cert que `a``x` + `b``y` = `c` té solució?.
     *
     * Podeu suposar que `a`, `b` i `c` són positius.
     */
    static boolean exercici2(int a, int b, int c) {
      return false; // TO DO
    }

    /*
     * Quin es l'invers de `a` mòdul `n`?
     *
     * Retornau l'invers sempre entre 1 i `n-1`, en cas que no existeixi retornau -1
     */
    static int exercici3(int a, int n) {
      return -1; // TO DO
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `mcd(a,b)`

      assertThat(
              exercici1(2, 4) == 2
      );

      assertThat(
              exercici1(1236, 984) == 12
      );

      // Exercici 2
      // `a``x` + `b``y` = `c` té solució?

      assertThat(
              exercici2(4,2,2)
      );
      assertThat(
              !exercici2(6,2,1)
      );
      // Exercici 3
      // invers de `a` mòdul `n`
      assertThat(exercici3(2, 5) == 3);
      assertThat(exercici3(2, 6) == -1);
    }
  }

  static class Tema4 {
    /*
     * Donada una matriu d'adjacencia `A` d'un graf no dirigit, retornau l'ordre i la mida del graf.
     */
    static int[] exercici1(int[][] A) {
      int grado=0;
      //método que devuelve el grado de la matriz adyacente.
      public void grado(int [][]m){   
     
     for (int i=0;i<m.length;i++){
         grado=i;
     }
     grado++;
     }
       return int []{grado};
    }
      //Método para la medida:
    int mida;
      for (int indice1=0;indice1<M.length;indice1++) {       
            for (int indice2=0;indice2<M[indice1].length;indice2++) {
                if(indice1==indice2-1){
                indice1=0;
                }
                while((indice1!=indice2)){
                    if(M[indice2][indice1]!=0){
                    mida++;
                    indice1++;
                    }else{
                    indice1++;
                    }
                }
            }            
        }

      return new int[]{mida}; // TO DO
    }

    /*
     * Donada una matriu d'adjacencia `A` d'un graf no dirigit, digau si el graf es eulerià.
     */
    static boolean exercici2(int[][] A) {
      return false; // TO DO
    }

    /*
     * Donat `n` el número de fulles d'un arbre arrelat i `d` el nombre de fills dels nodes interiors i de l'arrel,
     * retornau el nombre total de vèrtexos de l'arbre
     *
     */
    static int exercici3(int n, int d) {
      return -1; // TO DO
    }

    /*
     * Donada una matriu d'adjacencia `A` d'un graf connex no dirigit, digau si el graf conté algún cicle.
     */
    static boolean exercici4(int[][] A) {
    
      return false; // TO DO
    }
    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `ordre i mida`

      assertThat(
              Arrays.equals(exercici1(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}}), new int[] {3, 2})
      );

      assertThat(
              Arrays.equals(exercici1(new int[][] { {0, 1, 0, 1}, {1, 0, 1, 1}, {0 , 1, 0, 1}, {1, 1, 1, 0}}), new int[] {4, 5})
      );

      // Exercici 2
      // `Es eulerià?`

      assertThat(
              exercici2(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
      );
      assertThat(
              !exercici2(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}})
      );
      // Exercici 3
      // `Quants de nodes té l'arbre?`
      assertThat(exercici3(5, 2) == 9);
      assertThat(exercici3(7, 3) == 10);

      // Exercici 4
      // `Conté algún cicle?`
      assertThat(
              exercici4(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
      );
      assertThat(
              !exercici4(new int[][] { {0, 1, 0}, {1, 0, 1}, {0, 1, 0}})
      );

    }
  }


  /*
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
   */
  public static void main(String[] args) {
    Tema1.tests();
    Tema2.tests();
    Tema3.tests();
    Tema4.tests();
  }

  static void assertThat(boolean b) {
    if (!b)
      throw new AssertionError();
  }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
