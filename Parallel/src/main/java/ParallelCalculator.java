import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelCalculator {


    public double calculate(String expression) {
        Node root = new Node(Operators.ADD);
        root.setLeft(new Node(3.0));
        Node mulOp = new Node(Operators.MUL);
        mulOp.setLeft(new Node(4.0));
        mulOp.setRight(new Node(5.0));
        root.setRight(mulOp);
        return new ForkJoinPool().invoke(new ExpressionComputer(root));

    }


    public class ExpressionComputer extends RecursiveTask<Double> {
        private final Node node;

        public ExpressionComputer(Node node) {
            this.node = node;
        }

        @Override
        protected Double compute() {

            if (node == null) { // unary operation like sin or cos
                return 0.0;
            }

            if (node.getOperator() == Operators.FINAL) {
                return node.getValue();
            }

            ExpressionComputer leftNode = new ExpressionComputer(node.getLeft());
            leftNode.fork();

            ExpressionComputer rightNode = new ExpressionComputer(node.getRight());
            rightNode.fork();

            return node.getOperator().getFunction().apply(leftNode.join(), rightNode.join());
        }

    }
}
