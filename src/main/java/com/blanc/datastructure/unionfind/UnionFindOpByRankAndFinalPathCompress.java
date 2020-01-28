package com.blanc.datastructure.unionfind;

/**
 * 基于rank优化的并查集
 * 即是一种更好的合并方式,不是用size那种少节点的往多节点的上合并
 * 而是深度低的往深度高的树上合并
 * 这种更为合理,基于rank的合并
 * 不在find而是使用递归的方式直接进行路径的压缩
 * 实际上这个递归实现的路径压缩比在find中单词路径压缩要更慢一点
 */
public class UnionFindOpByRankAndFinalPathCompress implements UF {

    private int[] parent;

    /**
     * rank[i]表示根节点为i的树的深度
     */
    private int[] rank;

    /**
     * 构造函数
     * @param size
     */
    public UnionFindOpByRankAndFinalPathCompress(int size){
        this.parent = new int[size];
        this.rank = new int[size];

        for (int i = 0 ; i < size ; i++){
            parent[i] = i;
            rank[i] = 1;
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
        //基于rank的优化,深度小的树往深度高的树上合并
        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }else {//当两个rank是相等的话
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
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
        //递归函数
        if (p != parent[p]) {
            //路径压缩:只需要这一行代码
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }


}
