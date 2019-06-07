package com.blanc.datastructure.unionfind;

/**
 * 并查集实现
 */
public class UnionFindQuick implements UF {

    private int[] id;

    public UnionFindQuick(int size){
        id = new int[size];
        for (int i = 0 ; i < id.length ; i++){
            id[i] = i;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     * @param p
     * @param q
     */
    @Override
    public void unionElement(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        //如果两个元素已经是同一个集合了(已经相连)
        if (pId == qId){
            return;
        }
        //将p的集合id换成q的id
        for (int i = 0 ; i < id.length ; i++){
            if (id[i] == pId){
                id[i] = qId;
            }
        }
    }

    /**
     * 返回元素的个数
     * @return
     */
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
            throw new IllegalArgumentException("invalid index , p is out of bound");
        }
        return id[p];
    }
}
