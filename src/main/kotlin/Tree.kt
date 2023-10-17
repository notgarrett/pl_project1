class Node<T>(value: Comparable<T>) {
    private var left: Node<T>? = null
    private var right: Node<T>? = null
    private var value: Comparable<T>

    init {
        this.value = value
    }

    fun setLeft(node: Node<T>?) {
        this.left = node
    }

    fun setRight(node: Node<T>?){
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
        else if (comparison < 0) {
            if (root.getLeft() == null) return root.setLeft(Node(value))
            return add(value, root.getLeft()!!)
        }

        return

    }

    fun add(node: Node<T>, root: Node<T> = this.root) {
        val comparison = compareValues(node.getValue(), root.getValue())

        if (comparison > 0) {
            if (root.getRight() == null) return root.setRight(node)
            return add(node, root.getRight()!!)
        }
        else if (comparison < 0) {
            if (root.getLeft() == null) return root.setLeft(node)
            return add(node, root.getLeft()!!)
        }

        return
    }

    fun dfSearch(value: Comparable<T>, root: Node<T>? = this.root) : Node<T> {

    }

    fun bfSearch(value: Comparable<T>, root: Node<T>? = this.root) : Node<T> {

    }

    fun delete(value: Comparable<T>, root: Node<T>? = this.root) {

    }

    fun addSubtree(root: Node<T>?) {
        root?.let {
            val left = root.getLeft()
            val right = root.getRight()
            root.setLeft(null)
            root.setRight(null)
            this.add(root)
            this.addSubtree(left)
            this.addSubtree(right)
        }
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

    fun isBalanced(root:Node<T>? = this.root) : Boolean {
        root?.let {
            if (!isBalancedHelper(root)) return false
            return isBalanced(root.getLeft()) && isBalanced(root.getRight())
        }
        return true
    }
    private fun isBalancedHelper(root:Node<T>? = this.root) : Boolean {
            root ?: return true
            return getSize(root.getLeft()) == getSize(root.getRight())
                    || getSize(root.getLeft()) == getSize(root.getRight()) - 1
                    || getSize(root.getLeft()) == getSize(root.getRight()) + 1
    }




}