package com.blanc.datastructure.unionfind;

/**
 * 并查集接口
 * 并查集:一种很不一样的树结构,可以非常高效的解决连接问题
 * 连接问题
 * 适用于网络中节点间的连接状态:网络是个抽象的状态,比如用户之间形成的网络
 * 数学中的集合类的实现,路径问题
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
