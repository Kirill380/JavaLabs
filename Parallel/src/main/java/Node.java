
public class Node {
    private Node left;
    private Node right;

    private final Double value;
    private final Operators operator;

    public Node(Operators operator) {
        this.operator = operator;
        this.value = Double.NaN;
    }

    public Node(Double value) {
        this.value = value;
        this.operator = Operators.FINAL;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    public Double getValue() {
        return value;
    }

    public Operators getOperator() {
        return operator;
    }
}
