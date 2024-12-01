package HuffmanTree;

public class HuffmanBST {
    private HuffBaseNode root;

    public HuffmanBST(char el, int wt) {
        root = new HuffLeafNode(el, wt);
    }

    public HuffmanBST(HuffBaseNode l, HuffBaseNode r, int wt) {
        root = new HuffInternalNode(l, r, wt);
    }

    public HuffBaseNode root() {
        return root;
    }

    public int weight() // Weight of tree is weight of root
    {
        return root.weight();
    }

    int compareTo(Object t) {
        HuffmanBST that = (HuffmanBST) t;
        if (root.weight() < that.weight()) {
            return -1;
        } else if (root.weight() == that.weight()) {
            return 0;
        } else {
            return 1;
        }
    }

    public String searchLeaf(char element) {
        return searchLeaf(root, element, "");
    }

    private String searchLeaf(HuffBaseNode node, char element, String path) {
        if (node == null) {
            return null;
        }
        if (node.isLeaf()) {
            HuffLeafNode leaf = (HuffLeafNode) node;
            if (leaf.value() == element) {
                return path;
            } else {
                return null;
            }
        }
        HuffInternalNode internalNode = (HuffInternalNode) node;
        String leftPath = searchLeaf(internalNode.left(), element, path + "0");
        if (leftPath != null) {
            return leftPath;
        }
        return searchLeaf(internalNode.right(), element, path + "1");
    }

    public static HuffmanBST buildTree() {
        HuffmanBST tmp1, tmp2, tmp3 = null;

        while (Hheap.heapsize() > 1) { // While two items left
            tmp1 = Hheap.removemin();
            tmp2 = Hheap.removemin();
            tmp3 = new HuffmanBST(tmp1.root(), tmp2.root(),
                    tmp1.weight() + tmp2.weight());
            Hheap.insert(tmp3);
        }
        return tmp3;
    }
}