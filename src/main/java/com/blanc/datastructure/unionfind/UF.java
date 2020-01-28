package com.blanc.datastructure.unionfind;

/**
 * 并查集接口
 * 并查集:一种很不一样的树结构,可以非常高效的解决连接问题
 * 连接问题
 * 适用于网络中节点间的连接状态:网络是个抽象的状态,比如用户之间形成的网络
 * 数学中的集合类的实现,路径问题
 * 只要关注基于rank优化的树结构的并查集就行了,别的并查集都是演化过来的,比较垃圾
 * 还有注意路径压缩算法,对于并查集来说,深度是2是最理想的,所以不断的降低树的高度,把路径压缩发生在find操作中
 * 路径压缩核心操作 parent[p]=parent[parent[p]],有很大的效果的提升,因为运行find的过程中,树的高度都在不断的降低,very nice
 * @author wangbaolinag
 */
public interface UF {

    /**
     * 两个元素是否是可以连接的
     * @return
     */
    boolean isConnected(int p, int q);

    /**
     * 将两个元素连接在一起
     * @param p
     * @param q
     */
    void unionElement(int p, int q);

    /**
     * 获取并查集大小
     * @return
     */
    int getSize();
}
