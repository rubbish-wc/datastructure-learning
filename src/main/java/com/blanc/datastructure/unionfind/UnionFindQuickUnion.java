package com.blanc.datastructure.unionfind;

/**
 * 第二版unionFind,基于树实现,其中孩子指向父亲,用数组存储,数组中的索引的值表示父节点的index
 */
public class UnionFindQuickUnion implements UF {

    private int[] parent;

    /**
     * 构造函数
     * @param size
     */
    public UnionFindQuickUnion(int size){
        parent = new int[size];
        //意思是此时元素都没有相连,每一个元素都是一个自己的树
        for (int i = 0 ; i < size ; i++){
            parent[i] = i;
        }
    }

    /**
     * 判断p和q是否属于同一个集合
     * 内部就是找到这个节点的根节点,判断两个元素的根节点是否相同
     * O(h)
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连接两个元素
     * O(h)
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
        //合并操作
        parent[pRoot] = qRoot;
    }

    /**
     * 获取大小
     * @return
     */
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
