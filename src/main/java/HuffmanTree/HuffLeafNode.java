package HuffmanTree;

class HuffLeafNode implements HuffBaseNode {
    private char element;      // Element for this node
    private int weight;        // Weight for this node

    HuffLeafNode(char el, int wt) {
        element = el;
        weight = wt;
    }

    public char value() {
        return element;
    }

    public int weight() {
        return weight;
    }

    public boolean isLeaf() {
        return true;
    }
}