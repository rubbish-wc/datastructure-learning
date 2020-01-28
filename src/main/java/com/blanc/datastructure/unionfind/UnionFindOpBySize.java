package com.blanc.datastructure.unionfind;

/**
 * 基于size优化的并查集,即少节点树向多节点树合并
 */
public class UnionFindOpBySize implements UF {

    private int[] parent;

    /**
     * sz[i] 表示以i为根的集合中元素的个数
     * 这个玩意应用hashmap不是更好么
     */
    private int[] sz;

    /**
     * 构造函数
     * @param size
     */
    public UnionFindOpBySize(int size){
        this.parent = new int[size];
        this.sz = new int[size];

        for (int i = 0 ; i < size ; i++){
            parent[i] = i;
            sz[i] = i;
        }
    }

    /**
     * 判断是否连接
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * union操作:核心 小树往大树并
     * @param p
     * @param q
     */
    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot){
            return;
        }
        //基于size的优化,小树往大树上合并
        if (sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 找到父节点,即找到的节点的索引和value是相同的
     * parent[i] = i
     * @param p
     * @return
     */
    private int find(int p){
        if (p < 0 || p > parent.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }
}
