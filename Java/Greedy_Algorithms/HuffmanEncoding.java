import java.util.PriorityQueue;

// Given a set of characters and their corresponding frequencies,
// construct the Huffman Tree and generate Huffman Codes for each character.
// The goal is to use fewer bits for more frequent characters to achieve lossless data compression.

// Approach: Use a Greedy Algorithm by always combining the two nodes with the lowest frequencies.

class HuffmanEncoding {

    // Node class representing each character and its frequency
    static class Node {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        Node(int freq, Node left, Node right) {
            this.ch = '-';
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

    // Comparator for the min-heap (PriorityQueue)
    static class NodeComparator implements java.util.Comparator<Node> {
        public int compare(Node a, Node b) {
            return a.freq - b.freq;
        }
    }

    // Recursive function to print Huffman codes from the root of the tree
    static void printHuffmanCodes(Node root, String code) {
        if (root == null) return;

        // Leaf node: contains a character
        if (root.left == null && root.right == null && root.ch != '-') {
            System.out.println(root.ch + ": " + code);
        }

        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        // Example input: characters and their frequencies
        char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] freqs = { 5, 9, 12, 13, 16, 45 };

        // Min-heap to store nodes
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        // Step 1: Create a leaf node for each character and add it to the queue
        for (int i = 0; i < chars.length; i++) {
            pq.add(new Node(chars[i], freqs[i]));
        }

        // Step 2: Combine the two lowest frequency nodes until one remains
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node merged = new Node(left.freq + right.freq, left, right);
            pq.add(merged);
        }

        // Root of the Huffman Tree
        Node root = pq.poll();

        // Step 3: Print Huffman codes
        System.out.println("Huffman Codes:");
        printHuffmanCodes(root, "");
    }
}
