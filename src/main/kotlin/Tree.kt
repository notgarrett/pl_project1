import org.jetbrains.annotations.NotNull

class Node<T>(value: Comparable<T>) {
    private var left: Node<T>? = null
    private var right: Node<T>? = null
    private var value: Comparable<T>

    init {
        this.value = value
    }

    fun setLeft(node: Node<T>) {
        this.left = node
    }

    fun setRight(node: Node<T>){
        this.right = node
    }

    fun getRight() : Node<T>? {
        return this.right
    }

    fun getLeft() : Node<T>? {
        return this.left
    }

    fun getValue() : Comparable<T> {
        return this.value
    }

}

class Tree<T>(root: Node<T>) {
    private var root: Node<T>
    var height: Int = 0

    init {
        this.root = root
    }

    fun add(value: Comparable<T>, root: Node<T> = this.root) {
        val comparison = compareValues(value, root.getValue())
        if (comparison > 0) {
            if (root.getRight() == null) return root.setRight(Node(value))
            return add(value, root.getRight()!!)
        }
        else if (root.getLeft() == null) return root.setLeft(Node(value))
        return add(value, root.getLeft()!!)

    }

    fun search(value: Comparable<T>, root: Node<T> = this.root) : Boolean {
        return true
    }

     fun getSize (root:Node<T>? = this.root) : Int {
        root ?: return 0;
        return 1 + maxOf(getSize(root.getLeft()), getSize(root.getRight()))
    }

    fun getLeavesCount (root:Node<T>? = this.root ) : Int {
        root?.let {
            if (root.getLeft() == null && root.getRight() == null) return 1;
            return getLeavesCount(root.getLeft()) + getLeavesCount(root.getRight())
        }
        return 0;
    }

    fun printTree(root:Node<T>? = this.root) {
        root?.let {
            println("Main: " + root.getValue())
            printTree(root.getLeft())
            printTree(root.getRight())
        }

    }
}