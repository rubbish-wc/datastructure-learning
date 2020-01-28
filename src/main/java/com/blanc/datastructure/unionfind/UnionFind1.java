package com.blanc.datastructure.unionfind;

/**
 * 第一版并查集
 *
 */
public class UnionFind1 implements UF {

    /**
     * 存储每一个元素的编号
     */
    private int[] id;

    /**
     * 构造函数
     * @param size
     */
    public UnionFind1(int size){
        id = new int[size];
        //每个元素所属的集合编号都是自己,即每个元素都是不相连的
        for (int i = 0 ; i<id.length;i++){
            id[i] = i;
        }
    }

    /**
     * 查看元素p和元素q是否属于同一个集合 O(1)级别
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并两个元素所属的集合
     * O(n)
     * @param p
     * @param q
     */
    @Override
    public void unionElement(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        //如果已经是相连的话,则什么都不用做
        if (pID == qID){
            return;
        }
        //合并
        for (int i = 0 ; i < id.length ; i++){
            if (id[i] == pID){
                id[i] = qID;
            }
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查找元素p所对应的集合的编号
     * @param p
     * @return
     */
    private int find(int p){
        if (p < 0 || p > id.length){
            throw new IllegalArgumentException("invlid p , out of bound");
        }
        return id[p];
    }
}
