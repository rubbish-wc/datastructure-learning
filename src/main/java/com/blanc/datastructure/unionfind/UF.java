package com.blanc.datastructure.unionfind;

/**
 * 并查集接口
 * @author wangbaolinag
 */
public interface UF {

    /**
     * 两个元素是否是可以连接的
     * @return
     */
    boolean isConnected(int p , int q);

    /**
     * 将两个元素连接在一起
     * @param p
     * @param q
     */
    void unionElement(int p , int q);

    /**
     * 获取并查集大小
     * @return
     */
    int getSize();
}
