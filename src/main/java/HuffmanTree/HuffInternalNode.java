package HuffmanTree;

class HuffInternalNode implements HuffBaseNode {
    private int weight;
    private HuffBaseNode left;
    private HuffBaseNode right;

    public HuffInternalNode(HuffBaseNode l,
                     HuffBaseNode r, int wt) {
        left = l;
        right = r;
        weight = wt;
    }

    public HuffBaseNode left() {
        return left;
    }

    public HuffBaseNode right() {
        return right;
    }

    public int weight() {
        return weight;
    }

    public boolean isLeaf() {
        return false;
    }
}