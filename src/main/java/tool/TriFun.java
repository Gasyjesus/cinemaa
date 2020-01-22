package tool;



@FunctionalInterface
public interface TriFun<T,U,V,R> {

R apply(T t, U u, V v);
}
