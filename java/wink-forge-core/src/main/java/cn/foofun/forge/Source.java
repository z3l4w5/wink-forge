package cn.foofun.forge;

/**
 * 数据生成器
 *
 * @param <T>
 */
public interface Source<T> {

    /**
     * 生成一项数据
     *
     * @return 生成的数据
     */
    T next();
}
